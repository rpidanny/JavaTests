package com.sharingapples.test;

/**
 * Created by rpidanny on 1/10/16.
 */
public class RandomNumberGenerator {

    public int generate(){
        return (int)System.nanoTime()%10000;
    }
    public static void main(String ... Args){
        for (int i=0;i<100;i++){
            System.out.println((int)System.nanoTime()%10000);

        }
    }
}
