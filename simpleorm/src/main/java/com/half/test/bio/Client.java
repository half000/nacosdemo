package com.half.test.bio;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

/**
 * @Author: wangwei
 * @Date: 2020-04-30 8:56
 */
public class Client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket();
        SocketAddress socketAddress = new InetSocketAddress(12345);
        socket.connect(socketAddress);
        PrintWriter bw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

        bw.println("ddddde");


    }
}
