import java.net.*;
import java.io.*;

public class BlockChainClient {
  public BlockChainClient(String ipAddress) throws IOException {
    Socket clientSocket = new Socket(ipAddress, 4567);

    System.out.println("Connected to " + ipAddress + ":4567");

    PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
  }

  public static void main(String[] args) throws IOException {
    BlockChainClient client = new BlockChainClient("127.0.0.1");

    while(true) {
      // Keep connection alive
    }
  }
}
