import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class SignUp {

	private JFrame frame;
	private JTextField signId;
	private JTextField signName;
	private JTextField signNick;
	private JTextField signEmail;
	private JTextField signBirth;
	private JTextField signPhone;
	private JPasswordField signPwd;
	
	private Socket signSock; // Socket for sign up
	private Scanner in;
	private PrintWriter out;
	private RoundedButtonF signUpBT;
	private RoundedButtonF idCheckBT;
	private int isIdCheck=0;
	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp window = new SignUp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 */
	public SignUp(Socket tmpSock) {
		
		signSock=tmpSock;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		try {
			
		in=new Scanner(signSock.getInputStream()); 
		out=new PrintWriter(signSock.getOutputStream(),true);
		
		frame = new JFrame();
		frame.setBounds(100, 100, 481, 599);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(0, 0, 463, 552);
		panel.setBackground(new Color(231,223,216));
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel signImage = new JLabel("");
		signImage.setIcon(new ImageIcon(SignUp.class.getResource("/PngFile/SignUp_Img.png")));
		signImage.setBounds(160, 0, 150, 150);
		
		panel.add(signImage);
		
		signId = new JTextField();
		signId.setFont(new Font("HY∞ﬂ∞ÌµÒ", Font.BOLD, 19));
		signId.setForeground(SystemColor.activeCaptionBorder);
		signId.setText("ID");
		signId.setBounds(115, 162, 234, 32);
		panel.add(signId);
		signId.setColumns(10);
		
		signPwd = new JPasswordField();
		signPwd.setFont(new Font("HY∞ﬂ∏Ì¡∂", Font.BOLD, 19));
		
		signPwd.setForeground(SystemColor.activeCaptionBorder);
		signPwd.setColumns(10);
		signPwd.setBounds(115, 216, 234, 32);
		panel.add(signPwd);
		
		signPwd.setEchoChar('*');
		
		signName = new JTextField();
		signName.setFont(new Font("HY∞ﬂ∞ÌµÒ", Font.BOLD, 19));
		signName.setText("Name");
		signName.setColumns(10);
		signName.setForeground(SystemColor.activeCaptionBorder);
		signName.setBounds(115, 265, 234, 32);
		panel.add(signName);
		
		signNick = new JTextField();
		signNick.setFont(new Font("HY∞ﬂ∞ÌµÒ", Font.BOLD, 19));
		signNick.setText("NickName");
		signNick.setColumns(10);
		signNick.setForeground(SystemColor.activeCaptionBorder);
		signNick.setBounds(115, 315, 234, 32);
		panel.add(signNick);
		
		signEmail = new JTextField();
		signEmail.setFont(new Font("HY∞ﬂ∞ÌµÒ", Font.BOLD, 19));
		signEmail.setText("E_Mail");
		signEmail.setColumns(10);
		signEmail.setForeground(SystemColor.activeCaptionBorder);
		signEmail.setBounds(115, 360, 234, 32);
		panel.add(signEmail);
		
		signBirth = new JTextField();
		signBirth.setFont(new Font("HY∞ﬂ∞ÌµÒ", Font.BOLD, 15));
		signBirth.setText("BirthDate(0000_00_00)");
		signBirth.setForeground(SystemColor.activeCaptionBorder);
		signBirth.setColumns(10);
		signBirth.setBounds(115, 408, 234, 32);
		panel.add(signBirth);
		
		signPhone = new JTextField();
		signPhone.setFont(new Font("HY∞ﬂ∞ÌµÒ", Font.BOLD, 19));
		signPhone.setText("Phone Number");
		signPhone.setForeground(SystemColor.activeCaptionBorder);
		signPhone.setColumns(10);
		signPhone.setBounds(115, 453, 234, 32);
		panel.add(signPhone);
		
		signId.addMouseListener(new MouseListener(){
			
			@Override
			public void mouseClicked(MouseEvent e){
				signId.setText("");
				signId.setForeground(Color.BLACK);
				signId.setFont(new Font("HY∞ﬂ∞ÌµÒ", Font.PLAIN, 19));
			}
			
			@Override
			public void mousePressed(MouseEvent e){
				
				
			}
			
			@Override
			public void mouseReleased(MouseEvent e){
				
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e){
				
				
			}
			@Override
			public void mouseExited(MouseEvent e){
				
				
			}
			
			
		});
		signPwd.addMouseListener(new MouseListener(){
			
			@Override
			public void mouseClicked(MouseEvent e){
				signPwd.setText("");
				signPwd.setForeground(Color.BLACK);
				signPwd.setFont(new Font("HY∞ﬂ∞ÌµÒ", Font.PLAIN, 19));
			}
			
			@Override
			public void mousePressed(MouseEvent e){
				
				
			}
			
			@Override
			public void mouseReleased(MouseEvent e){
				
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e){
				
				
			}
			@Override
			public void mouseExited(MouseEvent e){
				
				
			}
			
			
		});
		
	
		signName.addMouseListener(new MouseListener(){
			
			@Override
			public void mouseClicked(MouseEvent e){
				signName.setText("");
				signName.setForeground(Color.BLACK);
				signName.setFont(new Font("HY∞ﬂ∞ÌµÒ", Font.PLAIN, 19));
			}
			
			@Override
			public void mousePressed(MouseEvent e){
				
				
			}
			
			@Override
			public void mouseReleased(MouseEvent e){
				
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e){
				
				
			}
			@Override
			public void mouseExited(MouseEvent e){
				
				
			}
			
			
		});
	
		signNick.addMouseListener(new MouseListener(){
			
			@Override
			public void mouseClicked(MouseEvent e){
				signNick.setText("");
				signNick.setForeground(Color.BLACK);
				signNick.setFont(new Font("HY∞ﬂ∞ÌµÒ", Font.PLAIN, 19));
			}
			
			@Override
			public void mousePressed(MouseEvent e){
				
				
			}
			
			@Override
			public void mouseReleased(MouseEvent e){
				
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e){
				
				
			}
			@Override
			public void mouseExited(MouseEvent e){
				
				
			}
			
			
		});
	
		signEmail.addMouseListener(new MouseListener(){
			
			@Override
			public void mouseClicked(MouseEvent e){
				signEmail.setText("");
				signEmail.setForeground(Color.BLACK);
				signEmail.setFont(new Font("HY∞ﬂ∞ÌµÒ", Font.PLAIN, 19));
			}
			
			@Override
			public void mousePressed(MouseEvent e){
				
				
			}
			
			@Override
			public void mouseReleased(MouseEvent e){
				
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e){
				
				
			}
			@Override
			public void mouseExited(MouseEvent e){
				
				
			}
			
			
		});
	
		signBirth.addMouseListener(new MouseListener(){
			
			@Override
			public void mouseClicked(MouseEvent e){
				signBirth.setText("");
				signBirth.setForeground(Color.BLACK);
				signBirth.setFont(new Font("HY∞ﬂ∞ÌµÒ", Font.PLAIN, 19));
			}
			
			@Override
			public void mousePressed(MouseEvent e){
				
				
			}
			
			@Override
			public void mouseReleased(MouseEvent e){
				
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e){
				
				
			}
			@Override
			public void mouseExited(MouseEvent e){
				
				
			}
			
			
		});
	
		signPhone.addMouseListener(new MouseListener(){
			
			@Override
			public void mouseClicked(MouseEvent e){
				signPhone.setText("");
				signPhone.setForeground(Color.BLACK);
				signPhone.setFont(new Font("HY∞ﬂ∞ÌµÒ", Font.PLAIN, 19));
			}
			
			@Override
			public void mousePressed(MouseEvent e){
				
				
			}
			
			@Override
			public void mouseReleased(MouseEvent e){
				
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e){
				
				
			}
			@Override
			public void mouseExited(MouseEvent e){
				
				
			}
			
			
			
		});
	
		
		idCheckBT = new RoundedButtonF("Check");
		idCheckBT.setFont(new Font("HY∞ﬂ∞ÌµÒ", Font.BOLD, 14));
		idCheckBT.setBounds(362, 163, 87, 31);
		idCheckBT.setBackground(new Color(255,51,153));
		panel.add(idCheckBT);
				
		idCheckBT.addActionListener(new checkIDAction());
		
		
		signUpBT = new RoundedButtonF("Sign Up");
		signUpBT.setFont(new Font("HY∞ﬂ∞ÌµÒ", Font.BOLD, 19));
		signUpBT.setBounds(115, 500, 234, 40);
		signUpBT.setBackground(new Color(111,79,40));
		
		signUpBT.addActionListener(new sendSignAction());
		
		panel.add(signUpBT);
		
		frame.setVisible(true);
		}
		catch(Exception e) { 
			System.out.println("Error:" +signSock);
		}
	}
	
	// when the check Id button clicked
	class checkIDAction implements ActionListener{ 

		public void actionPerformed(ActionEvent arg0) {
		
			
			String tmpId=signId.getText();  // Variable to receive the ID you entered

			
			try {
							
				if(tmpId.indexOf(" ")!=-1) { // if id contains blank
					
					JOptionPane.showMessageDialog(null,"You Can't Use Blank! Try Again!");
					signId.setForeground(Color.RED);
					
				}else {
					
					out.println("777"); // Attempt to verify that the ID is available
					
					out.println(tmpId); 
					String loginCheckCode= in.nextLine();	// Get ID availability code
					
					if(loginCheckCode.equals("700")) { // if there is no duplicate ID and it is available
						
						signId.setForeground(Color.BLACK);
						idCheckBT.setBackground(new Color(0,212,255));
						idCheckBT.setText("Checked");
						isIdCheck=1;
					}
					else if(loginCheckCode.equals("708")) { // if this ID is unavailable due to duplication
						signId.setForeground(Color.RED);
						JOptionPane.showMessageDialog(null,"ID is already used! Try Again!");
					
				}
			
			}
				
				
				
				
			}catch(Exception e) { 
				System.out.println("Error:" + signSock);
			}
			/*finally {
				try { signSock.close(); } catch (IOException e) {} 
				System.out.println("Closed: " + signSock);
					
			}*/
			
			
		
	}
	
	}
	
	// when sign button clicked
	class sendSignAction implements ActionListener{ 

		public void actionPerformed(ActionEvent arg0) {
		
			String tmpId=signId.getText();// Variable to receive the ID you entered
			char[] tmpPwd_2=signPwd.getPassword();  // Variable to receive the password you entered
			String tmpPwd_=new String(tmpPwd_2); // Variables that convert tmpPwd to string form and store it
			String tmpName=signName.getText(); // Variable to receive the name you entered
			String tmpNick=signNick.getText();// Variable to receive the nickname entered
			String tmpEmail=signEmail.getText(); // Variable to receive the email you entered
			String tmpBirth=signBirth.getText(); // Received variable for the date of birth entered
			String tmpPhone=signPhone.getText(); // Accepting variables for phone numbers
			
			
			try {

				
				if(tmpPwd_.equals("")) {
					
					JOptionPane.showMessageDialog(null,"You Didn't Write PassWord! Write it!");
				}
				else if(tmpName.equals("Name")) {
					
					JOptionPane.showMessageDialog(null,"You Didn't Write Your Name! Write it!");
				}else if(tmpNick.equals("NickName")) {
					
					JOptionPane.showMessageDialog(null,"You Didn't Write Your NickName! Write it!");
				}
				else if(tmpEmail.equals("E_Mail")) {
					
					JOptionPane.showMessageDialog(null,"You Didn't Write Your Email! Write it!");
				}
				else if(tmpBirth.equals("Birth Date")) {
					
					JOptionPane.showMessageDialog(null,"You Didn't Write Your BirthDate! Write it!");
				}else if(tmpBirth.equals("Phone Number")) {
					
					JOptionPane.showMessageDialog(null,"You Didn't Write Your BirthDate! Write it!");
				}else if(isIdCheck==0) {
					
					JOptionPane.showMessageDialog(null,"You Didn't Check Your Id! Write it!");
				}else if(tmpPhone.equals("Phone Number")) {
					out.println("555"); //Attempt to sign up for membership
					out.println(tmpId);
					out.println(calculate.encode(tmpPwd_,tmpPwd_.length()));
					out.println(tmpName);
					out.println(tmpNick);
					out.println(tmpEmail);
					out.println(tmpBirth);
					String makeNull=null;
					out.println(makeNull);
					
					
					JOptionPane.showMessageDialog(null,"Success Sign Up!");
					frame.setVisible(false);
					new Login(signSock);
				}else {
					
					out.println("555"); // Attempt to sign up for membership
					out.println(tmpId);
					out.println(calculate.encode(tmpPwd_,tmpPwd_.length()));
					out.println(tmpName);
					out.println(tmpNick);
					out.println(tmpEmail);
					out.println(tmpBirth);
					out.println(tmpPhone);
					
					JOptionPane.showMessageDialog(null,"Success Sign Up!");
					frame.setVisible(false);
					new Login(signSock);
				}
				
				
				
				
			}catch(Exception e) { 
				System.out.println("Error:" + signSock);
			}
			/*finally {
				try { signSock.close(); } catch (IOException e) {} 
				System.out.println("Closed: " + signSock);
					
			}*/
			
			
			
		
			
			
			
			
		
	}
	
	}
	// round button form
	public class RoundedButtonF extends JButton {


	    public RoundedButtonF() {
	        super();
	        decorate();
	    }

	    public RoundedButtonF(String text) {
	        super(text);
	        decorate();
	    }

	    public RoundedButtonF(Action action) {
	        super(action);
	        decorate();
	    }

	    public RoundedButtonF(Icon icon) {
	        super(icon);
	        decorate();
	    }

	    public RoundedButtonF(String text, Icon icon) {
	        super(text, icon);
	        decorate();
	    }

	    protected void decorate() {
	        setBorderPainted(false);
	        setOpaque(false);
	    }
	    @Override
		protected void paintComponent(Graphics g) {
		    int width = getWidth();
		    int height = getHeight();

		    Graphics2D graphics = (Graphics2D) g;

		    graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		    if (getModel().isArmed()) {
		        graphics.setColor(getBackground().darker());
		    } else if (getModel().isRollover()) {
		        graphics.setColor(getBackground().brighter());
		    } else {
		        graphics.setColor(getBackground());
		    }

		    graphics.fillRoundRect(0, 0, width, height, 10, 10);

		    FontMetrics fontMetrics = graphics.getFontMetrics();
		    Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds();

		    int textX = (width - stringBounds.width) / 2;
		    int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent();

		    graphics.setColor(getForeground());
		    graphics.setFont(getFont());
		    graphics.drawString(getText(), textX, textY);
		    graphics.dispose();

		    super.paintComponent(g);
		}
	}
	
	
	
}
