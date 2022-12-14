import java.awt.event.*;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;
import java.awt.*;
import javax.swing.border.LineBorder;
import javax.swing.*;
import javax.swing.event.*;
import java.util.*;



public class mainBoard {

	private JFrame frame;
	private GridBagLayout Gbag = new GridBagLayout();
	private GridBagConstraints gbc1;
	private JScrollPane searchFriendP; // The part where the searched user appears
	private JScrollPane friendListP; // Where the list of friends appears
	private JScrollPane chattRoomP; // The part where the chatroom pops up
	private JPanel listContent; // A panel that actually contains the content in the scrollpan that appears in the Friend List
	private JPanel searchContent; // A panel that actually contains the contents in the scroll pane that the searched user appears
	private JPanel roomContent; // A panel that actually contains the contents in the scroll pane where the chat room appears
	
	private JPanel listContent_2; // A panel that actually contains the content in the scrollpan that appears in the Friend List
	private JPanel searchContent_2; // A panel that actually contains the contents in the scroll pane that the searched user appears
	private JPanel roomContent_2; // A panel that actually contains the contents in the scroll pane where the chat room appears
	
	
	private int firstListShow=0; // Variables used to display a list of friends at the beginning of the gel after login
	private Socket mainSock; // socket for main board
	private Scanner in;
	private PrintWriter out;
	private String friends;// name for friends
	private  int count =1; // for scroll pane design
	private String userId;
	private JTextField searchText; // search field
	private JPanel searchTotal; // search bar
	
