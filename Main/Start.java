import java.util.Scanner;
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
			CheckOwner test1 = new CheckOwner();
		}	
		
	}
}