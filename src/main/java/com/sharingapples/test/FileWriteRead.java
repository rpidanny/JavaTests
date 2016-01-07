package com.sharingapples.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by rpidanny on 1/7/16.
 */
public class FileWriteRead {

  static public void main(String ... Args){
    String message="file test";

    URL resource = FileChannelTest.class.getResource("demo1.txt");

    try {
      File file = new File(resource.toURI());
      FileInputStream is = new FileInputStream(file);
      FileOutputStream os = new FileOutputStream(file);
      FileChannel ifc = is.getChannel();
      FileChannel ofc = os.getChannel();
      ByteBuffer buffer = ByteBuffer.allocate(100);

      for (int i=0;i<message.length();i++){
        buffer.put(message[i]);
      }
      buffer.put((byte)12);
      buffer.flip();
      fc.write(buffer);
    }catch (Exception e){
      System.out.println(e);
    }
  }
}
