package task9;


import static java.lang.Thread.sleep;

/**
 * Created by User on 10.10.2018.
 */
public class Thread2 implements Runnable{
    @Override
    public void run() {
        int i = 0;
        long start = System.currentTimeMillis();

        while(i++ < 10){
            System.out.println((System.currentTimeMillis() - start) / 1000 + " seconds");
            try {
                sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
