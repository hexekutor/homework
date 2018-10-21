package task8;

import java.util.function.Function;
import java.util.function.Predicate;
public class Main {
    private static Function<Integer, Integer> intFunc= (x) -> (int)(Math.random() * 10 - 5);

    private static Function<Function<Integer, Integer>,Predicate<Integer>> function = (x)
            -> x.apply(0) > 0 ? Main.predicate1 : Main.predicate2;

    private static Predicate<Integer> predicate1;
    private static Predicate<Integer> predicate2;

    public static void main(String[] args) {
        function.apply(intFunc);



    }
}
