import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.Cipher;
public class RSA{

	public RSA(){}

	public void generateKeys(String publicFN,String privateFN)throws IOException{
		try{
			String publicFileName = publicFN;
			String privateFileName = privateFN;
			RSAEncryptionDecryption test1 = new RSAEncryptionDecryption();
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(1024); //1024 used for normal securities
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			PublicKey publicKey = keyPair.getPublic();
			PrivateKey privateKey = keyPair.getPrivate();

			//Pullingout parameters which makes up Key
			System.out.println("\n------- PULLING OUT PARAMETERS WHICH MAKES KEYPAIR----------\n");
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			RSAPublicKeySpec rsaPubKeySpec = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
			RSAPrivateKeySpec rsaPrivKeySpec = keyFactory.getKeySpec(privateKey, RSAPrivateKeySpec.class);
			// //Share public key with other so they can encrypt data and decrypt thoses using private key(Don't share with Other)
			System.out.println("\n--------SAVING PUBLIC KEY AND PRIVATE KEY TO FILES-------\n");
		 	RSAEncryptionDecryption rsaObj = new RSAEncryptionDecryption();
			rsaObj.saveKeys(publicFileName, rsaPubKeySpec.getModulus(), rsaPubKeySpec.getPublicExponent());
			rsaObj.saveKeys(privateFileName, rsaPrivKeySpec.getModulus(), rsaPrivKeySpec.getPrivateExponent());
		}catch(Exception e){System.out.println(e);}
	}

	public byte[] encryptMessage(String publicKey,String message)throws IOException{
		 byte[] siphertext =null;
		try{
			RSAEncryptionDecryption rsaObj = new RSAEncryptionDecryption();
			rsaObj.setPublicKey(publicKey);
			byte[] encryptedData = rsaObj.encryptData(message);
			return encryptedData;
		 }catch(Exception e){}
		 
		 return siphertext;
	}

	public byte[] decryptMessage(String privateKey, byte[] encryptedMessage)throws IOException{
		byte[] decryptedMessage = null;
		RSAEncryptionDecryption rsaObj = new RSAEncryptionDecryption();
		rsaObj.setPrivateKey(privateKey);
		decryptedMessage = rsaObj.decryptData(encryptedMessage);
		return decryptedMessage;
	}
}
