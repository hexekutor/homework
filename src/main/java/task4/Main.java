package task4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    private static void printList(Iterator<String> iter){
        if(iter.hasNext()){
            String str = iter.next();
            printList(iter);
            System.out.println(str);
        }
    }

    public static void main(String[] args) {
        List<String> stringList= new ArrayList<>();
        stringList.add("aa");
        stringList.add("bb");
        stringList.add("cc");
        Iterator<String> iter = stringList.iterator();
        printList(iter);
    }
}
