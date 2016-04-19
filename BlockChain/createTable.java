import java.sql.*;
import java.sql.Statement;

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
		 	
			Statement stmt = conn.createStatement();
			String sql = "INSERT INTO BlockChain SELECT * FROM someBlockChain"; 
			stmt.executeUpdate(sql);
			
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
	
	public static void main(String[] args) throws Exception {  // CONCATENATES FIELDS INTO A STRING
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
			
			String concatString = "";
			
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
			
			System.out.println("The string is... " + concatString);
				
			 	}
			 }
			 catch (SQLException e){    // if failed connection
			 	System.out.println("Got an exception");
			 	System.out.println(e.getMessage());
			 }
			 
			 
			 }
	   	}
