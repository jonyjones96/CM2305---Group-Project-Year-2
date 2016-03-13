class LinkedList {

 private Node head = null;

  public LinkedList () {
    
  }

  public boolean isEmpty () {
    if (head == null) return true;
    else return false;
  }

  public void insert (int d) {

    head = new Node(d,head);
 
  }

  public int delete () {
    if(head == null) throw new RuntimeException("cannot delete");
    
      int tmpValue = head.getValue();
      head = head.getPointer();
      return tmpValue;
  }

  public boolean has (int d) {
    Node tmp = head;

    while(tmp != null){
      int value = tmp.getValue();
      if (d == value)return true;
      tmp = tmp.getPointer();
    } 
    return false;
  }

  public void print () {
    Node tmp = head;
    int value = head.getValue();
    while(tmp != null)
      {
        try{
          System.out.print("[" + value+ "] -> ");
          tmp = tmp.getPointer();
          value = tmp.getValue(); 
        }
        catch(NullPointerException e){
            System.out.print("End");
            System.out.println("");
        }


      }
    
  }

}
