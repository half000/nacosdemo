package com.half.test.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @Author: wangwei
 * @Date: 2020-04-30 8:47
 */
public class Server {

    public static void main(String[] args) {
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(12345);
            Socket socket = ss.accept();
            try (BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
                while (true) {
                    System.out.println(br.readLine());
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }


    }

}
