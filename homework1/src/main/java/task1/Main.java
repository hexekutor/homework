package task1;

import java.util.Scanner;
import java.util.function.Supplier;

public class Main {

    private static Supplier<Integer> getInteger =
        () -> {
            Scanner in = new Scanner(System.in);
            return in.nextInt();
        };

    private static int printNumbers(Integer num){
        try{
            printNumbers(num / num * num - 1);
        }catch (ArithmeticException e){
            return 0;
        }
        System.out.println(num);
        return 0;
    }

    public static void main(String[] args) {
        printNumbers(getInteger.get());
    }
}
