import java.security.*;
import java.math.*;
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
		this.sellerID = sellerID;
		this.buyerID = buyerID;
		this.transactionAmount = transactionAmount;
		this.levelDifficulty = levelDifficulty;
		this.previousHash =previousHash;
		this.pointer = pointer;
		// test to make sure the output are the same
		// System.out.println("sellerID = "+sellerID+"buyerID = "+buyerID+" transactionAmount = " +transactionAmount+" levelDifficulty = "+levelDifficulty +" previousHash = "+previousHash );
	}

	public String getCurrentHash()throws Exception{
		try{
			System.out.println(getString());
			String s = getString();
			MessageDigest m=MessageDigest.getInstance("MD5");
			m.update(s.getBytes(),0,s.length());
			String hash = new BigInteger(1,m.digest()).toString(16);

			return hash;
		}catch(Exception e){ return null;}
	}
	public String getPreviousHash(){
		return previousHash;
	}
	// add the string to the previous hash to create the current hash and then compare
	// also see CM2305---Group-Project-Year-2/tutorials/testMD5.java
	// the time of the hash has been excluded atm to simplify the process
	public String getString(){
		String data = (previousHash +String.valueOf(sellerID) + String.valueOf(buyerID) + String.valueOf(transactionAmount));
		return data;
	}
	
	public Block getPointer(){
		return pointer;
	}


	
}
