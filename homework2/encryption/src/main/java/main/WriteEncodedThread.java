package main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * Created by main.User on 27.10.2018.
 */
public class WriteEncodedThread implements Runnable {
    private BlockingQueue<User> encodedQueue;
    private BlockingQueue<User> writedEncodedQueue;
    private FileWriter encodedWriter;
    private static Integer writedEncodedUsers = 0;
    WriteEncodedThread(FileWriter encodedWriter, BlockingQueue<User> encodedQueue, BlockingQueue<User> writedEncodedQueue)
    {
        this.encodedWriter = encodedWriter;
        this.encodedQueue = encodedQueue;
        this.writedEncodedQueue = writedEncodedQueue;
    }
    public void run() {
        try {
            do{
                User user = encodedQueue.take();
                encodedWriter.write(user.getEncodedPassword());
                encodedWriter.write('|');
                encodedWriter.write(user.getEncodedStringEmail());
                encodedWriter.write('|');
                writedEncodedQueue.put(user);
                writedEncodedUsers++;
            }while (!Objects.equals(AmountOfUsers.getAmountOfUsers(), writedEncodedUsers));

            try{
                encodedWriter.flush();
                encodedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("writed encoded users");
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

    }
}
