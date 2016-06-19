import junit.framework.Assert;

import static org.junit.Assert.*;

/**
 * Created by wouter on 19-6-2016.
 */
public class UtilsTest extends Utils {
    String password = "a";
    String message = "blablablabla";
    Utils u;

//    @org.junit.Before
//    public void setUp() throws Exception {
//        u = new Utils();
//    }

    @org.junit.Test
    public void testEncrypt() throws Exception {
        u = new Utils();
        u.encrypt(message,password.toCharArray());
        u.encrypt(message,password.toCharArray());
        String a = u.decrypt(password.toCharArray());
        System.out.println(a);
        Assert.assertEquals(a,message);
    }

}