package kr.or.ddit.tcp;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*
	TCP : 신뢰성있는 통신 보장(오류제어, 흐름제어 등). 데이터 전송을 보장하는 연결성 프로토콜로, 동기화(SYN 패킷), 확인(ACK 패킷)을 3번 진행하는 3-way handshaking 방식으로 상호연결을 설정한다. 전송단위는 Segment 라고 칭한다.
	UDP : 비연결성 프로토콜로, 데이터를 빠르게 전달하는 데 초점을 둔다. 전송만 하면 종료되므로 오버헤드가 적으며, 신뢰성보단 스트리밍같이 연속적인 특성을 갖는 서비스에 주로 이용. 전송단위는 Datagram 이라고 칭한다.
 */
public class TcpSocketServerTest {
	public static void main(String[] args) throws IOException {
		
		//소켓이란? 두 호스트간 통신을 하기 위한 양 끝단(Endpoint)을 말한다.
		
		//TCP소켓 통신을 하기 위해 ServerSocket객체 생성
		ServerSocket server = new ServerSocket(7777); //7777은 포트번호
		System.out.println("서버가 접속을 기다립니다...");
		
		//accept()는 클라이언트에서 연결 요청이 올 때까지 계속 기다린다.
		//연결 요청이 오면 Socket객체를 생성해서 클라이언트의 Socket과 연결한다.
		Socket socket = server.accept(); //만들어진소켓은 리턴받아서 저장됨
		
		//---------------------------
		//이 이후는 클라이언트와 연결된 후의 작업을 진행하면 된다.
		
		System.out.println("접속 클라이언트 정보");
		System.out.println("IP주소 : " + socket.getInetAddress());
		
		//클라이언트에 메시지 보내기
		
		//소켓에서 OutputStream객체를 꺼내 사용한다.
		OutputStream out = socket.getOutputStream();
		
		DataOutputStream dos = new DataOutputStream(out);
		dos.writeUTF("어서오세요..."); //메세지 보내기
		
		dos.close();
		
		System.out.println("메시지를 보냈습니다");
		
		server.close(); // 서버소켓 종료.
		
		
		
		
	}
}