	private String roomMaker;
	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainBoard window = new mainBoard();
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
	public mainBoard(Socket tmp, String usrID) {
		mainSock=tmp;
		userId=usrID;
		initialize();
	}
	/*
	public mainBoard() {
		initialize();
	}*/
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		try {
			
		in=new Scanner(mainSock.getInputStream()); 
		out=new PrintWriter(mainSock.getOutputStream(),true);
		frame = new JFrame();
		frame.setBounds(100, 100, 915, 647);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel totalPl = new JPanel(); // Full Parent Panel
		totalPl.setBounds(0, 0, 897, 600);
		frame.getContentPane().add(totalPl);
		totalPl.setLayout(null);
		
		JPanel selectP = new JPanel(); // PANEL WITH SELECTION BUTTON
		selectP.setBackground(new Color(251, 206, 177));
		selectP.setBounds(0, 0, 109, 600);
		totalPl.add(selectP);
		selectP.setLayout(null);
		
		RoundedButtonF friendListBT = new RoundedButtonF("Friends"); // Friend List button
		friendListBT.setBounds(25, 35, 65, 65);
		friendListBT.setBackground(new Color(197,154,127));
		friendListBT.setFont(new Font("HY°ß°íµñ", Font.BOLD, 12));
		selectP.add(friendListBT);
		friendListBT.addActionListener(new listAction());
		
		RoundedButtonF searchFriendBT = new RoundedButtonF("Search"); // Search for friends button
		searchFriendBT.setBounds(25, 135, 65, 65);
		searchFriendBT.setFont(new Font("HY°ß°íµñ", Font.BOLD, 15));
		searchFriendBT.setBackground(new Color(197,154,127));
		selectP.add(searchFriendBT);
		searchFriendBT.addActionListener(new searchAction());
		
		RoundedButtonF chattRoomBT = new RoundedButtonF("Room"); // A button to display the chat room list
		chattRoomBT.setBounds(25, 235, 65, 65);
		chattRoomBT.setFont(new Font("HY°ß°íµñ", Font.BOLD, 15));
		chattRoomBT.setBackground(new Color(197,154,127));
		selectP.add(chattRoomBT);
		chattRoomBT.addActionListener(new roomAction());
		
		JButton profileBT = new JButton("");
		profileBT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					
					out.println("768");	
					out.println(userId);
					String tmpId=in.nextLine();
					String tmpName=in.nextLine();
					String tmpConnect=in.nextLine();
					String tmpLastLogin=in.nextLine();
					String tmpTodayWords=in.nextLine();
					
					new UserProfile(tmpId,tmpName,tmpConnect,tmpLastLogin,tmpTodayWords);
					
					
				}catch(Exception e1) { 
					System.out.println("Error:" +mainSock);
				}
				
			}
		});
		profileBT.setIcon(new ImageIcon(Client.class.getResource("/PngFile/selectProfile_Img.png")));
		profileBT.setFocusPainted(false);
		profileBT.setContentAreaFilled(false);
		profileBT.setBorderPainted(false);
		profileBT.setBackground(Color.WHITE);
		profileBT.setBounds(24, 324, 65, 65);
		selectP.add(profileBT);
		
		
		JButton changeInfoBT = new JButton(""); // profile edit button
		changeInfoBT.setIcon(new ImageIcon(Client.class.getResource("/PngFile/changeprof_Img_3.png")));
		changeInfoBT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new changeUserInfo(mainSock);
			}
		});
		
		changeInfoBT.setBackground(Color.WHITE);
		changeInfoBT.setBorderPainted(false);
		changeInfoBT.setContentAreaFilled(false);
		changeInfoBT.setFocusPainted(false);
		changeInfoBT.setBounds(25, 409, 65, 65);
		selectP.add(changeInfoBT);
		
		frame.setVisible(true);
		
		JButton exitBT = new JButton(""); // log out button
		exitBT.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
					out.println("169"); // log out
					JOptionPane.showMessageDialog(null,"Log Out!!!");
					frame.setVisible(false);
					new Login(mainSock);
					
				}catch(Exception e) { 
					System.out.println("Error:" +mainSock);
				}
				
			}
		});
		
		exitBT.setIcon(new ImageIcon(Client.class.getResource("/PngFile/exit_Img.png"))); // log out button
		exitBT.setBounds(25, 507, 65, 65);
		
		exitBT.setBackground(Color.WHITE);
		exitBT.setBorderPainted(false);
		exitBT.setContentAreaFilled(false);
		exitBT.setFocusPainted(false);
		selectP.add(exitBT);
		
		
		

		// API °ª ºÒ·¯¿À±â
		JPanel mainP = new JPanel();
		mainP.setBackground(new Color(255, 204, 102));
		mainP.setBounds(108, 0, 789, 600);
		totalPl.add(mainP);
		mainP.setLayout(null);
		
		String data[][] = new String[6][2];
		try {
			data = ApiWeather.getWeather();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		JPanel newsP = new JPanel();
		newsP.setBackground(new Color(255, 255, 255));
		newsP.setBounds(0, 0, 789, 81);
		newsP.setLayout(null);
		
		ImageIcon img = new ImageIcon(mainBoard.class.getResource("/PngFile/thermometer2.png"));
		if(Integer.parseInt(data[0][1]) < 20) {
			img = new ImageIcon(mainBoard.class.getResource("/PngFile/thermometer1.png"));
		}
		Image temp = img.getImage();
		temp = temp.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		img = new ImageIcon(temp);
		JLabel lblApiData0 = new JLabel(data[0][0] + " : " + data[0][1] + "¡É");
		lblApiData0.setIcon(img);
		lblApiData0.setBounds(30, 9, 180, 27);
		newsP.add(lblApiData0);
		
		ImageIcon img2 = new ImageIcon(mainBoard.class.getResource("/PngFile/rain.png"));
		temp = img2.getImage();
		temp = temp.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		img2 = new ImageIcon(temp);
		JLabel lblApiData1 = new JLabel(data[1][0] + " : " + data[1][1] + "mm");
		lblApiData1.setBounds(30, 45, 180, 27);
		lblApiData1.setIcon(img2);
		newsP.add(lblApiData1);
		
		ImageIcon img3 = new ImageIcon(mainBoard.class.getResource("/PngFile/humidity.png"));
		temp = img3.getImage();
		temp = temp.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		img3 = new ImageIcon(temp);
		JLabel lblApiData2 = new JLabel(data[2][0] + " : " + data[2][1] + "%");
		lblApiData2.setBounds(230, 9, 180, 27);
		lblApiData2.setIcon(img3);
		newsP.add(lblApiData2);
		
		ImageIcon img4 = new ImageIcon(mainBoard.class.getResource("/PngFile/weather.png"));
		temp = img4.getImage();
		temp = temp.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		img4 = new ImageIcon(temp);
		JLabel lblApiData3 = new JLabel(data[3][0] + " : " + data[3][1]);
		lblApiData3.setBounds(230, 45, 180, 27);
		lblApiData3.setIcon(img4);
		newsP.add(lblApiData3);
		
		ImageIcon img5 = new ImageIcon(mainBoard.class.getResource("/PngFile/windway.png"));
		temp = img5.getImage();
		temp = temp.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		img5 = new ImageIcon(temp);
		JLabel lblApiData4 = new JLabel(data[4][0] + " : " + data[4][1]);
		lblApiData4.setBounds(430, 9, 180, 27);
		lblApiData4.setIcon(img5);
		newsP.add(lblApiData4);
		
		ImageIcon img6 = new ImageIcon(mainBoard.class.getResource("/PngFile/windspeed.png"));
		temp = img6.getImage();
		temp = temp.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		img6 = new ImageIcon(temp);
		JLabel lblApiData5 = new JLabel(data[5][0] + " : " + data[5][1] + "m/s");
		lblApiData5.setBounds(430, 45, 180, 27);
		lblApiData5.setIcon(img6);
		newsP.add(lblApiData5);
		
		ImageIcon img0 = new ImageIcon(mainBoard.class.getResource("/PngFile/KMA.png"));
		temp = img0.getImage();
		temp = temp.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		img0 = new ImageIcon(temp);
		JLabel lblApiinfo = new JLabel("<html><body><center>Korea Meteorological Administration</center></body></html>");
		lblApiinfo.setBounds(620, 9, 159, 63);
		lblApiinfo.setIcon(img0);
		newsP.add(lblApiinfo);
		mainP.add(newsP);
		
		listContent = new JPanel();
		listContent.setLayout(Gbag);  
		listContent.setBackground(Color.WHITE);
		
		searchContent = new JPanel();
		searchContent.setLayout(Gbag);  
		searchContent.setBackground(Color.WHITE);
		
		roomContent = new JPanel();
		roomContent.setLayout(Gbag);  
		roomContent.setBackground(Color.WHITE);
		
		searchFriendP = new JScrollPane(searchContent);
		searchFriendP.setBounds(0, 181, 789, 419);
		searchFriendP.getViewport().setBackground(Color.WHITE);
		searchFriendP.setBorder(new LineBorder(new Color(230,230,230)));
		mainP.add(searchFriendP);
		
		friendListP = new JScrollPane(listContent);
		friendListP.setBounds(0, 81, 789, 519);
		friendListP.getViewport().setBackground(Color.WHITE);
		friendListP.setBorder(new LineBorder(new Color(230,230,230)));
		mainP.add(friendListP);
		
		
		chattRoomP = new JScrollPane(roomContent);
		chattRoomP.setBounds(0, 81, 789, 519);
		chattRoomP.getViewport().setBackground(Color.WHITE);
		chattRoomP.setBorder(new LineBorder(new Color(230,230,230)));
		mainP.add(chattRoomP);
		
		
		searchTotal = new JPanel();
		searchTotal.setBounds(0, 81, 789, 100);
		mainP.add(searchTotal);
		searchTotal.setLayout(null);
		
		RoundedButtonF searchBT = new RoundedButtonF("Search"); // just for search user
		searchBT.setFont(new Font("HY°ß°íµñ", Font.BOLD, 17));
		searchBT.setBounds(659, 12, 105, 76);
		searchBT.setBackground(new Color(197,154,127));
		searchTotal.add(searchBT);
		searchBT.addActionListener(new searchBTAction());
		
		searchText = new JTextField();
		searchText.setFont(new Font("HY°ß°íµñ", Font.PLAIN, 17));
		searchText.setBounds(45, 23, 552, 54);
		searchTotal.add(searchText);
		searchText.setColumns(10);
				
		searchTotal.setVisible(false);
		chattRoomP.setVisible(false);
		friendListP.setVisible(true);
		searchFriendP.setVisible(false);
		
		if(firstListShow==0) { // when login success, show the friend list first
			out.println("888");
			
			
			while(!(friends=in.nextLine()).equals("800")) { // loop until server send 800 
				
				String checkOnline=in.nextLine(); // check whether the user is online or not
				String friendId=in.nextLine();
				
				PopupMenu listMenu=new PopupMenu(); // Pop Up Menu
				MenuItem listMenu_1=new MenuItem("Profile");
				MenuItem listMenu_2=new MenuItem("1:1 Chat");
				MenuItem listMenu_3=new MenuItem("Sending File");
				MenuItem listMenu_4=new MenuItem("Start 1:1 Game");
				listMenu.add(listMenu_1);
				listMenu.add(listMenu_2);
				listMenu.add(listMenu_3);
				listMenu.add(listMenu_4);
				listMenu_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						try {
							
							
							out.println("768");	
							out.println(friendId);
							String tmpId=in.nextLine();
							String tmpName=in.nextLine();
							String tmpConnect=in.nextLine();
							String tmpLastLogin=in.nextLine();
							String tmpTodayWords=in.nextLine();
							
							new UserProfile(tmpId,tmpName,tmpConnect,tmpLastLogin,tmpTodayWords);
							
							
						}catch(Exception e1) { 
							System.out.println("Error:" +mainSock);
						}
						
					}
				});
				listMenu_2.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						JOptionPane.showMessageDialog(null,"Wanna Chat 1 by 1!!!");	
					}
				});
				listMenu_3.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						JOptionPane.showMessageDialog(null,"Wanna Send File!!!");	
					}
				});
				listMenu_4.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						JOptionPane.showMessageDialog(null,"Wanna Start 1:1 Game!!!");	
					}
				});
				
				
				
				JPanel cont=new JPanel(); // for each friend
				cont.setBackground(Color.WHITE);
				cont.setSize(789, 100);
				cont.setLayout(new BorderLayout(0, 0));
				cont.add(listMenu);
				
				
				cont.addMouseListener(new MouseAdapter(){
					
					@Override
					public void mousePressed(MouseEvent me) {
						
						if(me.getButton()==MouseEvent.BUTTON3) {
							
							listMenu.show(cont,me.getX(),me.getY());
						}
						
					}
					
					
				});
				
				
				JLabel cont_img=new JLabel();
				cont_img.setIcon(new ImageIcon(mainBoard.class.getResource("/PngFile/profile_img.png")));
				
				JButton prof_id=new JButton(friends+"                                                                                                          ");
				prof_id.setBackground(Color.WHITE);
				prof_id.setBorderPainted(false);
				prof_id.setContentAreaFilled(false);
				prof_id.setFocusPainted(false);
				
				PopupMenu listMenu0=new PopupMenu(); // Pop Up Menu
				MenuItem listMenu_a=new MenuItem("Profile");
				MenuItem listMenu_b=new MenuItem("1:1 Chat");
				MenuItem listMenu_c=new MenuItem("Sending File");
				MenuItem listMenu_d=new MenuItem("Start 1:1 Game");
				listMenu0.add(listMenu_a);
				listMenu0.add(listMenu_b);
				listMenu0.add(listMenu_c);
				listMenu0.add(listMenu_d);
				listMenu_a.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						try {
							
							
							out.println("768");	
							out.println(friendId);
							String tmpId=in.nextLine();
							String tmpName=in.nextLine();
							String tmpConnect=in.nextLine();
							String tmpLastLogin=in.nextLine();
							String tmpTodayWords=in.nextLine();
							
							new UserProfile(tmpId,tmpName,tmpConnect,tmpLastLogin,tmpTodayWords);
							
							
						}catch(Exception e1) { 
							System.out.println("Error:" +mainSock);
						}
								
					}
				});
				listMenu_b.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						JOptionPane.showMessageDialog(null,"Wanna Chat 1 by 1!!!");	
					}
				});
				listMenu_c.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						JOptionPane.showMessageDialog(null,"Wanna Send File!!!");	
					}
				});
				listMenu_d.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						JOptionPane.showMessageDialog(null,"Wanna Start 1:1 Game!!!");	
					}
				});
				
				prof_id.add(listMenu0);
				prof_id.addMouseListener(new MouseAdapter(){
					
					@Override
					public void mousePressed(MouseEvent me) {
						
						if(me.getButton()==MouseEvent.BUTTON3) {
							
							listMenu.show(prof_id,me.getX(),me.getY());
						}
						
					}
					
					
				});
				JButton isOnline = new JButton("");
				isOnline.setSize(65,65);
				
				isOnline.setText("Offline");
				isOnline.setBackground(new Color(211,211,211));
				
				if(checkOnline.equals("online")) {
					isOnline.setText("Online");
					isOnline.setBackground(new Color(0,255,0));
					
				}else if(checkOnline.equals("offline")) {
					
					isOnline.setText("Offline");
					isOnline.setBackground(new Color(211,211,211));
				}
				
				
				isOnline.addActionListener(new ActionListener() { // try to make a room with the friend
					public void actionPerformed(ActionEvent arg0) {
						if(isOnline.getText().equals("Online")) { // success to make a room with the friend								
							Chat_room_UI chatUser=new Chat_room_UI();
							chatUser.setVisible(true);

						}else if(isOnline.getText().equals("Offline")){ // faile to make a romm with the friend
							JOptionPane.showMessageDialog(null,"You can't invite the friend!!!");								
						}

					}
				});
				
				cont.add(cont_img,BorderLayout.WEST);
				
				cont.add(isOnline,BorderLayout.EAST);
				
				JPanel centerP = new JPanel(); // just for prof_id
				cont.add(centerP, BorderLayout.CENTER);
				centerP.setLayout(new BorderLayout(0, 0));
				centerP.add(prof_id,BorderLayout.WEST);
				
				
			
				
				make_list_scroll(cont,0,count++*300,150,250);
			}
			
			
			firstListShow++;
		}
		
		
		
		
		
		
	
		}catch(Exception e) { 
			System.out.println("Error:" +mainSock);
		}
	}
	
	
	
	
	// when push the friendlist Button
	class listAction implements ActionListener{ 

		public void actionPerformed(ActionEvent arg0) {
			try {
				
				searchTotal.setVisible(false);
				chattRoomP.setVisible(false);
				friendListP.setVisible(true);
				searchFriendP.setVisible(false);
								
				out.println("888");
				
				friendListP.remove(listContent);
				
				listContent_2= new JPanel();
				
				listContent_2.setLayout(Gbag);  

				listContent_2.setBackground(Color.WHITE);
				 
				friendListP.setViewportView(listContent_2);
				
				while(!(friends=in.nextLine()).equals("800")) { // loop until server send 800 
					
					String checkOnline=in.nextLine(); // check whether the user is online or not
					String friendId=in.nextLine();
					
					PopupMenu listMenu=new PopupMenu(); // Pop Up Menu
					MenuItem listMenu_1=new MenuItem("Profile");
					MenuItem listMenu_2=new MenuItem("1:1 Chat");
					MenuItem listMenu_3=new MenuItem("Sending File");
					MenuItem listMenu_4=new MenuItem("Start 1:1 Game");
					listMenu.add(listMenu_1);
					listMenu.add(listMenu_2);
					listMenu.add(listMenu_3);
					listMenu.add(listMenu_4);
					listMenu_1.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							try {
								
								
								out.println("768");	
								out.println(friendId);
								String tmpId=in.nextLine();
								String tmpName=in.nextLine();
								String tmpConnect=in.nextLine();
								String tmpLastLogin=in.nextLine();
								String tmpTodayWords=in.nextLine();
								
								new UserProfile(tmpId,tmpName,tmpConnect,tmpLastLogin,tmpTodayWords);
								
								
							}catch(Exception e1) { 
								System.out.println("Error:" +mainSock);
							}
							
						}
					});
					listMenu_2.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							JOptionPane.showMessageDialog(null,"Wanna Chat 1 by 1!!!");	
						}
					});
					listMenu_3.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							JOptionPane.showMessageDialog(null,"Wanna Send File!!!");	
						}
					});
					listMenu_4.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							JOptionPane.showMessageDialog(null,"Wanna Start 1:1 Game!!!");	
						}
					});
					
					
					

					JPanel cont=new JPanel(); // for each friend
					cont.setBackground(Color.WHITE);
					cont.setSize(789, 100);
					cont.setLayout(new BorderLayout(0, 0));
					cont.add(listMenu);
					
					cont.addMouseListener(new MouseAdapter(){
						
						@Override
						public void mousePressed(MouseEvent me) {
							
							if(me.getButton()==MouseEvent.BUTTON3) {
								
								listMenu.show(cont,me.getX(),me.getY());
							}
							
						}
						
						
					});
					
					
					JLabel cont_img=new JLabel();
					cont_img.setIcon(new ImageIcon(mainBoard.class.getResource("/PngFile/profile_img.png")));
					
					JButton prof_id=new JButton(friends+"                                                                                                          ");
					prof_id.setBackground(Color.WHITE);
					prof_id.setBorderPainted(false);
					prof_id.setContentAreaFilled(false);
					prof_id.setFocusPainted(false);
					
					PopupMenu listMenu0=new PopupMenu(); // Pop Up Menu
					MenuItem listMenu_a=new MenuItem("Profile");
					MenuItem listMenu_b=new MenuItem("1:1 Chat");
					MenuItem listMenu_c=new MenuItem("Sending File");
					MenuItem listMenu_d=new MenuItem("Start 1:1 Game");
					listMenu0.add(listMenu_a);
					listMenu0.add(listMenu_b);
					listMenu0.add(listMenu_c);
					listMenu0.add(listMenu_d);
					listMenu_a.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							try {
								
								
								out.println("768");	
								out.println(friendId);
								String tmpId=in.nextLine();
								String tmpName=in.nextLine();
								String tmpConnect=in.nextLine();
								String tmpLastLogin=in.nextLine();
								String tmpTodayWords=in.nextLine();
								
								new UserProfile(tmpId,tmpName,tmpConnect,tmpLastLogin,tmpTodayWords);
								
								
							}catch(Exception e1) { 
								System.out.println("Error:" +mainSock);
							}
								
						}
					});
					listMenu_b.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							JOptionPane.showMessageDialog(null,"Wanna Chat 1 by 1!!!");	
						}
					});
					listMenu_c.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							JOptionPane.showMessageDialog(null,"Wanna Send File!!!");	
						}
					});
					listMenu_d.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							JOptionPane.showMessageDialog(null,"Wanna Start 1:1 Game!!!");	
						}
					});
					
					prof_id.add(listMenu0);
					prof_id.addMouseListener(new MouseAdapter(){
						
						@Override
						public void mousePressed(MouseEvent me) {
							
							if(me.getButton()==MouseEvent.BUTTON3) {
								
								listMenu0.show(prof_id,me.getX(),me.getY());
							}
							
						}
						
						
					});
					
					JButton isOnline = new JButton("");
					isOnline.setSize(65,65);
					
					isOnline.setText("Offline");
					isOnline.setBackground(new Color(211,211,211));
					
					if(checkOnline.equals("online")) {
						isOnline.setText("Online");
						isOnline.setBackground(new Color(0,255,0));
						
					}else if(checkOnline.equals("offline")) {
						
						isOnline.setText("Offline");
						isOnline.setBackground(new Color(211,211,211));
					}
					
					isOnline.addActionListener(new ActionListener() { // try to make a room with the friend
						public void actionPerformed(ActionEvent arg0) {
							if(isOnline.getText().equals("Online")) { // success to make a room with the friend								
								Chat_room_UI chatUser=new Chat_room_UI();
								chatUser.setVisible(true);

							}else if(isOnline.getText().equals("Offline")){ // faile to make a romm with the friend
								JOptionPane.showMessageDialog(null,"You can't invite the friend!!!");								
							}

						}
					});
					
					
					cont.add(cont_img,BorderLayout.WEST);
					
					cont.add(isOnline,BorderLayout.EAST);
					
					JPanel centerP = new JPanel(); // just for prof_id
					cont.add(centerP, BorderLayout.CENTER);
					centerP.setLayout(new BorderLayout(0, 0));
					centerP.add(prof_id,BorderLayout.WEST);
					
				
					
					make_list_scroll_2(cont,0,count++*300,150,250);
					
					
				}
			}catch(Exception e) { 
				System.out.println("Error:" +mainSock);
			}
		
		
		}
	}
	
	// when push the search Button
	class searchAction implements ActionListener{ 

		public void actionPerformed(ActionEvent arg0) {
		
			try {
				searchTotal.setVisible(true);
				chattRoomP.setVisible(false);
				friendListP.setVisible(false);
				searchFriendP.setVisible(true);
				
			}catch(Exception e) { 
				System.out.println("Error:" +mainSock);
			}
		
		}
	}
	
	// when push the id search Button
	class searchBTAction implements ActionListener{ 

		public void actionPerformed(ActionEvent arg0) {
		
			try {
				
				out.println("999"); // friend search
				String friendReadlID=searchText.getText();
				out.println(friendReadlID);
				
				
				String isUser=in.nextLine(); // check if there is the user
				
				if(isUser.equals("900")) {
					
					String isFriend=in.nextLine(); // check if they are friends
							
					searchFriendP.remove(searchContent);
					
					searchContent_2= new JPanel();
					
					searchContent_2.setLayout(Gbag);  

					searchContent_2.setBackground(Color.WHITE);
					 
					searchFriendP.setViewportView(searchContent_2);
					
					
					JPanel cont=new JPanel(); // for each friend
					cont.setBackground(Color.WHITE);
					cont.setSize(789, 100);
					cont.setLayout(new BorderLayout(0, 0));
					
					JLabel cont_img=new JLabel();
					cont_img.setIcon(new ImageIcon(mainBoard.class.getResource("/PngFile/profile_img.png")));
					
					JButton prof_id=new JButton(friendReadlID+"                                                                                          ");
					prof_id.setBackground(Color.WHITE);
					prof_id.setBorderPainted(false);
					prof_id.setContentAreaFilled(false);
					prof_id.setFocusPainted(false);
					
					JButton isFriendBT = new JButton("");
					isFriendBT.setSize(65,65);
					
					if(friendReadlID.equals(userId)) { // you can not add yourself for friend
						isFriendBT.setVisible(false);
						
					};
					
					isFriendBT.setText("Add");
					isFriendBT.setBackground(new Color(211,211,211));
					
					if(isFriend.equals("901")) { // friend O
						isFriendBT.setText("Friend");
						isFriendBT.setBackground(new Color(80,188,233));
						
					}else if(isFriend.equals("902")) { // friend X
						
						isFriendBT.setText("Add");
						isFriendBT.setBackground(new Color(211,211,211));
					}
					
					isFriendBT.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							if(isFriendBT.getText().equals("Friend")) {
								out.println("608"); //delete friend
								out.println(friendReadlID);
								isFriendBT.setText("Add");
								isFriendBT.setBackground(new Color(211,211,211));
								
							}else if(isFriendBT.getText().equals("Add")){
								out.println("666");// add friend
								out.println(friendReadlID);
								isFriendBT.setText("Friend");
								isFriendBT.setBackground(new Color(80,188,233));
								
							}
							
							
						}
					});
					
					
					cont.add(cont_img,BorderLayout.WEST);
					
					cont.add(isFriendBT,BorderLayout.EAST);
					
					JPanel centerP = new JPanel(); // just for prof_id
					cont.add(centerP, BorderLayout.CENTER);
					centerP.setLayout(new BorderLayout(0, 0));
					centerP.add(prof_id,BorderLayout.WEST);
					
					make_search_scroll_2(cont,0,count++*300,150,250);
					
				}else if(isUser.equals("908")) {
					
					JOptionPane.showMessageDialog(null,"the user doesn't exist!!!");
				}
				
				
			}catch(Exception e) { 
				System.out.println("Error:" +mainSock);
			}
		
		}
	}
	
	// when push the chatt room Button
	class roomAction implements ActionListener{ 

		public void actionPerformed(ActionEvent arg0) {
		
			try {
				
				searchTotal.setVisible(false);
				chattRoomP.setVisible(true);
				friendListP.setVisible(false);
				searchFriendP.setVisible(false);
				
				out.println("222");

				chattRoomP.remove(roomContent);
				
				roomContent_2= new JPanel();
				
				roomContent_2.setLayout(Gbag);  

				roomContent_2.setBackground(Color.WHITE);
				 
				chattRoomP.setViewportView(roomContent_2);
				
				while(!(roomMaker=in.nextLine()).equals("205")) {
					
					
					JPanel cont=new JPanel(); // for each friend
					cont.setBackground(Color.WHITE);
					cont.setSize(789, 100);
					cont.setLayout(new BorderLayout(0, 0));
					
					JLabel cont_img=new JLabel();
					cont_img.setIcon(new ImageIcon(mainBoard.class.getResource("/PngFile/profile_img.png")));
					
					JButton prof_id=new JButton(roomMaker+"                                                                                                          ");
					prof_id.setBackground(Color.WHITE);
					prof_id.setBorderPainted(false);
					prof_id.setContentAreaFilled(false);
					prof_id.setFocusPainted(false);
					
					RoundedButtonF isParti = new RoundedButtonF(""); // check whether participate into the room and attempt to enter the room
					isParti.setSize(65,65);
					
					isParti.setText("Enter");
					isParti.setBackground(new Color(211,211,211));
					
					isParti.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent arg0) {
							if(isParti.getText().equals("Enter")) {
								Chat_room_UI chatUser=new Chat_room_UI();
								chatUser.setVisible(true);
							}else {
								
								JOptionPane.showMessageDialog(null,"You are aleary entered!");
								
							}
							
							
						}
					});
					
					
					cont.add(cont_img,BorderLayout.WEST);
					
					cont.add(isParti,BorderLayout.EAST);
					
					JPanel centerP = new JPanel(); // just for prof_id
					cont.add(centerP, BorderLayout.CENTER);
					centerP.setLayout(new BorderLayout(0, 0));
					centerP.add(prof_id,BorderLayout.WEST);
					
					
				
					
					make_room_scroll_2(cont,0,count++*300,150,250);
					
				}
				

				
			}catch(Exception e) { 
				System.out.println("Error:" +mainSock);
			}
		
		
		}
	}
	
	
	// make the friend scrollpane smoothly
	public void make_list_scroll(Component cmpt, int x, int y, int w, int h){

		  GridBagConstraints gbc = new GridBagConstraints();
		  gbc.fill = GridBagConstraints.BOTH;
		  gbc.gridx = x;
		  gbc.gridy = y;
		  gbc.gridwidth = w;
		  gbc.gridheight = h;
		  this.Gbag.setConstraints(cmpt, gbc);
		  
		  listContent.add(cmpt);
		  listContent.updateUI();

		}
	
	// make the friend scrollpane smoothly
		public void make_list_scroll_2(Component cmpt, int x, int y, int w, int h){

			  GridBagConstraints gbc = new GridBagConstraints();
			  gbc.fill = GridBagConstraints.BOTH;
			  gbc.gridx = x;
			  gbc.gridy = y;
			  gbc.gridwidth = w;
			  gbc.gridheight = h;
			  this.Gbag.setConstraints(cmpt, gbc);
			  
			  listContent_2.add(cmpt);
			  listContent_2.updateUI();

			}
		// make the search scrollpane smoothly
	public void make_search_scroll(Component cmpt, int x, int y, int w, int h){

		  GridBagConstraints gbc = new GridBagConstraints();
		  gbc.fill = GridBagConstraints.BOTH;
		  gbc.gridx = x;
		  gbc.gridy = y;
		  gbc.gridwidth = w;
		  gbc.gridheight = h;
		  this.Gbag.setConstraints(cmpt, gbc);
		  
		  searchContent.add(cmpt);
		  searchContent.updateUI();

		}
	// make the search scrollpane smoothly
	public void make_search_scroll_2(Component cmpt, int x, int y, int w, int h){

		  GridBagConstraints gbc = new GridBagConstraints();
		  gbc.fill = GridBagConstraints.BOTH;
		  gbc.gridx = x;
		  gbc.gridy = y;
		  gbc.gridwidth = w;
		  gbc.gridheight = h;
		  this.Gbag.setConstraints(cmpt, gbc);
		  
		  searchContent_2.add(cmpt);
		  searchContent_2.updateUI();

		}
	// make the chatt room scrollpane smoothly
	public void make_room_scroll(Component cmpt, int x, int y, int w, int h){

		  GridBagConstraints gbc = new GridBagConstraints();
		  gbc.fill = GridBagConstraints.BOTH;
		  gbc.gridx = x;
		  gbc.gridy = y;
		  gbc.gridwidth = w;
		  gbc.gridheight = h;
		  this.Gbag.setConstraints(cmpt, gbc);
		  
		  roomContent.add(cmpt);
		  roomContent.updateUI();

		}
	// make the chatt room scrollpane smoothly
	public void make_room_scroll_2(Component cmpt, int x, int y, int w, int h){

		  GridBagConstraints gbc = new GridBagConstraints();
		  gbc.fill = GridBagConstraints.BOTH;
		  gbc.gridx = x;
		  gbc.gridy = y;
		  gbc.gridwidth = w;
		  gbc.gridheight = h;
		  this.Gbag.setConstraints(cmpt, gbc);
		  
		  roomContent_2.add(cmpt);
		  roomContent_2.updateUI();

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