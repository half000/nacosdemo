package com.half.test.zk.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Author: wangwei
 * @Date: 2020-05-03 22:28
 */
public class TestMain {

    //private final static String connectString="127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183";
    private final static String connectString = "127.0.0.1:2180";
    private static ZooKeeper zk;
    private static byte[] data = "test".getBytes();
    private static byte[] data1 = "test111".getBytes();
    private static volatile CountDownLatch countDownLatch = new CountDownLatch(1);

    public static void main(String[] args) {
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String path = "/zktest1";
        zk.exists(path, (e) -> System.out.println("*******111**********" + e.toString()), (a, b, c, d) -> {
            System.out.println(a + b + c + d);
        }, "context");

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void test1(String path) throws Exception {

        if (null == zk.exists(path, (e) -> System.out.println("*******111**********" + e.toString()))) {
            System.out.println(zk.create(path, data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT));
        }

        System.out.println(zk.getData(path, (e) -> System.out.println("*******222**********" + e), null));

        System.out.println(zk.setData(path, data1, -1));
        System.out.println(zk.getData(path, (e) -> System.out.println("*******333**********" + e), null));
        //子节点
        System.out.println("1子节点:" + zk.getChildren(path, true));
        zk.create(path + "/node1", data, ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
        List<String> list = zk.getChildren(path, true);
        System.out.println("2子节点:" + list.toString());
        list.forEach((node) -> {
            try {
                zk.delete(path + "/" + node, -1);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } catch (KeeperException ex) {
                ex.printStackTrace();
            }
        });

        zk.delete(path, -1);
    }

    static {
        try {
            zk = new ZooKeeper(connectString, 5000, (e) -> {
                if (e.getState() == Watcher.Event.KeeperState.SyncConnected) {
                    countDownLatch.countDown();
                    System.out.println("*******000**********" + e.toString());
                }
                System.out.println("*******0001111111**********" + e.toString());
            });
            //Frames not available for unsuspended thread
            //貌似lambda死锁了  上面的lambda需要等待主类TestMain初始化完成
            /*try {
                countDownLatch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
