package query;
import java.util.Scanner;

public class userInput {
	
	public static String insertKey(){
		Scanner user_input = new Scanner( System.in );
		String key;
		System.out.println("Enter the key: ");
		key = user_input.next( );
		return key;
	}
	
	public static String insertValue(){
		Scanner user_input = new Scanner( System.in );
		String value;
		System.out.println("Enter the value: ");
		value = user_input.next( );
		return value;
	}
}