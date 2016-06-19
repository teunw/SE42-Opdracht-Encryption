import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import java.io.*;
import java.security.SecureRandom;

/**
 * Created by wouter on 19-6-2016.
 */
public class Utils {
    SecureRandom sr = new SecureRandom();


    public void encrypt(File file, String message, char[] password) throws Exception{
        PBEKeySpec pbeKeySpec;
        PBEParameterSpec pbeParamSpec;
        SecretKeyFactory keyFac;

        // Salt
        byte[] salt = new byte[8];
        sr.nextBytes(salt);

        // Iteration count
        int count = 20;

        // Create PBE parameter set
        pbeParamSpec = new PBEParameterSpec(salt, count);

        // Prompt user for encryption password.
        // Collect user password as char array (using the
        // "readPassword" method from above), and convert
        // it into a SecretKey object, using a PBE key
        // factory.

        pbeKeySpec = new PBEKeySpec(password);
        keyFac = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);

        // Create PBE Cipher
        Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");

        // Initialize PBE Cipher with key and parameters
        pbeCipher.init(Cipher.ENCRYPT_MODE, pbeKey, pbeParamSpec);

        // Our cleartext
//        byte[] cleartext = "This is another example".getBytes();

        // Encrypt the cleartext
        byte[] ciphertext = pbeCipher.doFinal(message.getBytes());
//        return ciphertext;
        FileOutputStream fos = new FileOutputStream(file.toString());
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(salt);
        oos.writeObject(ciphertext);
        oos.close();
        fos.close();
    }
    public String decrypt(File file, char[] password) throws Exception{

        PBEKeySpec pbeKeySpec;
        PBEParameterSpec pbeParamSpec;
        SecretKeyFactory keyFac;

        FileInputStream fis = new FileInputStream(file.toString());
        ObjectInputStream ois = new ObjectInputStream(fis) {
        };


        byte[] salt = (byte[]) ois.readObject();
        byte[] message = (byte[]) ois.readObject();
        ois.close();
        fis.close();
        // Iteration count
        int count = 20;

        // Create PBE parameter set
        pbeParamSpec = new PBEParameterSpec(salt, count);

        // Prompt user for encryption password.
        // Collect user password as char array (using the
        // "readPassword" method from above), and convert
        // it into a SecretKey object, using a PBE key
        // factory.

        pbeKeySpec = new PBEKeySpec(password);
        keyFac = SecretKeyFactory.getInstance("PBEWithMD5AndDES");
        SecretKey pbeKey = keyFac.generateSecret(pbeKeySpec);

        // Create PBE Cipher
        Cipher pbeCipher = Cipher.getInstance("PBEWithMD5AndDES");

        // Initialize PBE Cipher with key and parameters
        pbeCipher.init(Cipher.DECRYPT_MODE, pbeKey, pbeParamSpec);

        // Our cleartext
//        byte[] cleartext = "This is another example".getBytes();

        // Encrypt the cleartext
        byte[] ciphertext = pbeCipher.doFinal(message);
        return new String(ciphertext);

    }
}
