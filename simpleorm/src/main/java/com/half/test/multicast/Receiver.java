package com.half.test.multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * @Author: wangwei
 * @Date: 2020-04-30 11:16
 */
public class Receiver {

    public static void main(String[] args) throws IOException {
        MulticastSocket ms = new MulticastSocket(8888);
        InetAddress inetAddress = InetAddress.getByName("224.0.0.1");
        ms.joinGroup(inetAddress);
        DatagramPacket datagramPacket = new DatagramPacket(new byte[1024], 1024);
        ms.receive(datagramPacket);
        System.out.println(new String(datagramPacket.getData()));
    }

}
