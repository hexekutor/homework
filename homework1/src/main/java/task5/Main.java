package task5;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Main {

    private static void listAddition(ListIterator<Integer> iter){
        if(iter.hasNext()){
            Integer numb = iter.next();
            listAddition(iter);
            if(numb > 0)
                iter.add(numb);
        }
    }
    public static void main(String[] args) {
        List<Integer> intList= new ArrayList<>();
        intList.add(1);
        intList.add(-5);
        intList.add(4);
        ListIterator<Integer> iter = intList.listIterator();
        listAddition(iter);
        System.out.println(intList);
    }
}
