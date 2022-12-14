
import java.io.*;
import java.net.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.*;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Server {
	public static void main(String[] args) throws Exception {

		ServerSocket welcomeSocket = new ServerSocket(3321);
		System.out.println("Server is runing");
		ExecutorService pool = Executors.newFixedThreadPool(20);// 최대 20개의 client와 동시에 연결 가능
			while (true) {// 들어올때까지 기다리는중
				Socket socket = welcomeSocket.accept(); // client에서 연결요청이 들어오면 전용 socket을 새로만듬
				pool.execute(new Operation(socket));// thread를 생성하고 전용 socket을 전달
			}
	}

	private static class Operation implements Runnable {
		// client information
		
		private Socket socket;
		private String uid = null;
		private String upw = null;
		private String uip = null;
		private int uport = 0;
		private int chat_id;
		private String message = null;

		// server information
		private Statement stmt = null;
		private String url = "jdbc:mysql://localhost/networktp"; // server conputer's local DB address
		private String id = "root"; // DB id
		private String pw = "0625"; // DB password
		private Connection con = null;
		private Scanner in = null;
		private PrintWriter out = null;
		private	PreparedStatement pstmt = null;

		
		Operation(Socket socket) throws ClassNotFoundException {
			this.socket = socket;// thread의 socket에 연결되어 있는 socket을 알려줌

			// connection Database and client

		}

		@Override
		public void run() {
			System.out.println("Connected: "+ socket);//연결되었을때 message
			
			try {
				java.lang.Thread.activeCount();
				con = DriverManager.getConnection(url,id,pw);
				stmt=con.createStatement();
				in = new Scanner(socket.getInputStream());// client와의 inputstream
				out = new PrintWriter(socket.getOutputStream(),true);// client와의 Outstream
				uip=socket.getInetAddress().toString();
				uport = socket.getPort();
				System.out.println("client address:"+uip);	//client ip address
				System.out.println("client port:"+uport);	//client port number


				while(true)
				{
					

					while(in.hasNextLine()) {//inputstream에서 들어올때마다 읽음

						String protocol= in.nextLine();
						
						
						//user is not login
						if(uid==null&&upw==null)
						{
							
						
						if(protocol.equals("333"))
						{
							if(protocol.equals("333"))
							{
								System.out.println("login");

								login();
								if(login_check(User_2.id,upw))//user is login	
									break;
								
									
							}
							else {
								System.out.println(protocol);
							}
						}
						else if(protocol.equals("555"))
						{
							if(protocol.equals("555"))
							{
								System.out.println("enter");
								join();
								System.out.println("exit");

							}
							else {
								System.out.println(protocol);
							}
						}
						else if(protocol.equals("777"))
						{
							if(protocol.equals("777"))
							{
								id_check();
							}
							else {
								System.out.println(protocol);
							}
						}
						}
						//user is login
						
						if(protocol.equals("888"))
						{
							friend_list();	
							
						}
						if(protocol.equals("999"))
						{
							friend_search();
						}
						if(protocol.equals("608"))
						{
							friend_delete();
						}
						if(protocol.equals("666"))
						{
							friend_add();
						}	
						
						
					}
				}
				
			
				
			}catch(Exception e) {
				System.out.println("Error" + socket);
			}finally {//예외처리로 나올시 socket을 닫아주고 이를 알려줌
				try {socket.close();} catch(IOException e) {}
				System.out.println("Closed"+socket);
			}
			
		}
		private void friend_add() throws SQLException
		{
			String add_id = null;
			String query = null;
			
			if(in.hasNextLine()) add_id = in.nextLine();
			query = "INSERT INTO friend(fr_id, usr_id, usr_fr_id) values(?,?,?)";
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, User_2.id.concat(add_id));
			pstmt.setString(2, User_2.id);
			pstmt.setString(3, add_id);
			pstmt.executeUpdate();
		

		}
		private void friend_delete() throws SQLException
		{
			String delete_id = null;
			String query = null;
			
			if(in.hasNextLine()) delete_id = in.nextLine();
			query = "DELETE FROM friend WHERE usr_id=\'" +User_2.id+ "\'"+" and usr_fr_id=\'" +delete_id+ "\'";
			pstmt = con.prepareStatement(query);
			
			pstmt.executeUpdate();
			
		}
		
		private void friend_search() throws SQLException
		{
			String search_id = null;
			String query = null;
			if(in.hasNextLine()) search_id = in.nextLine();
			query = "SELECT usr_id FROM User WHERE usr_id=\'" +search_id+ "\'";
			ResultSet rs = stmt.executeQuery(query);
			
			if(rs.next())
			{
				out.println("900");
				query = "SELECT * FROM friend WHERE usr_id=\'" +User_2.id+ "\'"+" and usr_fr_id=\'" +search_id+ "\'";
				rs = stmt.executeQuery(query);
				if(rs.next())
				{
					out.println("901");
				}
				else
					out.println("902");
				
			}
			
				
			
			
			
		}
		private void friend_list() throws SQLException
		{
			String friend =null;
			String query1 = "SELECT usr_fr_id FROM friend WHERE usr_id=\'" + User_2.id + "\'";
			String query2 = null;
			//esultSet friend_rs = stmt.executeQuery(query1);
			
			PreparedStatement prepare=con.prepareStatement(query1);
			
			ResultSet resultSet= prepare.executeQuery();
				while(resultSet.next())
				{
					friend = resultSet.getString(1);
					query2 = "SELECT usr_nickname, usr_connect FROM User WHERE usr_id=\'"+friend+"\'";
					ResultSet usr_rs = stmt.executeQuery(query2);
					usr_rs.next();


					out.println(usr_rs.getString(1));//nickname
					out.println(usr_rs.getString(2));// usr_connect
					out.println(friend);//friend
				}
				
		
			
			System.out.println("800");
			out.println("800");
			
			
		}
		private void create_room() throws SQLException
		{
			String other_user = null;
			
			String query = "insert into chatting(chat_id, usr_id) values(?,?)";
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, User_2.id);
			pstmt.setString(2, other_user);
			pstmt.executeUpdate();
			pstmt.setString(1, User_2.id);
			pstmt.setString(2, User_2.id);
			pstmt.executeUpdate();
		}
		private void invite() throws SQLException
		{
			String other_user = null;
			String other_ip =null;
			String other_port = null;
			
			String query = "SELECT  FROM USER WHERE usr_id=\'" + other_user + "\'";
			ResultSet result = stmt.executeQuery(query);
			result.next();
			
			
			
/*
			if (result.next()) {
				System.out.println(result.getString("usr_id"));
				Socket socket2 =null;
				ServerSocket welcomeSocket2 = new ServerSocket(1234);
				socket2 = welcomeSocket2.accept(); // client에서 연결요청이 들어오면 전용 socket을 새로만듬
				
				
				
					
}*/
			
			
			
			query = "insert into chatting(chat_id, usr_id) values(?,?)";
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, User_2.id);
			pstmt.setString(2, other_user);
			pstmt.executeUpdate();
			pstmt.setString(1, User_2.id);
			pstmt.setString(2, User_2.id);
			pstmt.executeUpdate();
		}

		private void join() throws SQLException// if click join button then client transport special data and implement
		{
			String temp_id = null;
			String temp_pw = null;
			String temp_name = null;
			String temp_nick = null;
			String temp_email = null;
			String temp_birth = null;
			String temp_phone = null;
			System.out.println("start");

			if (in.hasNextLine())
				temp_id = in.nextLine();// user id
			if (in.hasNextLine())
				temp_pw = in.nextLine();// user password
			if (in.hasNextLine())
				temp_name = in.nextLine();// user port
			if (in.hasNextLine())
				temp_nick = in.nextLine();// user ip
			if (in.hasNextLine())
				temp_email = in.nextLine();// user port
			if (in.hasNextLine())
				temp_birth = in.nextLine();// user ip
			if (in.hasNextLine())
				temp_phone = in.nextLine();// user ip
			System.out.println(temp_id);
			System.out.println(temp_pw);
			System.out.println(temp_name);
			System.out.println(temp_nick);
			System.out.println(temp_email);
			System.out.println(temp_birth);
			System.out.println(temp_phone);


			String query = "insert into user(usr_id,usr_pwd,usr_nickname,usr_name,usr_email,usr_birthdate,usr_phone)"
					+ " values(?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(query);

			pstmt.setString(1, temp_id);
			pstmt.setString(2, temp_pw);
			pstmt.setString(3, temp_nick);
			pstmt.setString(4, temp_name);
			pstmt.setString(5, temp_email);
			pstmt.setString(6, temp_birth);
			pstmt.setString(7, temp_phone);
			pstmt.executeUpdate();
		}

		private void id_check() throws SQLException// if click join button then client transport special data and implement
		{
			String temp_id = null;

			if (in.hasNextLine())
				temp_id = in.nextLine();// user id

			String query = "SELECT * FROM USER WHERE usr_id=\'" + temp_id + "\'";
			ResultSet result = stmt.executeQuery(query);
			if (!result.next())
				out.println("700");
			else
				out.println("708");

		}

		@SuppressWarnings("null")
		private void login() throws SQLException, IOException {

			String temp_id = null;
			String temp_pw = null;
			String temp_ip = null;
			String temp_port = null;

			if (in.hasNextLine())
				temp_id = in.nextLine();// user id
			if (in.hasNextLine())
				temp_pw = in.nextLine();// user password
			if (in.hasNextLine())
				temp_port = in.nextLine();// user port
			if (in.hasNextLine())
				temp_ip = in.nextLine();// user ip

			System.out.println("ID : " + temp_id);
			System.out.println("PW : " + temp_pw);
			System.out.println("port : " + temp_port);
			System.out.println("IP : " + temp_ip);

			if (login_check(temp_id, temp_pw)) {
				System.out.println("변경전");
				uid = temp_id;
				uip = temp_ip;
				uport = Integer.parseInt(temp_port);
				System.out.println("pstmt 선언전");
				System.out.println("pstmt 선언후");
				pstmt = con.prepareStatement(
						"UPDATE user set usr_connect=?, usr_connect_ip=?, usr_connect_port=?, usr_last_login=now(), usr_num_of_logins=usr_num_of_logins+1 "
								+ "WHERE usr_id=\'" + uid+"\'");
				
				pstmt.setString(1, "online");
				pstmt.setString(2, uip);
				pstmt.setInt(3, uport);
				
				pstmt.executeUpdate();//update column
				
				
				//USER class update
				String query = "SELECT * FROM USER WHERE usr_id=\'" + uid + "\'";
				System.out.println("execute전");

				ResultSet result = stmt.executeQuery(query);
				System.out.println("execute후");
				result.next();
				User_2.id=result.getString("usr_id");
				User_2.name=result.getString("usr_name");
				User_2.nickName=result.getString("usr_nickname");
				User_2.email=result.getString("usr_email");
				User_2.phone=result.getString("usr_phone");
				User_2.birthdate=result.getString("usr_birthdate");
				User_2.connect=result.getString("usr_connect");
				User_2.clientIp=result.getString("usr_connect_ip");
				User_2.clientPort=result.getInt("usr_connect_port");
				User_2.lastLoginTime=result.getString("usr_last_login");
				User_2.logOutTime=result.getString("usr_log_out");
				User_2.todayWords=result.getString("usr_today_words");
				User_2.numOfLogins=result.getInt("usr_num_of_logins");
				
				User_2.serverIp=InetAddress.getLocalHost().toString();
				User_2.serverPort=socket.getPort();

				/*
				System.out.println("------------------------");
				System.out.println("id:"+User.id);
				System.out.println("name:"+User.name);
				System.out.println("nickName:"+User.nickName);
				System.out.println("email:"+User.email);
				System.out.println("phone:"+User.phone);
				System.out.println("birthdate:"+User.birthdate);
				System.out.println("connect:"+User.connect);
				System.out.println("clientIp:"+User.clientIp);
				System.out.println("clientPort:"+User.clientPort);
				System.out.println("lastLoginTime:"+User.lastLoginTime);
				System.out.println("logOutTime:"+User.logOutTime);
				System.out.println("todayWords:"+User.todayWords);
				System.out.println("numOfLogins:"+User.numOfLogins);
				System.out.println("serverIp:"+User.serverIp);
				System.out.println("serverPort:"+User.serverPort);
				System.out.println("------------------------");
				 */



				out.println("100");

			} else {
				temp_id = null;
				temp_pw = null;
			}

		}

		private boolean login_check(String usr_id, String pw) throws SQLException, IOException {
			boolean flag = false;

			try {
				String query = "SELECT * FROM USER WHERE usr_id=\'" + usr_id + "\'";
				ResultSet result = stmt.executeQuery(query);

				if (result.next()) {
					System.out.println(result.getString("usr_id"));

					if (result.getString("usr_id") != null) {
						if (pw.equals(result.getString("usr_pwd"))) {
							flag = true;
						}

						else {
							out.println("202");
							flag = false;
						}
					}
				}

				else {
					out.println("201");
					System.out.println("201");

				}

			} catch (Exception e) {
				flag = false;
			}

			return flag;

		}
	}

}