package com.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bean.Employee;
import com.bean.Recharge;
import com.bean.Spend;
import com.bean.Users;
import com.bean.Users;

public class UsersDao {
	PreparedStatement ps = null;
	ResultSet rs = null;
	DBSql mydb = null;
	public UsersDao() {
		mydb = new DBSql();
	}
	
	public int deleteUsersById(int id) {
		String sql = " delete from users where uid = ?";
		int i = 0;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			ps.setInt(1, id);
			i = ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			try {
				ps.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			mydb.close();
		}
		return i;
	}

	public List<Users> findAllUsers(Users user,int firstRecord, int lastRecord) {
		List<Users> list = new ArrayList<Users>();
		Users users = null;
		String sql = "select tt.*,(isnull(r.rmoney,0)-isnull(s.smoney,0))  remaining  from " +
				"(select *,row_number() over(order by uname) rn from users where 1=1 " ;
		if(user!=null)
		{
			if(user.getUname()!=null)
				sql+=" and (uname like '%"+user.getUname()+"%' or urealname like '%"+user.getUname()+"%') ";
		}
		sql +=" ) tt  left join (select uid,sum(rmoney) rmoney from recharge group by uid) r on r.uid = tt.uid " +
				" left join (select uid,sum(smoney) smoney from spend group by uid) s on s.uid = tt.uid  " +
				" where rn between "+firstRecord +" and "+lastRecord;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				users = new Users();
				users.setUid(rs.getInt("uid"));
				users.setUname(rs.getString("uname"));
				users.setUpassword(rs.getString("upassword"));
				users.setUrealname(rs.getString("urealname"));
				users.setUtel(rs.getString("utel"));
				users.setUaddress(rs.getString("uaddress"));
				users.setUsex(rs.getString("usex"));
				users.setRemaining(rs.getFloat("remaining"));
				list.add(users);
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
		return list;
	}

	public Users findUsersById(int id) {
		Users users = null;
		String sql = "select tt.*,(isnull(r.rmoney,0)-isnull(s.smoney,0))  remaining " +
				"from users tt left join (select uid,sum(rmoney) rmoney from recharge group by uid) r on r.uid = tt.uid " +
				" left join (select uid,sum(smoney) smoney from spend group by uid) s on s.uid = tt.uid  where tt.uid = "+id;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				users = new Users();
				users.setUid(rs.getInt("uid"));
				users.setUname(rs.getString("uname"));
				users.setUpassword(rs.getString("upassword"));
				users.setUrealname(rs.getString("urealname"));
				users.setUtel(rs.getString("utel"));
				users.setUaddress(rs.getString("uaddress"));
				users.setUsex(rs.getString("usex"));
				users.setRemaining(rs.getFloat("remaining"));
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
		return users;
	}

	
	public int updateUsers(Users users) {
		//tt.tid,tt.tname,tt.trealname,tt.tpassword,tsex
		String sql = "update users set uname=?,upassword=?,urealname=?,utel=?,uaddress=?,usex=?  where uid=?";
		int i = 0;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			ps.setString(1, users.getUname());
			ps.setString(2, users.getUpassword());
			ps.setString(3, users.getUrealname());
			ps.setString(4, users.getUtel());
			ps.setString(5, users.getUaddress());
			ps.setString(6, users.getUsex());
			ps.setInt(7, users.getUid());
			i = ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			try {
				ps.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			mydb.close();
		}
		return i;
	}
	public int findAllCounts(Users user) {
		int count = 0;
		String sql = "select count(*) from users where 1=1";
		if(user!=null)
		{
			if(user.getUname()!=null)
				sql+=" and (uname like '%"+user.getUname()+"%' or urealname like '%"+user.getUname()+"%') ";
			
		}
		try {
			ps = mydb.getCon().prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			try {
				ps.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			mydb.close();
		}
		return count;
	}
	public int insertUsers(Users users) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String sql = "insert into users ( uname,upassword,urealname,utel,uaddress,usex) values(?,?,?,?,?,?)";
		int i = 0;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			ps.setString(1, users.getUname());
			ps.setString(2, users.getUpassword());
			ps.setString(3, users.getUrealname());
			ps.setString(4, users.getUtel());
			ps.setString(5, users.getUaddress());
			ps.setString(6, users.getUsex());
			i = ps.executeUpdate();
		} catch (SQLException e) {			
			e.printStackTrace();
		}finally{
			try {
				ps.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			mydb.close();
		}
		return i;
	}
	
	public int updatePassword(Users users) {
		//                                  1         2      
		String sql = "update users set upassword=?  where uid=?";
		int i = 0;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			ps.setString(1, users.getUpassword());
			ps.setInt(2, users.getUid());
			i = ps.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally{
			try {
				ps.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			mydb.close();
		}
		return i;
	}
	
}
