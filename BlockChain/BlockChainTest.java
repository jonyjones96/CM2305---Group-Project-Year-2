class BlockChainTest{
  public static void main(String[] args) throws Exception{
	  	try{BlockChain block = new BlockChain();

		  	// System.out.println(block.isEmpty ());
		 
		  	block.insert(121,122,1,5,"google.com","74.125.224.72");
		    block.insert(123,124,8,4,"facebook.com","69.63.176.13");
		    block.insert(125,126,2,4,"github.com","192.30.252.0");
		    block.insert(127,128,5,5,"bbc.co.uk","212.58.246.94");

		    block.print ();
	  	}
	  	catch(Exception e){e.printStackTrace();}
	}
}