package com.groot.knowledge;

import java.util.concurrent.Callable;

public class ThreadDemo {

    public static void main(String[] args) {
//        Thread t = new Thread(new Runnable() {
//            public void run() {
//                pong();
//            }
//        });
//        t.run();//先运行pong()，如果Thread的构造类用Runnable，那么Runnable的run方法会运行
//        t.start();//调用native方法执行
//        System.out.println("ping");

        Thread tt = new Thread(new Task());
        tt.run();//串行运行，直接运行Runnable的run方法
        System.out.println("ping");

        //ArrayList list = new ArrayList(20);中的 list 扩充几次 0
        System.out.println(Integer.MAX_VALUE+1);
    }

    static class Task implements Runnable {

        public void run() {
            pong();
        }
    }


    static void pong() {
        System.out.println("pong");
    }

    public abstract interface A {

    }
}
