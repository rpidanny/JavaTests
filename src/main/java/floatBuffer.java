/**
 * Created by rpidanny on 1/7/16.
 */

import java.nio.*;

public class floatBuffer {
  public static void main(String ... Args){
    System.out.println("Hello from the other side");
    FloatBuffer buffer = FloatBuffer.allocate(10);

    for (int i=0; i<buffer.capacity();i++){
      float f= i;
      buffer.put(f);
    }

    System.out.println("Before Flip");

    while (buffer.hasRemaining()){
      float f = buffer.get();
      System.out.println(f);
    }

    buffer.flip();

    System.out.println("After Flip");

    while (buffer.hasRemaining()){
      float f = buffer.get();
      System.out.println(f);
    }
  }
}
