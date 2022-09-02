package manage.action;

import java.io.*;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

import manage.dao.BanJiDao_gbt;
import manage.dao.KechengDao_gbt;
import manage.model.Kecheng_gbt;
import manage.util.Pager;

import com.opensymphony.xwork2.ActionSupport;

public class KechengAction_gbt extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7963004028001698964L;

	private KechengDao_gbt kechengDao_gbt;
	private BanJiDao_gbt banjiDao;

	public BanJiDao_gbt getBanjiDao() {
		return banjiDao;
	}

	public void setBanjiDao(BanJiDao_gbt banjiDao) {
		this.banjiDao = banjiDao;
	}
	
	public KechengDao_gbt getKechengDao() {
		return kechengDao_gbt;
	}

	public void setKechengDao(KechengDao_gbt kechengDao_gbt) {
		this.kechengDao_gbt = kechengDao_gbt;
	}


	public String kechenglist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		int numPerPage = 20;
		 int pageNum = 1;
		if(request.getParameter("pageNum")!=null && !request.getParameter("pageNum").equals("")){
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		if(request.getParameter("numPerPage")!=null){
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		}
		int total = kechengDao_gbt.selectAllKechengCount();
		List<Kecheng_gbt> kecheng_gbts = kechengDao_gbt.selectAllKecheng(
				(pageNum - 1) * numPerPage, numPerPage);
		request.setAttribute("totalCount", total);
		request.setAttribute("ps", numPerPage);
		request.setAttribute("pn", pageNum);
		request.setAttribute("kechenglist", kecheng_gbts);
		request.setAttribute("banjilist", banjiDao.getAll(""));
		return "success";
	}
	
	public String kechenglist2() {
		HttpServletRequest request = ServletActionContext.getRequest();
		int numPerPage = 20;
		 int pageNum = 1;
		if(request.getParameter("pageNum")!=null && !request.getParameter("pageNum").equals("")){
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		if(request.getParameter("numPerPage")!=null){
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		}
		int total = kechengDao_gbt.selectAllKechengCount();
		HttpSession session = request.getSession();
		String role = (String)session.getAttribute("role");
		if("role4".equals(role)||role=="role4" || "role3".equals(role)){
			String banjinum=(String) session.getAttribute("banjinum"); 
			request.setAttribute("kechenglist", kechengDao_gbt.selectAllKechengBy((pageNum - 1) * numPerPage, 50,
					"and banjinum='"+banjinum+"'"));
		}else{
			request.setAttribute("kechenglist", kechengDao_gbt.selectAllKecheng(
					(pageNum - 1) * numPerPage, numPerPage));
			request.setAttribute("totalCount", total);
			request.setAttribute("ps", numPerPage);
			request.setAttribute("pn", pageNum);
		}
		
		
		//request.setAttribute("kechenglist", kechengs);
		return "success";
	}
	
	public String kechengadd(){
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("banjilist", banjiDao.getAll(""));
		return "success";
	}
	
	public String kechengadd2() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		
		Kecheng_gbt kecheng_gbt = new Kecheng_gbt();
		
		kecheng_gbt.setKechengname1(request.getParameter("kechengname1"));
		kecheng_gbt.setKechengname2(request.getParameter("kechengname2"));
		kecheng_gbt.setKechengname3(request.getParameter("kechengname3"));
		kecheng_gbt.setKechengname4(request.getParameter("kechengname4"));
		kecheng_gbt.setKechengname5(request.getParameter("kechengname5"));
		kecheng_gbt.setKechengname6(request.getParameter("kechengname6"));
		kecheng_gbt.setKechengname7(request.getParameter("kechengname7"));
		
		kecheng_gbt.setKechenglock(Integer.parseInt(request.getParameter("kechenglock")));
		kecheng_gbt.setBanji(request.getParameter("banji"));
		kecheng_gbt.setBanjinum(request.getParameter("banjinum"));
		kecheng_gbt.setCreatetime(new Date());
		
		
			kechengDao_gbt.insertKecheng(kecheng_gbt);
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		//out.write(manage.util.Util.outPutMsg("200", "添加成功", "", "", "kechenglist.html"));
		
		out.print("{\"statusCode\":\"200\", \"message\":\"添加成功！\",\"navTabId\":\"kechengList\", \"rel\":\"kechengList\", \"callbackType\":\"closeCurrent\",\"forwardUrl\":\"kecheng///kechenglist.html\"}");

		out.flush();
		out.close();
		return null;
	}
	
	public String kechengdel() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		int id = Integer.parseInt(request.getParameter("id"));
		kechengDao_gbt.delKecheng(kechengDao_gbt.selectKecheng(id));
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
	//	out.print("{\"statusCode\":\"200\", \"message\":\"删除成功！\",\"navTabId\":\"kechengList\", \"rel\":\"kechengList\", \"callbackType\":\"closeCurrent\",\"forwardUrl\":\"kecheng///kechenglist.html\"}");
		out.write(manage.util.Util.outPutMsg("200", "修改成功", "kechengList", "", false, "kecheng///kechenglist.html"));
		out.flush();
		out.close();
		return null;
	}

	
	public String  kechengupdate(){
		HttpServletRequest request = ServletActionContext.getRequest();
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("bean", kechengDao_gbt.selectKecheng(id));
		request.setAttribute("id", id);
		//request.setAttribute("subjectilist", subjectDao.getAll(" and subjectlock=0 "));
		return "success";
	}
	
	public String kechengupdate2() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		Kecheng_gbt bean = kechengDao_gbt.selectKecheng(id);
		
		if(request.getParameter("kechengname1")!=null){
			bean.setKechengname1(request.getParameter("kechengname1"));
		}
		if(request.getParameter("kechengname2")!=null){
			bean.setKechengname2(request.getParameter("kechengname2"));
		}
		if(request.getParameter("kechengname3")!=null){
			bean.setKechengname3(request.getParameter("kechengname3"));
		}
		if(request.getParameter("kechengname4")!=null){
			bean.setKechengname4(request.getParameter("kechengname4"));
		}
		if(request.getParameter("kechengname5")!=null){
			bean.setKechengname5(request.getParameter("kechengname5"));
		}
		if(request.getParameter("kechengname6")!=null){
			bean.setKechengname6(request.getParameter("kechengname6"));
		}
		if(request.getParameter("kechengname7")!=null){
			bean.setKechengname7(request.getParameter("kechengname7"));
		}
		
		if(request.getParameter("kechenglock")!=null){
			bean.setKechenglock(Integer.parseInt(request.getParameter("kechenglock")));
		}
		if(request.getParameter("banji")!=null){
			bean.setBanji(request.getParameter("banji"));
		}
		if(request.getParameter("banjiid")!=null){
			bean.setBanjinum(request.getParameter("banjinum"));
		}
		kechengDao_gbt.updateKecheng(bean);
		//out.write(manage.util.Util.outPutMsg("200", "修改成功", "", "", "kechenglist.html"));
		out.print("{\"statusCode\":\"200\", \"message\":\"修改成功！\",\"navTabId\":\"kechengList\", \"rel\":\"kechengList\", \"callbackType\":\"closeCurrent\",\"forwardUrl\":\"kecheng///kechenglist.html\"}");

		out.flush();
		out.close();
		return null;
	}
	
	
	public String searchkecheng(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String keyword  = request.getParameter("keyword");
		
		int currentpage = 1;
		int pagesize = 50;
		if (request.getParameter("pagenum") != null)
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		request.setAttribute("kechenglist", kechengDao_gbt.selectAllKechengBy((currentpage - 1) * pagesize, 50,
						"and banjinum like '%"+keyword+"%'"));
		request.setAttribute("searchkechenglist", kechengDao_gbt.getAll(" and kechenglock=0 "));
		request.setAttribute("banjilist", banjiDao.getAll(""));
		return "success";
		
	}
	
	
	
	
}
