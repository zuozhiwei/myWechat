package com.hgo.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;
import javax.swing.*;
import com.hgo.controller.*;
import com.hgo.sql.SqlQuery;
import com.hgo.bean.*;
public class AddUser extends JFrame implements ActionListener{
	User user = new User();
	JLabel jLabel1;
	JTextField jTextFieldSearchUesrID;
	JButton jButtonSearch;
	public AddUser() {
		// TODO Auto-generated constructor stub
		super("添加好友");
		jLabel1 = new JLabel("输入用户ID");
		jTextFieldSearchUesrID = new JTextField();
		jButtonSearch = new JButton("添加");
		getContentPane().add(jLabel1,BorderLayout.NORTH);
		getContentPane().add(jTextFieldSearchUesrID,BorderLayout.CENTER);
		getContentPane().add(jButtonSearch,BorderLayout.SOUTH);
		jButtonSearch.addActionListener(this);
		//窗体居中显示 
		this.setLocationRelativeTo(null);
		//设置窗体可见
		this.setVisible(true);
		//不可最大化
		this.setResizable(false);
		this.setSize(230,100);
		this.getContentPane().setBackground(new Color(245, 245, 245));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource().equals(jButtonSearch))
		{
			String newUserID = jTextFieldSearchUesrID.getText();
			String result = user.addContact(VariableStatic.getUserID(),newUserID);
			if(result.equals("ok")) {
				JOptionPane.showMessageDialog(null, "添加成功！", "提示信息", JOptionPane.PLAIN_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "重复添加！", "提示信息", JOptionPane.PLAIN_MESSAGE);
			}
		}
	}

}
