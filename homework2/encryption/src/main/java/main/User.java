package main;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.util.Arrays;

public class User {
    private String password;
    private String email;

    private String encodedPassword;
    private byte[] encodedEmail;

    private String decodedPassword;
    private String decodedEmail;
    User(String password, String email){
        this.password = password;
        this.email = email;
    }

    void encodePassword(){
        this.encodedPassword = Base64.encode(password.getBytes());
    }
    void decodePassword(){
        this.decodedPassword = new String(Base64.decode(encodedPassword));
    }
    void encodeEmail(Cipher cipher, Key publicKey) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        this.encodedEmail =  cipher.doFinal(email.getBytes());
    }
    void decodeEmail(Cipher cipher, Key privateKey) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        byte[] encodedBytesEmail =  cipher.doFinal(encodedEmail);
        StringBuilder stringBuilder = new StringBuilder();
        for (byte anEncodedBytesEmail : encodedBytesEmail) {
            stringBuilder.append((char) anEncodedBytesEmail);
        }
        this.decodedEmail = stringBuilder.toString();
    }
    String getEncodedStringEmail(){

        return Arrays.toString(encodedEmail);
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    String getEncodedPassword() {
        return encodedPassword;
    }

    String getDecodedEmail() {
        return decodedEmail;
    }

    String getDecodedPassword() {
        return decodedPassword;
    }


}
