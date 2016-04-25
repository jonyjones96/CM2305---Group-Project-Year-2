import java.net.*;
import java.io.*;
import java.util.*;

public class CommunicationTest {
  public static void main(String[] args) throws IOException {
  	new Thread()
    {
    public void run() {
    	try {
    		BlockChainServer.startListening();    	
    	}
    	catch(IOException e) {}
    }
    }.start();
    new Thread()
    {
    public void run() {
    	BlockChainClient client = new BlockChainClient("10.0.0.8");
    }
    }.start();
  }
}