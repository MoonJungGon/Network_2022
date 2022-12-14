import java.io.*;
import java.net.*;
import java.util.*;




// Client
public class Client {
	
	
	
	public static void main(String[] args) throws Exception, IOException{
		
		String fileName="server_info.dat"; // the name of the file with the server's IP address and port number
		
		DataInputStream inputStream = null; // Use to read the contents of a file
		String ip="";// IP address of the server
		int port=0; // Port number of the server
		
		try {
			inputStream = new DataInputStream(new BufferedInputStream(new FileInputStream(fileName)));
			
			ip=inputStream.readUTF(); // Read IP address from file
			port=inputStream.readInt(); // Read Port number from file
			
		}
		catch(FileNotFoundException e) { // if the file is not found
			
			ip="localhost"; 
			port=3321;
		}
		finally {
			
			
			Socket socket = new Socket(ip,port); // Client Socket			
			Scanner in=new Scanner(socket.getInputStream()); // Variables to use when reading values from servers
			PrintWriter out=new PrintWriter(socket.getOutputStream(),true); // Variable to use when sending values to the server
					
			new Login(socket);

		}
	}

}
