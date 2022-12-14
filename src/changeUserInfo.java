import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.awt.event.ActionEvent;

public class changeUserInfo {
	private Scanner in;
	private PrintWriter out;
	private JFrame frame;
	private JTextField nickField; // nickname field
	private JTextField todayField; // today's words field
	private Socket changeSock; // socket for change Info

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					changeUserInfo window = new changeUserInfo();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public changeUserInfo(Socket tmpSock) {
		changeSock=tmpSock;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		try {
			
		in=new Scanner(changeSock.getInputStream()); 
		out=new PrintWriter(changeSock.getOutputStream(),true);
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 432, 253);
		frame.getContentPane().add(panel);
		panel.setBackground(new Color(218, 165, 32));
		panel.setLayout(null);
		
		nickField = new JTextField();
		nickField.setBounds(32, 76, 296, 42);
		panel.add(nickField);
		nickField.setColumns(10);
		
		todayField = new JTextField();
		todayField.setColumns(10);
		todayField.setBounds(32, 171, 296, 42);
		panel.add(todayField);
		
		JLabel nickLabel = new JLabel("NickName");
		nickLabel.setFont(new Font("HY°ß°íµñ", Font.BOLD, 23));
		nickLabel.setBounds(32, 46, 146, 18);
		panel.add(nickLabel);
		
		JLabel todayLabel = new JLabel("Today's Words");
		todayLabel.setFont(new Font("HY°ß°íµñ", Font.BOLD, 23));
		todayLabel.setBounds(32, 141, 226, 27);
		panel.add(todayLabel);
		
		JButton nickEdBT = new JButton(""); 
		nickEdBT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // when button clicked, nick name is edited!
				try {
					String newNick=nickField.getText();
					out.println("351");
					
					out.println(newNick);
					JOptionPane.showMessageDialog(null,"NickName edited!");
					
				}catch(Exception e1) { 
					System.out.println("Error:" +changeSock);
				}
				
			}
		});
		
		nickEdBT.setBackground(Color.WHITE);
		nickEdBT.setBorderPainted(false);
		nickEdBT.setContentAreaFilled(false);
		nickEdBT.setFocusPainted(false);
		
		nickEdBT.setIcon(new ImageIcon(changeUserInfo.class.getResource("/PngFile/edit_Img.png")));
		nickEdBT.setFont(new Font("HY°ß°íµñ", Font.BOLD, 21));
		nickEdBT.setBounds(340, 62, 65, 65);
		panel.add(nickEdBT);
		
		JButton todayEdBT = new JButton("");
		todayEdBT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { // when button clicked, today's word is edited!
				try {
					String newToday=todayField.getText();
					
					out.println("352");	
					out.println(newToday);
					JOptionPane.showMessageDialog(null,"You Today's Words edited!");
					
				}catch(Exception e1) { 
					System.out.println("Error:" +changeSock);
				}
				
			}
		});
		todayEdBT.setIcon(new ImageIcon(changeUserInfo.class.getResource("/PngFile/edit_Img.png")));
		todayEdBT.setFont(new Font("HY°ß°íµñ", Font.BOLD, 21));
		todayEdBT.setBounds(340, 157, 65, 65);
		
		todayEdBT.setBackground(Color.WHITE);
		todayEdBT.setBorderPainted(false);
		todayEdBT.setContentAreaFilled(false);
		todayEdBT.setFocusPainted(false);
		panel.add(todayEdBT);
		
		JButton closeBT = new JButton("X");
		closeBT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		
		closeBT.setBackground(Color.WHITE);
		closeBT.setBorderPainted(false);
		closeBT.setContentAreaFilled(false);
		closeBT.setFocusPainted(false);
		closeBT.setFont(new Font("HY°ß°íµñ", Font.BOLD, 25));
		closeBT.setBounds(353, 12, 65, 27);
		panel.add(closeBT);
		
		frame.setVisible(true);
		}catch(Exception e) { 
			System.out.println("Error:" +changeSock);
		}
	}
}
