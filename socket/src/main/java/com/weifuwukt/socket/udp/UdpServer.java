package com.weifuwukt.socket.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * udp server端
 * @author 杨郑兴
 * @Date 2019/1/18 1:09
 * @官网 www.weifuwukt.com
 */
public class UdpServer {
    public static void main(String[] args)  throws Exception{
        System.out.println("udp server启动了.....8080");
        //创建服务端 ip地址+端口号，服务端在哪里运行，则就是那个端口号，这里就是127.0.0.1:8080
        DatagramSocket ds = new DatagramSocket(8080);
        //服务端接收客户端1024个字节
        byte[] bytes = new byte[1024];
        //定义数据包
        DatagramPacket dp = new DatagramPacket(bytes, bytes.length);
        //接收客户端请求，将数据封装给数据包，如果客户端没有发送数据给服务端，则会一直等待(既阻塞)
        ds.receive(dp);
        System.out.println("来源ip地址:"+dp.getAddress()+":"+dp.getPort());
        String result = new String(dp.getData(), 0, dp.getLength());
        System.out.println(result);
        ds.close();
    }
}
