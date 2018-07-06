package com.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.bean.Recharge;
public class RechargeDao {
	PreparedStatement ps = null;
	ResultSet rs = null;
	DBSql mydb = null;
	public RechargeDao() {
		mydb = new DBSql();
	}
	
	public int deleteRechargeById(int id) {
		String sql = "delete from recharge where rid = ?";
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

	public List<Recharge> findAllRecharge(Recharge aff,int firstRecord, int lastRecord) {
		List<Recharge> list = new ArrayList<Recharge>();
		Recharge recharge = null;
		String sql = "select * from  (select e.*,row_number() over(order by rid) rn from recharge e " +
				"    where uid="+aff.getUid()+") tt" ;
		
		sql +=" where rn between "+firstRecord +" and "+lastRecord;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				recharge = new Recharge();
				recharge.setRid(rs.getInt("rid"));
				recharge.setRdate(rs.getDate("rdate"));
				recharge.setRmoney(rs.getFloat("rmoney"));
				recharge.setUid(rs.getInt("uid"));
				list.add(recharge);
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

	public Recharge findRechargeById(int id) {
		Recharge recharge = null;
		String sql = "select *  " +
				"from Recharge   where rid = "+id;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				recharge = new Recharge();
				recharge.setRid(rs.getInt("rid"));
				recharge.setRdate(rs.getDate("rdate"));
				recharge.setRmoney(rs.getFloat("rmoney"));
				recharge.setUid(rs.getInt("uid"));
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
		return recharge;
	}

	
	public int updateRecharge(Recharge recharge) {
		//    atitle,acontent,atime           1         2         3         4         5   6          7       8       
		String sql = "update recharge set rmoney=?" +
		//         
				"  where rid=?";
		int i = 0;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			
			ps.setFloat(1, recharge.getRmoney());
			ps.setInt(2, recharge.getRid());
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
	public int findAllCounts(Recharge aff) {
		int count = 0;
		String sql = "select count(*) from recharge  where 1=1 and uid="+aff.getUid();
		
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
	
	public Float findsum(String startdate, String enddate,String key) {
		Float count = new Float(0);
		String sql = "select sum(rmoney) from recharge  where 1=1 ";
		if(startdate!=null&&enddate!=null&&!startdate.equals("")&&!enddate.equals(""))
			sql+=" and rdate between  '"+startdate+" 00:00:00' and '"+enddate+" 23:59:59'  " ;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				count = rs.getFloat(1);
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
	public int insertRecharge(Recharge recharge) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String sql = "insert into recharge ( uid,rmoney,rdate) " +
				"values(?,?,getdate())";
		int i = 0;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			ps.setInt(1, recharge.getUid());
			ps.setFloat(2, recharge.getRmoney());
//			ps.setDate(3, (Date) recharge.getRdate());
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
