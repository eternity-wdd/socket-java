//package com.dandan.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

//import org.omg.CORBA.PRIVATE_MEMBER;

//import com.sun.xml.internal.bind.v2.TODO;


public class Server {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket server = new ServerSocket(2000);
		System.out.println("������׼��������");
		System.out.println("��������Ϣ��" + server.getInetAddress() + " P��" + server.getLocalPort());
		
		//�ȴ��ͻ�������
		for(;;) {
			// �õ��ͻ���
			Socket client = server.accept();
			// �ͻ��˹����첽�߳�
			ClientHandler clientHandler = new ClientHandler(client);
			// �����߳�
			clientHandler.start();
		}
		
	}
	
	/**
	 *  �ͻ�����Ϣ����
	 */
	private static class ClientHandler extends Thread{
		private Socket socket;
		private boolean flag = true;
		
		public ClientHandler(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run() {
			super.run();
			System.out.println("�¿ͻ������ӣ�" + socket.getInetAddress() + "P��" + socket.getPort());
			
			try {
				// �õ���ӡ�������������������������������ʹ��
				PrintStream socketOutput = new PrintStream(socket.getOutputStream());
				// �õ������������ڽ�������
				BufferedReader socketInput = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				do {
					// �ͻ����õ�һ������
					String str = socketInput.readLine();
					if("bye".equalsIgnoreCase(str)) {
						flag = false;
						// ����
						socketOutput.println("bye");
					}else {
						// ��ӡ����Ļ�����������ݳ���
						System.out.println(str);
						socketOutput.println("���ͣ�" + str.length());
					}
				} while (flag);
				socketInput.close();
				socketOutput.close();
				
			} catch (Exception e) {
				System.out.println("�����쳣�Ͽ�");
			}finally {
				// �ر�����
				try {
					socket.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			System.out.println("�ͻ������˳���" + socket.getInetAddress() + " P��" + socket.getPort());
		}
	}
	
}











