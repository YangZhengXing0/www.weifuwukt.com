package com.weifuwukt.socket.tcp;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * tcp client
 * @author 杨郑兴
 * @Date 2019/1/18 1:58
 * @官网 www.weifuwukt.com
 */
public class TcpClient {
    public static void main(String[] args) throws Exception {
        //创建客户端Socket，指定服务器地址和端口
        Socket socket = new Socket("127.0.0.1", 8081);//端口号要和服务器端相同
        //获取输出流，向服务器端发送登录的信息
        OutputStream os = socket.getOutputStream();//字节输出流
        OutputStreamWriter osw = new OutputStreamWriter(os);//字符输出流
        BufferedWriter bw = new BufferedWriter(osw);//加上缓冲流,提高效率
        bw.write("我是tcp客户端，我发消息了，用户名：admin;密码：123");
        /**
         * BufferedWriter是缓冲输入流，意思是调用BufferedWriter的write方法时候。数据是先写入到缓冲区里，
         * 并没有直接写入到目的文件里。必须调用BufferedWriter的flush()方法。这个方法会刷新一下该缓冲流，
         * 也就是会把数据写入到目的文件里。或者你可以调用BufferedWriter的close()方法，
         * 该方法会在关闭该输入流之前先刷新一下该缓冲流。也会把数据写入到目的文件里。
         */
        bw.flush();
        //关闭输出流
        socket.shutdownOutput();
        //关闭资源
        bw.close();
        osw.close();
        os.close();
        socket.close();
    }
}
