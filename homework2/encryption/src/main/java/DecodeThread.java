import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;

public class DecodeThread implements Runnable{
    private BlockingQueue<User> writedEncodedQueue;
    private BlockingQueue<User> decodedQueue;
    private Cipher cipher;
    private Key privateKey;
    private static Integer decodedUsers = 0;


    DecodeThread(BlockingQueue<User> writedEncodedQueue, BlockingQueue<User> decodedQueue, Cipher cipher, Key privateKey)
    {
        this.writedEncodedQueue = writedEncodedQueue;
        this.decodedQueue = decodedQueue;
        this.cipher = cipher;
        this.privateKey = privateKey;
    }
    public void run() {
        try {
            do{
                User user = writedEncodedQueue.take();
                user.decodeEmail(cipher, privateKey);
                user.decodePassword();
                decodedQueue.put(user);
                decodedUsers++;
            }while (!Objects.equals(AmountOfUsers.getAmountOfUsers(), decodedUsers));

            System.out.println("decoded users");

        } catch (InterruptedException | BadPaddingException | InvalidKeyException | IllegalBlockSizeException e) {
            System.out.println("Decoded error");
        }

    }
}
