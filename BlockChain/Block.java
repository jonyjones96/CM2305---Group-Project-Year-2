class Block{

	 
	private int sellerID;
	private int buyerID;
	private int transactionAmount;
	// In the real world the level of difficulty is determined by equation solved by a computer 
	// If someone tried to forge a fake block and place a fork in the chain 
	// the level difficulty of the two blocks could be compared  and the highest would be declared the real block.(also know as longest chain)
	// In future we would like to add this feature but for now just stick random number in each one.
	private int levelDifficulty;
	private String previousHash;
	private String currentHash;
	private Block pointer;

	public Block(int sellerID,int buyerID,int transactionAmount,int levelDifficulty,String previousHash,Block pointer){
		value = newValue;
		pointer = newPointer;

	}

	public String getCurrentHash(){
		return value;
	}

	public String getPreviousHash(){
		return value;
	}

	public String getString(){
		return 
	}
	
	public Block getPointer(){
		return pointer;
	}


	
}
