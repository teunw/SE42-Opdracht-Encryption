package nl.teunwillems;

import nl.teunwillems.applications.DecryptionApp;
import nl.teunwillems.applications.EncryptionApp;
import nl.teunwillems.applications.PublicPrivateKeyApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Teun on 13/06/2016.
 */
public class ApplicationPortal {

    public static List<Runnable> getApplications() {
        List<Runnable> r = new ArrayList<>(3);
        r.add(new PublicPrivateKeyApp());
        r.add(new DecryptionApp());
        r.add(new EncryptionApp());
        return r;
    }

    public static void main(String[] args) {

        System.out.println("Select an application (1,2 or 3)");
        System.out.println("Application 0: Public/Private key generator");
        System.out.println("Application 1: Decryption");
        System.out.println("Application 2: Encryption");

        String inputString = new Scanner(System.in).nextLine();
        try {
            int input = Integer.parseInt(inputString);
            if (input > getApplications().size()) {
                ApplicationPortal.main(args);
            } else {
                getApplications().get(input).run();
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input, restarting application");
            ApplicationPortal.main(args);
        }
    }

}
