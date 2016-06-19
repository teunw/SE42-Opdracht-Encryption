import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.PBEParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Wouter on 19-6-2016.
 */
public class UtilsBackup {
    SecureRandom sr = new SecureRandom();

    public void encrypt(String message, char[] password) throws Exception {
        byte[] salt = new byte[32];
        sr.nextBytes(salt);

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
//        KeySpec spec = new PBEKeySpec(password, salt, 65536, 256);
        KeySpec spec = new PBEKeySpec(password, salt, 65536, 128);
        SecretKey tmp = factory.generateSecret(spec);
        SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");

        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secret);
        System.out.println(secret.toString());

        byte[] ciphertext = cipher.doFinal(message.getBytes("UTF-8"));


        FileOutputStream fos = new FileOutputStream("AAA");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.write(salt);
        oos.write(ciphertext);
        oos.close();
        fos.close();
    }

    public String decrypt(char[] password) throws Exception {
        FileInputStream fis = new FileInputStream("AAA");
        ObjectInputStream ois = new ObjectInputStream(fis) {
        };

        byte[] salt = new byte[32];
        ois.read(salt);
        byte[] message = test(ois);
        ois.close();
        fis.close();


        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        KeySpec spec = new PBEKeySpec(password, salt, 65536, 128);
        SecretKey tmp = factory.generateSecret(spec);
        SecretKey secret = new SecretKeySpec(tmp.getEncoded(), "AES");
        System.out.println(secret.toString());

//        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        byte[] iv = cipher.getParameters().getParameterSpec(IvParameterSpec.class).getIV();

        cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(iv));
        byte[] ciphertext = cipher.doFinal(message);
        String a = new String(ciphertext, "UTF-8");
        System.out.println(a);
        return new String(ciphertext,"UTF-8");

    }




    public byte[] test (InputStream is ) throws Exception{

        ByteArrayOutputStream buffer = new ByteArrayOutputStream();

        int nRead;
        byte[] data = new byte[16384];

        while ((nRead = is.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }

        buffer.flush();

        return buffer.toByteArray();

    }



}
