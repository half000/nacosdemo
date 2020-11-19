package com.half.test.zk.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.retry.BoundedExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

/**
 * @Author: wangwei
 * @Date: 2020-05-16 14:44
 */
public class TestCurator {
    private final static String connectString = "127.0.0.1:2180";
    private static CuratorFrameworkFactory.Builder builder;

    public static void main(String[] args) {
        RetryPolicy retryPolicy = new BoundedExponentialBackoffRetry(1000, 10000, 10);
        builder = CuratorFrameworkFactory.builder()
                .connectionTimeoutMs(5000)
                //会话超时  临时节点 注册的监控都会失效
                .sessionTimeoutMs(10000)
                //所有的操作都在这个路径下
                .namespace("TestCurator")
                .retryPolicy(retryPolicy)
                .connectString(connectString);
        CuratorFramework curatorFramework = builder
                .build();
        try {
            curatorFramework.start();
            curatorFramework.createContainers("curator");
            curatorFramework.create().creatingParentContainersIfNeeded().forPath("/curator/wangwei1", "niubi".getBytes());
            curatorFramework.setData().compressed().inBackground((a, curatorEvent) ->
                    System.out.println(String.format("eventType:%s,resultCode:%s", curatorEvent.getType(), curatorEvent.getResultCode()
                    ))).forPath("/curator/wangwei1", "niubiya".getBytes());
            byte[] rtn = curatorFramework.getData().decompressed().forPath("/curator/wangwei1");
            System.out.println(new String(rtn));
            curatorFramework.delete().forPath("/curator/wangwei1");
            //事务不支持递归创建
            curatorFramework.inTransaction().create().withMode(CreateMode.PERSISTENT).forPath("/tx/1").and().setData().forPath("/tx/1", "dd1".getBytes()).and().commit();
            System.out.println(curatorFramework.getData().forPath("/tx"));
            // curatorFramework.delete().forPath("/tx");
            //curatorFramework.transactionOp().create().forPath("/dd");
            curatorFramework.checkExists().usingWatcher((CuratorWatcher) (a) -> System.out.println(a));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
