package com.sharingapples.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


/**
 * Created by rpidanny on 1/10/16.
 */
public class SocketServerTest extends Thread {

    static private ServerSocket serverSocket;
    static final int SocketServerPORT = 8080;
    static int count =0;
    public SocketServerTest(){

    }
    public static void main(String ... Args){
        try {
            Socket socket = null;
            serverSocket = new ServerSocket(SocketServerPORT);

            while (true){
                socket = serverSocket.accept();
                count++;
                Thread acceptedThread = new Thread(new ServerSocketAcceptedThread(socket,count));
                acceptedThread.setDaemon(true);
                acceptedThread.start();
            }

        }catch (IOException e){
            System.out.println(e);
        }

    }


    private static class ServerSocketAcceptedThread extends Thread{
        Socket socket = null;
        DataInputStream dataInputStream= null;
        DataOutputStream dataOutputStream = null;
        int count;

        ServerSocketAcceptedThread(Socket s, int c){
            socket =s ;
            count = c;
        }

        @Override
        public void run(){
            try {
                dataInputStream = new DataInputStream(socket.getInputStream());
                dataOutputStream = new DataOutputStream(socket.getOutputStream());

                dataOutputStream.writeUTF("Welcome");
                //userCount++;
                System.out.print(socket.getInetAddress()+" Connected!");

                while (socket.isConnected()){

                }
                System.out.print(socket.getInetAddress()+" Disconnected!");



            }catch (IOException e){
                System.out.println(e);
            }finally {
                try {
                    socket.close();
                    System.out.println("Socket Closed");
                }catch (IOException e){

                }

            }
        }
    }
}
