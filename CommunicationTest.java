import java.net.*;
import java.io.*;
import java.util.*;

public class CommunicationTest {
  public static void main(String[] args) throws IOException {
    BlockChainServer.startListening();
    BlockChainClient client = new BlockChainClient("10.0.0.8");
  }
}