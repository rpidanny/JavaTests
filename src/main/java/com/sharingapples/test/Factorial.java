package com.sharingapples.test;

/**
 * Created by rpidanny on 1/7/16.
 */
public class Factorial {
  public static int getFactorial(int x){
    int temp=1;
    for(int i=1;i<=x;i++){
      temp*=i;
    }
    return temp;
  }
}
