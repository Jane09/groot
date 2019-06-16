package com.groot.knowledge;

public class Demo {

    public Demo(){

    }

    public void Demo() {
        System.out.println("demo");
    }

    static class A {
        public A(){
            System.out.println("hello a");
        }
        {
            System.out.println("I'm a class");
        }

        static {
            System.out.println("static a");
        }
    }

    class AA {
        protected int m1(int a, int b){
            return 0;
        }
    }

    class AB extends AA {
//        private int m1(int a, int b) {
//            return 0;
//        }
        public int m1(int a, int b) {
            return 0;
        }

        private int m1(int a, float b) {
            return 0;
        }

//        public short m1(int a, int b) {
//            return 0;
//        }
    }
}
