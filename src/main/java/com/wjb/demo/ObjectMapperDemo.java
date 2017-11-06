package com.wjb.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2017/11/4.
 */
public class ObjectMapperDemo {
    public static void main(String[] args)throws Exception {
        String c;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do {
            c = br.readLine();
            System.out.println(c);
        }while (!c.equals("a"));

    }
}
