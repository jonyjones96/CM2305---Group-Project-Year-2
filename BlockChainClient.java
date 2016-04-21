import java.net.*;
import java.io.*;
import java.util.*;

public class BlockChainClient extends Thread {
  private BlockChainConnection connection;
  protected static ArrayList<BlockChainConnection> servers = new ArrayList<BlockChainConnection>();

  public BlockChainClient(String ipAddress) {
    try {
      Socket clientSocket = new Socket(ipAddress, 4567);

      System.out.println("Connected to " + ipAddress + ":4567");

      connection = new BlockChainConnection(ipAddress, 4567, clientSocket);
      servers.add(connection);
      start();
    }
    catch(Exception e) {
      System.out.println("Couldn't connect to " + ipAddress);
    }
  }

  public void run() {
    try {
      PrintWriter out = new PrintWriter(connection.getClientSocket().getOutputStream(), true);
      BufferedReader in = new BufferedReader(new InputStreamReader(connection.getClientSocket().getInputStream()));

      String inLine;
      while((inLine = in.readLine()) != null) {
        String messageType = inLine.split(":")[0];
        String messageContent = inLine.split(":")[1];

        switch(messageType) {
          case "CLIENT":
          if(!BlockChainClient.isConnected(messageContent)) {
            BlockChainClient client = new BlockChainClient(messageContent);
          }
          break;
        }
      }
    }
    catch(IOException e) {
      System.out.println("Couldn't connect to " + connection.getIpAddress());
    }
  }

  public static boolean isConnected(String ipAddress) {
    for(BlockChainConnection server : servers) {
      if(ipAddress.equals(server.getIpAddress())) return true;
    }
    return false;
  }

  public static void main(String[] args) throws IOException {
    BlockChainClient client = new BlockChainClient("127.0.0.1");
  }
}
