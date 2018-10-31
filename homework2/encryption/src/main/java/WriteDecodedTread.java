import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;

public class WriteDecodedTread implements Runnable {
    private BlockingQueue<User> decodedQueue;
    private FileWriter decodedWriter;
    private ExecutorService executor;
    private static Integer writedDecodedUsers = 0;
    WriteDecodedTread(FileWriter decodedWriter, BlockingQueue<User> decodedQueue, ExecutorService executor)
    {
        this.decodedWriter = decodedWriter;
        this.decodedQueue = decodedQueue;
        this.executor = executor;
    }
    public void run() {
        try {
            do{
                User user = decodedQueue.take();
                decodedWriter.write(user.getDecodedPassword());
                decodedWriter.write('|');
                decodedWriter.write(user.getDecodedEmail());
                decodedWriter.write('|');
                writedDecodedUsers++;
            }while (!Objects.equals(AmountOfUsers.getAmountOfUsers(), writedDecodedUsers));

            try {
                decodedWriter.flush();
                decodedWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("writed decoded users");
            executor.shutdown();
        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        }

    }
}
