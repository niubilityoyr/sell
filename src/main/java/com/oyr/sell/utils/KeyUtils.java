package com.oyr.sell.utils;

import java.util.Random;

/**
 * Create by 欧阳荣
 * 2018/3/13 14:39
 */
public class KeyUtils {

    public static void main(String[] args) {
        System.out.println(createKey());
    }

    public static String createKey(){
        Random random = new Random();
        Integer number= random.nextInt(900000) + 100000;
        return System.currentTimeMillis()+String.valueOf(number);
    }

}
