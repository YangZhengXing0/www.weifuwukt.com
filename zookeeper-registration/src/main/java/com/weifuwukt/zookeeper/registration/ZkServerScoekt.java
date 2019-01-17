package com.weifuwukt.zookeeper.registration;

import org.I0Itec.zkclient.ZkClient;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


//##ServerScoekt服务端
public class ZkServerScoekt implements Runnable {
	private static int port = 18082;

	public static void main(String[] args) throws IOException {

		ZkServerScoekt server = new ZkServerScoekt(port);
		Thread thread = new Thread(server);
		thread.start();
	}

	public ZkServerScoekt(int port) {
		this.port = port;
	}

	// 启动注册服务
	private void regServer() {
		ZkClient zkClient = new ZkClient("127.0.0.1:2181", 6000, 1000);
		//前提条件：先手动创建/test节点，不然创建/test/server节点要报错
		String path = "/test/server" + port;
		if (zkClient.exists(path)) {
			zkClient.delete(path);
		}
		// 创建临时节点
		zkClient.createEphemeral(path, "127.0.0.1:" + port);
	}

	public void run() {
		ServerSocket serverSocket = null;
		try {
			//创建一个服务器端的Socket,即ServerSocket，指定绑定的端口
			serverSocket = new ServerSocket(port);
			//启动注册服务
			regServer();
			System.out.println("Server start port:" + port);
			Socket socket = null;
			while (true) {
				//调用accept方法开始监听，等待客户端的连接，阻塞的，如果客户端没有发送消息过来，则一直阻塞
				socket = serverSocket.accept();
				//创建一个线程，用于把服务端接受到的消息，返回到客户端进行打印
				new Thread(new ServerHandler(socket)).start();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (serverSocket != null) {
					serverSocket.close();
				}
			} catch (Exception e2) {

			}
		}
	}
}