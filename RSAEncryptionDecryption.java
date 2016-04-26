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
import javax.xml.bind.DatatypeConverter;

/**
 * 
 * @author Anuj
 * Blog www.goldenpackagebyanuj.blogspot.com
 * RSA - Encrypt Data using Public Key
 * RSA - Descypt Data using Private Key
 */
public class RSAEncryptionDecryption {

	private static String PUBLIC_KEY_FILE ;
	private static String PRIVATE_KEY_FILE ;
	
	public RSAEncryptionDecryption() throws IOException {
	}

	public void setPrivateKey( String filenamePrivate){
		PRIVATE_KEY_FILE = filenamePrivate;
	}
	public void setPublicKey(String filenamePublic){
		PUBLIC_KEY_FILE = filenamePublic;
	}
	
	/**
	 * Save Files
	 * @param fileName
	 * @param mod
	 * @param exp
	 * @throws IOException
	 */
	public void saveKeys(String fileName,BigInteger mod,BigInteger exp) throws IOException{
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		
		try {
			System.out.println("Generating "+fileName + "...");
			fos = new FileOutputStream(fileName);
			oos = new ObjectOutputStream(new BufferedOutputStream(fos));
			
			oos.writeObject(mod);
			oos.writeObject(exp);			
			
			System.out.println(fileName + " generated successfully");
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(oos != null){
				oos.close();
				
				if(fos != null){
					fos.close();
				}
			}
		}		
	}
	
	/**
	 * Encrypt Data
	 * @param data
	 * @throws IOException
	 */
	public byte[] encryptData(String data) throws IOException {
		System.out.println("\n----------------ENCRYPTION STARTED------------");
		
		System.out.println("Data Before Encryption :" + data);
		byte[] dataToEncrypt = data.getBytes();
		byte[] encryptedData = null;
		try {
			PublicKey pubKey = readPublicKeyFromFile(PUBLIC_KEY_FILE);
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.ENCRYPT_MODE, pubKey);
			encryptedData = cipher.doFinal(dataToEncrypt);
			// System.out.println("Encryted Data: " + encryptedData);
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		System.out.println("----------------ENCRYPTION COMPLETED------------");		
		return encryptedData;
	}

	/**
	 * Encrypt Data
	 * @param data
	 * @throws IOException
	 */
	public byte[] decryptData(String data) throws IOException {
		System.out.println("\n----------------DECRYPTION STARTED------------");
		byte[] descryptedData = null;
		byte[] data2 = DatatypeConverter.parseBase64Binary(data);
		
		try {
			PrivateKey privateKey = readPrivateKeyFromFile(PRIVATE_KEY_FILE);
			Cipher cipher = Cipher.getInstance("RSA");
			cipher.init(Cipher.DECRYPT_MODE, privateKey);
			descryptedData = cipher.doFinal(data2);
			// System.out.println("Decrypted Data: " + new String(descryptedData));
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		System.out.println("----------------DECRYPTION COMPLETED------------");	
		return descryptedData;	
	}
	
	/**
	 * read Public Key From File
	 * @param fileName
	 * @return PublicKey
	 * @throws IOException
	 */
	public PublicKey readPublicKeyFromFile(String fileName) throws IOException{
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(new File(fileName));
			ois = new ObjectInputStream(fis);
			
			BigInteger modulus = (BigInteger) ois.readObject();
		    BigInteger exponent = (BigInteger) ois.readObject();
			
		    //Get Public Key
		    RSAPublicKeySpec rsaPublicKeySpec = new RSAPublicKeySpec(modulus, exponent);
		    KeyFactory fact = KeyFactory.getInstance("RSA");
		    PublicKey publicKey = fact.generatePublic(rsaPublicKeySpec);
		    		    
		    return publicKey;
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(ois != null){
				ois.close();
				if(fis != null){
					fis.close();
				}
			}
		}
		return null;
	}
	
	/**
	 * read Public Key From File
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public PrivateKey readPrivateKeyFromFile(String fileName) throws IOException{
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(new File(fileName));
			ois = new ObjectInputStream(fis);
			
			BigInteger modulus = (BigInteger) ois.readObject();
		    BigInteger exponent = (BigInteger) ois.readObject();
			
		    //Get Private Key
		    RSAPrivateKeySpec rsaPrivateKeySpec = new RSAPrivateKeySpec(modulus, exponent);
		    KeyFactory fact = KeyFactory.getInstance("RSA");
		    PrivateKey privateKey = fact.generatePrivate(rsaPrivateKeySpec);
		    		    
		    return privateKey;
		    
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(ois != null){
				ois.close();
				if(fis != null){
					fis.close();
				}
			}
		}
		return null;
	}
}
