package main;

import java.util.Comparator;
import java.util.List;

public class Main {
    public static int sumOfList(List<Integer> list){
        return  list.stream().mapToInt(Integer::intValue).sum();
    }
    public static List<List<Integer>> sortList(List<List<Integer>> list){
        list.sort(Comparator.comparing(Main::sumOfList).reversed());
        return list;
    }
}
