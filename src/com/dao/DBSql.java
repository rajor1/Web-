package com.dao;

import java.sql.Connection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBSql {
	Connection con = null;
	public DBSql() {
		try {
//			Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver"); //2000
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); //2005
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Connection getCon(){
		try {
//			con = DriverManager.getConnection("jdbc:microsoft:sqlserver://localhost:1433;databasename=course;User=sa;Password=123");//2000
			con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databasename=sitemanage;User=sa;Password=123");;//2005
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	public void close(){
		try {
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
