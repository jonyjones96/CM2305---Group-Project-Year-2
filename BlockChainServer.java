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
    if(!BlockChainClient.isConnected(this.connection.getIpAddress())) {
      BlockChainClient client = new BlockChainClient(connection.getIpAddress());
    }

    BlockChainServer.sendToAll("CLIENT", this.connection.getIpAddress());

    for(BlockChainConnection client : clients) {
      BlockChainServer.sendToClient(this.connection, "CLIENT", client.getIpAddress());
      System.out.print(client.getIpAddress());
    }
    System.out.println();
  }

  public static void startListening() throws IOException {
    new Thread()
    {
    public void run() {
        try {
          ServerSocket serverSocket = new ServerSocket(4567);
          System.out.println("Listening on port " + 4567);
          
          while(true) {
            new BlockChainServer(serverSocket.accept());
          }
        }
        catch(IOException e)
        {}
    }
    }.start();
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
    BlockChainServer.startListening();
  }
}
