package com.servlet;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.PagingModel;
import com.bean.Recharge;
import com.bean.Site;
import com.bean.Users;
import com.dao.UsersDao;
import com.dao.RechargeDao;
import com.tool.Tools;

public class RechargeServlet extends HttpServlet {

	UsersDao usersDao = new UsersDao();
	RechargeDao rechargeDao = new RechargeDao();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String methodName = request.getParameter("method");
		if(methodName != null&& methodName != ""){
			if (methodName.equals("findAllUsers")) {
				findAllUsers(request, response);
			} else if (methodName.equals("statistic")) {
				statistic(request, response);
			} else if (methodName.equals("findAllRecharge")) {
				findAllRecharge(request, response);
			} else if (methodName.equals("updatePage")) {
				updatePage(request, response);
			} else if (methodName.equals("deleteRecharge")) {
				deleteRecharge(request, response);
			} else if (methodName.equals("saveRecharge")) {
				try {
					saveRecharge(request, response);
				} catch (NoSuchAlgorithmException e) {
					
					e.printStackTrace();
				}
			} else if (methodName.equals("addRecharge")) {
				Users user = usersDao.findUsersById(Integer.parseInt(request.getParameter("uid")));
				request.setAttribute("ruser", user);
				request.getRequestDispatcher("/pages/recharge/addrecharge.jsp")
						.forward(request, response);

			} else if (methodName.equals("updateRecharge")) {
				try {
					
					updateRecharge(request, response);
				} catch (NoSuchAlgorithmException e) {					
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
		pagingModel.setPageLink(url+"/servlet/RechargeServlet?method=findAllUsers&uname="+Tools.encode(request.getParameter("uname")));
		request.setAttribute("list", list);
		request.setAttribute("users", users);
		request.setAttribute("pagingModel", pagingModel);
		request.setAttribute("currentp", pageSize);
		request.setAttribute("pagenum", showpage);
		request.getRequestDispatcher("/pages/recharge/userslist.jsp").forward(
				request, response);
	}
	public void findAllRecharge(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String showpage = request.getParameter("showpage");
		if (showpage == "" || showpage == null) {// 当前页
			showpage = "1";
		}
		Recharge recharge = new Recharge();
		recharge.setUid(Integer.parseInt(request.getParameter("uid")));
		int allRecord = rechargeDao.findAllCounts(recharge);
		int pageIndex = Integer.parseInt(showpage);
		int pageSize = Tools.PageSize;
		int firstRecord = (pageIndex - 1)*pageSize+1;
		int lastRecord = pageIndex * pageSize;
		List<Recharge> list = rechargeDao.findAllRecharge(recharge,firstRecord, lastRecord);
		PagingModel pagingModel = new PagingModel();
		pagingModel.setPerR(pageSize);
		pagingModel.setCurrentP(showpage);
		pagingModel.setAllR(allRecord);
		pagingModel.setAllP();
		pagingModel.setPageInfo();
		String url = request.getContextPath();
		pagingModel.setPageLink(url+"/servlet/RechargeServlet?method=findAllRecharge&uid="+Tools.encode(request.getParameter("uid")));
		request.setAttribute("list", list);
		request.setAttribute("uid", request.getParameter("uid"));
		request.setAttribute("recharge", recharge);
		request.setAttribute("pagingModel", pagingModel);
		request.setAttribute("currentp", pageSize);
		request.setAttribute("pagenum", showpage);
		request.getRequestDispatcher("/pages/recharge/rechargelist.jsp").forward(
				request, response);
	}
	public void updatePage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("rid");
		if (id != null &&!id.equals("") ) {
			int rid= Integer.parseInt(id);
			Recharge recharge = rechargeDao.findRechargeById(rid);
			request.setAttribute("recharge", recharge);
			Users user = usersDao.findUsersById(recharge.getUid());
			request.setAttribute("users",user );
			System.out.println(recharge.getUid()+"----"+request.getParameter("uid"));
			request.getRequestDispatcher("/pages/recharge/updaterecharge.jsp")
					.forward(request, response);

		} else {
			request.setAttribute("message", "出错啦");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}
	
	

	public void deleteRecharge(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("rid");
		if (id != null &&!id.equals("") ) {
			int rid = Integer.parseInt(id);
			rechargeDao.deleteRechargeById(rid);
			request.getRequestDispatcher(
					"RechargeServlet?method=findAllRecharge").forward(
					request, response);
		} else {
			request.setAttribute("message", "删除失败，请确认");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}

	public void saveRecharge(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			NoSuchAlgorithmException {
		Integer uid = Integer.parseInt(request.getParameter("uid"));
		Float rmoney = Float.parseFloat(request.getParameter("rmoney"));
		
		Recharge recharge = new Recharge();
		recharge.setUid(uid);
		recharge.setRmoney(rmoney);
		
		int i = rechargeDao.insertRecharge(recharge);
		if (i > 0) {
			
			request.setAttribute("url", "servlet/RechargeServlet?method=findAllUsers&uid="+uid);
			request.setAttribute("message", "添加成功");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
			
		}
	}

	public void updateRecharge(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, NoSuchAlgorithmException {
		Integer rid = Integer.parseInt(request.getParameter("rid"));
		Integer uid = Integer.parseInt(request.getParameter("uid"));
		Float rmoney = Float.parseFloat(request.getParameter("rmoney"));
	
		Recharge recharge = new Recharge();
		recharge.setRid(rid);
		recharge.setRmoney(rmoney);
		int i = rechargeDao.updateRecharge(recharge);
		if (i > 0) {
			request.setAttribute("url", "servlet/RechargeServlet?method=findAllRecharge&uid="+uid);
			System.out.print(request.getParameter("url"));
			request.setAttribute("message", "更新成功");
			
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}
	
	public void statistic(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String startdate =request.getParameter("startdate");
		String enddate =(request.getParameter("enddate"));
		Float total = rechargeDao.findsum(startdate, enddate, "");
		request.setAttribute("total", total);
		request.getRequestDispatcher("/pages/sorder/statisticlist.jsp").forward(
				request, response);
	}
}
	
