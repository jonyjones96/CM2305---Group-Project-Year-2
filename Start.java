import java.util.Scanner;
// import java.RSA.*;
public class Start{

	public static void main(String args[]){
		System.out.println("Welcome to the Decentralised DNS");
		System.out.println("Please select an option (A,B,C): ");
		System.out.println("A-Query a Domain ");
		System.out.println("B-Register a Domain ");
		System.out.println("C-Change the value of a Domain");

		Scanner user_input = new Scanner( System.in );
		String option;
		option = user_input.next( );

		if (option.equals("a") ){}
		else if(option.equals("b") ){
			try{BlockChain block = new BlockChain();

		  	// System.out.println(block.isEmpty ());
		 	userInput user = new userInput();
			String key = userInput.insertKey();
			String userValue = userInput.insertValue();
			System.out.println("Your key: "+ key);
			System.out.println("Your value: "+ userValue);
			int buyerID = 101;
			int sellerID = 122;
			int transactionAmount = key.length();
			int levelOfDifficulty = userValue.length();
		 	
		 	block.insert(buyerID,sellerID,transactionAmount,levelOfDifficulty,key,userValue);
			//block.insert(121,122,1,5,"google.com","74.125.224.72");
			//block.insert(123,124,8,4,"facebook.com","69.63.176.13");
			//block.insert(125,126,2,4,"github.com","192.30.252.0");
			//block.insert(127,128,5,5,"bbc.co.uk","212.58.246.94");

			block.print ();
	  		}
	  		catch(Exception e){e.printStackTrace();}
		}
		else if(option.equals("c" )){
			System.out.println("Enter the location of the private key: ");
			// Check weather they have the right to update the key
			try{
			RSA test1 = new RSA();
			String pu = "keyFiles/test3.key";
			String pr = "keyFiles/test4.key";
			// test1.generateKeys(pu,pr);
			String first = "helloworld";
			byte[] encypted =test1.encryptMessage(pu,first);
			test1.decryptMessage(pr,encypted);
		}
		catch(Exception e){}
		}	
		
	}
}
