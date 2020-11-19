package com.half.test.zk.zkclient;

import com.github.zkclient.IZkChildListener;
import com.github.zkclient.IZkDataListener;
import com.github.zkclient.IZkStateListener;
import com.github.zkclient.ZkClient;
import org.apache.zookeeper.Watcher;

import java.util.List;

/**
 * @Author: wangwei
 * @Date: 2020-05-04 11:45
 */
public class TestZkClientMain {
    //private final static String connectString="127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183";
    private final static String connectString = "127.0.0.1:2180";

    private static byte[] data = "test".getBytes();
    private static byte[] data1 = "test111".getBytes();

    public static void main(String[] args) {
        ZkClient zkClient = new ZkClient(connectString);
        String path = "/zktest2";
        String path1 = "/zktest2/node11";
        zkClient.subscribeDataChanges(path, new IZkDataListener() {

            @Override
            public void handleDataChange(String dataPath, byte[] data) throws Exception {
                System.out.println(dataPath + " handleDataChange  " + new String(data));
            }

            @Override
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println(dataPath + "  handleDataDeleted " + new String(data));
            }
        });

        zkClient.subscribeStateChanges(new IZkStateListener() {
            @Override
            public void handleStateChanged(Watcher.Event.KeeperState state) throws Exception {
                System.out.println(" handleStateChanged  " + state);
            }

            @Override
            public void handleNewSession() throws Exception {
                System.out.println(" handleNewSession  ");

            }
        });

        zkClient.subscribeChildChanges(path, new IZkChildListener() {
            @Override
            public void handleChildChange(String parentPath, List<String> currentChildren) throws Exception {
                System.out.println(parentPath + " handleChildChange  " + currentChildren);
            }
        });
        zkClient.createPersistent(path1, true);
        zkClient.writeData(path, data);
        zkClient.writeData(path, data1);
        //循环删除
        zkClient.deleteRecursive(path);
    }

}
