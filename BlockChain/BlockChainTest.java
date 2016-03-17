class BlockChainTest{
  public static void main(String[] args) throws Exception{
	  	try{BlockChain block = new BlockChain();

		  	// System.out.println(block.isEmpty ());
		 
		  	block.insert(121,122,1,5);
		    block.insert(123,124,8,4);
		    block.insert(125,126,2,4);
		    block.insert(127,128,5,5);

		    block.print ();
	  	}
	  	catch(Exception e){e.printStackTrace();}
	}
}