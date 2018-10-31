import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;

public class EncodeThread implements Runnable{
    private BlockingQueue<User> readedQueue;
    private BlockingQueue<User> encodedQueue;
    private Cipher cipher;
    private Key publicKey;
    private Integer encodedUsers = 0;
    EncodeThread(BlockingQueue<User> readedQueue, BlockingQueue<User> encodedQueue, Cipher cipher, Key publicKey)
    {
        this.readedQueue = readedQueue;
        this.encodedQueue = encodedQueue;
        this.cipher = cipher;
        this.publicKey = publicKey;
    }
    public void run() {
        try {
            do{
                User user = readedQueue.take();
                user.encodeEmail(cipher, publicKey);
                user.encodePassword();
                encodedQueue.put(user);
                encodedUsers++;
            }while (!Objects.equals(AmountOfUsers.getAmountOfUsers(), encodedUsers));

            System.out.println("encoded users");
        } catch (InterruptedException | BadPaddingException | InvalidKeyException | IllegalBlockSizeException e) {
            System.out.println("Encoded error");
        }

    }
}
