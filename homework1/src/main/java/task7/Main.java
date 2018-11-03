package task7;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
public class Main {


    public static void main(String[] args) {
        List<String> stringList = new ArrayList<>();
        stringList.add("aaa");stringList.add("bbb");stringList.add("ccc");stringList.add("aerer");
        stringList.add("aaaa");stringList.add("bbbb");stringList.add("cccc");stringList.add("dddd");
        File file = new File("stringFile.csv");
        String separator = " | ";
        try {
            FileWriter writer = new FileWriter(file);
            Deque<String> writedString = new ArrayDeque<>();

            for(int i = 3; i <= 5; i++){
                int length = i;

                stringList.stream()
                        .filter(string -> string.length() == length)
                        .forEach(string -> {
                            writedString.addLast(string);
                            writedString.addLast(separator);
                        } );
                writedString.removeLast();

                if(!writedString.isEmpty()){
                    writedString.addFirst(separator);
                    writedString.addFirst(String.valueOf(length));
                }

                writedString
                        .forEach(string -> {
                            try {
                                writer.write(string);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });

                writer.write("\n");
                writedString.clear();
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
