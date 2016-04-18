import java.io.*;
import java.io.FileWriter;
import java.io.IOException;

public class testPublicString {
	
	public static void main(String[] args) {	
		 FileWriter writer = null;
		 BufferedReader reader = null;
		 String total = "";
		try {
			writer = new FileWriter("test12.key");
		    File file = new File("public.key");
		    reader = new BufferedReader(new FileReader(file));

		    String line;
		    while ((line = reader.readLine()) != null) {
		        total = total +line ;
		        
		    }
		    System.out.println(total);
	writer.write(total);
		} catch (IOException e) {
		    e.printStackTrace();
		} finally {
		    try {
		        reader.close();
		        writer.close();
		    } catch (IOException e) {
		        e.printStackTrace();
		    }
		}
	 }

}
