package com.half.test.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * @Author: wangwei
 * @Date: 2020-05-02 8:09
 */
public interface PersonInter extends Remote {
    public String say(String name) throws RemoteException;
}
