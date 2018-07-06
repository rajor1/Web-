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
import com.bean.Sorder;
import com.tool.Tools;

public class SorderDao {
	PreparedStatement ps = null;
	ResultSet rs = null;
	DBSql mydb = null;

	public SorderDao() {
		mydb = new DBSql();
	}

	public int deleteSorderById(int id) {
		String sql = "delete from sorder where oid = ?";
		int i = 0;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			ps.setInt(1, id);
			i = ps.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			mydb.close();
		}
		return i;
	}

	public List<Sorder> findAllsorder(Sorder aff, int firstRecord,
			int lastRecord) {
		List<Sorder> list = new ArrayList<Sorder>();
		Sorder sorder = null;
		String sql = "select * from  (select e.*,u.uname,u.urealname,s.sremarks,row_number() over(order by oid desc) rn from sorder e "
				+ "  left join users u on u.uid=e.uid left join site s on s.sid=e.sid  where 1=1 ";

		if (aff != null && aff.getUid() != 0) {
			sql += " and e.uid=" + aff.getUid();
		}
		if (aff != null && aff.getUname() != null)

			sql += " and (uname like '%" + aff.getUname()
					+ "%' or urealname like '%" + aff.getUname() + "%' )";
		if (aff != null && aff.getSname() != null)
			sql += "and sremarks like '%" + aff.getSname() + "%' ";
		sql += ") tt where rn between " + firstRecord + " and " + lastRecord;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				sorder = new Sorder();

				sorder.setOid(rs.getInt("oid"));
				sorder.setOdate(rs.getDate("odate"));
				sorder.setOstart(rs.getString("ostart"));
				sorder.setOtel(rs.getString("otel"));
				sorder.setOtime(rs.getString("otime"));
				sorder.setSid(rs.getInt("sid"));
				sorder.setSpend(rs.getFloat("spend"));
				sorder.setUid(rs.getInt("uid"));
				sorder.setUname(rs.getString("uname"));
				sorder.setUrealname(rs.getString("urealname"));
				sorder.setSname(rs.getString("sremarks"));
				sorder.setSpendtime(rs.getDate("spendtime"));
				sorder.setSpendtype(rs.getString("spendtype"));
				list.add(sorder);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
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

	public Sorder findsorderById(int id) {
		Sorder sorder = null;
		String sql = "select e.*,u.uname,u.urealname,s.sremarks  "
				+ "from sorder e  left join users u on u.uid=e.uid left join site s on s.sid=e.sid   where oid = "
				+ id;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				sorder = new Sorder();
				sorder.setOid(rs.getInt("oid"));
				sorder.setOdate(rs.getDate("odate"));
				sorder.setOstart(rs.getString("ostart"));
				sorder.setOtel(rs.getString("otel"));
				sorder.setOtime(rs.getString("otime"));
				sorder.setSid(rs.getInt("sid"));
				sorder.setSpend(rs.getFloat("spend"));
				sorder.setUid(rs.getInt("uid"));
				sorder.setUname(rs.getString("uname"));
				sorder.setUrealname(rs.getString("urealname"));
				sorder.setSname(rs.getString("sremarks"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
		return sorder;
	}

	public int updatesorder(Sorder sorder) {
		// atitle,acontent,atime 1 2 3 4 5 6 7 8
		String sql = "update sorder set odate=?,ostart=?,otel=?,otime=?,sid=?,spend=?,uid=?,spendtype=?,spendtime=getdate()"
				+
				//         
				"  where oid=?";
		int i = 0;
		try {
			ps = mydb.getCon().prepareStatement(sql);

			ps.setDate(1, (Date.valueOf(Tools.DatetoStr(sorder.getOdate(),
					"yyyy-MM-dd"))));
			ps.setString(2, sorder.getOstart());
			ps.setString(3, sorder.getOtel());
			ps.setString(4, sorder.getOtime());
			ps.setInt(5, sorder.getSid());
			ps.setFloat(6, sorder.getSpend());
			ps.setInt(7, sorder.getUid());
			ps.setString(8, sorder.getSpendtype());
			ps.setInt(9, sorder.getOid());
			i = ps.executeUpdate();
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			mydb.close();
		}
		return i;
	}

	public int findAllCounts(Sorder aff) {
		int count = 0;
		String sql = "select count(*) from sorder  e  left join users u on u.uid=e.uid left join site s on s.sid=e.sid  where 1=1 ";
		if (aff != null && aff.getUid() != 0) {
			sql += " and e.uid=" + aff.getUid();
		}
		if (aff != null && aff.getUname() != null)

			sql += " and (uname like '%" + aff.getUname()
					+ "%' or urealname like '%" + aff.getUname() + "%' )";
		if (aff != null && aff.getSname() != null)
			sql += "and sremarks like '%" + aff.getSname() + "%' ";
		try {
			ps = mydb.getCon().prepareStatement(sql);
			rs = ps.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			mydb.close();
		}
		return count;
	}

	public List<Site> findsum(String startdate, String enddate, String key) {
		List<Site> list = new ArrayList<Site>();
		Site site = null;
		String sql = "select o.sid,s.sremarks,sum(spend) total from sorder o " +
				"left join site s on s.sid = o.sid  where 1=1 ";
		if(startdate!=null&&enddate!=null&&!startdate.equals("")&&!enddate.equals(""))
		sql+=" and spendtime between  '"+startdate+" 00:00:00' and '"+enddate+" 23:59:59'  " ;
		sql+="group by o.sid,s.sremarks ";
		try {
			ps = mydb.getCon().prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				site = new Site();
				site.setSid(rs.getInt("sid"));
				site.setSprice(rs.getFloat("total"));
//				site.setSimg(rs.getString("simg"));
				site.setSremarks(rs.getString("sremarks"));
				list.add(site);
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				ps.close();
			} catch (SQLException e) {

				e.printStackTrace();
			}
			mydb.close();
		}
		return list;
	}

	public int insertsorder(Sorder sorder) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		String sql = "insert into sorder ( odate,ostart,otel,otime,sid,spend,uid,spendtype) "
				+ "values(?,?,?,?,?,?,?,?)";
		int i = 0;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			ps.setDate(1, (Date.valueOf(Tools.DatetoStr(sorder.getOdate(),
					"yyyy-MM-dd"))));
			ps.setString(2, sorder.getOstart());
			ps.setString(3, sorder.getOtel());
			ps.setString(4, sorder.getOtime());
			ps.setInt(5, sorder.getSid());
			ps.setFloat(6, sorder.getSpend());
			ps.setInt(7, sorder.getUid());
			ps.setString(8, sorder.getSpendtype());
			i = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
