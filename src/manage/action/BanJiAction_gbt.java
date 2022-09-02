package manage.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import manage.dao.BanJiDao_gbt;
import manage.dao.KechengDao_gbt;
import manage.model.BanJi_gbt;
import manage.model.Kecheng_gbt;
import manage.model.User_gbt;
import manage.util.Util;

public class BanJiAction_gbt  {
	
	private static final long serialVersionUID = 7963004028001698964L;

	private BanJiDao_gbt banjiDao;

	public BanJiDao_gbt getBanjiDao() {
		return banjiDao;
	}


	public void setBanjiDao(BanJiDao_gbt banjiDao) {
		this.banjiDao = banjiDao;
	}


	@SuppressWarnings("unchecked")
	public String banjilist() throws Exception {	
		HttpServletRequest request = ServletActionContext.getRequest();
		int numPerPage = 10;
		 int pageNum = 1;
		if(request.getParameter("pageNum")!=null && !request.getParameter("pageNum").equals("")){
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		if(request.getParameter("numPerPage")!=null){
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		}
		int total = banjiDao.selectAllBanJiCount();
		List<BanJi_gbt> banjis = banjiDao.selectAllBanJi(
				(pageNum - 1) * numPerPage, numPerPage);
		request.setAttribute("totalCount", total);
		request.setAttribute("ps", numPerPage);
		request.setAttribute("pn", pageNum);
		request.setAttribute("banjilist", banjis);
		return "success";
	}
	
	
	public String banjiadd(){
		HttpServletRequest request = ServletActionContext.getRequest();
		return "success";
	}
	
	

	public String banjiadd2() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
		
		BanJi_gbt banji = new BanJi_gbt();
		
		banji.setBanjiname(request.getParameter("banjiname"));
		banji.setBanjinum(request.getParameter("banjinum"));
		banji.setYuanxi(request.getParameter("yuanxi"));
		banji.setZhuanye(request.getParameter("zhuanye"));
		
		
		banji.setCreatetime(new Date());
		
		List<BanJi_gbt> u = banjiDao.selectAllBanJiBy(0, 10, "and banjinum='"+request.getParameter("banjinum")+"'");
		
		if(u.size()==0){
			banjiDao.insertBanJi(banji);
		
			
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		//out.write(manage.util.Util.outPutMsg("200", "添加成功", "", "", "banjilist.html"));
		
		out.print("{\"statusCode\":\"200\", \"message\":\"添加成功！\",\"navTabId\":\"banjiList\", \"rel\":\"banjiList\", \"callbackType\":\"closeCurrent\",\"forwardUrl\":\"banji/banjilist.html\"}");
		out.flush();
		out.close();
		}else{
			request.setAttribute("info", "新增班级失败，班级编号已存在，请检查输入");
			HttpServletResponse resp = ServletActionContext.getResponse();
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			//out.write(manage.util.Util.outPutMsg("200", "添加成功", "", "", "banjilist.html"));
			
			out.print("{\"statusCode\":\"200\", \"message\":\"添加失败，班级编号已存在！\",\"navTabId\":\"banjiList\", \"rel\":\"banjiList\", \"callbackType\":\"closeCurrent\",\"forwardUrl\":\"banji/banjilist.html\"}");
			out.flush();
			out.close();
		}
		return null;
	}
	

	public String  banjiupdate(){
		HttpServletRequest request = ServletActionContext.getRequest();
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("bean", banjiDao.selectBanJi(id));
		request.setAttribute("id", id);
		//request.setAttribute("subjectilist", subjectDao.getAll(" and subjectlock=0 "));
		return "success";
	}
	
	

	public String  banjiupdate2() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		BanJi_gbt bean = banjiDao.selectBanJi(id);
		
		
		if(request.getParameter("banjiname")!=null){
			bean.setBanjiname(request.getParameter("banjiname"));
		}
		if(request.getParameter("banjinum")!=null){
			bean.setBanjinum(request.getParameter("banjinum"));
		}
		if(request.getParameter("yuanxi")!=null){
			bean.setYuanxi(request.getParameter("yuanxi"));
		}
		if(request.getParameter("zhuanye")!=null){
			bean.setZhuanye(request.getParameter("zhuanye"));
		}
		
		banjiDao.updateBanJi(bean);
		//out.write(manage.util.Util.outPutMsg("200", "修改成功", "", "", "banjilist.html"));
		out.print("{\"statusCode\":\"200\", \"message\":\"修改成功！\",\"navTabId\":\"banjiList\", \"rel\":\"banjiList\", \"callbackType\":\"closeCurrent\",\"forwardUrl\":\"banji///banjilist.html\"}");

		out.flush();
		out.close();
		return null;
	}
	

	public String banjidel() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		int id = Integer.parseInt(request.getParameter("id"));
		banjiDao.delBanJi(banjiDao.selectBanJi(id));
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		//out.print("{\"statusCode\":\"200\", \"message\":\"删除成功！\",\"navTabId\":\"banjiList\", \"rel\":\"banjiList\", \"callbackType\":\"closeCurrent\",\"forwardUrl\":\"banji///banjilist.html\"}");
		out.write(manage.util.Util.outPutMsg("200", "修改成功", "banjiList", "", false, "banji///banjilist.html"));
		out.flush();
		out.close();
		return null;
	}
	
	
}
