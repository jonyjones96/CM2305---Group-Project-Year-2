import java.sql.*;
import java.util.Scanner;

public class createTable {
	public void duplicateBchain() {  // CREATES COPY OF someBlockChain table to BlockChain table.
		try {
		 	Class.forName("com.mysql.jdbc.Driver");      // driver used for mysql configuration in Java
		 } catch (ClassNotFoundException err){
		 	System.out.println(err);
		 }
		 
		 Connection conn = null;
		 
		 try {
		 	conn = DriverManager.getConnection("jdbc:mysql://csmysql.cs.cf.ac.uk/c1314249","c1314249", "rodim8" ); // connection configuration to the mysql database
		 	if (conn!= null){       // successful connection
		 		
		 	}
		 	
			Statement stmt = conn.createStatement();
			String sql = "INSERT INTO BlockChain (SELECT * FROM someBlockChain WHERE someBlockChain.a_key NOT IN (SELECT BlockChain.a_key FROM BlockChain));";
			int count = stmt.executeUpdate(sql);
			
			if (count > 0){
				System.out.println(count + " record(s) successfully updated to BlockChain.");
			} else {
				System.out.println("BlockChain is up to date.");
			}
		 }
		 catch (SQLException e){    // if failed connection
		 	System.out.println("Got an exception");
		 	System.out.println(e.getMessage());
		 } 
}

	public void newTable() {  // CREATES NEW TABLE CALLED BLOCKCHAIN
		 try {
			 	Class.forName("com.mysql.jdbc.Driver");      // driver used for mysql configuration in Java
			 } catch (ClassNotFoundException err){
			 	System.out.println(err);
			 }
			 
			 Connection conn = null;
			 
			 try {
			 	conn = DriverManager.getConnection("jdbc:mysql://csmysql.cs.cf.ac.uk/c1314249","c1314249", "rodim8" ); // connection configuration to the mysql database
			 	if (conn!= null){       // successful connection
			 	//System.out.println("Connected");
			 	
			 	//System.out.println("Creating table...");
				Statement stmt = conn.createStatement();
				
			String sql = " CREATE TABLE someBlockChain " +
							"(sellerID INTEGER, " +
							"  buyerID INTEGER, " +
							"transactionAmt INTEGER, " +
							"levelDifficulty INTEGER, " +
							"previousHash VARCHAR(40), " +
							"a_key VARCHAR(50), " +
							"a_value VARCHAR(18), " +
							"publicKey VARCHAR(1000)) ";
							
				
				stmt.executeUpdate(sql);
				//System.out.println("Created.");
			 	}
			 }
			 catch (SQLException e){    // if failed connection
			 	System.out.println("Got an exception");
			 	System.out.println(e.getMessage());
			 }
	}
	
	public static String concatFields() {  // CONCATENATES FIELDS INTO A STRING
		String concatString = "";
		 try {
			 	Class.forName("com.mysql.jdbc.Driver");      // driver used for mysql configuration in Java
			 } catch (ClassNotFoundException err){
			 	System.out.println(err);
			 }
			 
			 Connection conn = null;
			 
			 try {
			 conn = DriverManager.getConnection("jdbc:mysql://csmysql.cs.cf.ac.uk/c1314249","c1314249", "rodim8" ); // connection configuration to the mysql database
			 if (conn!= null){       // successful connection
			 	//System.out.println("Connected");

			Statement stmt = conn.createStatement();
			String sql = "SELECT * FROM someBlockChain";
			ResultSet rs = stmt.executeQuery(sql);
			//String sql = "SELECT CONCAT(sellerID,',',buyerID,',',transactionAmount,',',levelDifficulty,',',previousHash,',',a_key,',',a_value,',',publicKey) FROM someBlockChain";
			
			
			while (rs.next()){
				concatString += rs.getString("sellerID"); concatString +=  ",";
				concatString += rs.getString("buyerID" ); concatString +=  ",";
				concatString += rs.getString("transactionAmount" ); concatString +=  ",";
				concatString += rs.getString("levelDifficulty" ); concatString +=  ",";
				concatString += rs.getString("previousHash" ); concatString +=  ",";
				concatString += rs.getString("a_key" ); concatString +=  ",";
				concatString += rs.getString("a_value" ); concatString +=  ",";
				concatString += rs.getString("a_message" ); concatString +=  ",";
				concatString += rs.getString("enc_seed" ); concatString += "####";
				
			}
			
				
			 	}
			 }
			 catch (SQLException e){    // if failed connection
			 	System.out.println("Got an exception");
			 	System.out.println(e.getMessage());
			 }
			 
			 return concatString;
		}
	
