import java.net.*;
import java.io.*;
import java.util.*;

public class BlockChainServer extends Thread {
  protected BlockChainConnection connection;

  protected static ArrayList<BlockChainConnection> clients = new ArrayList<BlockChainConnection>();

  public BlockChainServer(Socket clientSocket) {
    this.connection = new BlockChainConnection(clientSocket.getRemoteSocketAddress().toString().split("/")[1].split(":")[0], clientSocket.getPort(), clientSocket);
    clients.add(this.connection);
    start();
  }

  public void run() {
    System.out.println("Connected!");

    if(!BlockChainClient.isConnected(this.connection.getIpAddress())) {
      BlockChainClient client = new BlockChainClient(connection.getIpAddress());
    }

    BlockChainServer.sendToAll("CLIENT", this.connection.getIpAddress());

    for(BlockChainConnection client : clients) {
      BlockChainServer.sendToClient(this.connection, "CLIENT", client.getIpAddress());
    }
  }

  public static void startListening(int port) throws IOException {

    ServerSocket serverSocket = new ServerSocket(port);
    System.out.println("Listening on port " + port);

    while(true) {
      new BlockChainServer(serverSocket.accept());
    }
  }

  public static void sendToClient(BlockChainConnection client, String type, String message) {
    try {
      PrintWriter out = new PrintWriter(client.getClientSocket().getOutputStream(), true);
      out.println(type + ":" + message);
    }
    catch(IOException e) {
      System.out.println("Could not send to " + client.getIpAddress());
    }
  }

  public static void sendToAll(String type, String message) {
    for(BlockChainConnection client : clients) {
      BlockChainServer.sendToClient(client, type, message);
    }
  }

  public static void main(String[] args) throws IOException {
    BlockChainServer.startListening(4567);
  }
}
