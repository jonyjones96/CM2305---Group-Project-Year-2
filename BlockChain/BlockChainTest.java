class BlockChainTest{
  public static void main(String[] args) throws Exception{
	  	try{BlockChain block = new BlockChain();

		  	// System.out.println(block.isEmpty ());
		 	userInput user = new userInput();
			String key = userInput.insertKey();
			String userValue = userInput.insertValue();
			System.out.println("Your key: "+ key);
			System.out.println("Your value: "+ userValue);
			int buyerID = 101;
			int sellerID = 122;
			int transactionAmount = key.length();
			int levelOfDifficulty = userValue.length();
		 	
		 	block.insert(buyerID,sellerID,transactionAmount,levelOfDifficulty,key,userValue);
			block.insert(121,122,1,5,"google.com","74.125.224.72");
			block.insert(123,124,8,4,"facebook.com","69.63.176.13");
			block.insert(125,126,2,4,"github.com","192.30.252.0");
			block.insert(127,128,5,5,"bbc.co.uk","212.58.246.94");

			block.print ();
	  	}
	  	catch(Exception e){e.printStackTrace();}
	}
}
