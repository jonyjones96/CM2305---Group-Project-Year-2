import java.io.*;
import java.io.FileWriter;
import java.io.IOException;

public class testPublicString {
	
	public static void main(String[] args) {	
		// FileReader reader = null;
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
//  byte[] test =null;
		// try {
		//     reader = new FileReader("public.key");
		//     // writer = new FileWriter("test10.key");
		//     int a = 0;
		//         while ((a = reader.read()) != -1) {
		//              // writer.write(a);
		//             // System.out.println(a);
		//              test = test + a.getBytes();
		//         }
		//         // System.out.println(test);
		//         // writer.write(test);

		//     } catch (FileNotFoundException e) {
		//     e.printStackTrace();
		// } catch (IOException e) {
		//     e.printStackTrace();
		// } finally {
		//     try {
		//         reader.close();
		//         writer.close();
		//     } catch (IOException e) {
		//         e.printStackTrace();
		//     }
		// }
