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
import com.bean.Site;
public class SiteDao {
	PreparedStatement ps = null;
	ResultSet rs = null;
	DBSql mydb = null;
	public SiteDao() {
		mydb = new DBSql();
	}
	
	public int deleteSiteById(int id) {
		String sql = "delete from site where sid = ?";
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

	public List<Site> findAllSite(Site aff,int firstRecord, int lastRecord) {
		List<Site> list = new ArrayList<Site>();
		Site site = null;
		String sql = "select * from  (select e.*,row_number() over(order by sid) rn from site e " +
				"    where 1=1 " ;
		if(aff.getSremarks()!=null)
			sql+=" and sremarks like '%"+aff.getSremarks()+"%' ";
				sql+=	") tt" ; 
		sql +=" where rn between "+firstRecord +" and "+lastRecord;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				site = new Site();
				site.setSid(rs.getInt("sid"));
				site.setSprice(rs.getFloat("sprice"));
				site.setSimg(rs.getString("simg"));
				site.setSremarks(rs.getString("sremarks"));
				site.setSinfo(rs.getString("sinfo"));
				list.add(site);
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

	public Site findSiteById(int id) {
		Site site = null;
		String sql = "select *  " +
				"from site   where sid = "+id;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				site = new Site();
				site.setSid(rs.getInt("sid"));
				site.setSprice(rs.getFloat("sprice"));
				site.setSimg(rs.getString("simg"));
				site.setSremarks(rs.getString("sremarks"));
				site.setSinfo(rs.getString("sinfo"));
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
		return site;
	}

	
	public int updateSite(Site site) {
		//    atitle,acontent,atime           1         2         3         4         5   6          7       8       
		String sql = "update site set sprice=?,sremarks=?,simg=?,sinfo=?" +
		//         
				"  where sid=?";
		int i = 0;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			
			ps.setFloat(1, site.getSprice());
			ps.setString(2, site.getSremarks());
			ps.setString(3, site.getSimg());
			ps.setString(4, site.getSinfo());
			ps.setInt(5, site.getSid());
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
	public int findAllCounts(Site aff) {
		int count = 0;
		String sql = "select count(*) from site  where 1=1 ";
		if(aff.getSremarks()!=null)
			sql+=" and sremarks like '%"+aff.getSremarks()+"%' ";
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
	
	
	public int insertSite(Site site) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String sql = "insert into site ( sprice,sremarks,simg,sinfo) " +
				"values(?,?,?)";
		int i = 0;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			ps.setFloat(1, site.getSprice());
			ps.setString(2, site.getSremarks());
			ps.setString(3,  site.getSimg());
			ps.setString(4,  site.getSinfo());
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
