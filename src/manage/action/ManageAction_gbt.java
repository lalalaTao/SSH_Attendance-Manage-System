package manage.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import manage.dao.BanJiDao_gbt;
import manage.dao.KaoQinLogDao_gbt;
import manage.dao.UserDao_gbt;
import manage.model.BanJi_gbt;
import manage.model.KaoQinLog_gbt;
import manage.model.User_gbt;
import manage.util.CONST;

import org.apache.struts2.ServletActionContext;




import com.opensymphony.xwork2.ActionSupport;

public class ManageAction_gbt extends ActionSupport {

	public static String time2  = "";
	/**
	 * 
	 */
	private static final long serialVersionUID = 7963004028001698964L;

	private UserDao_gbt userDao_gbt;
	private BanJiDao_gbt banjiDao;
	private KaoQinLogDao_gbt kaoqinlogDao;

	
	public KaoQinLogDao_gbt getKaoqinlogDao() {
		return kaoqinlogDao;
	}

	public void setKaoqinlogDao(KaoQinLogDao_gbt kaoqinlogDao) {
		this.kaoqinlogDao = kaoqinlogDao;
	}

	public BanJiDao_gbt getBanjiDao() {
		return banjiDao;
	}

	public void setBanjiDao(BanJiDao_gbt banjiDao) {
		this.banjiDao = banjiDao;
	}
	
	//取小树点后2位
	static  double  convert(double  value){  
        long  lg  =  Math.round(value*100); //四舍五入  
        double  d=  lg/100.0; //注意：使用 100.0而不是  100  
        return  d;  
    }  

	private int numPerPage = 20;
	private int pageNum = 1;
	
	
	public int getNumPerPage() {
		return numPerPage;
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public UserDao_gbt getUserDao() {
		return userDao_gbt;
	}

	public void setUserDao(UserDao_gbt userDao_gbt) {
		this.userDao_gbt = userDao_gbt;
	}


	public String login() throws Exception {
		HttpServletRequest request = ServletActionContext.getRequest();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User_gbt user_gbt = userDao_gbt.selectUserbByusernameByPassword(username, password);
		
		if (user_gbt!=null) {
			List<KaoQinLog_gbt> kaoqinlogs = kaoqinlogDao.selectAllKaoQinLogBy(0, 100," and codenum='"+user_gbt.getCodenum()+"'");
			String kaoqinlogtishi="";
			if(kaoqinlogs.size()>=3){
				kaoqinlogtishi="旷课次数已经超过3次!!!";
			}else{
				kaoqinlogtishi="";
			}
			
			HttpSession session = request.getSession();
			session.setAttribute("username", user_gbt.getUsername());
			session.setAttribute("codenum", user_gbt.getCodenum());
			session.setAttribute("banjinum", user_gbt.getBanjinum());
			session.setAttribute("role", "role"+user_gbt.getRole());
			session.setAttribute("loginuser", user_gbt);
			session.setAttribute("kaoqinlogtishi", kaoqinlogtishi);
			
//			DatagramSocket datagramSocket=null;
//	        try {
//	            //监视8081端口的内容
//	            datagramSocket=new DatagramSocket(8081);
//	            byte[] buf=new byte[1024];
//
//	            //定义接收数据的数据包
//	            DatagramPacket datagramPacket=new DatagramPacket(buf, 0, buf.length);
//	            datagramSocket.receive(datagramPacket);
//
//	            //从接收数据包取出数据
//	            String data=new String(datagramPacket.getData() , 0 ,datagramPacket.getLength());
//	            time2 = data;
//	        } catch (SocketException e) {
//	            e.printStackTrace();
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        } finally {
//	            datagramSocket.close();
//	        }
//			
			return "success";
		} else {
			request.setAttribute("errorMessage", "请重新输入帐号密码！！");
			return "fail";
		}
	}

	public String loginout() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute("username");
		session.removeAttribute("role");
		return "success";
	}
	

	
	public String userlist() {
		HttpServletRequest request = ServletActionContext.getRequest();
		int numPerPage = 10;
		int pageNum = 1;
		if(request.getParameter("pageNum")!=null && !request.getParameter("pageNum").equals("")){
			pageNum = Integer.parseInt(request.getParameter("pageNum"));
		}
		if(request.getParameter("numPerPage")!=null){
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		}
		int total = userDao_gbt.selectAllUserCount();
		List<User_gbt> user_gbts = userDao_gbt.selectAllUser(
				(pageNum - 1) * numPerPage, numPerPage);
		request.setAttribute("totalCount", total);
		request.setAttribute("ps", numPerPage);
		request.setAttribute("pn", pageNum);
		request.setAttribute("userlist", user_gbts);

		
		request.setAttribute("searchuserlist", userDao_gbt.getAll(" "));
		return "success";
	}
	
	public String useradd(){
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setAttribute("banjilist", banjiDao.getAll(""));
		return "success";
	}
	
	public String useradd2() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		
				
		User_gbt user_gbt = userDao_gbt.selectUserByusername(request.getParameter("username"));
		
		if(user_gbt!=null){
			HttpServletResponse resp = ServletActionContext.getResponse();
			resp.setCharacterEncoding("utf-8");
			PrintWriter out = resp.getWriter();
			out.print("{\"statusCode\":\"200\", \"message\":\"该用户名已经存在，添加失败！\",\"navTabId\":\"userList\", \"rel\":\"userList\", \"callbackType\":\"closeCurrent\",\"forwardUrl\":\"userlist.html\"}");
			out.flush();
			out.close();
			return null;
		}
		
		user_gbt = new User_gbt();
		
		String username = request.getParameter("username");
		
		user_gbt.setUsername(username);
		
		user_gbt.setPassword(request.getParameter("password"));
		
		user_gbt.setAddress(request.getParameter("address"));

