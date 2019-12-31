package com.dandan.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Client {

	public static void main(String[] args) throws IOException {
		Socket socket = new Socket(); 
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
	
	private static void todo(Socket client) throws IOException {
		// ��������������
		InputStream in = System.in;
		BufferedReader input = new BufferedReader(new InputStreamReader(in));
		
		// �õ�Socket���������ת��Ϊ��ӡ��
		OutputStream outputStream = client.getOutputStream();
		PrintStream socketPrintStream = new PrintStream(outputStream);
		
		// �õ�Socket����������ת��ΪBufferedReader
		InputStream inputStream = client.getInputStream();
		BufferedReader socketBufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		
		boolean flag = true;
		do {
			// ���̶�ȡһ��
			String str = input.readLine();
			// ���͵�������
			socketPrintStream.println(str);
			
			// �ӷ�������ȡһ��
			String echo = socketBufferedReader.readLine();
			if("bye".equalsIgnoreCase(echo)) {
				flag = false;
			}else {
				System.out.println(echo);
			}
		} while (flag);
		
		// ��Դ�ͷ�
		socketPrintStream.close();
		socketBufferedReader.close();
		
	}

}
