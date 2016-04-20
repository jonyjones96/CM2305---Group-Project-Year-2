import java.net.*;
import java.io.*;
import java.util.*;

public class CommunicationTest {
  public static void main(String[] args) {
    BlockChainServer.startListening(4567);
    BlockChainClient client = new BlockChainClient("10.0.0.8");
  }
}