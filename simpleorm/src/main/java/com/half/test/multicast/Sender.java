package com.half.test.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.concurrent.TimeUnit;

/**
 * IPv4的本地广播地址为“255.255.255.255”，路由器不会转发此广播
 * 组播IP地址就是D类IP地址，即224.0.0.0至239.255.255.255之间的IP地址
 *
 * @Author: wangwei
 * @Date: 2020-04-30 9:43
 */
public class Sender {

    public static void main(String[] args) throws Exception {
        MulticastSocket ms = new MulticastSocket(8888);
        InetAddress inetAddress = InetAddress.getByName("224.0.0.1");
        ms.joinGroup(inetAddress);

        new Thread(() -> {
            DatagramPacket datagramPacket = new DatagramPacket(new byte[1024], 1024);
            try {
                ms.receive(datagramPacket);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(new String(datagramPacket.getData()));
        }).start();
        TimeUnit.SECONDS.sleep(2);
        String str = "sdsd";
        byte[] bytes = str.getBytes();
        ms.send(new DatagramPacket(bytes, bytes.length, inetAddress, 8888));
    }
}
