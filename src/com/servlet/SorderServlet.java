package com.servlet;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.bean.PagingModel;
import com.bean.Recharge;
import com.bean.Site;
import com.bean.Sorder;
import com.bean.Spend;
import com.bean.Users;
import com.dao.SiteDao;
import com.dao.SorderDao;
import com.dao.SpendDao;
import com.dao.UsersDao;
import com.dao.RechargeDao;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.HeaderTokenizer.Token;
import com.tool.Tools;

public class SorderServlet extends HttpServlet {
	UsersDao usersDao = new UsersDao();
 	SorderDao sorderDao = new SorderDao();
	SiteDao siteDao= new SiteDao();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String methodName = request.getParameter("method");
		if(methodName != null&& methodName != ""){
			if (methodName.equals("findAllSorder")) {
				findAllSorder(request, response);
			} else if (methodName.equals("findAllSorderSign")) {
				findAllSorderSign(request, response);
			} else if (methodName.equals("findAllSite")) {
						findAllSite(request, response);
			} else if (methodName.equals("findAllUsers")) {
				findAllUsers(request, response);
			} else if (methodName.equals("updatePage")) {
				updatePage(request, response);
			}else if (methodName.equals("signPage")) {
				signPage(request, response);
			} else if (methodName.equals("updateSorder")) {
				try {
					updateSorder(request, response);
				} catch (NoSuchAlgorithmException e) {					
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (methodName.equals("spend")) {
				try {
					request.setAttribute("update", "spend");
					updateSorder(request, response);
				} catch (NoSuchAlgorithmException e) {					
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (methodName.equals("deleteSorder")) {
				deleteSorder(request, response);
			} else if (methodName.equals("saveSorder")) {
				try {
					saveSorder(request, response);
				} catch (NoSuchAlgorithmException e) {
					
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (methodName.equals("addSorder")) {
				request.setAttribute("addtype", request.getParameter("addtype"));
				if(request.getParameter("addtype").equals("myadd"))
					request.getRequestDispatcher("/pages/sorder/addsordermy.jsp")
					.forward(request, response);
				if(request.getParameter("addtype").equals("add"))
				request.getRequestDispatcher("/pages/sorder/addsorder.jsp")
						.forward(request, response);
				if(request.getParameter("addtype").equals("sign"))
				request.getRequestDispatcher("/pages/sorder/addsordersign.jsp")
				.forward(request, response);

			} else if (methodName.equals("updateSorder")) {
				try {
					updateSorder(request, response);
				} catch (NoSuchAlgorithmException e) {					
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (methodName.equals("statistic")) {
				statistic(request, response);
			}else {
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
		pagingModel.setPageLink(url+"/servlet/SorderServlet?method=findAllUsers&uname="+Tools.encode(request.getParameter("uname")));
		request.setAttribute("list", list);
		request.setAttribute("users", users);
		request.setAttribute("pagingModel", pagingModel);
		request.setAttribute("currentp", pageSize);
		request.setAttribute("pagenum", showpage);
		request.getRequestDispatcher("/pages/sorder/userslist.jsp").forward(
				request, response);
	}
	public void findAllSite(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String showpage = request.getParameter("showpage");
		if (showpage == "" || showpage == null) {// 当前页
			showpage = "1";
		}
		Site site = new Site();
		site.setSremarks(Tools.encode(request.getParameter("sremarks")));
		int allRecord = siteDao.findAllCounts(site);
		int pageIndex = Integer.parseInt(showpage);
		int pageSize = 5;
		int firstRecord = (pageIndex - 1)*pageSize+1;
		int lastRecord = pageIndex * pageSize;
		List<Site> list = siteDao.findAllSite(site,firstRecord, lastRecord);
		PagingModel pagingModel = new PagingModel();
		pagingModel.setPerR(pageSize);
		pagingModel.setCurrentP(showpage);
		pagingModel.setAllR(allRecord);
		pagingModel.setAllP();
		pagingModel.setPageInfo();
		String url = request.getContextPath();
		pagingModel.setPageLink(url+"/servlet/SorderServlet?method=findAllSite&sremarks="+Tools.encode(request.getParameter("sremarks")));
		request.setAttribute("list", list);
		request.setAttribute("site", site);
		
		request.setAttribute("pagingModel", pagingModel);
		request.setAttribute("currentp", pageSize);
		request.setAttribute("pagenum", showpage);
		request.getRequestDispatcher("/pages/sorder/sitelist.jsp").forward(
				request, response);
	}
	
	public void findAllSorder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String showpage = request.getParameter("showpage");
		if (showpage == "" || showpage == null) {// 当前页
			showpage = "1";
		}
		Sorder sorder = new Sorder();
		if(request.getParameter("uid")!=null)
		{
			sorder.setUid(Integer.parseInt(request.getParameter("uid")));
		}
		sorder.setSname(Tools.encode(request.getParameter("sname")));
		sorder.setUname(Tools.encode(request.getParameter("uname")));
		int allRecord = sorderDao.findAllCounts(sorder);
		int pageIndex = Integer.parseInt(showpage);
		int pageSize = Tools.PageSize;
		int firstRecord = (pageIndex - 1)*pageSize+1;
		int lastRecord = pageIndex * pageSize;
		List<Sorder> list = sorderDao.findAllsorder(sorder,firstRecord, lastRecord);
		PagingModel pagingModel = new PagingModel();
		pagingModel.setPerR(pageSize);
		pagingModel.setCurrentP(showpage);
		pagingModel.setAllR(allRecord);
		pagingModel.setAllP();
		pagingModel.setPageInfo();
		String url = request.getContextPath();
		pagingModel.setPageLink(url+"/servlet/SorderServlet?method=findAllSorder&uid="+sorder.getUid()+"&uname="+Tools.encode(request.getParameter("uname"))+"&sname="+Tools.encode(request.getParameter("sname")));
		request.setAttribute("list", list);
		request.setAttribute("sorder", sorder);
		request.setAttribute("pagingModel", pagingModel);
		request.setAttribute("currentp", pageSize);
		request.setAttribute("pagenum", showpage);
		request.getRequestDispatcher("/pages/sorder/sorderlist.jsp").forward(
				request, response);
	}

	public void findAllSorderSign(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String showpage = request.getParameter("showpage");
		if (showpage == "" || showpage == null) {// 当前页
			showpage = "1";
		}
		Sorder sorder = new Sorder();
		sorder.setSname(Tools.encode(request.getParameter("sname")));
		sorder.setUname(Tools.encode(request.getParameter("uname")));
		int allRecord = sorderDao.findAllCounts(sorder);
		int pageIndex = Integer.parseInt(showpage);
		int pageSize =  Tools.PageSize;
		int firstRecord = (pageIndex - 1)*pageSize+1;
		int lastRecord = pageIndex * pageSize;
		List<Sorder> list = sorderDao.findAllsorder(sorder,firstRecord, lastRecord);
		PagingModel pagingModel = new PagingModel();
		pagingModel.setPerR(pageSize);
		pagingModel.setCurrentP(showpage);
		pagingModel.setAllR(allRecord);
		pagingModel.setAllP();
		pagingModel.setPageInfo();
		String url = request.getContextPath();
		pagingModel.setPageLink(url+"/servlet/SorderServlet?method=findAllSorderSign&uname="+Tools.encode(request.getParameter("uname"))+"&sname="+Tools.encode(request.getParameter("sname")));
		request.setAttribute("list", list);
		request.setAttribute("sorder", sorder);
		request.setAttribute("pagingModel", pagingModel);
		request.setAttribute("currentp", pageSize);
		request.setAttribute("pagenum", showpage);
		request.getRequestDispatcher("/pages/sorder/sordersignlist.jsp").forward(
				request, response);
	}
	public void updatePage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("oid");
		if (id != null &&!id.equals("") ) {
			int oid= Integer.parseInt(id);
			Sorder sorder = sorderDao.findsorderById(oid);
			request.setAttribute("sorder", sorder);
			request.setAttribute("updatetype", request.getParameter("updatetype"));
			if(request.getParameter("updatetype").equals("update"))
			request.getRequestDispatcher("/pages/sorder/updatesorder.jsp")
					.forward(request, response);
			if(request.getParameter("updatetype").equals("updatemy"))
				request.getRequestDispatcher("/pages/sorder/updatesordermy.jsp")
						.forward(request, response);
			if(request.getParameter("updatetype").equals("sign"))
				request.getRequestDispatcher("/pages/sorder/updatesordersign.jsp")
						.forward(request, response);
			if(request.getParameter("updatetype").equals("pay"))
			{
				Users susers=usersDao.findUsersById(sorder.getUid());
				request.setAttribute("susers", susers);
				Site site = siteDao.findSiteById(sorder.getSid());
				request.setAttribute("site", site);
				request.getRequestDispatcher("/pages/sorder/updatesorderpay.jsp")
						.forward(request, response);
			}

		} else {
			request.setAttribute("message", "出错啦");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}
	public void signPage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("oid");
		if (id != null &&!id.equals("") ) {
			int oid= Integer.parseInt(id);
			Sorder sorder = sorderDao.findsorderById(oid);
			request.setAttribute("sorder", sorder);
			request.getRequestDispatcher("/pages/sorder/signsorder.jsp")
					.forward(request, response);

		} else {
			request.setAttribute("message", "出错啦");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}
	

	public void deleteSorder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("oid");
		if (id != null &&!id.equals("") ) {
			int oid = Integer.parseInt(id);
			sorderDao.deleteSorderById(oid);
			request.getRequestDispatcher(
					"SorderServlet?method=findAllSorder&uid="+request.getParameter("uid")+"&uname="+Tools.encode(request.getParameter("uname"))+"&sname="+Tools.encode(request.getParameter("sname"))).forward(
					request, response);
		} else {
			request.setAttribute("message", "删除失败，请确认");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}

	public void saveSorder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			NoSuchAlgorithmException, ParseException {
		Date odate = Tools.StrToDate(Tools.FormatDate(request.getParameter("odate"), "yyyy-MM-dd"), "yyyy-MM-dd");
		Float spend = 0f;
		if(request.getParameter("spend")!=null)
			 spend = Float.parseFloat(request.getParameter("spend"));
		
		String otime = Tools.encode(request.getParameter("otime"));
		String spendtype = Tools.encode(request.getParameter("spendtype"));
		String ostart = Tools.encode(request.getParameter("ostart"));
		String otel = Tools.encode(request.getParameter("otel"));
		int sid = Integer.parseInt(request.getParameter("sid"));
		int uid = Integer.parseInt(request.getParameter("uid"));
		Sorder sorder = new Sorder();
		sorder.setOdate(odate);
		sorder.setOstart(ostart);
		sorder.setOtel(otel);
		sorder.setOtime(otime);
		sorder.setSid(sid);
		sorder.setSpend(spend);
		sorder.setSpendtype(spendtype);
		sorder.setUid(uid);
		
		int i = sorderDao.insertsorder(sorder);
		if (i > 0) {
			if(request.getParameter("addtype")!=null&&request.getParameter("addtype").equals("myadd"))
				request.setAttribute("url", "servlet/SorderServlet?method=findAllSorder&uid="+sorder.getUid());
			else if(request.getParameter("addtype")!=null&&request.getParameter("addtype").equals("sign"))
				request.setAttribute("url", "servlet/SorderServlet?method=findAllSorderSign");
			else
				request.setAttribute("url", "servlet/SorderServlet?method=findAllSorder");
			request.setAttribute("message", "添加成功");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
			
		}
	}

	public void updateSorder(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, NoSuchAlgorithmException, ParseException {
		Date odate = Tools.StrToDate(Tools.FormatDate(request.getParameter("odate"), "yyyy-MM-dd"), "yyyy-MM-dd");
		Float spend = 0f;
		if(request.getParameter("spend")!=null)
			 spend = Float.parseFloat(request.getParameter("spend"));
		
		String otime = Tools.encode(request.getParameter("otime"));
		String spendtype = Tools.encode(request.getParameter("spendtype"));
		String ostart = Tools.encode(request.getParameter("ostart"));
		String otel = Tools.encode(request.getParameter("otel"));
		int sid = Integer.parseInt(request.getParameter("sid"));
		int uid = Integer.parseInt(request.getParameter("uid"));
		int oid = Integer.parseInt(request.getParameter("oid"));
		Sorder sorder = new Sorder();
		sorder.setOdate(odate);
		sorder.setOstart(ostart);
		sorder.setOtel(otel);
		sorder.setOtime(otime);
		sorder.setSid(sid);
		sorder.setSpend(spend);
		sorder.setSpendtype(spendtype);
		sorder.setUid(uid);
		sorder.setOid(oid);
		int i = sorderDao.updatesorder(sorder);
		if(spendtype.equals("账户余额"))
		{
			SpendDao spendDao = new SpendDao();
			Spend spendin = new Spend();
			spendin.setOid(sorder.getOid());
			spendin.setUid(sorder.getUid());
			spendin.setSmoney(spend);
			i=spendDao.insertSpend(spendin);
		}
		if (i > 0) {
			if(request.getParameter("updatetype")!=null&&request.getParameter("updatetype").equals("sign"))
			{
				request.setAttribute("url", "servlet/SorderServlet?method=findAllSorderSign");
			}
			else if(request.getParameter("updatetype")!=null&&request.getParameter("updatetype").equals("pay"))
				request.setAttribute("url", "servlet/SorderServlet?method=findAllSorderSign");
			else if(request.getParameter("updatetype")!=null&&request.getParameter("updatetype").equals("update"))
				request.setAttribute("url", "servlet/SorderServlet?method=findAllSorder");
			else
				request.setAttribute("url", "servlet/SorderServlet?method=findAllSorder&uid="+sorder.getUid());
			request.setAttribute("message", "更新成功");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}
	public void statistic(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		String startdate =request.getParameter("startdate");
		String enddate =(request.getParameter("enddate"));
		List<Site> list = sorderDao.findsum(startdate, enddate, "");
		request.setAttribute("list", list);
		RechargeDao rechargeDao = new RechargeDao();
		Float total = rechargeDao.findsum(startdate, enddate, "");
		request.setAttribute("rechargetotal", total);
		request.setAttribute("startdate", startdate);
		request.setAttribute("enddate", enddate);
		request.getRequestDispatcher("/pages/sorder/statisticlist.jsp").forward(
				request, response);
	}
	
}
	
