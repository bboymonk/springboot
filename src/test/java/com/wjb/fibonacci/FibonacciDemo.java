package com.wjb.fibonacci;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wjb on 2019/3/26.
 */
public class FibonacciDemo {

    public static void main(String[] args) {
        Date date =  new Date();
        long time1 = date.getTime();
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        System.out.println("当前时间是：" + sd.format(date));
        System.out.println(f3(100000));
        Date date2  = new Date();
        long time = date2.getTime();
        System.out.print("执行花了："+(time-time1)+"毫秒");

    }

    public static int f(int n) {
        if(n == 1 || n == 2) {
            return n;
        } else {
            return f(n - 1) +f(n - 2);
        }

    }


    public static long f2(int n){
        List<Integer> list = new ArrayList<Integer>();
        list.add(1);
        list.add(2);
        for(int i =2;i<n;i++){
            list.add(list.get(i-1)+list.get(i-2));
        }
        return list.get(n-1);
    }

    /* 速度最快的方法 */
    public static long f3(int n){
        Integer[] arr = new Integer[2];
        arr[0]=1;
        arr[1]=2;
        for (int i = 2; i< n; i++){
            arr[i%2] = arr[0] + arr[1];
        }
        return arr[(n-1)%2];
    }

}
