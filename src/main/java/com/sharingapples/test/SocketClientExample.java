package com.sharingapples.test;

/**
 * Created by rpidanny on 1/10/16.
 */
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;

public class SocketClientExample {
    private static ArrayList<Integer> portList = new ArrayList<Integer>();
    private int port;
    //public SocketClientExample(int port){
    //    this.port = port;
    //}
    public SocketClientExample(){
        this.port=2000;
    }
    public void startClient() {
        InetSocketAddress hostAddress = new InetSocketAddress("localhost", 8080);

        try {
            SocketChannel client = SocketChannel.open();
            do {
                port = (int)System.nanoTime()%10000;
            }while (port<4000);
            if (portList.contains(port)){
                portList.add(port);
            }
            //this.port+=1;
            System.out.println(port);
            client.bind(new InetSocketAddress("localhost",port++));
            client.connect(new InetSocketAddress("localhost",8080));


            //System.out.println("Client... started");

            String threadName = Thread.currentThread().getName();

            // Send messages to server
            String [] messages = new String []
                    {threadName + ": test1",threadName + ": test2",threadName + ": test3"};

            /*for (int i = 0; i < messages.length; i++) {
                byte [] message = new String(messages [i]).getBytes();
                ByteBuffer buffer = ByteBuffer.wrap(message);
                client.write(buffer);
                //System.out.println(messages [i]);
                buffer.clear();
                Thread.sleep(5000);
            }*/
            while (client.isConnected()){

            }
            client.close();
        }catch (IOException e){

        }catch (Exception e){

        }

    }
}