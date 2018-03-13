package com.hgo.bean;

public class VariableStatic {
	static String toUserID;
	static String userID;
	static int fromPort;
	static int toPort;
	public static int getFromPort() {
		return fromPort;
	}
	public static void setFromPort(int fromPort) {
		VariableStatic.fromPort = fromPort;
	}
	public static int getToPort() {
		return toPort;
	}
	public static void setToPort(int toPort) {
		VariableStatic.toPort = toPort;
	}
	public static String getToUserID() {
		return toUserID;
	}
	public static void setToUserID(String toUserID) {
		VariableStatic.toUserID = toUserID;
	}
	public static String getUserID() {
		return userID;
	}
	public static void setUserID(String userID) {
		VariableStatic.userID = userID;
	}
	
}
