package com.half.test;

import org.openjdk.jol.info.ClassLayout;

/**
 * -XX:-UseCompressedOops   指针压缩，堆内存小于32G模式启动压缩
 * 对象头=8字节markword + 4字节指针（压缩前8字节） 数组长度4字节
 * 对象会8字节对齐
 * 开启压缩后，如果有4字节数据会先往对象头里补齐 最多补4字节
 * 不开启压缩，则会直接对齐，可能+8字节（4内部 4外部）
 *
 * @Author: wangwei
 * @Date: 2020-04-28 9:53
 */
public class ObjectTest {


    public static void main(String[] args) {
        A a = new A();
        System.out.println(ClassLayout.parseInstance(a).toPrintable());
        B b = new B();
        System.out.println(ClassLayout.parseInstance(b).toPrintable());
        C c = new C();
        System.out.println(ClassLayout.parseInstance(c).toPrintable());
        int[] aa = new int[0];
        System.out.println(ClassLayout.parseInstance(aa).toPrintable());

        int[] bb = {1};
        System.out.println(ClassLayout.parseInstance(bb).toPrintable());

        int[] cc = {1, 2, 3};
        System.out.println(ClassLayout.parseInstance(cc).toPrintable());
    }

    public static class A {
    }

    public static class B {
        private long s = 11L;
    }

    public static class C {
        //private int a=3;
        private long s = 8;
        private String c = "dd";
    }


}
