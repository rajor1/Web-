package com.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bean.PagingModel;
import com.bean.Employee;
import com.dao.EmployeeDao;
import com.tool.Tools;

public class EmployeeServlet extends HttpServlet {
	EmployeeDao employeeDao = new EmployeeDao();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String methodName = request.getParameter("method");
		if(methodName != null&& methodName != ""){
			if (methodName.equals("findAllEmployee")) {
				findAllEmployee(request, response);
			} else if (methodName.equals("updatePage")) {
				updatePage(request, response);
			} else if (methodName.equals("deleteEmployee")) {
				deleteEmployee(request, response);
			} else if (methodName.equals("saveEmployee")) {
				try {
					saveEmployee(request, response);
				} catch (NoSuchAlgorithmException e) {
					
					e.printStackTrace();
				}
			} else if (methodName.equals("addEmployee")) {
				int allRecord = employeeDao.findAllCounts(null);
				List<Employee> list = employeeDao.findAllEmployee(null,1, allRecord);
				request.setAttribute("list", list);
				request.getRequestDispatcher("/pages/employee/addemployee.jsp")
						.forward(request, response);
			} else if (methodName.equals("updateEmployee")) {
				try {
					updateEmployee(request, response);
				} catch (NoSuchAlgorithmException e) {					
					e.printStackTrace();
				}
			}else if (methodName.equals("updatePasswordPage")) {
					updatePasswordPage(request, response);
				
			}else if (methodName.equals("updatePassword")) {
				try {
					updatePassword(request, response);
				} catch (NoSuchAlgorithmException e) {					
					e.printStackTrace();
				}
			}  else {
				request.setAttribute("message", "口令出错");
				request.getRequestDispatcher("/pages/message.jsp").forward(
						request, response);
			}
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	public void findAllEmployee(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String showpage = request.getParameter("showpage");
		
		if (showpage == "" || showpage == null) {// 当前页
			showpage = "1";
		}
		Employee employee = new Employee();
		employee.setEname(Tools.encode(request.getParameter("ename")));
		int allRecord = employeeDao.findAllCounts(employee);
		int pageIndex = Integer.parseInt(showpage);
		int pageSize = Tools.PageSize;
		int firstRecord = (pageIndex - 1)*pageSize+1;
		int lastRecord = pageIndex * pageSize;
		List<Employee> list = employeeDao.findAllEmployee(employee,firstRecord, lastRecord);
		PagingModel pagingModel = new PagingModel();
		pagingModel.setPerR(pageSize);
		pagingModel.setCurrentP(showpage);
		pagingModel.setAllR(allRecord);
		pagingModel.setAllP();
		pagingModel.setPageInfo();
		String url = request.getContextPath();
		pagingModel.setPageLink(url+"/servlet/EmployeeServlet?method=findAllEmployee&ename="+Tools.encode(request.getParameter("ename")));
		request.setAttribute("list", list);
		request.setAttribute("employee", employee);
		request.setAttribute("pagingModel", pagingModel);
		request.setAttribute("currentp", pageSize);
		request.setAttribute("pagenum", showpage);
		request.getRequestDispatcher("/pages/employee/employeelist.jsp").forward(
				request, response);
	}

	public void updatePage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("eid");
		if (id != null &&!id.equals("") ) {
			int uid= Integer.parseInt(id);
			Employee employee = employeeDao.findEmployeeById(uid);
			request.setAttribute("employee", employee);
			int allRecord = employeeDao.findAllCounts(null);
			List<Employee> list = employeeDao.findAllEmployee(null,1, allRecord);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/pages/employee/updateemployee.jsp")
					.forward(request, response);

		} else {
			request.setAttribute("message", "出错啦");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}
	
	public void updatePasswordPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Object uid = request.getSession().getAttribute("userid");
		if (uid != null ) {
			int eid= (Integer)uid;
			Employee employee = employeeDao.findEmployeeById(eid);
			request.setAttribute("employee", employee);
			int allRecord = employeeDao.findAllCounts(null);
			List<Employee> list = employeeDao.findAllEmployee(null,1, allRecord);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/pages/employee/updatepassword.jsp")
					.forward(request, response);

		} else {
			request.setAttribute("message", "出错啦");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}

	public void deleteEmployee(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("eid");
		if (id != null &&!id.equals("") ) {
			int uid = Integer.parseInt(id);
			employeeDao.deleteEmployeeById(uid);
			request.getRequestDispatcher(
					"EmployeeServlet?method=findAllEmployee").forward(
					request, response);
		} else {
			request.setAttribute("message", "删除失败，请确认");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}

	public void saveEmployee(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			NoSuchAlgorithmException {
		String ename = request.getParameter("ename");
		String epassword = request.getParameter("epassword");
		Employee employee = new Employee();
		employee.setEname(Tools.encode(ename));
		employee.setEpassword(Tools.encode(epassword));	
		employee.setErealname(Tools.encode(request.getParameter("erealname")));
		employee.setEtel(Tools.encode(request.getParameter("etel")));
		employee.setEaddress(Tools.encode(request.getParameter("eaddress")));
		
		int i = employeeDao.insertEmployee(employee);
		if (i > 0) {
			request.setAttribute("url", "servlet/EmployeeServlet?method=findAllEmployee");
			request.setAttribute("message", "添加成功");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}

	public void updateEmployee(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, NoSuchAlgorithmException {
		String ename = request.getParameter("ename");
		String epassword = request.getParameter("epassword");
		String eid = request.getParameter("eid");
		Employee employee = new Employee();
		employee.setEname(Tools.encode(ename));
		employee.setEpassword(Tools.encode(epassword));	
		employee.setErealname(Tools.encode(request.getParameter("erealname")));
		employee.setEtel(Tools.encode(request.getParameter("etel")));
		employee.setEaddress(Tools.encode(request.getParameter("eaddress")));
		employee.setEid(Integer.parseInt(eid));
		int i = employeeDao.updateEmployee(employee);
		if (i > 0) {
			request.setAttribute("url", "servlet/EmployeeServlet?method=findAllEmployee");
			request.setAttribute("message", "更新成功");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}
	public void updatePassword(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, NoSuchAlgorithmException {
		String eid = request.getParameter("eid");
		String epassword = request.getParameter("epassword");
		Employee employee = new Employee();
		employee.setEid(Integer.parseInt(eid));
		employee.setEpassword(Tools.encode(epassword));
		int i = employeeDao.updatePassword(employee);
		if (i > 0) {
			request.setAttribute("url", "pages/sy.jsp");
			request.setAttribute("message", "更新成功,请记住新密码！");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}
}
	
