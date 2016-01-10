package com.sharingapples.test;

import javafx.application.Platform;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.server.ExportException;

/**
 * Created by rpidanny on 1/10/16.
 */
public class SocketServerTest {

    static private ServerSocket serverSocket;
    public static void main(String ... Args){
        //serverSocket = new Ser

    }

    private class SocketServerThread extends Thread{
        static final int SocketServerPORT = 8080;
        int count =0;

        public void run(){
            try {
                Socket socket = null;
                serverSocket = new ServerSocket(SocketServerPORT);

                while (true){
                    socket = serverSocket.accept();
                    count++;
                    Thread acceptedThread = new Thread();
                }

            }catch (IOException e){
                System.out.println(e);
            }

        }
    }

    private class ServerSocketAcceptedThread extends Thread{
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


            }catch (IOException e){
                System.out.println(e);
            }
        }
    }
}
