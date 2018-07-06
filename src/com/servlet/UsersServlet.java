package com.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.PagingModel;
import com.bean.Users;
import com.dao.UsersDao;
import com.tool.Tools;

public class UsersServlet extends HttpServlet {

	UsersDao usersDao = new UsersDao();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String methodName = request.getParameter("method");
		if(methodName != null&& methodName != ""){
			if (methodName.equals("findAllUsers")) {
				findAllUsers(request, response);
			} else if (methodName.equals("updatePage")) {
				updatePage(request, response);
			} else if (methodName.equals("deleteUsers")) {
				deleteUsers(request, response);
			} else if (methodName.equals("saveUsers")) {
				try {
					saveUsers(request, response);
				} catch (NoSuchAlgorithmException e) {
					
					e.printStackTrace();
				}
			} else if (methodName.equals("addUsers")) {
				int allRecord = usersDao.findAllCounts(null);
				List<Users> list = usersDao.findAllUsers(null,1, allRecord);
				request.setAttribute("list", list);
				request.getRequestDispatcher("/pages/users/addusers.jsp")
						.forward(request, response);

			} else if (methodName.equals("updateUsers")) {
				try {
					updateUsers(request, response);
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
	
	public void findAllUsers(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String showpage = request.getParameter("showpage");
		if (showpage == "" || showpage == null) {// 当前页
			showpage = "1";
		}
		Users users = new Users();
		users.setUname(Tools.encode(request.getParameter("uname")));
		int allRecord = usersDao.findAllCounts(users);
		int pageIndex = Integer.parseInt(showpage);
		int pageSize = Tools.PageSize;
		int firstRecord = (pageIndex - 1)*pageSize+1;
		int lastRecord = pageIndex * pageSize;
		List<Users> list = usersDao.findAllUsers(users,firstRecord, lastRecord);
		PagingModel pagingModel = new PagingModel();
		pagingModel.setPerR(pageSize);
		pagingModel.setCurrentP(showpage);
		pagingModel.setAllR(allRecord);
		pagingModel.setAllP();
		pagingModel.setPageInfo();
		String url = request.getContextPath();
		pagingModel.setPageLink(url+"/servlet/UsersServlet?method=findAllUsers&uname="+Tools.encode(request.getParameter("uname")));
		request.setAttribute("list", list);
		request.setAttribute("users", users);
		request.setAttribute("pagingModel", pagingModel);
		request.setAttribute("currentp", pageSize);
		request.setAttribute("pagenum", showpage);
		request.getRequestDispatcher("/pages/users/userslist.jsp").forward(
				request, response);
	}

	public void updatePage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("uid");
		if (id != null &&!id.equals("") ) {
			int uid= Integer.parseInt(id);
			Users users = usersDao.findUsersById(uid);
			request.setAttribute("users", users);
			int allRecord = usersDao.findAllCounts(null);
			List<Users> list = usersDao.findAllUsers(null,1, allRecord);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/pages/users/updateusers.jsp")
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
			Integer eid = (Integer) uid;
			Users users = usersDao.findUsersById(eid);
			request.setAttribute("users", users);
			int allRecord = usersDao.findAllCounts(null);
			List<Users> list = usersDao.findAllUsers(null,1, allRecord);
			request.setAttribute("list", list);
			request.getRequestDispatcher("/pages/users/updatepassword.jsp")
					.forward(request, response);

		} else {
			request.setAttribute("message", "出错啦");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}

	public void deleteUsers(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("uid");
		if (id != null &&!id.equals("") ) {
			int uid = Integer.parseInt(id);
			usersDao.deleteUsersById(uid);
			request.getRequestDispatcher(
					"UsersServlet?method=findAllUsers").forward(
					request, response);
		} else {
			request.setAttribute("message", "删除失败，请确认");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}

	public void saveUsers(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			NoSuchAlgorithmException {
		String uname = request.getParameter("uname");
		String urealname = request.getParameter("urealname");
		String upassword = request.getParameter("upassword");
		String utel = request.getParameter("utel");
		String uaddress = request.getParameter("uaddress");
		String usex = request.getParameter("usex");
		Users users = new Users();
		users.setUname(Tools.encode(uname));
		users.setUpassword(Tools.encode(upassword));
		users.setUrealname(Tools.encode(urealname));
		users.setUtel(Tools.encode(utel));
		users.setUaddress(Tools.encode(uaddress));
		users.setUsex(Tools.encode(usex));
		int i = usersDao.insertUsers(users);
		String addtype=request.getParameter("addtype");
		if (i > 0) {
			if(addtype.equals("reg"))
			{
					request.setAttribute("url", "servlet/LoginServlet?method=login&type=2&type=2&userName="+users.getUname()+"&password="+users.getUpassword());
					request.setAttribute("message", "添加成功");
					request.getRequestDispatcher("/pages/message.jsp").forward(request,
							response);
				
			}
			else
			{
			request.setAttribute("url", "servlet/UsersServlet?method=findAllUsers");
			request.setAttribute("message", "添加成功");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
			}
		}
	}

	public void updateUsers(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, NoSuchAlgorithmException {
		int uid = Integer.parseInt(request.getParameter("uid"));
		String uname = request.getParameter("uname");
		String urealname = request.getParameter("urealname");
		String upassword = request.getParameter("upassword");
		String utel = request.getParameter("utel");
		String uaddress = request.getParameter("uaddress");
		String usex = request.getParameter("usex");
		Users users = new Users();
		users.setUid(uid);
		users.setUname(Tools.encode(uname));
		users.setUpassword(Tools.encode(upassword));
		users.setUrealname(Tools.encode(urealname));
		users.setUtel(Tools.encode(utel));
		users.setUaddress(Tools.encode(uaddress));
		users.setUsex(Tools.encode(usex));
		int i = usersDao.updateUsers(users);
		System.out.print(request.getParameter("url"));
		if (i > 0) {
			if(!request.getParameter("updatetype").equals("2"))
				request.setAttribute("url", "servlet/UsersServlet?method=findAllUsers");
			System.out.print(request.getParameter("url"));
			request.setAttribute("message", "更新成功");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}
	public void updatePassword(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, NoSuchAlgorithmException {
		String uid = request.getParameter("uid");
		String upassword = request.getParameter("upassword");
		Users users = new Users();
		users.setUid(Integer.parseInt(uid));
		users.setUpassword(Tools.encode(upassword));
		int i = usersDao.updatePassword(users);
		if (i > 0) {
			request.setAttribute("url", "pages/sy.jsp");
			request.setAttribute("message", "更新成功，请记住新密码！");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}
}
	