	public void printTable() {			// PRINTS the someBlockChain table
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException err) {
			System.out.println(err);
		}
	
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try{
			//my localhost:
			//con = DriverManager.getConnection("jdbc:mysql://localhost:3306/gproject","root","Willtheshiba12");
			con = DriverManager.getConnection("jdbc:mysql://csmysql.cs.cf.ac.uk/c1314249","c1314249","rodim8");
			if (con != null) {
				//System.out.println("Connected");
			}
			
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM someBlockChain");
			

			while (rs.next()){
				// some stuff
				int sellID = rs.getInt(1);
				int buyID = rs.getInt(2);
				int transAmt = rs.getInt(3);
				int levelDiff = rs.getInt(4);
				String prevHash = rs.getString(5);
				String aKey = rs.getString(6);
				String aValue = rs.getString(7);
				String pubKey = rs.getString(8);
				
				System.out.printf("%6d\t %6d\t %3d\t %3d\t %40s\t %50s\t %20s\t %1000s\t", sellID, buyID, transAmt, levelDiff, prevHash, aKey, aValue, pubKey);
				System.out.println();
				}
			
			System.out.println();
			
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null) rs.close();
				if (st != null) st.close();
				if (con != null) con.close();
			} catch (SQLException e){
				
			}
		}
	}
	
	public String getEncryptedMessage(String key){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException err) {
			System.out.println(err);
		}
	
		Connection con = null;
		String message = " ";
		try{
			con = DriverManager.getConnection("jdbc:mysql://csmysql.cs.cf.ac.uk/c1314249","c1314249","rodim8");
			if (con != null) {
				//System.out.println("Connected");
			}
			Statement stat = con.createStatement();
			String SQL = "SELECT a_message FROM someBlockChain WHERE `a_key` = '" + key + "'";
			ResultSet rs;
			rs = stat.executeQuery(SQL);
			while (rs.next()){
				message = rs.getString("a_message");
			}		
			if ( message == " "){
				System.out.println("System unable to get your request, make sure the key exists" );
			}
			con.close();
			return message;
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
		}
		return message;
	}
	
	public int getSeed(String key){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException err) {
			System.out.println(err);
		}
	
		Connection con = null;
		int seed = -1;
		try{
			con = DriverManager.getConnection("jdbc:mysql://csmysql.cs.cf.ac.uk/c1314249","c1314249","rodim8");
			if (con != null) {
				//System.out.println("Connected");
			}
			Statement stat = con.createStatement();
			String SQL = "SELECT enc_seed FROM someBlockChain WHERE `a_key` = '" + key + "'";
			ResultSet rs;
			rs = stat.executeQuery(SQL);
			while (rs.next()){
				seed = rs.getInt("enc_seed");
			}		
			if ( seed == -1){
				System.out.println("System unable to get your request, make sure the key exists" );
			}
			con.close();
			return seed;
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
		}
		return seed;
	}
	
	public void updateDomain(String key){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException err) {
			System.out.println(err);
		}
	
		Connection con = null;
		try{
			con = DriverManager.getConnection("jdbc:mysql://csmysql.cs.cf.ac.uk/c1314249","c1314249","rodim8");
			if (con != null) {
				//System.out.println("Connected");
			}
			Statement stat = con.createStatement();
			System.out.println("Enter the value: ");
			Scanner user_input = new Scanner( System.in );
			String value = user_input.next( );
			String SQL = "Update someBlockChain SET `a_value` = '" + value + "' WHERE `a_key` = '" + key + "'";
			stat.executeUpdate(SQL);
			con.close();
		}
		catch (SQLException e){
			System.out.println(e.getMessage());
		}
	}
	
			
}