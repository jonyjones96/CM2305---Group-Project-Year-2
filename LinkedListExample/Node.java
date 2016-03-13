class Node{

	private int value;
	private Node pointer;

	public Node(int newValue,Node newPointer){
		value = newValue;
		pointer = newPointer;

	}

	public void setValue(int newValue){
		value = newValue;
	}

	public void setPointer(Node newPointer){
		pointer = newPointer;
	}


	public int getValue(){
		return value;
	}

	public Node getPointer(){
		return pointer;
	}


	
}
