import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static List<String> readExceptions(InputStream exceptionStream) {
        List<String> exceptions = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        int intSymbol;
        char symbol;
        try {
            while((intSymbol =  exceptionStream.read()) != -1){
                symbol = (char)intSymbol;
                if(Character.isLetter(symbol)){
                    stringBuilder.append(symbol);
                }else if(stringBuilder.length() != 0){
                    exceptions.add(stringBuilder.toString());
                    stringBuilder.setLength(0);
                }
            }
            if(stringBuilder.length() != 0)
                exceptions.add(stringBuilder.toString());
            exceptionStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return exceptions;
    }
    private static String readText(InputStream textStream){
        StringBuilder stringBuilder = new StringBuilder();
        int intSymbol;
        char symbol;
        try {
            while((intSymbol =  textStream.read()) != -1){
                symbol = (char)intSymbol;
                stringBuilder.append(symbol);
            }
            textStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();
    }
    private static Boolean checkExceptions(List<String> exceptions, String word){
        for(String exception : exceptions)
            if(word.equals(exception))
                return true;
        return false;
    }
    private static String modifyText(String text, List<String> exceptions, Integer n, Integer start, Integer size) throws StringIndexOutOfBoundsException{
        StringBuilder modifidedText = new StringBuilder();
        Integer index = 0;
        while(start > 1){
            if(Character.isLetter(text.charAt(index)) && !Character.isLetter(text.charAt(index + 1))){
                start--;
            }
            modifidedText.append(text.charAt(index));
            index++;
        }
        StringBuilder word = new StringBuilder();
        while(n > 0){
            if(Character.isLetter(text.charAt(index))){
                word.append(text.charAt(index));
            }else{

                if(word.length() != 0){
                    n--;
                    if(checkExceptions(exceptions, word.toString())){
                        modifidedText.append("(censure)");
                    }else if(word.length() < size){
                        modifidedText.append("(length less than ").append(size.toString()).append(")");
                    }else
                        modifidedText.append(word);
                }
                modifidedText.append(text.charAt(index));
                word.setLength(0);
            }
            index++;
        }
        while(text.length() > index){
            modifidedText.append(text.charAt(index));
            index++;
        }
        return modifidedText.toString();
    }
    public static void main(String[] args) {
        /*Settings settings = new Settings();
        CmdLineParser parser = new CmdLineParser(settings);
        try {
            parser.parseArgument(args);
            System.out.println("settings = " + settings);
        } catch (CmdLineException e) {
            System.err.println("e = " + e.toString());
            parser.printUsage(System.out);
        }*/
        Integer n =10;
        Integer start = 10;
        Integer size = 4;
        InputStream textStream = Main.class.getResourceAsStream("text.txt");
        InputStream exceptionStream = Main.class.getResourceAsStream("exception.txt");
        List<String> exceptions = readExceptions(exceptionStream);
        String text = readText(textStream);
        try{
            System.out.println(modifyText(text, exceptions, n, start, size));
        }catch(StringIndexOutOfBoundsException e){
            System.out.println("Wrong parameters");
        }



    }
}
