import java.io.*;
import java.util.*;

// Create a file that stores the IP address and port number of the server named server_info.dat
public class Ip_Port_Info {
	
	public static void main(String[] args) throws IOException {
		
	String fileName="server_info.dat"; // server_info.dat 이라는 서버의 IP주소와 Port 번호를 저장하는 파일의 이름 
	
	DataOutputStream outputStream = null; // The name of the file containing the IP address and port number of the server named server_info.dat
	
	try {
		
		outputStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(fileName)));
		outputStream.writeUTF("127.0.0.1"); // Write IP address to file
		outputStream.writeInt(3321); // Write Port Number to File
		
	}
	catch(FileNotFoundException e){ // If the file is not found
		
		System.out.println("Problem opening the file" + fileName);
		
	}
	finally {
		
		outputStream.close();
	}
		
	}
}
