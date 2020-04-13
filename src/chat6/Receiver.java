package chat6;

import java.io.*;
import java.net.*;

//서버가 보내는 메세지를 읽어오는 쓰레드 클래스
public class Receiver extends Thread{
	
	Socket socket;
	BufferedReader in = null;
	
	public Receiver(Socket socket) {
		this.socket = socket;
		
		try {
			in = new BufferedReader(new
					InputStreamReader(this.socket.getInputStream()));
		}catch (Exception e) {
			System.out.println("예외1:"+e);
		}
	}
	
	@Override
	public void run() {
		
		//소켓이 종료되면 while문을 벗어나서 input스트림을 종료한다.
		while(in != null) {
			try {
				System.out.println("Thread Receive : "+in.readLine());
			}catch (SocketException e) {
				System.out.println("SocketException");
				break;
			}catch (Exception e) {
				System.out.println("예외>Receiver>run1:"+e);
			}
		}
		
		try {
			in.close();
		} catch (Exception e) {
			System.out.println("예외2>Receiver>run2"+e);
		}
	}
	
}
