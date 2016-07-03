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

import static nl.teunwillems.applications.utils.EncryptionUtil.*;

/**
 * Created by Teun on 13/06/2016.
 */
public class DecryptionApp implements Runnable {

    @Override
    public void run() {

        System.out.println("Specify input file");
        String inputFile = new Scanner(System.in).nextLine();


        ObjectInputStream inputStream = null;

        // Encrypt the string using the public key
        try {
            // Decrypt the cipher text using the private key.
            byte[] cipherText = FileUtils.readFileToByteArray(new File(inputFile));

            inputStream = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
            final PrivateKey privateKey = (PrivateKey) inputStream.readObject();
            final String plainText = decrypt(cipherText, privateKey);

            System.out.println(plainText);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ApplicationPortal.main(new String[] {});

    }
}
