import java.net.*;

public class BlockChainConnection {
  private String ipAddress;
  private int port;
  private Socket clientSocket;

  public BlockChainConnection(String ipAddress, int port, Socket clientSocket) {
    this.ipAddress = ipAddress;
    this.port = port;
    this.clientSocket = clientSocket;
  }

  public String getIpAddress() {
    return this.ipAddress;
  }

  public int getPort() {
    return this.port;
  }

  public Socket getClientSocket() {
    return this.clientSocket;
  }
}
