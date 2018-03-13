package com.hgo.controller;
import java.sql.SQLException;

import com.hgo.sql.*;

public class Auth {

	public boolean checkLogin(String userID, String password) {
		// TODO Auto-generated method stub
		SqlQuery sqlQuery = new SqlQuery();
		try {
			if(sqlQuery.checkLogin(userID,password)) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public boolean addRegister(String userID, String password) throws SQLException {
		// TODO Auto-generated method stub
		SqlQuery sqlQuery = new SqlQuery();
		if(sqlQuery.addRegister(userID,password)) {
			return true;
		}
		return false;
	}

	public int getPort(String userID) {
		// TODO Auto-generated method stub
		SqlQuery sqlQuery = new SqlQuery();
		try {
			return sqlQuery.getPort(userID);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
}
