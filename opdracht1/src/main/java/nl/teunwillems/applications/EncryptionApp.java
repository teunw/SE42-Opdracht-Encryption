package nl.teunwillems.applications;

import nl.teunwillems.ApplicationPortal;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Scanner;

import static nl.teunwillems.applications.utils.EncryptionUtil.PRIVATE_KEY_FILE;
import static nl.teunwillems.applications.utils.EncryptionUtil.PUBLIC_KEY_FILE;
import static nl.teunwillems.applications.utils.EncryptionUtil.encrypt;

/**
 * Created by Teun on 13/06/2016.
 */
public class EncryptionApp implements Runnable {

    @Override
    public void run() {
        System.out.println("Specify input text");
        String originalText = new Scanner(System.in).nextLine();
        System.out.println("Specify output file");
        File outputFile = new File(new Scanner(System.in).nextLine());

        ObjectInputStream inputStream = null;
        // Decrypt the cipher text using the private key.
        try {
            // Encrypt the string using the public key
            inputStream = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
            final PublicKey publicKey = (PublicKey) inputStream.readObject();
            final byte[] cipherText = encrypt(originalText, publicKey);

            FileUtils.writeByteArrayToFile(outputFile, cipherText);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ApplicationPortal.main(new String[] {});
    }
}
