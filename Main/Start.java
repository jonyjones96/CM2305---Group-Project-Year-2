import java.util.Scanner;
import java.RSA.*;
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
		else if(option.equals("b") ){}
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