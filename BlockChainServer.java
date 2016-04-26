import java.net.*;
import java.io.*;
import java.util.*;
import java.sql.*;

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
    }

    BlockChainServer.sendDatabase(this.connection);
  }

  public static void startListening() throws IOException {
    try {
      ServerSocket serverSocket = new ServerSocket(4567);
      
      while(true) {
        new BlockChainServer(serverSocket.accept());
      }
    }
    catch(IOException e)
    {}
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

  public static void sendDatabase(BlockChainConnection client) {
    String connectionString = null;
    try {
      BufferedReader in = new BufferedReader(new FileReader("localhost.txt"));
      connectionString = in.readLine();
    }
    catch(IOException e){
        System.out.println(e);
    } 

    String[] DB = connectionString.split(",");
    try {
      Class.forName("com.mysql.jdbc.Driver");
    }
    catch (ClassNotFoundException err) {
      System.out.println(err);
    }
  
    Connection con = null;
    int seed = -1;
    try{
      //con = DriverManager.getConnection("jdbc:mysql://csmysql.cs.cf.ac.uk/c1314249","c1314249","rodim8");
      con = DriverManager.getConnection(DB[0],DB[1],DB[2]);
      if (con != null) {
        //System.out.println("Connected");

          Statement stmt = con.createStatement();
          String sql = "SELECT * FROM someBlockChain";
          ResultSet rs = stmt.executeQuery(sql);
           
          while (rs.next()){
            String concatString = "";
            concatString += rs.getString("sellerID"); concatString +=  ",";
            concatString += rs.getString("buyerID" ); concatString +=  ",";
            concatString += rs.getString("transactionAmount" ); concatString +=  ",";
            concatString += rs.getString("levelDifficulty" ); concatString +=  ",";
            concatString += rs.getString("previousHash" ); concatString +=  ",";
            concatString += rs.getString("a_key" ); concatString +=  ",";
            concatString += rs.getString("a_value" ); concatString +=  ",";
            concatString += rs.getString("a_message" ); concatString +=  ",";
            concatString += rs.getString("enc_seed" );
            

            BlockChainServer.sendToClient(client, "BLOCK", concatString);  
          }
      }
    }
       catch (SQLException e){    // if failed connection
        System.out.println("Got an exception");
        System.out.println(e.getMessage());
       }
  }

  public static void sendUpdate(String key) {
    String connectionString = null;
    try {
      BufferedReader in = new BufferedReader(new FileReader("localhost.txt"));
      connectionString = in.readLine();
    }
    catch(IOException e){
        System.out.println(e);
    }
    String[] DB = connectionString.split(",");
    try {
      Class.forName("com.mysql.jdbc.Driver");
    }
    catch (ClassNotFoundException err) {
      System.out.println(err);
    }
  
    Connection con = null;
    int seed = -1;
    try{
      //con = DriverManager.getConnection("jdbc:mysql://csmysql.cs.cf.ac.uk/c1314249","c1314249","rodim8");
      con = DriverManager.getConnection(DB[0],DB[1],DB[2]);
      if (con != null) {
        //System.out.println("Connected");

          Statement stmt = con.createStatement();
          String sql = "SELECT * FROM someBlockChain WHERE `a_key` = '" + key + "'";
          ResultSet rs = stmt.executeQuery(sql);
           
          if (rs.next()){
            String concatString = "";
            concatString += rs.getString("sellerID"); concatString +=  ",";
            concatString += rs.getString("buyerID" ); concatString +=  ",";
            concatString += rs.getString("transactionAmount" ); concatString +=  ",";
            concatString += rs.getString("levelDifficulty" ); concatString +=  ",";
            concatString += rs.getString("previousHash" ); concatString +=  ",";
            concatString += rs.getString("a_key" ); concatString +=  ",";
            concatString += rs.getString("a_value" ); concatString +=  ",";
            concatString += rs.getString("a_message" ); concatString +=  ",";
            concatString += rs.getString("enc_seed" );
            

            BlockChainServer.sendToAll("BLOCK", concatString);  
          }
      }
    }
       catch (SQLException e){    // if failed connection
        System.out.println("Got an exception");
        System.out.println(e.getMessage());
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
