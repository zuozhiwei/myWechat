package com.hgo.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.*;
import com.hgo.controller.*;
import com.hgo.sql.SqlQuery;
import com.hgo.bean.*;
import com.hgo.controller.*;
public class Main extends JFrame implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	User user;
	SocketWechat socketWechat;
	static String userID = Login.userID;
	JPanel jPanel1;
	JPanel jPanel11;
	JPanel jPanel12;
	JPanel jPanel13;
	JPanel jPanel14;
	JPanel jPanel15;
	JPanel jPanel2;
	JPanel jPanel21;
	JPanel jPanel22;
	JPanel jPanelEdit;
	JPanel jPanelButton;
	JLabel jLabelUnderEdit;
	JLabel jLabelFriends;
	JButton jButtonSend;
	JTextArea jTextAreaPanel;
    JTextArea jTextAreaEdit;
    JPopupMenu popupMenu = new JPopupMenu();  //弹出式菜单
    JMenuItem menuItem;
	public Main() {
		// TODO Auto-generated constructor stub
		super(userID);
		//创建socket对象，开启服务监听
		socketWechat = new SocketWechat(VariableStatic.getFromPort());
		//设置网格布局
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {  
			public void windowClosing(WindowEvent e) {
				socketWechat.close();
			 }  
		});
		jPanel11 = new JPanel() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {
                ImageIcon icon =
                        new ImageIcon(getClass().getResource("img/wechat.png"));
                // 图片随窗体大小而变化
                g.drawImage(icon.getImage(), 0, 0,this);
            }
        };
		jPanel12 = new JPanel() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {
                ImageIcon icon =
                        new ImageIcon(getClass().getResource("img/chat1.png"));
                // 图片随窗体大小而变化
                g.drawImage(icon.getImage(),0,0,this);
            }
        };
		jPanel13 = new JPanel() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {
                ImageIcon icon =
                        new ImageIcon(getClass().getResource("img/user1.png"));
                // 图片随窗体大小而变化
                g.drawImage(icon.getImage(), 0, 0,this);
            }
        };
        jPanel14 = new JPanel() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {
                ImageIcon icon =
                        new ImageIcon(getClass().getResource("img/add1.png"));
                // 图片随窗体大小而变化
                g.drawImage(icon.getImage(), 0, 0,this);
            }
        };
        
        jPanel15 = new JPanel() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void paintComponent(Graphics g) {
                ImageIcon icon =
                        new ImageIcon(getClass().getResource("img/wechat-friend.png"));
                // 图片随窗体大小而变化
                g.drawImage(icon.getImage(), 0, 0,this);
            }
        };
        jPanel11.setLayout(new BorderLayout());
        jPanel12.setLayout(new BorderLayout());
        jPanel13.setLayout(new BorderLayout());
        jPanel14.setLayout(new BorderLayout());
        jPanel15.setLayout(new BorderLayout());
		//更换图标
		Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/wechat.png")); 
		this.setIconImage(icon);
		//添加鼠标点击事件
        jPanel11.addMouseListener(this);
        jPanel12.addMouseListener(this);
        jPanel13.addMouseListener(this);
        jPanel14.addMouseListener(this);
        jPanel15.addMouseListener(this);
        //左侧栏
        jPanel1 = new JPanel();
        jPanel1.setLayout(new GridLayout(10,1));
        jPanel1.add(jPanel11);
        jPanel1.add(jPanel12);
        jPanel1.add(jPanel13);
        jPanel1.add(jPanel14);
        jPanel1.add(new JLabel(""));
        jPanel1.add(new JLabel(""));
        jPanel1.add(new JLabel(""));
        jPanel1.add(new JLabel(""));
        jPanel1.add(new JLabel(""));
        jPanel1.add(jPanel15);
        jPanel1.setBackground(new Color(42,45,50));
        getContentPane().add(jPanel1, BorderLayout.WEST);
        jPanel1.setPreferredSize(new Dimension(40, 150));
        //右侧栏
        jPanel2 = new JPanel();
        jPanel2.setLayout(new BorderLayout());
        jPanel21 = new JPanel();
        jPanel22 = new JPanel();
        //显示区域
        jPanel22.setLayout(new BorderLayout());
        JLabel jLabelUserID = new JLabel("微信用户");
        jTextAreaPanel = new JTextArea();
        jTextAreaEdit = new JTextArea();
        jTextAreaPanel.setEditable(false);
        jTextAreaPanel.setLineWrap(true);
        //编辑区域面板
        jPanelEdit = new JPanel();
        jPanelEdit.setLayout(new BorderLayout());
        jLabelUnderEdit = new JLabel("表情");
        jButtonSend = new JButton("发送");
        jPanelButton = new JPanel();
        jPanelButton.setLayout(new BorderLayout());
        jPanelButton.add(jButtonSend,BorderLayout.EAST);
        jButtonSend.addActionListener(this);
        
        jPanelEdit.add(jLabelUnderEdit,BorderLayout.NORTH);
        jPanelEdit.add(jTextAreaEdit,BorderLayout.CENTER);
        jPanelEdit.add(jPanelButton,BorderLayout.SOUTH);
        jPanelEdit.setPreferredSize(new Dimension(580, 150));
        //加入右侧
        jPanel22.add(jLabelUserID,BorderLayout.NORTH);
        jPanel22.add(new JScrollPane(jTextAreaPanel),BorderLayout.CENTER);
        jPanel22.add(jPanelEdit,BorderLayout.SOUTH);
        
        jPanel21.setLayout(new BorderLayout());
        jPanel21.setPreferredSize(new Dimension(170, 670));
        jPanel21.setBackground(new Color(228,228,229));
        //搜索框
        JPanel jPanelSearch = new JPanel();
        jPanelSearch.setLayout(new BorderLayout());
        JTextField jTextSearch = new JTextField();
        ImageIcon iconButton = new ImageIcon(getClass().getResource("img/plus.png"));
        JButton jButtonSearch = new JButton(iconButton);
        jButtonSearch.setPreferredSize(new Dimension(30,30));
        jPanelSearch.add(jTextSearch,BorderLayout.CENTER);
        jPanelSearch.add(jButtonSearch,BorderLayout.EAST);
        jPanelSearch.setPreferredSize(new Dimension(170,30));
        jPanel21.add(jPanelSearch,BorderLayout.NORTH);
        jPanel2.add(jPanel21,BorderLayout.WEST);
        jPanel2.add(jPanel22,BorderLayout.CENTER);
        
        menuItem = new JMenuItem("删除好友");
        popupMenu.add(menuItem);
        menuItem.addActionListener(this);
        
        getContentPane().add(jPanel2, BorderLayout.CENTER);
        
		this.setSize(890,700);
		//窗体居中显示 
		this.setLocationRelativeTo(null);
		//设置窗体可见
		this.setVisible(true);
		//不可最大化
		this.setResizable(false);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(jButtonSend)) {
			String sendText = jTextAreaEdit.getText();
			user = new User();
			String result = user.send(sendText,userID,VariableStatic.getToUserID());
			if("success".equals(result)) {
				System.out.println("发送成功");
				jTextAreaEdit.setText("");
				refreshContentCurrent(VariableStatic.getUserID(), sendText);
				try {
					socketWechat.clientSocket(sendText, VariableStatic.getToPort());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}else {
				System.out.println("发送失败");
			}
		}
		if(e.getSource().equals(menuItem)) {
			//TODO 删除好友
			user.deleteFriend(VariableStatic.getUserID(),VariableStatic.getToUserID());
			refreshFriend();
		}
	}

	private void refreshContent() {
		// TODO Auto-generated method stub
		ArrayList<Map<String, String>> content = new ArrayList<Map<String,String>>();
		content = user.refreshContent(VariableStatic.getUserID(),VariableStatic.getToUserID());
		String strContent = "";
		for(int i=0;i<content.size();i++) {
			strContent+= content.get(i).get("from")+"  "+content.get(i).get("time")+"\n";
			strContent+= content.get(i).get("content")+"\n\n";
		}
		System.out.println(strContent);
		jTextAreaPanel.setText(strContent);
	}
	public void refreshContentCurrent(String userID,String content) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String currentDate = df.format(new Date());// new Date()为获取当前系统时间
		String strContent = "";
		strContent+= userID+"  "+currentDate+"\n";
		strContent+= content+"\n\n";
		jTextAreaPanel.append(strContent);
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(jPanel14))
		{
			new AddUser();
		}
		if(e.getSource().equals(jPanel13)) {
			
			refreshFriend();
		}
	}

	private void refreshFriend() {
		// TODO Auto-generated method stub
		//显示好友列表
		List<Map<String,String>> friends = new ArrayList<Map<String,String>>();
		System.out.println(VariableStatic.getUserID());
		user = new User();
		friends = user.showFriends(VariableStatic.getUserID());
		
		JPanel jPanel211 = new JPanel();
        jPanel211.setLayout(new GridLayout(13, 1));
		if(null != friends)
		{
			for(int i=0;i<friends.size();i++)
			{
				JPanel jPanelFriendItem = new JPanel();
				jPanelFriendItem.setLayout(new BorderLayout());
				jLabelFriends = new JLabel(friends.get(i).get("userID"));
				JLabel jLabelIcon = new JLabel();
				ImageIcon icon = new ImageIcon(getClass().getResource("img/wechat.png"));
				icon.setImage(icon.getImage().getScaledInstance(icon.getIconWidth(),icon.getIconHeight(), Image.SCALE_DEFAULT));
				jLabelIcon.setIcon(icon);
				jPanelFriendItem.add(jLabelIcon,BorderLayout.WEST);
				jPanelFriendItem.add(jLabelFriends,BorderLayout.CENTER);
				jPanel211.add(jPanelFriendItem);
				jPanel2.revalidate();
				jPanel21.revalidate();
				jPanel211.revalidate();
				final String ff  = friends.get(i).get("userID");
				final int port  = Integer.parseInt(friends.get(i).get("port"));
				jPanelFriendItem.addMouseListener(new MouseListener() {
					@Override
					public void mouseClicked(MouseEvent e) {
						// TODO Auto-generated method stub
						VariableStatic.setToUserID(ff);
						VariableStatic.setToPort(port);
						refreshContent();
						Runnable runnable = new Runnable() {
							public void run() {
					                // task to run goes here  
								String content = socketWechat.serverSocket();
								refreshContentCurrent(VariableStatic.getToUserID(),content);
					        }  
					    };
					    ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();  
					        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间  
					    service.scheduleAtFixedRate(runnable, 0, 1, TimeUnit.SECONDS);  
					}

					@Override
					public void mouseEntered(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mousePressed(MouseEvent e) {
						// TODO Auto-generated method stub
						if(e.getButton()==3)   //1左键,2中键，在这里可以设置键值，这里可设置的不正确，请核实下
		                {
							VariableStatic.setToUserID(ff);
							popupMenu.show(e.getComponent(),e.getX(),e.getY());
		                }
					}

					@Override
					public void mouseReleased(MouseEvent e) {
						// TODO Auto-generated method stub
						
					}
				});
			}
			jPanel21.add(jPanel211,BorderLayout.CENTER);
		} else {
			jPanel211.add(new JLabel(""));
			jPanel2.revalidate();
			jPanel21.revalidate();
			jPanel211.revalidate();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
