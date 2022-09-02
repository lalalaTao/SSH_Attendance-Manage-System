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
import manage.dao.SubjectDao_gbt;
import manage.model.BanJi_gbt;
import manage.model.Kecheng_gbt;
import manage.model.Subject_gbt;
import manage.model.User_gbt;
import manage.util.Util;

public class SubjectAction_gbt  {
	
	private static final long serialVersionUID = 7963004028001698964L;

	private SubjectDao_gbt subjectDao_gbt;

	public SubjectDao_gbt getSubjectDao() {
		return subjectDao_gbt;
	}


	public void setSubjectDao(SubjectDao_gbt subjectDao_gbt) {
		this.subjectDao_gbt = subjectDao_gbt;
	}


	@SuppressWarnings("unchecked")
	public String subjectlist() throws Exception {	
		HttpServletRequest request = ServletActionContext.getRequest();
		int numPerPage = 20;
		 int pageNum = 1;
		if(request.getParameter("pageNum")!=null && !request.getParameter("pageNum").equals("")){
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		if(request.getParameter("numPerPage")!=null){
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		}
		int total = subjectDao_gbt.selectAllSubjectCount();
		List<Subject_gbt> subject_gbts = subjectDao_gbt.selectAllSubject(
				(pageNum - 1) * numPerPage, numPerPage);
		request.setAttribute("totalCount", total);
		request.setAttribute("ps", numPerPage);
		request.setAttribute("pn", pageNum);
		request.setAttribute("subjectlist", subject_gbts);
		return "success";
	}
	
	
	

	public String subjectadd(){
		HttpServletRequest request = ServletActionContext.getRequest();
		return "success";
	}
	
	
	
	public String subjectadd2() throws Exception{
        HttpServletRequest request = ServletActionContext.getRequest();
		
		Subject_gbt subject_gbt = new Subject_gbt();
		
		subject_gbt.setSubjectname(request.getParameter("subjectname"));
		
		subject_gbt.setCreatetime(new Date());
		
		subjectDao_gbt.insertSubject(subject_gbt);
		
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		//out.write(manage.util.Util.outPutMsg("200", "添加成功", "", "", "subjectlist.html"));
		
		out.print("{\"statusCode\":\"200\", \"message\":\"添加成功！\",\"navTabId\":\"subjectList\", \"rel\":\"subjectList\", \"callbackType\":\"closeCurrent\",\"forwardUrl\":\"subject/subjectlist.html\"}");
		out.flush();
		out.close();
	
		return null;
	}
	
	
	public String  subjectupdate(){
		HttpServletRequest request = ServletActionContext.getRequest();
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("bean", subjectDao_gbt.selectSubject(id));
		request.setAttribute("id", id);
		//request.setAttribute("subjectilist", subjectDao_gbt.getAll(" and subjectlock=0 "));
		return "success";
	}
	
	

	public String  subjectupdate2() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		Subject_gbt bean = subjectDao_gbt.selectSubject(id);
		
		
		if(request.getParameter("subjectname")!=null){
			bean.setSubjectname(request.getParameter("subjectname"));
		}
		
		subjectDao_gbt.updateSubject(bean);
		//out.write(manage.util.Util.outPutMsg("200", "修改成功", "", "", "subjectlist.html"));
		out.print("{\"statusCode\":\"200\", \"message\":\"修改成功！\",\"navTabId\":\"subjectList\", \"rel\":\"subjectList\", \"callbackType\":\"closeCurrent\",\"forwardUrl\":\"subject///subjectlist.html\"}");

		out.flush();
		out.close();
		return null;
	}
	
	

	public String subjectdel() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		int id = Integer.parseInt(request.getParameter("id"));
		subjectDao_gbt.delSubject(subjectDao_gbt.selectSubject(id));
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		//out.print("{\"statusCode\":\"200\", \"message\":\"删除成功！\",\"navTabId\":\"subjectList\", \"rel\":\"subjectList\", \"callbackType\":\"closeCurrent\",\"forwardUrl\":\"subject///subjectlist.html\"}");
		out.write(manage.util.Util.outPutMsg("200", "修改成功", "subjectList", "", false, "subject///subjectlist.html"));
		out.flush();
		out.close();
		return null;
	}
	
	
}
