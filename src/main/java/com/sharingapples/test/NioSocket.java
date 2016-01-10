package com.sharingapples.test;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.nio.channels.SocketChannel;
import java.util.*;

/**
 * Created by rpidanny on 1/10/16.
 */
public class NioSocket {
    private Map<SocketChannel,List> dataMapper;
    private Selector selector;
    private boolean debugFlag;
    public NioSocket(boolean x){
        dataMapper = new HashMap<SocketChannel,List>();
        debugFlag = x;
    }

    public static void main(String ... Args){
        final NioSocket nio = new NioSocket(false);
        Runnable server = new Runnable() {
            public void run() {
                nio.startServer();
            }
        };

        new Thread(server).start();

        while (true){
            try {
                Thread.sleep(1000);
                nio.broadcast("Ping!!\n");
                System.out.println("User Count is : "+nio.getUserCount());
            }catch (Exception e){
                System.out.println("Error in delay of main thread!!");
            }
        }
    }
    public Map<SocketChannel,List> getDataMapper(){
        return dataMapper;
    }
    public void startServer(){
        try {
            this.selector = Selector.open();
            ServerSocketChannel serverChannel = ServerSocketChannel.open();
            serverChannel.configureBlocking(false);

            serverChannel.socket().bind(new InetSocketAddress(8080));
            serverChannel.register(this.selector, SelectionKey.OP_ACCEPT);
            System.out.println("Server Started!! at port 8080" );

            //Thread.sleep(10000);
            //System.out.println("After Delay");
            while (true){

                this.selector.select();

                Iterator keys = this.selector.selectedKeys().iterator();

                while (keys.hasNext()){
                    SelectionKey key = (SelectionKey)keys.next();
                    //System.out.println(key);
                    keys.remove();

                    if(key.isAcceptable()){
                        if(debugFlag)
                            System.out.println("Accepted");
                        this.accept(key);
                    }else if (key.isReadable()){
                        if (debugFlag)
                            System.out.println("Readable");
                        this.read(key);
                    }else if (key.isWritable()){

                    }
                }
            }
        }catch (IOException e){
            System.out.print(e);
        }catch (Exception e){
            System.out.print(e);
        }


    }

    public void accept(SelectionKey key){
        try {
            ServerSocketChannel serverChannel = (ServerSocketChannel)key.channel();
            java.nio.channels.SocketChannel channel = serverChannel.accept();
            channel.configureBlocking(false);
            Socket socket = channel.socket();
            SocketAddress remoteAddr = socket.getRemoteSocketAddress();
            if (debugFlag)
                System.out.println("Connected to "+remoteAddr);
            byte [] message = new String("Welcome").getBytes();
            ByteBuffer buffer = ByteBuffer.wrap(message);
            channel.write(buffer);
            this.dataMapper.put(channel,new ArrayList());
            channel.register(selector,SelectionKey.OP_READ);
        }catch (IOException e){
            System.out.println("Crashed here");
        }

    }

    public void read(SelectionKey key)throws IOException{
        SocketChannel channel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        int numRead =-1;
        numRead = channel.read(buffer);
        if (debugFlag)
            System.out.print("Inside Read Function");
        if(numRead == -1){
            this.dataMapper.remove(channel);
            Socket socket = channel.socket();
            SocketAddress remoteAddr = socket.getRemoteSocketAddress();
            if (debugFlag)
                System.out.println("Connection Closed by Client "+remoteAddr);
            channel.close();
            return;

        }

        byte[] data =new byte[numRead];
        System.arraycopy(buffer.array(),0,data,0,numRead);
        System.out.println("Got : "+ new String(data));
    }

    public void broadcast(String s){
        try {
            for(Map.Entry<SocketChannel, List> entry:this.dataMapper.entrySet()) {
                byte [] message = s.getBytes();
                ByteBuffer buffer = ByteBuffer.wrap(message);
                //System.out.println("Key : "+entry.getKey().getClass().getName());
                //System.out.println("Key : "+entry.getValue().getClass().getName());
                entry.getKey().write(buffer);
            }
        }catch (IOException e){
            System.out.println(e);
        }
    }

    public int getUserCount(){
        return dataMapper.size();
    }
}
