
/* FOR MYSQL CONNECTIONS */
// import java.sql.Connection;		
// import java.sql.DriverManager;
// import java.sql.SQLException;
// import java.sql.Statement;
// import java.sql.PreparedStatement;

class BlockChain{
	private Block head = null;
	private String hashCurrentBlock = null;

	public BlockChain(){

	}
	public boolean isEmpty () {
    	if (head == null) return true;
    	else return false;
  	}

    public void insert(int sellerID,int buyerID,int transactionAmount,int levelDifficulty)throws Exception {
    	 try{
    		 head = new Block(sellerID,buyerID,transactionAmount,levelDifficulty,hashCurrentBlock,head);
    		 // test to check if the hashes are the same each time you run BlockChainTest.java
    		  hashCurrentBlock = head.getCurrentHash();

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
		    while(tmp != null)
		    {
		        try{
		          System.out.print("[" + hash+ "] -> ");
		          //copytoDB(hash);
		          tmp = tmp.getPointer();
		          hash = tmp.getCurrentHash(); 
		        }
		        catch(NullPointerException e){
		            System.out.print("Beginning");
		            System.out.println("");
		        }
		    }
  		}
  	catch(Exception e){e.printStackTrace();}
  }

/* FOR MYSQL CONNECTION */
  //   public void copytoDB(String some_hash) throws Exception {
		// try {
		// 	Class.forName("com.mysql.jdbc.Driver");
		// } catch (ClassNotFoundException err){
		// 	System.out.println(err);
		// }
		// Connection conn = null;
		// //Statement stmt = null;
		// try {
		// 	conn = DriverManager.getConnection("jdbc:mysql://csmysql.cs.cf.ac.uk/c1314249","c1314249", "rodim8" );
		// 	if (conn!= null){
		// 		//System.out.println("Connected");
		// 	//System.out.println("Inserting records...");
			
		// 	PreparedStatement pstmt = conn.prepareStatement("INSERT INTO blockChain " + "VALUES ('121.131.0.1', 'groupeleven.com', 'Group11 Ltd.', ?)");
		// 	pstmt.setString(1, some_hash);
		// 	pstmt.executeUpdate();
		// 	}
		// }
		// catch (SQLException e){
		// 	System.out.println("Got an exception");
		// 	System.out.println(e.getMessage());
		// }
  // 	}
}
