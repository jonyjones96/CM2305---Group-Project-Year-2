import java.security.*;

     import java.math.*;

     

     public class MD5 {

        public static void main(String args[]) throws Exception{
           String s="This is a test";
           MessageDigest m=MessageDigest.getInstance("MD5");
           m.update(s.getBytes(),0,s.length());
           String hash = new BigInteger(1,m.digest()).toString(16);
           // System.out.println("MD5: "+ (new BigInteger(1,m.digest()).toString(16)));
           System.out.println("MD5: " + hash);

       }

   }


