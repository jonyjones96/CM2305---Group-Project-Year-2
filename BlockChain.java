import java.sql.Connection;		
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

class BlockChain{
	private Block head = null;
	private String hashCurrentBlock = null;

	public BlockChain(){

	}
	public boolean isEmpty () {
    	if (head == null) return true;
    	else return false;
  	}
  	
  	public boolean check_toinsert (String key, String value) throws Exception { // checks for existing records in the block chain
		/* Not on full working mode. 
			* So far, it throws an error if key_toinsert (with same value) comes subsequently 
			* example: 	insert("bbc.co.uk");
						insert("bbc.co.uk");    --- SUCCESS: Record wont be inserted ---
						
					However, 
						insert("bbc.co.uk");
						insert("github.com");
						insert("bbc.co.uk");	--- FAIL: Record insertion will be successful --- 
		*/
		boolean aMatch = false;
		while ( head != null ) {
			if (key.equals(head.getKey())) {    // if there is a match return true ( hence true for an invalidinsert -- see insert() )
				aMatch =  true;					// iteration/comparing stops on an instance of a match
				break;
			} else {							// return false otherwise
				aMatch =  false;
			}
			head = head.getPointer();
		}
		return aMatch;		
	}

    public void insert(int sellerID,int buyerID,int transactionAmount,int levelDifficulty,String key,String value,String publicKey)throws Exception {
    	 try{
    	 	boolean invalidInsert = check_toinsert(key, value);  // start validation 
    		if (invalidInsert == false){   // if there's no match, insert desire record.
    		 head = new Block(sellerID,buyerID,transactionAmount,levelDifficulty,hashCurrentBlock,key,value,publicKey,head);
    		 // test to check if the hashes are the same each time you run BlockChainTest.java
    		  hashCurrentBlock = head.getCurrentHash();
    		   	int a_sellID = head.getSellerID();
  	    		int a_buyID = head.getBuyerID();
  	    		int a_transaction = head.getAmount();
  	    		int a_level = head.getLevel();
  	    		String some_key = head.getKey();
  	    		String some_value = head.getValue();
				String some_publicKey = head.getPublicKey();
				System.out.println("Record insertion successful!");
				// Need to add the publicKey
  	    		copytoDB(a_sellID, a_buyID, a_transaction, a_level, hashCurrentBlock, some_key, some_value, some_publicKey); // store to database
			} else {
    			 
    			 System.out.println("This key is invalid. Record already exists.");
    		 }
    		  // System.out.println("New Block hash = " + hashCurrentBlock);
 		 }catch(Exception e){e.printStackTrace();}
 	}

 	public boolean has (String hash)throws Exception {
 		try{
	    	Block tmp = head;

		    while(tmp != null){
		      String value = tmp.getCurrentHash();
		      if (hash == value)return true;
		      tmp = tmp.getPointer();
		    } 
		    return false;
		}catch(Exception e){e.printStackTrace();return false;}
  	}

  	public void print ()throws Exception {
  		try{
	    	Block tmp = head;
		    String hash = head.getCurrentHash();
		    String value = head.getString();
		    while(tmp != null)
		    {
		        try{
		          System.out.print("[" + hash+ " ] -> ");
		          //copytoDB(hash);
		          tmp = tmp.getPointer();
		          hash = tmp.getCurrentHash(); 
		          value = tmp.getString();
		        }
		        catch(NullPointerException e){
		            System.out.print("Beginning");
		            System.out.println("");
		        }
		    }
  		}
  	catch(Exception e){e.printStackTrace();}
  }

/* MYSQL CONNECTION */
  public void copytoDB(int sell_ID, int buy_ID, int trans_amount, int level_difficulty, String previous_hash, String a_key, String a_value, String publicKey) throws Exception {
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
		 	//System.out.println("Inserting records...");
			
		 	// insert values to table called someBlockChain:
		 	PreparedStatement pstmt = conn.prepareStatement("INSERT INTO someBlockChain " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
		 	pstmt.setInt(1, sell_ID);
		 	pstmt.setInt(2, buy_ID);
		 	pstmt.setInt(3, trans_amount);
		 	pstmt.setInt(4, level_difficulty);
		 	pstmt.setString(5, previous_hash);
		 	pstmt.setString(6, a_key);
		 	pstmt.setString(7, a_value);
		 	pstmt.setString(8, publicKey);
		 	pstmt.executeUpdate();
		 	}
		 }
		 catch (SQLException e){    // if failed connection
		 	System.out.println("Got an exception");
		 	System.out.println(e.getMessage());
		 }
   	}
}
