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
public class RSATest{
	public static void main(String[] args)throws IOException {
		try {
			String publicFileName = "test1.key";
			String privateFileName = "test2.key";
			RSAEncryptionDecryption test1 = new RSAEncryptionDecryption();
			test1.setPrivateKey(privateFileName);
			test1.setPublicKey(publicFileName);

			System.out.println("-------GENRATE PUBLIC and PRIVATE KEY-------------");
			KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
			keyPairGenerator.initialize(1024); //1024 used for normal securities
			KeyPair keyPair = keyPairGenerator.generateKeyPair();
			PublicKey publicKey = keyPair.getPublic();
			PrivateKey privateKey = keyPair.getPrivate();


			// //Pullingout parameters which makes up Key
			System.out.println("\n------- PULLING OUT PARAMETERS WHICH MAKES KEYPAIR----------\n");
			KeyFactory keyFactory = KeyFactory.getInstance("RSA");
			RSAPublicKeySpec rsaPubKeySpec = keyFactory.getKeySpec(publicKey, RSAPublicKeySpec.class);
			RSAPrivateKeySpec rsaPrivKeySpec = keyFactory.getKeySpec(privateKey, RSAPrivateKeySpec.class);

			//Share public key with other so they can encrypt data and decrypt thoses using private key(Don't share with Other)
				System.out.println("\n--------SAVING PUBLIC KEY AND PRIVATE KEY TO FILES-------\n");
				RSAEncryptionDecryption rsaObj = new RSAEncryptionDecryption();
				rsaObj.saveKeys(publicFileName, rsaPubKeySpec.getModulus(), rsaPubKeySpec.getPublicExponent());
				rsaObj.saveKeys(privateFileName, rsaPrivKeySpec.getModulus(), rsaPrivKeySpec.getPrivateExponent());
				
				//Encrypt Data using Public Key
				byte[] encryptedData = rsaObj.encryptData("Anuj Patel - Classified Information !");
				
				//Descypt Data using Private Key
				rsaObj.decryptData(encryptedData);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}catch (InvalidKeySpecException e) {
			e.printStackTrace();
		}

	}
}