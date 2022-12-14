import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;



public class chatting_client {




	public static int port=5000;
	public static String IP="localhost";

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception, IOException {
		Socket socket = null;
		socket = new Socket(IP,port);// 서버의 IP주소
		Scanner scanner = new Scanner(System.in);
		Scanner in = new Scanner(socket.getInputStream()); // 서버로부터 계산 결과를 읽어올 때 사용할 변수
		PrintWriter out = new PrintWriter(socket.getOutputStream(), true); // 서버로 계산식을 보낼 때 사용할 변수'
		
		
		while(true){//client가 입력하는 값이 있을때마다 1줄 출력
			if(scanner.hasNextLine())out.println(scanner.nextLine());// server에 전송
			if(in.hasNextLine())System.out.println(in.nextLine());// server에서 온 내용 출력
		}

	}

}