		user_gbt.setBanjinum(request.getParameter("banjinum"));
		
		user_gbt.setCodenum(request.getParameter("username"));
		
		user_gbt.setPhone(request.getParameter("phone"));
		
		user_gbt.setRoom(request.getParameter("room"));
		
		user_gbt.setUserlock(Integer.parseInt(request.getParameter("userlock")));
		
		user_gbt.setRole(Integer.parseInt(request.getParameter("role")));
		
		user_gbt.setCreatetime(new Date());
		
		User_gbt u = userDao_gbt.selectUserbByusernameByPassword(user_gbt.getUsername(), user_gbt.getPassword());
		
		if(u==null){
			userDao_gbt.insertUser(user_gbt);
			}else{
			request.setAttribute("info", "新增失败，用户帐号已存在，请检查输入");
		}
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		out.print("{\"statusCode\":\"200\", \"message\":\"添加成功！\",\"navTabId\":\"userList\", \"rel\":\"userList\", \"callbackType\":\"closeCurrent\",\"forwardUrl\":\"userlist.html\"}");
		out.flush();
		out.close();
		return null;
	}
	
	public String userdel() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		int id = Integer.parseInt(request.getParameter("id"));
		userDao_gbt.delUser(userDao_gbt.selectUser(id));
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();	
		out.write(manage.util.Util.outPutMsg("200", "修改成功", "userList", "", false, "userlist.html"));
		out.flush();
		out.close();
		return null;
	}

	
	public String  userupdate(){
		HttpServletRequest request = ServletActionContext.getRequest();
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("bean", userDao_gbt.selectUser(id));
		request.setAttribute("id", id);
		request.setAttribute("banjilist", banjiDao.getAll(""));
		return "success";
	}
	
	public String userupdate2() throws Exception{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		int id = Integer.parseInt(request.getParameter("id"));
		User_gbt bean = userDao_gbt.selectUser(id);
		
		if(request.getParameter("username")!=null){
			bean.setUsername(request.getParameter("username"));
		}
		if(request.getParameter("password")!=null){
			bean.setPassword(request.getParameter("password"));
		}
		if(request.getParameter("address")!=null){
			bean.setAddress(request.getParameter("address"));
		}
		if(bean.getRole()==2){
			if(request.getParameterValues("banjinum")!=null){
				String[] banjinums=request.getParameterValues("banjinum");
				String banjinum="";
				for(String str : banjinums){
					banjinum=banjinum+str;
					bean.setBanjinum(banjinum);
				}
			}
			
		}else{
			if(request.getParameter("banjinum")!=null){
				bean.setBanjinum(request.getParameter("banjinum"));
			}
		}
		
		
		if(request.getParameter("phone")!=null){
			bean.setPhone(request.getParameter("phone"));
		}
		if(request.getParameter("room")!=null){
			bean.setRoom(request.getParameter("room"));		
		}
			
		if(request.getParameter("userlock")!=null){
			bean.setUserlock(Integer.parseInt(request.getParameter("userlock")));
		}
		if(request.getParameter("role")!=null){
			bean.setRole(Integer.parseInt(request.getParameter("role")));
		}
		userDao_gbt.updateUser(bean);	
		out.print("{\"statusCode\":\"200\", \"message\":\"修改成功！\",\"navTabId\":\"userList\", \"rel\":\"userList\", \"callbackType\":\"closeCurrent\",\"forwardUrl\":\"userlist.html\"}");
		out.flush();
		out.close();
		return null;
	}
	
	/*查找用户*/
	public String searchuser(){
		HttpServletRequest request = ServletActionContext.getRequest();
		String keyword  = request.getParameter("keyword");
		
		int currentpage = 1;
		int pagesize = 50;
		if (request.getParameter("pagenum") != null)
			currentpage = Integer.parseInt(request.getParameter("pagenum"));
		request
				.setAttribute("userlist", userDao_gbt.selectAllUserByusername((currentpage - 1) * pagesize, 50,
						keyword));
		request.setAttribute("searchuserlist", userDao_gbt.getAll(" and userlock=0 "));
		return "success";
		
	}
	
	public String  passwordupdateok(){
		HttpServletRequest request = ServletActionContext.getRequest();
		return "success";
	}
	
	public String  passwordupdate() throws IOException{
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("codenum");

		String password1 = request.getParameter("oldpwd");
		String password2 = request.getParameter("newspwd");
		User_gbt u = userDao_gbt.selectUserbByusernameByPassword(username, password1);
		if(u!=null){
			u.setPassword(password2);
			userDao_gbt.updateUser(u);
			request.setAttribute("info", "密码修改成功");
		}else{
			request.setAttribute("info", "修改密码失败，请先确认原密码是否正确");
		}
		HttpServletResponse resp = ServletActionContext.getResponse();
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		if(u!=null){
			out.print("{\"statusCode\":\"200\", \"message\":\"密码修改成功！\",\"navTabId\":\"userList\", \"rel\":\"userList\", \"callbackType\":\"closeCurrent\",\"forwardUrl\":\"userlist.html\"}");
		}else{
			out.print("{\"statusCode\":\"200\", \"message\":\"修改密码失败，请先确认原密码是否正确！\",\"navTabId\":\"userList\", \"rel\":\"userList\", \"callbackType\":\"closeCurrent\",\"forwardUrl\":\"userlist.html\"}");
		}
		out.flush();
		out.close();
		return null;
	}
	
	public String  usershow(){
		HttpServletRequest request = ServletActionContext.getRequest();
		int id = Integer.parseInt(request.getParameter("id"));
		request.setAttribute("bean", userDao_gbt.selectUser(id));
		request.setAttribute("id", id);
		request.setAttribute("banjilist", banjiDao.getAll(""));
		return "success";
	}
}
