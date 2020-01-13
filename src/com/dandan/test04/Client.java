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
		// ��ʱʱ��
		socket.setSoTimeout(3000);
		
		// ���ӱ��أ��˿�2000�� ��ʱʱ��3000
		socket.connect(new InetSocketAddress(Inet4Address.getLocalHost(), 2000));
		
		System.out.println("�ѷ�����������ӣ��������������~");
		System.out.println("�ͻ�����Ϣ��" + socket.getLocalAddress() + " P��" + socket.getLocalPort());
		System.out.println("��������Ϣ��" + socket.getInetAddress() + " P��" + socket.getPort());
	
		 try {
			// ���ͽ�������
			todo(socket);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("�쳣�ر�");
		}
		 
		 //�ͷ���Դ
		 socket.close();
		 System.out.println("�ͻ������˳�");
	}
	
	private static Socket createSocket() throws IOException {
//		// �޴���ģʽ����Ч�ڿչ��캯��
//		Socket socket = new Socket(Proxy.NO_PROXY);
//		
//		// �½�һ�ݾ���HTTP������׽��֣��������ݽ�ͨ��www.baidu.com:8080�˿�ת��
//		Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(Inet4Address.getByName("wwww.baidu.com"),8800));
//		socket = new Socket(proxy);
//		
//		// �½�һ���׽��֣�����ֱ�����ӵ�����20000�ķ�������
//		socket = new Socket("localhost", PORT);
//		
//		// �½�һ���׽��֣�����ֱ�����ӵ�����20000�ķ�������
//		socket = new Socket(Inet4Address.getLocalHost(), PORT);
//		
//		// �½�һ���׽��֣�����ֱ�����ӵ�����20000�ķ������ϣ����Ұ󶨵�����20001�˿���
//		socket = new Socket("localhost", PORT, Inet4Address.getLocalHost(), LOCAL_PORT);
//		socket = new Socket(Inet4Address.getLocalHost(), PORT, Inet4Address.getLocalHost(),LOCAL_PORT);
		
		Socket socket = new Socket();
		// �󶨵�����20001�˿�
		socket.bind(new InetSocketAddress(Inet4Address.getLocalHost(), LOCAL_PORT));
		
		return socket;
	
	}
	
	private static void initSocket(Socket socket) throws SocketException {
		// ���ö�ȡ��ʱʱ��2��(���ӺͶ�ȡ���������ģ�������ר�ŵ����ӳ�ʱ���ʴ˴˴�Ϊ��ȡʱ��ĳ�ʱ)
		socket.setSoTimeout(2000);
		
		// �Ƿ���δ��ȫ�رյ�Socket��ַ�� ����ָ��bind��������׽�����Ч
		socket.setReuseAddress(true);
		
		// �Ƿ���Nagle�㷨�����������еĻ��Ͱ���
		 socket.setTcpNoDelay(false);
		
		// �Ƿ���Ҫ�ڳ�ʱ��������Ӧʱ����ȷ�����ݣ���������������ʱ���ԼΪ2Сʱ
		socket.setKeepAlive(true);
		
		// ����close�رղ�����Ϊ���������Ĵ���Ĭ��Ϊfalse��0
		// false��0��Ĭ��������ر�ʱ�������أ��ײ�ϵͳ�ӹ�����������������ڵ����ݷ������
		// true��0���ر�ʱ�������أ�����������������ֱ�ӷ���RST����������Է��������辭��2MSL�ȴ�
		// true��200���ر�ʱ�����200���롣��󰴵ڶ��������
		socket.setSoLinger(true, 20);
		
		// �Ƿ��ý������������� Ĭ��false����������ͨ��socket.sendUrgentData(1);����
		socket.setOOBInline(true);
		
		// ���ý��ܷ��ͻ�������С
		socket.setReceiveBufferSize(64*1024*1024);
		socket.setSendBufferSize(64*1024*1024);
		
		
	}
	

}





















