package com.weifuwukt.zookeeper.registration;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

//ServerHandler
public class ServerHandler implements Runnable {
	private Socket socket;

	public ServerHandler(Socket socket) {
		this.socket = socket;
	}

	public void run() {
		BufferedReader in = null;
		PrintWriter out = null;
		try {
			//读取客户端发送过来的信息
			in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			//返回数据给客户端：创建一个打印流，把服务端接受到的消息，返回给客户端，用于返回给户端控制它打印出来的
			out = new PrintWriter(this.socket.getOutputStream(), true);
			String body = null;
			while (true) {
				body = in.readLine();
				if (body == null)
					break;
				//在服务端打印
				System.out.println("Receive : " + body);
				//返回给客户端打印
				out.println("Hello, " + body);
			}

		} catch (Exception e) {
			if (in != null) {
				try {
					in.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
			if (out != null) {
				out.close();
			}
			if (this.socket != null) {
				try {
					this.socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				this.socket = null;
			}
		}
	}
}
