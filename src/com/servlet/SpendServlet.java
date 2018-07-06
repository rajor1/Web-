package com.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.PagingModel;
import com.bean.Recharge;
import com.bean.Spend;
import com.bean.Users;
import com.dao.SpendDao;
import com.dao.UsersDao;
import com.dao.RechargeDao;
import com.tool.Tools;

public class SpendServlet extends HttpServlet {

	UsersDao usersDao = new UsersDao();
	SpendDao spendDao = new SpendDao();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String methodName = request.getParameter("method");
		if(methodName != null&& methodName != ""){
			if (methodName.equals("findAllUsers")) {
				findAllUsers(request, response);
			} else if (methodName.equals("findAllSpend")) {
				findAllSpend(request, response);
			} else if (methodName.equals("updatePage")) {
				updatePage(request, response);
			} else if (methodName.equals("deleteSpend")) {
				deleteSpend(request, response);
			} else if (methodName.equals("saveSpend")) {
				try {
					saveSpend(request, response);
				} catch (NoSuchAlgorithmException e) {
					
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (methodName.equals("addSpend")) {
				request.getRequestDispatcher("/pages/Spend/addSpend.jsp")
						.forward(request, response);

			} else if (methodName.equals("updateSpend")) {
				try {
					updateSpend(request, response);
				} catch (NoSuchAlgorithmException e) {					
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
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
		pagingModel.setPageLink(url+"/servlet/SpendServlet?method=findAllUsers&uname="+Tools.encode(request.getParameter("uname")));
		request.setAttribute("list", list);
		request.setAttribute("users", users);
		request.setAttribute("pagingModel", pagingModel);
		request.setAttribute("currentp", pageSize);
		request.setAttribute("pagenum", showpage);
		request.getRequestDispatcher("/pages/Spend/userslist.jsp").forward(
				request, response);
	}
	public void findAllSpend(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String showpage = request.getParameter("showpage");
		if (showpage == "" || showpage == null) {// 当前页
			showpage = "1";
		}
		Spend Spend = new Spend();
		Spend.setUid(Integer.parseInt(request.getParameter("uid")));
		int allRecord = spendDao.findAllCounts(Spend);
		int pageIndex = Integer.parseInt(showpage);
		int pageSize = Tools.PageSize;
		int firstRecord = (pageIndex - 1)*pageSize+1;
		int lastRecord = pageIndex * pageSize;
		List<Spend> list = spendDao.findAllSpend(Spend,firstRecord, lastRecord);
		PagingModel pagingModel = new PagingModel();
		pagingModel.setPerR(pageSize);
		pagingModel.setCurrentP(showpage);
		pagingModel.setAllR(allRecord);
		pagingModel.setAllP();
		pagingModel.setPageInfo();
		String url = request.getContextPath();
		pagingModel.setPageLink(url+"/servlet/SpendServlet?method=findAllSpend&uid="+Tools.encode(request.getParameter("uid")));
		request.setAttribute("list", list);
		request.setAttribute("uid", request.getParameter("uid"));
		request.setAttribute("Spend", Spend);
		request.setAttribute("pagingModel", pagingModel);
		request.setAttribute("currentp", pageSize);
		request.setAttribute("pagenum", showpage);
		request.getRequestDispatcher("/pages/Spend/Spendlist.jsp").forward(
				request, response);
	}
	public void updatePage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("rid");
		if (id != null &&!id.equals("") ) {
			int rid= Integer.parseInt(id);
			Spend Spend = spendDao.findSpendById(rid);
			request.setAttribute("Spend", Spend);
			Users user = usersDao.findUsersById(Spend.getUid());
			request.setAttribute("users",user );
			System.out.println(Spend.getUid()+"----"+request.getParameter("uid"));
			request.getRequestDispatcher("/pages/Spend/updateSpend.jsp")
					.forward(request, response);

		} else {
			request.setAttribute("message", "出错啦");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}
	
	

	public void deleteSpend(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("uid");
		if (id != null &&!id.equals("") ) {
			int uid = Integer.parseInt(id);
			spendDao.deleteSpendById(uid);
			request.getRequestDispatcher(
					"SpendServlet?method=findAllSpend").forward(
					request, response);
		} else {
			request.setAttribute("message", "删除失败，请确认");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}

	public void saveSpend(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			NoSuchAlgorithmException, ParseException {
		Integer uid = Integer.parseInt(request.getParameter("uid"));
		Integer sid = Integer.parseInt(request.getParameter("sid"));
		Date sdate = Tools.StrToDate(Tools.FormatDate(request.getParameter("sdate"), "yyyy-MM-dd"), "yyyy-MM-dd");
		Float smoney = Float.parseFloat(request.getParameter("smoney"));
		Spend spend = new Spend();
//		spend.setOid(oid);
		spend.setSdate(sdate);
		spend.setSid(sid);
		spend.setSmoney(smoney);
		spend.setUid(uid);
		int i = spendDao.insertSpend(spend);
		if (i > 0) {
			
			request.setAttribute("url", "servlet/SpendServlet?method=findAllUsers&uid="+uid);
			request.setAttribute("message", "添加成功");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
			
		}
	}

	public void updateSpend(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, NoSuchAlgorithmException, ParseException {
		Integer oid = Integer.parseInt(request.getParameter("oid"));
		Integer uid = Integer.parseInt(request.getParameter("uid"));
		Integer sid = Integer.parseInt(request.getParameter("sid"));
		Date sdate = Tools.StrToDate(Tools.FormatDate(request.getParameter("sdate"), "yyyy-MM-dd"), "yyyy-MM-dd");
		Float smoney = Float.parseFloat(request.getParameter("smoney"));
		
		Spend spend = new Spend();
		spend.setOid(oid);
		spend.setSdate(sdate);
		spend.setSid(sid);
		spend.setSmoney(smoney);
		spend.setUid(uid);
		int i = spendDao.updateSpend(spend);
		if (i > 0) {
			request.setAttribute("url", "servlet/SpendServlet?method=findAllSpend&uid="+uid);
			System.out.print(request.getParameter("url"));
			request.setAttribute("message", "更新成功");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}
}
	
