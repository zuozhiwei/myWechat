package com.hgo.controller;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class SocketWechat {
	 DatagramSocket socket;
	 public SocketWechat(int port) {
		// TODO Auto-generated constructor stub
		 try {
			socket = new DatagramSocket(port);
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 public String serverSocket() {
		// 2.创建数据报，用于接收客户端发送的数据
		byte[] data = new byte[1024];// 创建字节数组，指定接收的数据包的大小
		DatagramPacket packet = new DatagramPacket(data, data.length);
		// 3.接收客户端发送的数据
		System.out.println("****服务器端已经启动，等待客户端发送数据");
		try {
			socket.receive(packet);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}// 此方法在接收到数据报之前会一直阻塞
		// 4.读取数据
		String info = new String(data, 0, packet.getLength());
		System.out.println("我是服务器，客户端说：" + info);
		//返回客户端发来的消息
		return info;
	 }
	 public void clientSocket(String sendMessage,int port) throws IOException {
		 InetAddress localhost = InetAddress.getLocalHost();
		 DatagramPacket packet= new DatagramPacket(sendMessage.getBytes(),sendMessage.getBytes().length,localhost,port);
		 socket.send(packet);
	 }
	public void close() {
		// TODO Auto-generated method stub
		socket.close();
	}
}
