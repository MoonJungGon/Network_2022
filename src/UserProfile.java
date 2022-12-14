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


public class UserProfile {

	private JFrame frame;
	private String user_id; // id
	private String user_name;  // name
	private String user_connect; // is online or offline
	private String user_lastLogin; // last login time 
	private String user_todayWords; // today's words

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UserProfile window = new UserProfile();
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
	public UserProfile(String tmpId, String tmpName, String tmpConnect, String tmpLastLogin, String tmpTodayWords) {
		user_id=tmpId;
		user_name=tmpName;
		user_connect=tmpConnect;
		user_lastLogin=tmpLastLogin;
		user_todayWords=tmpTodayWords;
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 732, 548);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 714, 501);
		frame.getContentPane().add(panel);
		panel.setBackground(new Color(255, 255, 240));
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 300, 300);
		panel.add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(UserProfile.class.getResource("/PngFile/profile_img_2.png")));
		
		JLabel nameLabel = new JLabel("Name");
		nameLabel.setFont(new Font("HY°ß°íµñ", Font.BOLD, 25));
		nameLabel.setText(user_name);
		nameLabel.setBounds(73, 312, 227, 55);
		panel.add(nameLabel);
		
		JLabel idLabel = new JLabel("Id");
		idLabel.setText(user_id);
		idLabel.setFont(new Font("HY°ß°íµñ", Font.BOLD, 21));
		idLabel.setBounds(73, 364, 227, 55);
		panel.add(idLabel);
		
		RoundedButtonF connectLabel = new RoundedButtonF("Offline");
		
		if(user_connect.equals("online")){
			connectLabel.setBackground(new Color(0,255,0));
			connectLabel.setText("Online");
		}else if(user_connect.equals("offline")){
			connectLabel.setBackground(new Color(211,211,211));
			connectLabel.setText("Offline");
		}
		
		connectLabel.setFont(new Font("HY°ß°íµñ", Font.BOLD, 17));
		connectLabel.setBounds(537, 12, 100, 100);
		panel.add(connectLabel);
		
		JLabel lblLast = new JLabel("Last Log In --->");
		lblLast.setFont(new Font("HY°ß°íµñ", Font.BOLD, 21));
		lblLast.setBounds(73, 431, 204, 55);
		panel.add(lblLast);
		
		JLabel lastLoginLabel = new JLabel("Date");
		lastLoginLabel.setText(user_lastLogin);
		lastLoginLabel.setFont(new Font("HY°ß°íµñ", Font.BOLD, 21));
		lastLoginLabel.setBounds(291, 439, 278, 39);
		panel.add(lastLoginLabel);
		
		JLabel lblNewLabel_2 = new JLabel("Today's Words");
		lblNewLabel_2.setFont(new Font("HY°ß°íµñ", Font.BOLD, 25));
		lblNewLabel_2.setBounds(329, 120, 227, 60);
		panel.add(lblNewLabel_2);
		
		JLabel todayLabel = new JLabel("Today's Words");
		todayLabel.setFont(new Font("HY°ß°íµñ", Font.BOLD, 25));
		todayLabel.setText(user_todayWords);
		todayLabel.setBounds(329, 192, 349, 55);
		panel.add(todayLabel);
		
		JButton closeBT = new JButton("X");
		closeBT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
			}
		});
		closeBT.setFocusPainted(false);
		closeBT.setContentAreaFilled(false);
		closeBT.setBorderPainted(false);
		closeBT.setBackground(Color.WHITE);
		closeBT.setFont(new Font("HY°ß°íµñ", Font.BOLD, 18));
		closeBT.setBounds(651, 12, 55, 46);
		panel.add(closeBT);
		
		frame.setVisible(true);
	}
	
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
