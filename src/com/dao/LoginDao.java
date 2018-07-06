package com.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.Login;
import com.bean.Users;
import com.bean.Users;

public class LoginDao {
	PreparedStatement ps = null;
	ResultSet rs = null;
	DBSql mydb = null;
	public LoginDao() {
		mydb = new DBSql();
	}
	
	public Login findUserByNameAndPassword(String uname, String upassword) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Login login = new Login();
		String sql = "";
		sql = " select uid,uname,upassword,2 utype,urealname from users " +
				"    where uname = ? and  upassword=? ";
		
		try {
			ps = mydb.getCon().prepareStatement(sql);
			ps.setString(1, uname);
			ps.setString(2, upassword);
			rs = ps.executeQuery();
			while(rs.next()){
				login.setUid(rs.getInt(1));
				login.setUname(rs.getString(2));
				login.setUpassword(rs.getString(3));
				login.setUtype(rs.getString(4));
				login.setUrealname(rs.getString(5));
			}
		} catch (SQLException e) {		
			e.printStackTrace();
		}finally{
			try {
				rs.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
			mydb.close();
		}
		return login;
	}
	public Login findEmployeeByNameAndPassword(String uname, String upassword) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		Login login = new Login();
		String sql = "";
		sql = " select eid uid,ename uname,epassword upassword,1 utype,erealname urealname from employee   where ename = ? and  epassword=? ";
		
		try {
			ps = mydb.getCon().prepareStatement(sql);
			ps.setString(1, uname);
			ps.setString(2, upassword);
			rs = ps.executeQuery();
			while(rs.next()){
				login.setUid(rs.getInt(1));
				login.setUname(rs.getString(2));
				login.setUpassword(rs.getString(3));
				login.setUtype(rs.getString(4));
				login.setUrealname(rs.getString(5));
			}
		} catch (SQLException e) {		
			e.printStackTrace();
		}finally{
			try {
				rs.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
			try {
				ps.close();
			} catch (SQLException e) {				
				e.printStackTrace();
			}
			mydb.close();
		}
		return login;
	}
	
}
