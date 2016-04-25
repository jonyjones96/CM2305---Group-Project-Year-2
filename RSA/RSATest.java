// import java.io.BufferedOutputStream;
// import java.io.File;
// import java.io.FileInputStream;
// import java.io.FileOutputStream;
// import java.io.IOException;
// import java.io.ObjectInputStream;
// import java.io.ObjectOutputStream;
// import java.math.BigInteger;
// import java.security.KeyFactory;
// import java.security.KeyPair;
// import java.security.KeyPairGenerator;
// import java.security.NoSuchAlgorithmException;
// import java.security.PrivateKey;
// import java.security.PublicKey;
// import java.security.spec.InvalidKeySpecException;
// import java.security.spec.RSAPrivateKeySpec;
// import java.security.spec.RSAPublicKeySpec;
// import javax.crypto.Cipher;
// public class RSATest{
// 	public static void main(String[] args)throws IOException {
// 		try {
// 			String publicFileName = "keyFiles/test1.key";
// 			String privateFileName = "keyFiles/test2.key";
// 			RSAEncryptionDecryption test1 = new RSAEncryptionDecryption();
// 			test1.setPrivateKey(privateFileName);
// 			test1.setPublicKey(publicFileName);

// 			System.out.println("-------GENRATE PUBLIC and PRIVATE KEY-------------");
// 			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
// 			keyPairGenerator.initialize(1024); //1024 used for normal securities
// 			KeyPair keyPair = keyPairGenerator.generateKeyPair();
// 			PublicKey publicKey = keyPair.getPublic();
// 			PrivateKey privateKey = keyPair.getPrivate();


// 			// //Pullingout parameters which makes up Key
// 			// System.out.println("\n------- PULLING OUT PARAMETERS WHICH MAKES KEYPAIR----------\n");
// 			// KeyFactory keyFactory = KeyFactory.getInstance("RSA");
// 			// RSAPublicKeySpec rsaPubKeySpec = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
// 			// RSAPrivateKeySpec rsaPrivKeySpec = keyFactory.getKeySpec(privateKey, RSAPrivateKeySpec.class);

// 			// //Share public key with other so they can encrypt data and decrypt thoses using private key(Don't share with Other)
// 			// 	System.out.println("\n--------SAVING PUBLIC KEY AND PRIVATE KEY TO FILES-------\n");
// 			 	RSAEncryptionDecryption rsaObj = new RSAEncryptionDecryption();
// 			// 	rsaObj.saveKeys(publicFileName, rsaPubKeySpec.getModulus(), rsaPubKeySpec.getPublicExponent());
// 			// 	rsaObj.saveKeys(privateFileName, rsaPrivKeySpec.getModulus(), rsaPrivKeySpec.getPrivateExponent());
				
// 				//Encrypt Data using Public Key
// 				byte[] encryptedData = rsaObj.encryptData("Anuj Patel - Classified Information !");
				
// 				//Descypt Data using Private Key
// 				rsaObj.decryptData(encryptedData);
// }catch(Exception e ){}
// 		// } catch (NoSuchAlgorithmException e) {
// 		// 	e.printStackTrace();
// 		// }catch (InvalidKeySpecException e) {
// 		// 	e.printStackTrace();
// 		// }

// 	}
// }
import java.util.Date;
import java.util.Random;
import java.util.*;
import javax.xml.bind.DatatypeConverter;

public class RSATest{
	private static Random random = new Random((new Date()).getTime());

	public static void main(String[] args){
		try{
			RSA test1 = new RSA();
			String pu = "keyFiles/test7.txt";
			String pr = "keyFiles/test8.txt";
			// test1.generateKeys(pu,pr);
			String first = generateRandomString(10);
			System.out.println("Message: " + first);
			byte[] encypted =test1.encryptMessage(pu,first);
			byte[] newMessage = null;
			System.out.println(new String(encypted));

			String oldMessage = DatatypeConverter.printBase64Binary(encypted);
			byte[] decoded = DatatypeConverter.parseBase64Binary(oldMessage);

			System.out.println("OLd message: " +oldMessage);
			System.out.println("new message: " + new String(decoded, "UTF-8"));
			newMessage = test1.decryptMessage(pr,encypted);
			System.out.println("Decrypted Data: " + new String(newMessage));
		}
		catch(Exception e){}
	}

	public static String generateRandomString(int length) {
      char[] values = {'a','b','c','d','e','f','g','h','i','j',
               'k','l','m','n','o','p','q','r','s','t',
               'u','v','w','x','y','z','0','1','2','3',
               '4','5','6','7','8','9'};

      String out = "";

      for (int i=0;i<length;i++) {
          int idx=random.nextInt(values.length);
          out += values[idx];
      }
      return out;
    }
}
