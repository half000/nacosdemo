package com.half.test.serializable;

import java.io.*;

/**
 * @Author: wangwei
 * @Date: 2020-04-30 13:59
 */
public class TestSerializable {

    public static void main(String[] args) throws Exception {
        SubClass obj = new SubClass();
        obj.setName("Lisi");
        obj.setAge(18);
        obj.setShortName("ll");
        ObjectOutputStream objectOutputStream1 = new ObjectOutputStream(new FileOutputStream(new File("1.obj")));
        ObjectOutputStream objectOutputStream2 = new ObjectOutputStream(new FileOutputStream(new File("2.obj")));
        objectOutputStream1.writeObject(obj);
        objectOutputStream1.flush();
        obj.setShortName("dd");
        //二次写入
        objectOutputStream1.writeObject(obj);
        objectOutputStream1.flush();

        objectOutputStream2.writeObject(obj);
        objectOutputStream2.flush();
        ObjectInputStream objectInputStream1 = new ObjectInputStream(new FileInputStream("1.obj"));
        ObjectInputStream objectInputStream2 = new ObjectInputStream(new FileInputStream("2.obj"));
        Object obj1 = objectInputStream1.readObject();
        Object obj10 = objectInputStream1.readObject();
        Object obj2 = objectInputStream2.readObject();
        System.out.println(obj1);
        System.out.println(obj10);
        System.out.println(obj2);
        System.out.println(obj1 == obj10);
        System.out.println(obj1 == obj2);

    }
}
