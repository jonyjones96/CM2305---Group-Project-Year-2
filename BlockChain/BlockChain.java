class BlockChain{
	private Block head = null;

	public BlockChain(){

	}
	public boolean isEmpty () {
    	if (head == null) return true;
    	else return false;
  	}

    public void insert (int sellerID,int buyerID,int transactionAmount,int levelDifficulty,String previousHash) {
    	head = new Block(sellerID,buyerID,transactionAmount,levelDifficulty,previousHash,head);
 	}

 	public boolean has (String hash)throws Exception {
    	Block tmp = head;

	    while(tmp != null){
	      String value = tmp.getCurrentHash();
	      if (hash == value)return true;
	      tmp = tmp.getPointer();
	    } 
	    return false;
  	}

  	public void print ()throws Exception {
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

}
