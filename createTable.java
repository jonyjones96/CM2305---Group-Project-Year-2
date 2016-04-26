import java.sql.*;
import java.util.Scanner;

public class createTable {
	
	public void duplicateBchain(String connectionString, boolean newCon) {  // CREATES COPY OF someBlockChain table to BlockChain table.
		String[] DB = connectionString.split(",");
		try {
		 	Class.forName("com.mysql.jdbc.Driver");      // driver used for mysql configuration in Java
		 } catch (ClassNotFoundException err){
		 	System.out.println(err);
		 }
		 
		 Connection conn = null;
		 
		 try {
		 	conn = DriverManager.getConnection(DB[0],DB[1],DB[2]); // connection configuration to the mysql database
		 	if (conn!= null){       // successful connection
		 	
			Statement stmt = conn.createStatement();
			String sql;
			if (newCon == true){
				sql = " CREATE TABLE dns " +
						"(sellerID INTEGER, " +
						"  buyerID INTEGER, " +
						"transactionAmt INTEGER, " +
						"levelDifficulty INTEGER, " +
						"previousHash VARCHAR(40), " +
						"a_key VARCHAR(50), " +
						"a_value VARCHAR(18), " +
						"a_message VARCHAR(1000), " +
						"enc_seed INTEGER)";
						
				stmt.executeUpdate(sql);
				sql = "INSERT INTO dns SELECT * FROM someBlockChain;";
			}
			else{
				sql = "INSERT INTO dns (SELECT * FROM someBlockChain WHERE someBlockChain.a_key NOT IN (SELECT dns.a_key FROM dns));";
			}
			int count = stmt.executeUpdate(sql);
			
			if (count > 0){
				System.out.println(count + " record(s) successfully updated to BlockChain.");
			} else {
				System.out.println("BlockChain is up to date.");
			}
		 }}
		 catch (SQLException e){    // if failed connection
		 	//System.out.println("Got an exception");
		 	//System.out.println(e.getMessage());
		 } 
}

	public void newTable(String connectionString, String blockChainString) {  // CREATES NEW TABLE CALLED BLOCKCHAIN
		String[] DB = connectionString.split(",");
		String[] bc = blockChainString.split(",");
		
		 try {
			 	Class.forName("com.mysql.jdbc.Driver");      // driver used for mysql configuration in Java
			 } catch (ClassNotFoundException err){
			 	System.out.println(err);
			 }
			 
			 Connection conn = null;
			 
			 try {
			 	conn = DriverManager.getConnection(DB[0],DB[1],DB[2]); // connection configuration to the mysql database
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
							"a_message VARCHAR(1000), " +
							"enc_seed INTEGER)";
							
				
				stmt.executeUpdate(sql);
				
				sql = "INSERT INTO someBlockChain VALUES ('" + Integer.parseInt(bc[0]) +"','"+ 
															  Integer.parseInt(bc[1]) +"','"+ 
															  Integer.parseInt(bc[2]) +"','"+ 
															  Integer.parseInt(bc[3]) +"','"+ 
															  bc[4] +"','"+ 
															  bc[5] +"','"+ 
															  bc[6] +"','"+
															  bc[7] +"','"+
															  Integer.parseInt(bc[8]) +"')";
				stmt.executeUpdate(sql);
			 	}
			 }
			 catch (SQLException e){
			 	//System.out.println("Got an exception");
			 	//System.out.println(e.getMessage());
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
				concatString += rs.getString("publicKey" ); concatString += "####";
				
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
	
	public String getEncryptedMessage(String key,String connectionString){
		String[] DB = connectionString.split(",");
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException err) {
			System.out.println(err);
		}
	
		Connection con = null;
		String message = " ";
		try{
			//con = DriverManager.getConnection("jdbc:mysql://csmysql.cs.cf.ac.uk/c1314249","c1314249","rodim8");
			con = DriverManager.getConnection(DB[0],DB[1],DB[2]);
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
	
	public int getSeed(String key,String connectionString){
		String[] DB = connectionString.split(",");
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException err) {
			System.out.println(err);
		}
	
		Connection con = null;
		int seed = -1;
		try{
			//con = DriverManager.getConnection("jdbc:mysql://csmysql.cs.cf.ac.uk/c1314249","c1314249","rodim8");
			con = DriverManager.getConnection(DB[0],DB[1],DB[2]);
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
	
	public void updateDomain(String key,String connectionString){
		String[] DB = connectionString.split(",");
		try {
			Class.forName("com.mysql.jdbc.Driver");
		}
		catch (ClassNotFoundException err) {
			System.out.println(err);
		}
	
		Connection con = null;
		try{
			//con = DriverManager.getConnection("jdbc:mysql://csmysql.cs.cf.ac.uk/c1314249","c1314249","rodim8");
			con = DriverManager.getConnection(DB[0],DB[1],DB[2]);
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

	public String newCon() {
	   // JDBC driver name and database URL
	   String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	   String DB_URL = "jdbc:mysql://localhost:3306/";

	   //  Database credentials
	   Scanner user_input = new Scanner( System.in );
	   System.out.println("Input localhost Username");
	   String USER = user_input.next( );
	   System.out.println("Input localhost password");
	   String PASS = user_input.next( );
	   
	  Connection conn = null;
	  Statement stmt = null;
	   try{
	      //STEP 2: Register JDBC driver
	      Class.forName("com.mysql.jdbc.Driver");

	      //STEP 3: Open a connection
	      //System.out.println("Connecting to database...");
	      conn = DriverManager.getConnection(DB_URL+"?useSSL=false", USER, PASS);

	      //STEP 4: Execute a query
	      //System.out.println("Creating database...");
	      stmt = conn.createStatement();
	      
	      String sql = "CREATE DATABASE gprojectDns";
	      stmt.executeUpdate(sql);
	      //System.out.println("Database created successfully...");
	   }catch(SQLException se){
	      //Handle errors for JDBC
	      //se.printStackTrace();
	   }catch(Exception e){
	      //Handle errors for Class.forName
	      //e.printStackTrace();
	   }
	   String conString = "jdbc:mysql://localhost:3306/gprojectDns?useSSL=false,"+USER+","+PASS;
	   return conString;
	}
}
