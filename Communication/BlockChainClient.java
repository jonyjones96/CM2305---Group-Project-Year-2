import java.net.*;
import java.io.*;
import java.util.*;

public class BlockChainClient {
  private BlockChainConnection connection;
  protected static ArrayList<BlockChainConnection> servers = new ArrayList<BlockChainConnection>();

  public BlockChainClient(String ipAddress) {
    try {
      Socket clientSocket = new Socket(ipAddress, 4567);

      System.out.println("Connected to " + ipAddress + ":4567");

      connection = new BlockChainConnection(ipAddress, 4567, clientSocket);
      servers.add(connection);

      PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
      BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }
    catch(IOException e) {
      System.out.println("Couldn't connect to " + ipAddress);
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

    while(true) {
      // Keep connection alive
    }
  }
}
