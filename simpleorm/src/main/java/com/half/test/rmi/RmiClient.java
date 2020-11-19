package com.half.test.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

/**
 * @Author: wangwei
 * @Date: 2020-05-02 8:07
 */
public class RmiClient {

    public static void main(String[] args) {
        try {
            Person obj = (Person) Naming.lookup("rmi://localhost:9999/RemoteObj2");
            String str = obj.say("lissa");
            System.out.println(str);
        } catch (NotBoundException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
