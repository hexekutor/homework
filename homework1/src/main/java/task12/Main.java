package task12;

public class Main {
    private static String code(String str){
        StringBuilder stringBuilder = new StringBuilder(str);
        for(int i = 0; i < str.length();i++){
            stringBuilder.setCharAt(i,(char) ('a' +  ((str.charAt(i) - 'a' + 1) % 26)));
        }
        stringBuilder.reverse();
        return stringBuilder.toString();
    }

    private static String encode(String str){
        StringBuilder stringBuilder = new StringBuilder(str);
        for(int i = 0; i < str.length();i++){
            stringBuilder.setCharAt(i,(char) ('a' +  ((str.charAt(i) - 'a' + 25) % 26)));
        }
        stringBuilder.reverse();
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        String string = "abcz";
        String codedstring = code(string);
        String encodedString = encode(codedstring);
        System.out.println(string + "\n" + codedstring + "\n" + encodedString);

    }
}
