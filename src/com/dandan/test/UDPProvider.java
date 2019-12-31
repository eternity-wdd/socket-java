package com.dandan.test;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * UDP �ṩ�ߣ� �����ṩ����
 * @author LiZheng
 */
public class UDPProvider {

	public static void main(String[] args) throws SocketException {
		System.out.println("UDPProvider Started.");
		
		// ��Ϊ�����ߣ� ָ��һ���˿��������ݽ���
		DatagramSocket ds = new DatagramSocket(20000);
		
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
		
		// ����һ�ݻ�������
		String responseData = "Receive data with len��" + dataLen;
		byte[] responseDataBytes = responseData.getBytes();
		// ֱ�Ӹ��ݷ����߹���һ�ݻ�����Ϣ
		DatagramPacket responsePacket = new DatagramPacket(responseDataBytes, 
				responseDataBytes.length, 
				receivePack.getAddress(), 
				receivePack.getPort());
		
		ds.send(responsePacket);
		// ���
		System.out.println("UDPProvide Finished.");
		ds.close();
		
	}

}























