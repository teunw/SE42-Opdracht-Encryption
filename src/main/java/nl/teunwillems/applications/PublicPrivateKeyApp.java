package nl.teunwillems.applications;

import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Teun on 13/06/2016.
 */
public class PublicPrivateKeyApp implements Runnable {

    public void run() {
        KeyPairGenerator keyGen = null;
        try {
            keyGen = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        keyGen.initialize(512);
        byte[] publicKey = keyGen.genKeyPair().getPublic().getEncoded();
        StringBuffer retString = new StringBuffer();
        for (int i = 0; i < publicKey.length; ++i) {
            retString.append(Integer.toHexString(0x0100 + (publicKey[i] & 0x00FF)).substring(1));
        }
        System.out.println(retString);
    }
}
