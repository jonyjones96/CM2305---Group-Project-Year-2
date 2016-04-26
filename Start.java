import java.util.Scanner;
import java.sql.*;
import java.util.Date;
import java.util.Random;
import java.io.*;
import javax.xml.bind.DatatypeConverter;
// import java.RSA.*;
public class Start{

	public static void main(String args[]){
		new Thread()
 		    {
 	    	public void run() {
		    	try {
 	    		BlockChainServer.startListening();    	
 		    	}
 		    	catch(IOException e) {}
 		    }
 		  }.start();
 		new Thread()
 		    {
 		    public void run() {
 		    	BlockChainClient client = new BlockChainClient("10.0.0.8");
 		    }
 		 }.start();

		boolean New = true;
		createTable table = new createTable();
		String connectionString = null;
		if(New == true){
			//System.out.println(connectionString);
			try {
 				BufferedReader in = new BufferedReader(new FileReader("localhost.txt"));
 				connectionString = in.readLine();
 				//System.out.println(connectionString);
 			}
 			catch(IOException ex){
 				//System.out.println(ex);
 			}	
			try {
				File file = new File("localhost.txt");

				if (!file.exists()) {
					connectionString = table.newCon();
					file.createNewFile();

					FileWriter fw = new FileWriter(file.getAbsoluteFile());
					BufferedWriter bw = new BufferedWriter(fw);
					bw.write(connectionString);
					bw.close();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}
			
			String blockChainString = "123,123,10,6,abc,will.com,123,123,123,38"; //blockChainString from connection"
			table.newTable(connectionString,blockChainString);
			table.duplicateBchain(connectionString,true);
		}
		mainMenu();
	}
	public static void mainMenu(){	
		System.out.println("Welcome to the Decentralised DNS");
		System.out.println("Please select an option (A,B,C): ");
		System.out.println("A-Query a Domain ");
		System.out.println("B-Register a Domain ");
		System.out.println("C-Change the value of a Domain");
		System.out.println("D-Quit");
		
		String connectionString = null;
		String[] DB = null;
		//need some code here to get the connectionString
		try {
			BufferedReader in = new BufferedReader(new FileReader("localhost.txt"));
			connectionString = in.readLine();
			DB = connectionString.split(",");
			//System.out.println(connectionString);
		}
		catch(IOException ex){
			System.out.println(ex);
		}
		
		Scanner user_input = new Scanner( System.in );
		String option;
		option = user_input.next( );

		if (option.toLowerCase().equals("a") ){
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
				con = DriverManager.getConnection(DB[0],DB[1],DB[2]);
				//con = DriverManager.getConnection("jdbc:mysql://csmysql.cs.cf.ac.uk/c1314249","c1314249","rodim8");
				if (con != null) {
    				//System.out.println("Connected");
				}
				Statement stat = con.createStatement();
				String SQL = "SELECT a_value FROM someBlockChain WHERE `a_key` = '" + key + "'";
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
		else if(option.toLowerCase().equals("b") ){
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
				publicKeyName = "public.key";
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
				String encryptedMDB = DatatypeConverter.printBase64Binary(encryptedMessage);
				System.out.println("THe encrypted string : " + encryptedMDB);
				// String encryptedMDB = new String(encryptedMessage);
				// need to add the randomNum and the encryptedMessageToAddToDatabase to add to the database
				
			 	
			 	block.insert(buyerID,sellerID,transactionAmount,levelOfDifficulty,key,userValue,encryptedMDB,randomNum);
				//block.insert(121,122,1,5,"google.com","74.125.224.72");
				//block.insert(123,124,8,4,"facebook.com","69.63.176.13");
				//block.insert(125,126,2,4,"github.com","192.30.252.0");
				//block.insert(127,128,5,5,"bbc.co.uk","212.58.246.94");
			 	
			 	table.duplicateBchain(connectionString,false);
			 	BlockChainServer.sendUpdate(key);
			}
			catch(Exception e){e.printStackTrace();}
		}

		else if(option.toLowerCase().equals("c" )){
			try{
				System.out.println("Enter the name of the Domain you want to change: ");
				String key = user_input.next( );
				System.out.println("Enter the location of the private key: ");
				// Check whether they have the right to update the key
				RSA transferKey = new RSA();
				String privateKey = user_input.next( );
				//System.out.println(privateKey);
				createTable table = new createTable();
				int seed = table.getSeed(key,connectionString);
				System.out.println("seed: " +seed);
				String originalMessage = generateRandomString(10,seed);
				System.out.println("originalMessage: " + originalMessage);
				String message = table.getEncryptedMessage(key,connectionString);
				//byte[] message2 = message.getBytes();
				System.out.println("encrypted: "+ message);
				byte[] messageText = transferKey.decryptMessage(privateKey,message);
				String decryptedMessage = new String(messageText);
				//Function to get originalText
				if(decryptedMessage.equals(originalMessage)){
					// update the database
					table.updateDomain(key,connectionString);
					System.out.println("Your domain has been changed");
										
					// update everyones database
					BlockChainServer.sendUpdate(key);
				
				}
			}
			catch(Exception e){e.printStackTrace();}
		}
		else if(option.toLowerCase().equals("d" )){
			System.out.println("You have quit the program");
			System.exit(0);
		}
		else{
			System.out.println("You input did not make sense, try again!");
			mainMenu();
		}
		
	System.out.println("----------------------");
	System.out.println();
	mainMenu();
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
