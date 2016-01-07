package com.sharingapples.test;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by rpidanny on 1/7/16.
 */
public class FileChannelTest {

  static public void main(String ... Args){

    URL resource = FileChannelTest.class.getResource("demo.txt");

    try {
      File file = new File(resource.toURI());
      FileInputStream is = new FileInputStream(file);
      FileChannel fc = is.getChannel();
      ByteBuffer buffer = ByteBuffer.allocate(100);
      fc.read(buffer);
      buffer.flip();
      while (buffer.hasRemaining()){
        System.out.print((char)buffer.get());
      }
    }catch (Exception e){
      System.out.println(e);
    }
  }
}
