package kr.or.ddit.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpChatServer {
	public static void main(String[] args) {
		ServerSocket server = null;
		Socket socket = null;
		
		try {
			
			server = new ServerSocket(7777);
			System.out.println("서버 준비 완료...");
			socket = server.accept(); //------여기까지 클라이언트가 소켓접속할 수 있도록
			System.out.println("클라이언트 접속 완료...");
			
			Sender sender = new Sender(socket);
			Receiver receiver = new Receiver(socket);
			
			sender.start();
			receiver.start();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
