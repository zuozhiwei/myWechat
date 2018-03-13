package com.hgo.gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.*;

import com.hgo.bean.VariableStatic;
import com.hgo.controller.*;
public class Login extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Auth auth = new Auth();
	static String userID;
	String password;
	JTextField jTextUserID;
	JPasswordField jTextPassword;
	JButton jRegister;
	JButton jLogin;
	JPanel jPanel1; 
	JPanel jPanel2;
	JLabel jLabel1;
	JPanel jPanelButton;
	User user= new User();
	Login(){
		super("微信");
		//设置网格布局
		this.setLayout(new GridLayout(10,1));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		/*可以去掉窗体的标题栏（但不方便关闭）
		this.setUndecorated(true); // 去掉窗口的装饰
		this.getRootPane().setWindowDecorationStyle(JRootPane.NONE);//采用指定的窗口装饰风格
		*/
		//初始化panel
		jPanel1 = new JPanel();
		jPanel2 = new JPanel();
		jLabel1 = new JLabel("微信",JLabel.CENTER);
		jPanelButton = new JPanel();
		//初始化控件
		jTextUserID = new JTextField(12);
		jTextPassword = new JPasswordField(12);
		jRegister = new JButton("注册");
		jLogin = new JButton("登录");
		//添加点击事件
		jLogin.addActionListener(this);
		jRegister.addActionListener(this);
		//控件添加到panel
		jPanel1.add(jTextUserID);
		jPanel2.add(jTextPassword);
		jPanelButton.add(jRegister);
		jPanelButton.add(jLogin);
		//panel添加到主窗体
		this.add(new JLabel(""));
		this.add(jLabel1);
		this.add(new JLabel(""));
		this.add(jPanel1);
		this.add(jPanel2);
		this.add(jPanelButton);
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		this.add(new JLabel(""));
		//设置窗体大小
		this.setSize(272,380);
		//窗体居中显示 
		this.setLocationRelativeTo(null);
		//设置窗体可见
		this.setVisible(true);
		//不可最大化
		this.setResizable(false);
		//更换图标
		Image icon = Toolkit.getDefaultToolkit().getImage(getClass().getResource("img/wechat.png")); 
		this.setIconImage(icon);
		//背景颜色
		this.getContentPane().setBackground(new Color(245, 245, 245));
		jPanel1.setBackground(new Color(245, 245, 245));
		jPanel2.setBackground(new Color(245, 245, 245));
		jPanelButton.setBackground(new Color(245, 245, 245));
		jRegister.setBackground(new Color(26, 173, 25));
		jLogin.setBackground(new Color(26, 173, 25));
	}
	//启动窗体
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new Login();
	}
	//点击事件
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		userID = jTextUserID.getText();
		password = new String(jTextPassword.getPassword());
		//登陆按钮点击事件
		if(e.getSource().equals(jLogin)){
			//获取控件文本
			//调用控制器判断登录信息
			if(auth.checkLogin(userID,password))
			{
				int port = auth.getPort(userID);
				VariableStatic.setFromPort(port);
				VariableStatic.setUserID(userID);
				JOptionPane.showMessageDialog(null, "登陆成功", "提示信息", JOptionPane.PLAIN_MESSAGE);
				new Main();
				this.dispose();
			}else {
				JOptionPane.showMessageDialog(null, "账号或密码错误", "提示信息", JOptionPane.PLAIN_MESSAGE);
			}
		}
		//注册按钮点击事件
		if(e.getSource().equals(jRegister)) {
			try {
				if(auth.addRegister(userID,password))
				{
					JOptionPane.showMessageDialog(null, "注册成功，请登录", "提示信息", JOptionPane.PLAIN_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "账户已存在", "提示信息", JOptionPane.PLAIN_MESSAGE);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
