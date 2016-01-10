package com.sharingapples.test;

import javax.swing.text.html.HTMLDocument;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by rpidanny on 1/8/16.
 */
public class SocketChannel {

  public static void main(String ... Args){
    try {
      Selector selector = Selector.open();
      ServerSocketChannel ssc1 = ServerSocketChannel.open();
      ssc1.configureBlocking(false);

      ServerSocket ss = ssc1.socket();
      InetSocketAddress address = new InetSocketAddress(8080);
      ss.bind(address);

     // SelectionKey key = ssc.register(selector, SelectionKey.OP_ACCEPT);

      int num = selector.select();
      Set selectedKeys = selector.selectedKeys();
      Iterator it = selectedKeys.iterator();

      while (it.hasNext()){
        SelectionKey key = (SelectionKey)it.next();
        if((key.readyOps() & SelectionKey.OP_ACCEPT)== SelectionKey.OP_ACCEPT){
        ServerSocketChannel ssc = (ServerSocketChannel)key.cancel();
          SocketChannel sc = ssc.accept();
          sc.con
        }
      }
    }catch (Exception e){
      System.out.println(e);
    }
  }
}
