package com.dao;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.bean.Employee;
public class EmployeeDao {
	PreparedStatement ps = null;
	ResultSet rs = null;
	DBSql mydb = null;
	public EmployeeDao() {
		mydb = new DBSql();
	}
	
	
	
	public int deleteEmployeeById(int id) {
		String sql = "delete from employee where eid = ?";
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

	public List<Employee> findAllEmployee(Employee user,int firstRecord, int lastRecord) {
		List<Employee> list = new ArrayList<Employee>();
		Employee employee = null;
		String sql = "select * from " +
				"(select e.*,row_number() over(order by ename) rn from employee e" +
				"   where 1=1 " ;
		if(user!=null)
		{
			if(user.getEname()!=null)
				sql+=" and (ename like '%"+user.getEname()+"%' or erealname like '%"+user.getEname()+"%') ";
			
		}
		sql +=" ) tt" +
				" where rn between "+firstRecord +" and "+lastRecord;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				employee = new Employee();
				employee.setEid(rs.getInt("eid"));
				employee.setEname(rs.getString("ename"));
				employee.setErealname(rs.getString("erealname"));
				employee.setEpassword(rs.getString("epassword"));
				employee.setEtel(rs.getString("etel"));
				employee.setEaddress(rs.getString("eaddress"));
				list.add(employee);
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

	public Employee findEmployeeById(int id) {
		Employee employee = null;
		//                     1    2    3        4         5     6      7   8     9 
		String sql = "select *  " +
				"from employee   where eid = "+id;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			rs = ps.executeQuery();
			if(rs.next()){
				employee = new Employee();
				employee.setEid(rs.getInt("eid"));
				employee.setEname(rs.getString("ename"));
				employee.setErealname(rs.getString("erealname"));
				employee.setEpassword(rs.getString("epassword"));
				employee.setEtel(rs.getString("etel"));
				employee.setEaddress(rs.getString("eaddress"));
			
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
		return employee;
	}

	public int updatePassword(Employee employee) {
		//
		String sql = "update employee set epassword=?  where eid=?";
		int i = 0;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			ps.setString(1, employee.getEpassword());
			ps.setInt(2, employee.getEid());
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
	public int updateEmployee(Employee employee) {
		//                                  1         2         3         4         5 
//		eid,ecode,ename,erealname,epassword,etel,email,eqq,emark
		String sql = "update employee set ename=?,erealname=?,epassword=?,etel=?,eaddress=?  where eid=?";
		int i = 0;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			ps.setString(1, employee.getEname());
			ps.setString(2, employee.getErealname());
			ps.setString(3, employee.getEpassword());
			ps.setString(4, employee.getEtel());
			ps.setString(5, employee.getEaddress());
			ps.setInt(6, employee.getEid());
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
	public int findAllCounts(Employee employee) {
		int count = 0;
		String sql = "select count(*) from employee where 1=1";
		if(employee!=null)
		{
			if(employee.getEname()!=null)
				sql+=" and (ename like '%"+employee.getEname()+"%' or erealname like '%"+employee.getEname()+"%' )";
			
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
	public int insertEmployee(Employee employee) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		String sql = "insert into employee ( ename,erealname,epassword,etel,eaddress) " +
				"values(?,?,?,?,?)";
		int i = 0;
		try {
			ps = mydb.getCon().prepareStatement(sql);
			ps.setString(1, employee.getEname());
			ps.setString(2, employee.getErealname());
			ps.setString(3, employee.getEpassword());
			ps.setString(4, employee.getEtel());
			ps.setString(5, employee.getEaddress());
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
