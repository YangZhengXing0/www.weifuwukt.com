package com.weifuwukt.socket.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * @author 杨郑兴
 * @Date 2019/1/18 1:23
 * @官网 www.weifuwukt.com
 */
public class UdpClient {
    public static void main(String[] args) throws Exception{
        System.out.println("udp client启动了...");
        //不传入端口号，表示客户端
        DatagramSocket ds = new DatagramSocket();
        String str = "udp 客户端消息来了！";
        byte[] bytes = str.getBytes();
        //数据包,发送到服务端的数据(这里bytes)+地址(服务端的host为：127.0.0.1)+端口(8080)
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length, InetAddress.getByName("127.0.0.1"),8080);
        ds.send(dp);
        ds.close();
    }
}
