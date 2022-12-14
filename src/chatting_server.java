import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.io.IOException;


public class chatting_server {
	public static int port;
	public chatting_server(int p)
	{
		port = 5000;
	}
	public static void main(String[] args) throws Exception{
		
		ServerSocket welcomeSocket = new ServerSocket(5000);//welcome socket의 port nmber를 1234로 설정 문제에 제시된 default값이 1234 
		System.out.println("chatting_server is runing");
		ExecutorService pool = Executors.newFixedThreadPool(20);//최대 20개의 client와 동시에 연결 가능
		while(true) {//들어올때까지 기다리는중
			Socket socket = welcomeSocket.accept(); //client에서 연결요청이 들어오면 전용 socket을 새로만듬
			pool.execute(new Operation(socket));//thread를 생성하고 전용 socket을 전달
		}
	}
	
	private static class Operation implements Runnable{
		private Socket socket;
		
		
		Operation(Socket socket){
			this.socket = socket;//thread의 socket에 연결되어 있는 socket을 알려줌
		}
		@Override
		public void run() {
			System.out.println("Connected: "+ socket);//연결되었을때 message
			try {
				String message = null;
				Scanner scanner = new Scanner(System.in);//message
				Scanner in = new Scanner(socket.getInputStream());// client와의 inputstream
				PrintWriter out = new PrintWriter(socket.getOutputStream(),true);// client와의 Outstream
				while (true) {
					if(scanner.hasNextLine())
					{
						message = in.nextLine();
						out.println(message);
					}
					if(in.hasNextLine()) {
						message = in.nextLine();
						System.out.println(message);
				}
					
					
					
					
					
				}
			}catch(Exception e) {
				System.out.println("Error" + socket);
			}finally {//예외처리로 나올시 socket을 닫아주고 이를 알려줌
				try {socket.close();} catch(IOException e) {}
				System.out.println("Closed"+socket);
			}
			
		}
	}

}