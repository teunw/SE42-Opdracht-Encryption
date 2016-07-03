package nl.teunwillems.applications;

import nl.teunwillems.ApplicationPortal;
import nl.teunwillems.applications.utils.EncryptionUtil;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Teun on 13/06/2016.
 */
public class PublicPrivateKeyApp implements Runnable {

    public void run() {
        EncryptionUtil.generateKey();
        System.out.println("Done");
        ApplicationPortal.main(new String[] {});

    }
}
