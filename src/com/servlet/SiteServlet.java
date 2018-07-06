package com.servlet;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
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
import com.bean.Users;
import com.dao.SiteDao;
import com.dao.UsersDao;
import com.dao.RechargeDao;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.HeaderTokenizer.Token;
import com.tool.Tools;

public class SiteServlet extends HttpServlet {

	SiteDao siteDao = new SiteDao();
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String methodName = request.getParameter("method");
		if(methodName != null&& methodName != ""){
			if (methodName.equals("uploadPicture")) {
				try {
					uploadPicture(request, response);
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (methodName.equals("findAllSite")) {
						findAllSite(request, response);
			} else if (methodName.equals("updatePage")) {
				updatePage(request, response);
			} else if (methodName.equals("deleteSite")) {
				deleteSite(request, response);
			} else if (methodName.equals("saveSite")) {
				try {
					saveSite(request, response);
				} catch (NoSuchAlgorithmException e) {
					
					e.printStackTrace();
				}
			} else if (methodName.equals("addSite")) {
				request.getRequestDispatcher("/pages/site/addsite.jsp")
						.forward(request, response);

			} else if (methodName.equals("updateSite")) {
				try {
					updateSite(request, response);
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
		pagingModel.setPageLink(url+"/servlet/SiteServlet?method=findAllSite&sremarks="+Tools.encode(request.getParameter("sremarks")));
		request.setAttribute("list", list);
		request.setAttribute("site", site);
		request.setAttribute("pagingModel", pagingModel);
		request.setAttribute("currentp", pageSize);
		request.setAttribute("pagenum", showpage);
		request.getRequestDispatcher("/pages/site/sitelist.jsp").forward(
				request, response);
	}
	
	public void uploadPicture(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			NoSuchAlgorithmException {

		String path = this.getServletContext().getRealPath("/picture");

		try {
			DiskFileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload up = new ServletFileUpload(factory);
			List<FileItem> ls = up.parseRequest(request);
			String filepath = "/picture/";
			for (FileItem fileItem : ls) {
				if (fileItem.isFormField()) {
					String FieldName = fileItem.getFieldName();
					// getName()返回的是文件名字 普通域没有文件 返回NULL
					// String Name = fileItem.getName();
					String Content = fileItem.getString("gbk");
					request.setAttribute(FieldName, Content);
				} else {
					
					String nm = fileItem.getName().substring(
							fileItem.getName().lastIndexOf("\\") + 1);
					int pos = nm.lastIndexOf(".");
					String extName = nm.substring(pos);
					String fileName = new Date().getTime() + extName;	
					filepath+=fileName;
					File mkr = new File(path, fileName);
					if (mkr.createNewFile()) {
						fileItem.write(mkr);// 非常方便的方法
					}
					
				}
				request.setAttribute("filepath", filepath);
				request.setAttribute("msg", "success");
				
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "type is diff!");
		}

		request.getRequestDispatcher("/pages/upload.jsp").forward(request,
				response);
	}
	public void updatePage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("sid");
		if (id != null &&!id.equals("") ) {
			int sid= Integer.parseInt(id);
			Site site = siteDao.findSiteById(sid);
			request.setAttribute("site", site);
			request.getRequestDispatcher("/pages/site/updatesite.jsp")
					.forward(request, response);

		} else {
			request.setAttribute("message", "出错啦");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}
	
	

	public void deleteSite(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("sid");
		if (id != null &&!id.equals("") ) {
			int sid = Integer.parseInt(id);
			siteDao.deleteSiteById(sid);
			request.getRequestDispatcher(
					"SiteServlet?method=findAllSite").forward(
					request, response);
		} else {
			request.setAttribute("message", "删除失败，请确认");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}

	public void saveSite(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException,
			NoSuchAlgorithmException {
		
		Float sprice = Float.parseFloat(request.getParameter("sprice"));
		String sremarks = Tools.encode(request.getParameter("sremarks"));
		String simg = Tools.encode(request.getParameter("simg"));
		String sinfo = Tools.encode(request.getParameter("sinfo"));
		Site site = new Site();
		site.setSimg(simg);
		site.setSremarks(sremarks);
		site.setSprice(sprice);
		site.setSinfo(sinfo);
		int i = siteDao.insertSite(site);
		if (i > 0) {
			
			request.setAttribute("url", "servlet/SiteServlet?method=findAllSite");
			request.setAttribute("message", "添加成功");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
			
		}
	}

	public void updateSite(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException, NoSuchAlgorithmException {
		Float sprice = Float.parseFloat(request.getParameter("sprice"));
		String sremarks = Tools.encode(request.getParameter("sremarks"));
		String simg = Tools.encode(request.getParameter("simg"));
		String sinfo = Tools.encode(request.getParameter("sinfo"));
		Site site = new Site();
		site.setSid(Integer.valueOf(request.getParameter("sid")));
		site.setSimg(simg);
		site.setSremarks(sremarks);
		site.setSprice(sprice);
		site.setSinfo(sinfo);
		int i = siteDao.updateSite(site);
		if (i > 0) {
			request.setAttribute("url", "servlet/SiteServlet?method=findAllSite");
//			System.out.print(request.getParameter("url"));
			request.setAttribute("message", "更新成功");
			request.getRequestDispatcher("/pages/message.jsp").forward(request,
					response);
		}
	}
}
	
