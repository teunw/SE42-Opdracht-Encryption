import junit.framework.Assert;

/**
 * Created by wouter on 19-6-2016.
 */
public class UtilsBackupTest extends UtilsBackup {
    String password = "a";
    String message = "blablablabla";
    UtilsBackup u;

//    @org.junit.Before
//    public void setUp() throws Exception {
//        u = new UtilsBackup();
//    }

    @org.junit.Test
    public void testEncrypt() throws Exception {
        u = new UtilsBackup();
        u.encrypt(message,password.toCharArray());
        String a = u.decrypt(password.toCharArray());
        System.out.println(a);
        Assert.assertEquals(a.toString(),message);
    }
//    @org.junit.Test
//    public void testEncrypt2() throws Exception {
//        Utils u2 = new Utils();
//        u2.encrypt(message,password.toCharArray());
//        String b = u2.decrypt(password.toCharArray());
//
//        Assert.assertEquals(b,message);
//    }

}