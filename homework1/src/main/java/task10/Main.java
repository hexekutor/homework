package task10;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        List<Integer> stringList = new ArrayList<>();

        for(int i = 0; i < 1000; i++){
            stringList.add(i);
        }

        List<Integer> stringList1 = new ArrayList<>();

        PipedOutputStream pout = new PipedOutputStream();
        PipedInputStream pin = new PipedInputStream(pout);

        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(() -> {
            for(int i = 0; i < 100; i++){
                try {
                    pout.write(i);
                    sleep(1000);
                    //System.out.println(stringList1.get(stringList1.size() - 1));
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        sleep(1000);

        executor.submit(() -> {
            for(int i = 0; i < 100; i++){
                try {
                    sleep(1000);
                    stringList1.add(pin.read());
                    System.out.println(stringList1.get(stringList1.size() - 1));
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            }

            executor.shutdown();

            try {
                pin.close();
                pout.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });



    }

}
