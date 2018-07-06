package com.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.bean.Login;
import com.bean.PagingModel;
import com.dao.LoginDao;
import com.tool.Tools;

public class LoginServlet extends HttpServlet {
	LoginDao loginDao = null;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String methodName = request.getParameter("method");
		if(methodName != null&& methodName.equals("login")){
			loginDao = new LoginDao();
			String name = Tools.encode(request.getParameter("userName"));
			String type = request.getParameter("type");
			String password = request.getParameter("password");
			Login login = new Login();
			if(!name.equals("")&&name!=null&&!password.equals("")&&password != ""){
				try {
					if(type.equals("1"))
						login = loginDao.findEmployeeByNameAndPassword(name, password);
					if(type.equals("2"))
						login = loginDao.findUserByNameAndPassword(name, password);
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if(login!=null&&login.getUid()!=0){
					HttpSession session = request.getSession();
					session.setAttribute("username", name);			
					session.setAttribute("userid", login.getUid());
					session.setAttribute("type", login.getUtype());
					session.setAttribute("urealname", login.getUrealname());
					request.getRequestDispatcher("/pages/index.jsp").forward(request, response);
				}else{
					request.setAttribute("message", "用户名或密码错误");
					request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
				}
			}
		}
		if(methodName != null&& methodName.equals("loginout")){
			request.setAttribute("username", "");			
			request.setAttribute("userid", "");
			request.setAttribute("type", "");
			request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
		}
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
	
