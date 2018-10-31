import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import java.io.FileWriter;
import java.io.IOException;
import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class Main {
    public static void main(String[] args) {

        try{
            BlockingQueue<User> readedQueue =new ArrayBlockingQueue<>(5);
            BlockingQueue<User> encodedQueue =new ArrayBlockingQueue<>(5);
            BlockingQueue<User> writetEncodedQueue =new ArrayBlockingQueue<>(5);
            BlockingQueue<User> decodedQueue =new ArrayBlockingQueue<>(5);

            FileWriter encodedWriter = new FileWriter("src/main/results/encodedUsers.csv");
            FileWriter decodedWriter = new FileWriter("src/main/results/decodedUsers.csv");

            Cipher cipher = Cipher.getInstance("RSA");
            KeyPairGenerator pairgen = KeyPairGenerator.getInstance("RSA");
            KeyPair keyPair = pairgen.generateKeyPair();
            Key publicKey = keyPair.getPublic();
            Key privateKey = keyPair.getPrivate();

            ExecutorService executor = Executors.newFixedThreadPool(5);
            executor.execute(new ReadFileThread(Main.class.getResourceAsStream("users.csv"), readedQueue));
            executor.execute(new EncodeThread(readedQueue, encodedQueue, cipher, publicKey));
            executor.execute(new WriteEncodedThread(encodedWriter, encodedQueue, writetEncodedQueue));
            executor.execute(new DecodeThread(writetEncodedQueue,decodedQueue, cipher, privateKey));
            executor.execute(new WriteDecodedTread(decodedWriter,decodedQueue, executor));

        } catch (IOException | NoSuchPaddingException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }
}
