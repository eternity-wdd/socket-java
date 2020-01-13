package com.dandan.test04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketException;

public class Client {
	private static final int PORT = 20000;
	private static final int LOCAL_PORT = 20001;

	public static void main(String[] args) throws IOException {
		Socket socket = createSocket(); 
		// 超时时间
		socket.setSoTimeout(3000);
		
		// 连接本地，端口2000； 延时时间3000
		socket.connect(new InetSocketAddress(Inet4Address.getLocalHost(), 2000));
		
		System.out.println("已发起服务器连接，并进入后续流程~");
		System.out.println("客户端信息：" + socket.getLocalAddress() + " P：" + socket.getLocalPort());
		System.out.println("服务器信息：" + socket.getInetAddress() + " P：" + socket.getPort());
	
		 try {
			// 发送接收数据
			todo(socket);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("异常关闭");
		}
		 
		 //释放资源
		 socket.close();
		 System.out.println("客户端已退出");
	}
	
	private static Socket createSocket() throws IOException {
//		// 无代理模式，等效于空构造函数
//		Socket socket = new Socket(Proxy.NO_PROXY);
//		
//		// 新建一份具有HTTP代理的套接字，传输数据将通过www.baidu.com:8080端口转发
//		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(Inet4Address.getByName("wwww.baidu.com"),8800));
//		socket = new Socket(proxy);
//		
//		// 新建一个套接字，并且直接连接到本地20000的服务器上
//		socket = new Socket("localhost", PORT);
//		
//		// 新建一个套接字，并且直接连接到本地20000的服务器上
//		socket = new Socket(Inet4Address.getLocalHost(), PORT);
//		
//		// 新建一个套接字，并且直接连接到本地20000的服务器上，并且绑定到本地20001端口上
//		socket = new Socket("localhost", PORT, Inet4Address.getLocalHost(), LOCAL_PORT);
//		socket = new Socket(Inet4Address.getLocalHost(), PORT, Inet4Address.getLocalHost(),LOCAL_PORT);
		
		Socket socket = new Socket();
		// 绑定到本地20001端口
		socket.bind(new InetSocketAddress(Inet4Address.getLocalHost(), LOCAL_PORT));
		
		return socket;
	
	}
	
	private static void initSocket(Socket socket) throws SocketException {
		// 设置读取超时时间2秒(连接和读取都是阻塞的，但是有专门的连接超时，故此此处为读取时间的超时)
		socket.setSoTimeout(2000);
		
		// 是否复用未完全关闭的Socket地址， 对于指定bind操作后的套接字有效
		socket.setReuseAddress(true);
		
		// 是否开启Nagle算法（减少网络中的回送包）
		 socket.setTcpNoDelay(false);
		
		// 是否需要在长时无数据响应时发送确认数据（类似心跳包），时间大约为2小时
		socket.setKeepAlive(true);
		
		// 对于close关闭操作行为进行怎样的处理；默认为false，0
		// false、0：默认情况，关闭时立即返回，底层系统接管输出流，将缓冲区内的数据发送完成
		// true、0：关闭时立即返回，缓冲区数据抛弃，直接发送RST结束命令给对方，并无需经过2MSL等待
		// true、200：关闭时最长阻塞200毫秒。随后按第二情况处理
		socket.setSoLinger(true, 20);
		
		// 是否让紧急数据内敛， 默认false；紧急数据通过socket.sendUrgentData(1);发送
		socket.setOOBInline(true);
		
		// 设置接受发送缓冲器大小
		socket.setReceiveBufferSize(64*1024*1024);
		socket.setSendBufferSize(64*1024*1024);
		
		
	}
	

}





















