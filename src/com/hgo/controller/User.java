package com.hgo.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hgo.sql.SqlQuery;

public class User {

	public String send(String sendText, String userID, String toUserID) {
		// TODO Auto-generated method stub
		try {
			SqlQuery sqlQuery = new SqlQuery();
			sqlQuery.send(sendText,userID,toUserID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "success";
	}
//
//	private boolean checkOnline(String toUserID) {
//		// TODO Auto-generated method stub
//		SqlQuery sqlQuery = new SqlQuery();
//		try {
//			if(sqlQuery.checkOnline(toUserID)){
//				return true;
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return false;
//	}

	public String addContact(String userID, String newUserID) {
		// TODO Auto-generated method stub
		if(checkFriend(userID,newUserID))
		{
			try {
				SqlQuery sqlQuery = new SqlQuery();
				sqlQuery.addContact(userID,newUserID);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			return "already";
		}
		return "ok";
	}

	private boolean checkFriend(String userID, String newUserID) {
		// TODO Auto-generated method stub
		try {
			SqlQuery sqlQuery = new SqlQuery();
			if(sqlQuery.checkFriend(userID,newUserID)) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public List<Map<String,String>> showFriends(String userID) {
		// TODO Auto-generated method stub
		List<Map<String, String>> friends = new ArrayList<Map<String,String>>();
		try {
			SqlQuery sqlQuery = new SqlQuery();
			friends = sqlQuery.showFriends(userID);
			System.out.println(friends);
			return friends;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return friends;
	}

	public ArrayList<Map<String, String>> refreshContent(String userID, String toUserID) {
		// TODO Auto-generated method stub
		ArrayList<Map<String, String>> content = new ArrayList<Map<String,String>>();
		try {
			SqlQuery sqlQuery = new SqlQuery(); 
			content = sqlQuery.refreshContent(userID,toUserID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return content;
	}

	public void deleteFriend(String userID, String toUserID) {
		// TODO Auto-generated method stub
		try {
			SqlQuery sqlQuery = new SqlQuery();
			sqlQuery.deleteFriend(userID,toUserID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
