package groupProject;

import java.util.Scanner;
import java.sql.*;
import java.util.Date;
import java.util.Random;
import java.io.*;
// import java.RSA.*;
public class Start{

	public static void main(String args[]){
		mainMenu();
	}
	public static void mainMenu(){	
		System.out.println("Welcome to the Decentralised DNS");
		System.out.println("Please select an option (A,B,C): ");
		System.out.println("A-Query a Domain ");
		System.out.println("B-Register a Domain ");
		System.out.println("C-Change the value of a Domain");

		Scanner user_input = new Scanner( System.in );
		String option;
		option = user_input.next( );

		if (option.equals("a") ){
			userInput user = new userInput();
			String key = userInput.insertKey();
			System.out.println("Your key: "+ key);
			try {
				Class.forName("com.mysql.jdbc.Driver");
			}
			catch (ClassNotFoundException err) {
				System.out.println(err);
			}
		
			Connection con = null;
			try{
				//my localhost:
				//con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gproject","root","Willtheshiba12");
				con = DriverManager.getConnection("jdbc:mysql://csmysql.cs.cf.ac.uk/c1314249","c1314249","rodim8");
				if (con != null) {
    				//System.out.println("Connected");
				}
				Statement stat = con.createStatement();
				String SQL = "SELECT a_value FROM BlockChain WHERE `a_key` = '" + key + "'";
				ResultSet rs;
				rs = stat.executeQuery(SQL); /*WHERE 'key'='abc123'*/
				String value = " ";
				while (rs.next()){
					value = rs.getString("a_value");
					System.out.println("The value is: " + value);
				}		
				if ( value == " "){
					System.out.println("System unable to get your request, make sure the key exists" );
				}
				con.close();
			}
			catch (SQLException e){
				System.out.println(e.getMessage());
			}
		}
		else if(option.equals("b") ){
			try{BlockChain block = new BlockChain();//if statement require here to check if a blockchain exist already
				createTable table = new createTable();
				RSA keyCreater = new RSA();
			  	// System.out.println(block.isEmpty ());
			 	userInput user = new userInput();
				String key = userInput.insertKey();
				String userValue = userInput.insertValue();
				String privateKeyName;
				String publicKeyName;
				System.out.println("Your key: "+ key);
				System.out.println("Your value: "+ userValue);
				System.out.println("Insert the name for the private key file: ");
				privateKeyName = user_input.next( );
				System.out.println("Your private key file name is: " +privateKeyName );
				publicKeyName = "publicKey.key";
				int buyerID = 101;
				int sellerID = 122;
				int transactionAmount = key.length();
				int levelOfDifficulty = userValue.length();
				keyCreater.generateKeys(publicKeyName,privateKeyName);
				String publicKeyString = getPublicKeyString();
				// insert random number in db
				Random rand = new Random();
				int randomNum = rand.nextInt(100);
				String message = generateRandomString(10,randomNum);
				byte[] encryptedMessage = keyCreater.encryptMessage(publicKeyName,message);
				String encryptedMDB = new String(encryptedMessage);
				// need to add the randomNum and the encryptedMessageToAddToDatabase to add to the database
				
			 	
			 	block.insert(buyerID,sellerID,transactionAmount,levelOfDifficulty,key,userValue,encryptedMDB,randomNum);
				//block.insert(121,122,1,5,"google.com","74.125.224.72");
				//block.insert(123,124,8,4,"facebook.com","69.63.176.13");
				//block.insert(125,126,2,4,"github.com","192.30.252.0");
				//block.insert(127,128,5,5,"bbc.co.uk","212.58.246.94");
			 	
			 	table.duplicateBchain();
			}
			catch(Exception e){e.printStackTrace();}
		}

		else if(option.equals("c" )){
			try{
				System.out.println("Enter the name of the Domain you want to change: ");
				String key = user_input.next( );
				System.out.println("Enter the location of the private key: ");
				// Check whether they have the right to update the key
				RSA transferKey = new RSA();
				String privateKey = user_input.next( );
				//System.out.println(privateKey);
				createTable table = new createTable();
				int seed = table.getSeed(key);
				System.out.println("seed: " +seed);
				String originalMessage = generateRandomString(10,seed);
				System.out.println("originalMessage: " + originalMessage);
				String message = table.getEncryptedMessage(key);
				//byte[] message2 = message.getBytes();
				System.out.println("encrypted: "+ message);
				byte[] messageText = transferKey.decryptMessage(privateKey,message);
				String decryptedMessage = new String(messageText);
				//Function to get originalText
				if(decryptedMessage.equals(originalMessage)){
					// update the database
					table.updateDomain(key);
					System.out.println("Your domain has been changed");
										
					// update everyones database
				
				}
			}
			catch(Exception e){e.printStackTrace();}
		}
	}
	
	public static String generateRandomString(int length, int seed) {
	      char[] values = {'a','b','c','d','e','f','g','h','i','j',
	               'k','l','m','n','o','p','q','r','s','t',
	               'u','v','w','x','y','z','0','1','2','3',
	               '4','5','6','7','8','9'};

	      String out = "";
	      Random random = new Random(seed);

	      for (int i=0;i<length;i++) {
	          int idx=random.nextInt(values.length);
	          out += values[idx];
	      }
	      return out;
	      }

	  public static String getPublicKeyString(){
	  	// FileWriter writer = null;
		BufferedReader reader = null;
		String total = "";
		try {
			// writer = new FileWriter("test12.key");
		    File file = new File("public.key");
		    reader = new BufferedReader(new FileReader(file));

		    String line;
		    while ((line = reader.readLine()) != null) {
		        total = total +line ;
		        
		    }
		    // System.out.println(total);
			// writer.write(total);
		}catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    try {
		        reader.close();
		        //writer.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
		return total;
	  }
}