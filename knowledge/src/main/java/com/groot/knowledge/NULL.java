package com.groot.knowledge;

public class NULL {

    /**
     * null可以转化为任意类型
     * @param args
     */
    public static void main(String[] args) {
        ((NULL)null).hh();
        int x=4;
        System.out.println("value is "+ ((x>4) ? 99.9 : 9));
        System.out.println("value is "+ ((x>4) ? 99.99 : 9));//double
        System.out.println("value is "+ 9);
    }

    public static void hh(){
        System.out.println(121);
    }

//    public void default() {
//
//    }
}
