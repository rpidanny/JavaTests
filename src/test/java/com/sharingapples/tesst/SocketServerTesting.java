package com.sharingapples.tesst;

import com.sharingapples.test.SocketServerTest;

/**
 * Created by rpidanny on 1/10/16.
 */
public class SocketServerTesting {
        public static void main(String ... Args){
            Thread socketServerThread = new Thread(new SocketServerTest());
            socketServerThread.setDaemon(true);
            socketServerThread.start();
    }

}
