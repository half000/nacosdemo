package com.half.test.rmi;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * @Author: wangwei
 * @Date: 2020-05-02 8:08
 */
public class Person extends UnicastRemoteObject implements PersonInter, Serializable {

    public Person() throws RemoteException {

    }

    @Override
    public String say(String name) throws RemoteException {
        System.out.println("hello" + name);
        return "hello" + name;
    }
}
