package com.msb;

public class MyTest {


    public static void main(String[] args) {
        System.out.println(-1L ^ (-1L << 5));

        System.out.println( (1L << 5));
      int number = -1;
      //原始数二进制
      printInfo(number);
      number = number << 1;
      System.out.println(number);
      //左移一位
      printInfo(number);
      number = number >> 1;
      //右移一位
      printInfo(number);
    }

    /**
     * 输出一个int的二进制数
     * @param num
     */
    private static void printInfo(int num){
      System.out.println(Integer.toBinaryString(num));
    }

}