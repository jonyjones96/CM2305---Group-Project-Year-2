import java.security.*;

     import java.math.*;

     

     public class testMD5 {

        public static void main(String args[]) throws Exception{
           String s="This is a test";
           MessageDigest m=MessageDigest.getInstance("MD5");
           m.update(s.getBytes(),0,s.length());
           System.out.println("MD5: "+new BigInteger(1,m.digest()).toString(16));
       }

   }
