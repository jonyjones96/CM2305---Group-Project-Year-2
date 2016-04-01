import java.net.*;
import java.io.*;

public class BlockChainServer extends Thread {
  protected Socket clientSocket;

  public BlockChainServer(Socket clientSocket) {
    this.clientSocket = clientSocket;
    start();
  }

  public void run() {
    System.out.println("Connected!");

  }

  public static void startListening(int port) throws IOException {

    ServerSocket serverSocket = new ServerSocket(port);
    System.out.println("Listening on port " + port);

    while(true) {
      new BlockChainServer(serverSocket.accept());
    }
  }

  public static void main(String[] args) throws IOException {
    BlockChainServer.startListening(4567);
  }
}
