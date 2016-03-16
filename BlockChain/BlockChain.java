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
    		 // hashCurrentBlock = head.getCurrentHash();
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
		          tmp = tmp.getPointer();
		          hash = tmp.getCurrentHash(); 
		        }
		        catch(NullPointerException e){
		            System.out.print("End");
		            System.out.println("");
		        }
		    }
  		}
  	catch(Exception e){e.printStackTrace();}
  }
}
