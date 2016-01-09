package com.sharingapples.test;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

/**
 * Created by rpidanny on 1/8/16.
 */
public class SocketChannel {

  public static void main(String ... Args){
    try {
      Selector selector = Selector.open();
      ServerSocketChannel ssc = ServerSocketChannel.open();
      ssc.configureBlocking(false);

      ServerSocket ss = ssc.socket();
      InetSocketAddress address = new InetSocketAddress(8080);
      ss.bind(address);

      SelectionKey key = ssc.register(selector, SelectionKey.OP_ACCEPT);

      //continue from here
    }catch (Exception e){
      System.out.println(e);
    }
  }
}
