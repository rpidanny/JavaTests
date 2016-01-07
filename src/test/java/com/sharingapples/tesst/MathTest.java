package com.sharingapples.tesst;

/**
 * Created by rpidanny on 1/7/16.
 */

import com.sharingapples.test.Factorial;
import org.junit.Test;
import static org.junit.Assert.*;
public class MathTest {

  @Test
  public void factorialTest(){
    assertEquals(5040, Factorial.getFactorial(7));
  }
}
