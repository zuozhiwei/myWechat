package com.hgo.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class SqlQuery {
	Connection conn;
	Statement stmt = null;
	ResultSet rs = null;
	//连接数据库
	public void connect() {
		try {
//			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver").newInstance();// 加载驱动程序
//		       System.out.println("开始连接数据库！");
//		    String url = "D:/myWechat.mdb";
//		    String dburl = "jdbc:odbc:driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="+url; //连接ACCESS数据库
//		    Properties prop= new Properties ();
//		    prop.put( "charSet", "gbk" );
//		    prop.put( "user", "" );
//		    prop.put( "password", "" );
//		    conn = DriverManager.getConnection(dburl,prop);
//	        System.out.println("连接成功！");
//	        if(null!=conn)
//	        {
//	        	System.out.println("不为空");
//	        }
			Class.forName("com.hxtt.sql.access.AccessDriver");
			conn = DriverManager.getConnection("jdbc:Access:///myWechat.mdb");//需要使用绝对路径
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//判断登陆
	public boolean checkLogin(String userID, String password) throws SQLException {
		try {
			connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from user where userID ='"+userID+"' and password ='"+password+"'");
		    if(rs.next())
		    {
		    	return true;
		    }
		    //关闭连接
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			rs.close();
		    stmt.close();
		    conn.close();
		}
		return false;
	}
	public boolean addRegister(String userID, String password) throws SQLException {
		// TODO Auto-generated method stub
		try {
			connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select * from user where userID ='"+userID+"'");
			if (rs.next()) {
				return false;
			}
			rs = stmt.executeQuery("select top1 port from user order by ID desc");
			int port = Integer.parseInt(rs.getString(1));
			port++;
			int rsInt = stmt.executeUpdate("insert into user(userID,password,port) values ('"+userID+"','"+password+"','"+port+"')");
			if(rsInt>0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			rs.close();
		    stmt.close();
		    conn.close();
		}
		return false;
	}
//	public boolean checkOnline(String toUserID) throws SQLException {
//		// TODO Auto-generated method stub
//		try {
//			stmt = conn.createStatement();
//			rs = stmt.executeQuery("select online from user where userID ='"+toUserID+"'");
//		    if(rs.next())
//		    {
//		    	if(rs.getString(1).equals("1")) {
//		    		return true;
//		    	}
//		    }
//		    //关闭连接
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally {
//		    rs.close();
//		    stmt.close();
//		    conn.close();
//		}
//		return false;
//	}
	public void send(String sendText, String userID, String toUserID) throws SQLException {
		// TODO Auto-generated method stub
		try {
			connect();
			stmt = conn.createStatement();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String currentDate = df.format(new Date());// new Date()为获取当前系统时间
			stmt.executeUpdate("insert into chatrecord(fromid,toid,content,logtime) values ('"+userID+"','"+toUserID+"','"+sendText+"','"+currentDate+"')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
		    stmt.close();
		    conn.close();
		}
	}
	public boolean checkFriend(String userID, String newUserID) throws SQLException {
		// TODO Auto-generated method stub
		try {
			connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select id from friend where fromid ='"+userID+"' and toid ='"+newUserID+"'");
		    if(rs.next())
		    {
	    		return false;
		    }
		    //关闭连接
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
			conn.close();
		}
		return true;
	}
	public void addContact(String userID, String newUserID) throws SQLException {
		// TODO Auto-generated method stub
		try {
			connect();
			stmt = conn.createStatement();
			stmt.executeUpdate("insert into friend(fromid,toid) values ('"+userID+"','"+newUserID+"')");
			stmt.executeUpdate("insert into friend(fromid,toid) values ('"+newUserID+"','"+userID+"')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			stmt.close();
			conn.close();
		}
	}
	public List<Map<String,String>> showFriends(String userID) throws SQLException {
		// TODO Auto-generated method stub
		List<Map<String,String>> friends = new ArrayList<Map<String,String>>();
		try {
			connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select friend.toid, user.port from friend left join user where friend.fromid ='"+userID+"' and user.userID = friend.toid");
		    while(rs.next())
		    {
		    	Map<String,String> map = new HashMap<String,String>();
		    	map.put("userID", rs.getString(1));
		    	map.put("port", rs.getString(2));
		    	friends.add(map);
		    }
		    return friends;
		    //关闭连接
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
			conn.close();
		}
		return friends;
	}
	public ArrayList<Map<String, String>> refreshContent(String userID, String toUserID) throws SQLException {
		// TODO Auto-generated method stub
		ArrayList<Map<String, String>> content = new ArrayList<Map<String,String>>();
		try {
			connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select fromid, content,logtime from chatrecord where ( fromid ='"+userID+"' and toid ='"+toUserID+"') or ( fromid='"+toUserID+"' and toid ='"+userID+"')");
		    while(rs.next())
		    {
		    	Map<String,String> map = new HashMap<String,String>();
		    	map.put("from", rs.getString(1));
		    	map.put("content",rs.getString(2));
		    	map.put("time",rs.getString(3));
		    	content.add(map);
		    }
		    return content;
		    //关闭连接
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
			conn.close();
		}
		return content;
	}
	public void deleteFriend(String userID, String toUserID) throws SQLException {
		// TODO Auto-generated method stub
		try {
			connect();
			stmt = conn.createStatement();
			stmt.executeUpdate("delete from friend where ( fromid ='"+userID+"' and toid ='"+toUserID+"') or ( fromid='"+toUserID+"' and toid ='"+userID+"')");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			stmt.close();
			conn.close();
		}
	}
	public void closeConnect() throws SQLException {
		if(null!=rs){
			rs.close();
		}
		if(null!=stmt) {
		    stmt.close();
		}
		if(null!=conn) {
			conn.close();
		}
	}
	public void openConnect() {
		// TODO Auto-generated method stub
		connect();
	}
	public int getPort(String userID) throws SQLException {
		try {
			connect();
			stmt = conn.createStatement();
			rs = stmt.executeQuery("select port from user where userID ='"+userID+"'");
			if(rs.next()) {
			    return rs.getInt(1);
			}
		    //关闭连接
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			rs.close();
			stmt.close();
			conn.close();
		}
		return 0;
	}
}
