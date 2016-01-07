package com.sharingapples.test;

import java.io.InputStream;

/**
 * Created by rpidanny on 1/7/16.
 */
public class ClassLoading {

  static public void main(String ... Args){
    InputStream f = ClassLoading.class.getResourceAsStream("demo.txt");
    // For files outside package
    // getClass().getClassLoader().getResourceAsStream("META-INF/MANIFEST.MF");
    try {
      System.out.println((char)f.read());
    }catch (Exception e){
      System.out.println(e);
    }

  }
}
