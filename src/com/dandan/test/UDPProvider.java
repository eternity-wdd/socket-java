//package com.dandan.test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.UUID;

/**
 * UDP �ṩ�ߣ� �����ṩ����
 * @author LiZheng
 * 3�µĵ�һ��������Ƶ
 */
//public class UDPProvider {
//	public static void main(String[] args) throws IOException {
//		System.out.println("UDPProvider Started.");
//		// ��Ϊ�����ߣ� ָ��һ���˿��������ݽ���
//		DatagramSocket ds = new DatagramSocket(20000);
//		// ��������ʵ��
//		final byte[] buf = new byte[512];
//		DatagramPacket receivePack = new DatagramPacket(buf, buf.length);
//		// ����
//		ds.receive(receivePack);
//		// ��ӡ���յ�����Ϣ�뷢���ߵ���Ϣ
//		// �����ߵ�IP��ַ
//		String ip = receivePack.getAddress().getHostAddress();
//		int port = receivePack.getPort();
//		int dataLen = receivePack.getLength();
//		String data = new String(receivePack.getData(), 0, dataLen);
//		System.out.println("UDPProvider receive from ip��" + ip + "\tport��" + port + "\tdata��" + data);
//		// ����һ�ݻ�������
//		String responseData = "Receive data with len��" + dataLen;
//		byte[] responseDataBytes = responseData.getBytes();
//		// ֱ�Ӹ��ݷ����߹���һ�ݻ�����Ϣ
//		DatagramPacket responsePacket = new DatagramPacket(responseDataBytes, 
//				responseDataBytes.length, 
//				receivePack.getAddress(), 
//				receivePack.getPort());
//		ds.send(responsePacket);
//		// ���
//		System.out.println("UDPProvide Finished.");
//		ds.close();
//	}
//}

/**
 * UDP �ṩ�ߣ� �����ṩ����
 * @author LiZheng
 * 3�µĵڶ�������
 */
public class UDPProvider {
	public static void main(String[] args) throws IOException {
		// ����Ψһ��ʶ
		String sn = UUID.randomUUID().toString();
		Provider provider = new Provider(sn);
		provider.start();
		
		// ��ȡ���������Ϣ�˳�
		System.in.read();
		provider.exit();
	}
	
	private static class Provider extends Thread{
		private final String sn;
		private boolean done = false;
		private DatagramSocket ds = null;
		
		public Provider(String sn) {
			super();
			this.sn = sn;
		}
		
		@Override
		public void run() {
			super.run();
			
			System.out.println("UDPProvider Started.");
			
			try {
				// ����20000 �˿�
				ds = new DatagramSocket(20000);
			
				while (!done) {
					
					// ��������ʵ��
					final byte[] buf = new byte[512];
					DatagramPacket receivePack = new DatagramPacket(buf, buf.length);
					
					// ����
					ds.receive(receivePack);
					
					// ��ӡ���յ�����Ϣ�뷢���ߵ���Ϣ
					// �����ߵ�IP��ַ
					String ip = receivePack.getAddress().getHostAddress();
					int port = receivePack.getPort();
					int dataLen = receivePack.getLength();
					String data = new String(receivePack.getData(), 0, dataLen);
					System.out.println("UDPProvider receive from ip��" + ip + "\tport��" + port + "\tdata��" + data);
					
					// �����˿ں�
					int responsePort = MessageCreator.parsePort(data);
					
					if(responsePort != -1) {
						// ����һ�ݻ�������
						String responseData = MessageCreator.buildWithSn(sn);
						byte[] responseDataBytes = responseData.getBytes();
						// ֱ�Ӹ��ݷ����߹���һ�ݻ�����Ϣ
						DatagramPacket responsePacket = new DatagramPacket(responseDataBytes, 
								responseDataBytes.length, 
								receivePack.getAddress(), 
								responsePort);
						ds.send(responsePacket);
					}
					
				}
			
			} catch (Exception ignored) {
				// TODO: handle exception
			}finally {
				close();
			}
			
			// ���
			System.out.println("UDPProvide Finished.");
			
		}
		
		private void close() {
			if(ds!=null) {
				ds.close();
				ds = null;
			}
		}
		
		/**
		 * �ṩ���
		 */
		void exit() {
			done = true;
			close();
		}
	}
}
























