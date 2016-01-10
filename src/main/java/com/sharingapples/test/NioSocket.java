package com.sharingapples.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by rpidanny on 1/10/16.
 */
public class NioSocket {
    private Map<SocketChannel,List> dataMapper;
    private Selector selector;

    public NioSocket(){

    }

    public static void main(String ... Args){
        Runnable server = new Runnable() {
            public void run() {
                new NioSocket().startServer();
            }
        };

        Runnable client = new Runnable() {
            public void run() {
                try {
                    new SocketClientExample().startClient();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        };
        new Thread(server).start();
        new Thread(client, "client-A").start();

    }

    public void startServer(){
        try {
            this.selector = Selector.open();
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);

            serverChannel.socket().bind(new InetSocketAddress(8080));
            serverChannel.register(this.selector, SelectionKey.OP_ACCEPT);
            System.out.println("Server Started!! at port 8080" );

            while (true){
                this.selector.select();

                Iterator keys = this.selector.selectedKeys().iterator();

                while (keys.hasNext()){
                    SelectionKey key = (SelectionKey)keys.next();
                    //System.out.println(key);
                    keys.remove();

                    if(!key.isAcceptable()){
                        System.out.println("Accepted");
                        this.accept(key);
                    }else if (key.isReadable()){
                        System.out.println("Readable");
                        this.read(key);
                    }else if (key.isWritable()){

                    }
                }
            }
        }catch (IOException e){
            System.out.print(e);
        }


    }

    public void accept(SelectionKey key)throws IOException{
        ServerSocketChannel serverChannel = (ServerSocketChannel)key.channel();
        java.nio.channels.SocketChannel channel = serverChannel.accept();
        channel.configureBlocking(false);
        Socket socket = channel.socket();
        SocketAddress remoteAddr = socket.getRemoteSocketAddress();
        System.out.println("Connected to "+remoteAddr);

        this.dataMapper.put(channel,new ArrayList());
        channel.register(selector,SelectionKey.OP_READ);
    }

    public void read(SelectionKey key)throws IOException{
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int numRead =-1;
        numRead = channel.read(buffer);
        System.out.print("Inside Read Function");
        if(numRead == -1){
            this.dataMapper.remove(channel);
            Socket socket = channel.socket();
            SocketAddress remoteAddr = socket.getRemoteSocketAddress();
            System.out.println("Connection Closed by Client "+remoteAddr);
            channel.close();
            return;

        }

        byte[] data =new byte[numRead];
        System.arraycopy(buffer.array(),0,data,0,numRead);
        System.out.println("Got : "+ new String(data));
    }
}
