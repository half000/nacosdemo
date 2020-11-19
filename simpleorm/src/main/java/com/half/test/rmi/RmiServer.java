package com.half.test.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * @Author: wangwei
 * @Date: 2020-05-02 7:52
 */
public class RmiServer {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(9999);
            PersonInter person = new Person();
            Naming.bind("rmi://localhost:9999/RemoteObj2", person);

            System.out.println(">>>>>INFO:远程对象绑定成功！");
//            try {
//                TimeUnit.SECONDS.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }


        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (AlreadyBoundException e) {
            e.printStackTrace();
        }

    }
}
