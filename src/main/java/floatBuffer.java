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

    //doesn't print anything as the position of the pointer is at the end of the buffer

    while (buffer.hasRemaining()){
      float f = buffer.get();
      System.out.println(f);
    }

    buffer.flip();
    //flip is used to reset the position to 0
    //it is used to flip buffer from writing to reading

    System.out.println("After Flip");

    while (buffer.hasRemaining()){
      float f = buffer.get();
      System.out.println(f);
    }
  }
}
