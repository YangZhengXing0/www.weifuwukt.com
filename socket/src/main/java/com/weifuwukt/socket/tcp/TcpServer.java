package com.weifuwukt.socket.tcp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * tcp server
 * @author 杨郑兴
 * @Date 2019/1/18 1:46
 * @官网 www.weifuwukt.com
 */
public class TcpServer {
    public static void main(String[] args) throws Exception{
        //创建一个服务器端的Socket,即ServerSocket，指定绑定的端口
        System.out.println("tcp 服务端启动了......");
        ServerSocket ss = new ServerSocket(8081);
        //调用accept方法开始监听，等待客户端的连接，阻塞的，如果客户端没有发送消息过来，则一直阻塞
        Socket accept = ss.accept();
        //获取一个输入流，并读取客户端信息
        InputStream is = accept.getInputStream();//字节输入流
        InputStreamReader isr = new InputStreamReader(is);//将字节输入流包装成字符输入流
        BufferedReader br = new BufferedReader(isr);//加上缓冲流，提高效率
        String line = null;
        while ((line=br.readLine()) != null){//循环读取客户端信息
            System.out.println("我是tcp服务端,客户端说："+line);
        }
        //关闭输入流
        accept.shutdownInput();
        //关闭资源
        br.close();
        isr.close();
        is.close();
        accept.close();
        ss.close();
    }
}
