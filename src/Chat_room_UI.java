

import java.awt.*;
import java.awt.Desktop.Action;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextPane;
import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.JLabel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


public class Chat_room_UI extends JFrame {
	static String filepath;
	
	public class AddUser extends JFrame implements ActionListener {

		private JButton addUser;
		private JTextField jtf;

		public AddUser() {
			addUser = new JButton("add");
			jtf = new JTextField();
			JPanel jpCenter = new JPanel();
			jpCenter.add(addUser);

			add("Center", jpCenter);
			add("South", jtf);

			addUser.addActionListener(this);

			setBounds(100, 100, 300, 300);
			setVisible(true);

		}// UseFileDialog

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == addUser) { // 파일열기 버튼 선택하면 파일경로를 가져와보자
				
			} // end if			
		}
	}	
	
	public class UseFileDialog extends JFrame implements ActionListener {

		private JButton jbtnOpen;
		private JButton jbtnSave;
		private JTextField jtf;

		public UseFileDialog() {

			jbtnOpen = new JButton("파일열기");
			jbtnSave = new JButton("파일저장");
			jtf = new JTextField();

			jtf.setBorder(new TitledBorder("파일 경로"));

			JPanel jpCenter = new JPanel();
			jpCenter.add(jbtnOpen);
			jpCenter.add(jbtnSave);

			add("Center", jpCenter);
			add("South", jtf);

			jbtnOpen.addActionListener(this);

			setBounds(100, 100, 500, 150);
			setVisible(true);

		}// UseFileDialog

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == jbtnOpen) { // 파일열기 버튼 선택하면 파일경로를 가져와보자
				FileDialog fdOpen = new FileDialog(this, "파일열기",FileDialog.LOAD);
				fdOpen.setVisible(true);

				String path = fdOpen.getDirectory(); // 파일경로 
				String name = fdOpen.getFile(); // 파일이름
				
				if (path != null) { // 파일이 선택되었다면 텍스트필드에 파일 경로를 보여주자
					jtf.setText(path+name);
				} // end if
			} // end if
			
			if(e.getSource() == jbtnSave) { // 얜 스트림배우고 나서 해볼 예쩡!
				FileDialog fdSave = new FileDialog(this, "파일열기",FileDialog.SAVE);
				fdSave.setVisible(true);

				String path = fdSave.getDirectory(); // 선택된 경로 가져오기
				String name = fdSave.getFile(); // 작성한 파일명 가져오기

				if (path == null) { // 취소 등 발생하면 return
					return;
				}//end if
				
				File file = new File(path); // 선택된 경로가 있으면 file 객체 생성

				BufferedWriter br = null;
				try {
					br = new BufferedWriter(new FileWriter(file + "/" + name)); // 스트림연결
					br.write("저장된 파일"); //쓸 내용
					br.flush(); // 목적지로 분출

					JOptionPane.showMessageDialog(this, "파일이 생성되었습니다."); // 사용자에게 알림

					br.close(); // 다 쓴 스트림 끊기
				} catch (IOException e1) {
					e1.printStackTrace();
				} // end catch
			}//end if
			
		}
	}
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Chat_room_UI frame = new Chat_room_UI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public class ExitChat extends JFrame implements ActionListener {
		
		
		private JButton addUser;
		private JTextField jtf;

		public ExitChat() {
			addUser = new JButton("exit");
			jtf = new JTextField();
			JPanel jpCenter = new JPanel();
			JLabel JL = new JLabel("Are you sure you want to exit?");
			jpCenter.add(JL);
			JLabel JL2 = new JLabel("You can't come back!");
			jpCenter.add(JL2);
			jpCenter.add(addUser);
			add("Center", jpCenter);
			add("South", jtf);

			
			
			addUser.addActionListener(this);

			setBounds(100, 100, 300, 300);
			setVisible(true);

		}// UseFileDialog

		@Override
		public void actionPerformed(ActionEvent e) {

			if (e.getSource() == addUser) { // 파일열기 버튼 선택하면 파일경로를 가져와보자
				
			} // end if			
		}
	}
	
	/**
	 * Create the frame.
	 */
	public Chat_room_UI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 250, 360, 640);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(10, 531, 254, 60);
		contentPane.add(textPane);
		RoundedButton btnSend = new RoundedButton("Send");
		btnSend.setBorderPainted(false);
		btnSend.setFont(new Font("HY견고딕", Font.PLAIN, 18));
		btnSend.setMargin(new Insets(2, 2, 2, 2));
		btnSend.setBounds(274, 531, 60, 60);
		contentPane.add(btnSend);
		
		JTextArea chatting = new JTextArea();
		chatting.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(chatting, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 10, 324, 480);
		contentPane.add(scrollPane);
		
		ImageIcon img = new ImageIcon("./img\\file.png");
		Image img2 = img.getImage();
		img2 = img2.getScaledInstance(28, 28, Image.SCALE_SMOOTH);
		img = new ImageIcon(img2);
		
		JButton btnNewButton = new JButton("File", img);
		btnNewButton.setMargin(new Insets(2, 2, 2, 2));
		btnNewButton.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		btnNewButton.setBounds(10, 495, 100, 30);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				new UseFileDialog();
			}
		});
		
		
		ImageIcon img3 = new ImageIcon("./img\\invite.png");
		Image img4 = img3.getImage();
		img4 = img4.getScaledInstance(28, 28, Image.SCALE_SMOOTH);
		img3 = new ImageIcon(img4);
		
		JButton btnAddUser = new JButton("Invite", img3);
		btnAddUser.setMargin(new Insets(2, 2, 2, 2));
		btnAddUser.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		btnAddUser.setBounds(120, 495, 100, 30);
		contentPane.add(btnAddUser);
		btnAddUser.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				new AddUser();
			}
		});
		
		JButton btnExit = new JButton("Exit");
		btnExit.setMargin(new Insets(2, 2, 2, 2));
		btnExit.setFont(new Font("HY견고딕", Font.PLAIN, 12));
		btnExit.setBounds(230, 495, 60, 30);
		contentPane.add(btnExit);
		btnExit.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				setVisible(false);
			}
		});
		
		
		this.setResizable(false);
	}
	
	
	

 class RoundedButton extends JButton {
      public RoundedButton() { super(); decorate(); } 
      public RoundedButton(String text) { super(text); decorate(); } 
      public RoundedButton(Action action) { super(); decorate(); } 
      public RoundedButton(Icon icon) { super(icon); decorate(); } 
      public RoundedButton(String text, Icon icon) { super(text, icon); decorate(); } 
      protected void decorate() { setBorderPainted(false); setOpaque(false); }
      @Override 
      protected void paintComponent(Graphics g) {
         Color c= new Color(250, 185, 23); //배경색 결정
         Color o= Color.WHITE; //글자색 결정
         int width = getWidth(); 
         int height = getHeight(); 
         Graphics2D graphics = (Graphics2D) g; 
         graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
         if (getModel().isArmed()) { graphics.setColor(c.darker()); } 
         else if (getModel().isRollover()) { graphics.setColor(c.brighter()); } 
         else { graphics.setColor(c); } 
         graphics.fillRoundRect(0, 0, width, height, 10, 10); 
         FontMetrics fontMetrics = graphics.getFontMetrics(); 
         Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds(); 
         int textX = (width - stringBounds.width) / 2; 
         int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent(); 
         graphics.setColor(o); 
         graphics.setFont(getFont()); 
         graphics.drawString(getText(), textX, textY); 
         graphics.dispose(); 
         super.paintComponent(g); 
         }
      }
}