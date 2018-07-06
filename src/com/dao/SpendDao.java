package com.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.bean.Spend;
public class SpendDao {
	PreparedStatement ps = null;
	ResultSet rs = null;
	DBSql mydb = null;
	public SpendDao() {
		mydb = new DBSql();
	}
	
	public int deleteSpendById(int id) {
		String sql = "delete from spend where sid = ?";
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

	public List<Spend> findAllSpend(Spend aff,int firstRecord, int lastRecord) {
		List<Spend> list = new ArrayList<Spend>();
		Spend Spend = null;
		String sql = "select * from  (select e.*,row_number() over(order by sid) rn from spend e " +
				"    where uid="+aff.getUid() ;
		
		sql +=" and rn between "+firstRecord +" and "+lastRecord;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				Spend = new Spend();
				Spend.setSid(rs.getInt("sid"));
				Spend.setSdate(rs.getDate("sdate"));
				Spend.setSmoney(rs.getFloat("smoney"));
				list.add(Spend);
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

	public Spend findSpendById(int id) {
		Spend spend = null;
		String sql = "select *  " +
				"from spend   where sid = "+id;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				spend = new Spend();
				spend.setSid(rs.getInt("sid"));
				spend.setSdate(rs.getDate("sdate"));
				spend.setSmoney(rs.getFloat("smoney"));
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
		return spend;
	}

	
	public int updateSpend(Spend Spend) {
		//    atitle,acontent,atime           1         2         3         4         5   6          7       8       
		String sql = "update Spend set atitle=?,acontent=?,atime=?" +
		//         
				"  where aid=?";
		int i = 0;
//		try {
//			ps = mydb.getCon().prepareStatement(sql);
//			ps.setString(1, Spend.getAtitle());
//			ps.setString(2, Spend.getAcontent());
//			ps.setDate(3, (Date) Spend.getAtime());
//			ps.setInt(4, Spend.getAid());
//			i = ps.executeUpdate();
//		} catch (SQLException e) {
//			
//			e.printStackTrace();
//		}finally{
//			try {
//				ps.close();
//			} catch (SQLException e) {
//				
//				e.printStackTrace();
//			}
//			mydb.close();
//		}
		return i;
	}
	public int findAllCounts(Spend aff) {
		int count = 0;
		String sql = "select count(*) from Spend  where 1=1 and uid="+aff.getUid();
		
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
	
	public Float findsum(Spend aff) {
		Float count = new Float(0);
		String sql = "select sum(smoney) from Spend  where 1=1 and uid="+aff.getUid();
		
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
	public int insertSpend(Spend spend) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String sql = "insert into Spend ( uid,smoney,sdate) " +
				"values(?,?,getdate())";
		int i = 0;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			ps.setInt(1, spend.getUid());
			ps.setFloat(2, spend.getSmoney());
//			ps.setDate(3, (Date) spend.getSdate());
//			ps.setInt(3, spend.getOid());
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
