package web;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AddDao;
import dao.AdminDao;
import dao.CustomerDao;
import dao.CustomerInfoDao;
import entity.Add;
import entity.Admin;
import entity.Cust;
import entity.Customer;
import util.UtilCheck;

public class CustServlet extends HttpServlet{


	//删除顾客
	protected void deleteRole(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String roleId = req.getParameter("custDeId");
		new CustomerInfoDao().deleteCust(new String(roleId));
		res.sendRedirect("findRoleList.do");
	}

		
	//转到增加顾客页面
	protected void toAddRole(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		Date sysdate = new Date(System.currentTimeMillis());
		req.setAttribute("sysdate", sysdate);
		req.getRequestDispatcher("WEB-INF/role/addRole.jsp").forward(req, res);
	}
	
	//增加顾客
	protected void addRole(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		//分装数据
		String custId = req.getParameter("custId");
		String name=req.getParameter("custName");
		String tel=req.getParameter("telephone");
		String sex=req.getParameter("custSex");
		String ageStr=req.getParameter("custAge");
		Integer age = 0;
		if(!UtilCheck.isEmpty(ageStr)) {
			age = Integer.valueOf(ageStr);
		}
		String addr=req.getParameter("custAddr");
		String content=req.getParameter("custContent");
		String bodyInfo=req.getParameter("bodyInfo");
		
		Date sysdate = new Date(System.currentTimeMillis());
		String regexZ = "[A-Za-z]+";
		CustomerDao custDao = new CustomerDao();
		HttpSession session = req.getSession();
		String adminCode = (String) session.getAttribute("adminCode");		
		Admin admin = findAdmin(adminCode);
		 
		if (custId==null||custId.equals("")) {
			req.setAttribute("custerror", "请输入编号。");
			req.setAttribute("sysdate", sysdate);
			req.setAttribute("message", "保存失败！");
			req.getRequestDispatcher("WEB-INF/role/addRole.jsp").forward(req, res);
		}else
			if (name==null||name.equals("")) {
				req.setAttribute("error", "请输入姓名。");
				req.setAttribute("sysdate", sysdate);
				req.setAttribute("message", "保存失败！");
				req.getRequestDispatcher("WEB-INF/role/addRole.jsp").forward(req, res);
			}else {
				if(!custId.substring(0,1).matches(regexZ)) {
					custId = admin.getAddNo().concat(custId);
				}else if(!custId.substring(0,1).toLowerCase().equals(admin.getAddNo())){
					custId = custId.substring(1, custId.length());
					custId = admin.getAddNo().concat(custId);
				}
				//主键排他
				Customer reCust = custDao.findByPk(custId);
				if(reCust!=null) {
					System.out.println(reCust.toString());
					req.setAttribute("custerror", "编号重复了。");
					req.setAttribute("sysdate", sysdate);
					req.setAttribute("message", "编号重复了！");
					req.getRequestDispatcher("WEB-INF/role/addRole.jsp").forward(req, res);
				}else {
					Customer b=new Customer();
					b.setCustId(custId);
					b.setAge(age);
					b.setName(name);
					b.setTel(tel);
					b.setSex(sex);
					b.setAddr(addr);
					b.setBodyInfo(bodyInfo);
					b.setAppendUserId(adminCode);
					b.setAppendDate(Calendar.getInstance().getTime());
					b.setKbn(custId.substring(0,1).toUpperCase());
					b.setContent(content);
					System.out.println("save:"+b.toString());
					custDao.saveCustomer(b);
					req.setAttribute("message", "保存成功！");
					res.setContentType("text/html");
					res.sendRedirect("toCustDetail.do?custMoId="+custId);
				}
			}
		
	}
	//转到增加消费页面
	protected void toAddConsum(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		Date sysdate = new Date(System.currentTimeMillis());
		req.setAttribute("sysdate", sysdate);
		//获取数据
		String custId = req.getParameter("custMoId");
		System.out.println("custMoId:"+custId);
		Customer role = findCustomer(custId);
		req.setAttribute("custMoId", custId);
		req.setAttribute("role", role);
		
		req.getRequestDispatcher("WEB-INF/role/addCustomer.jsp").forward(req, res);
	}
	
	protected Customer findCustomer(String custId) throws IOException{
		CustomerDao dao = new CustomerDao();
		Customer customer = dao.findByPk(custId);
		return customer;
	}

	//增加消费记录
	protected void addConsum(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		//获取数据
		String custId = req.getParameter("custId");
		String consum=req.getParameter("consum");
		String content=req.getParameter("content");
		String consumKbn=req.getParameter("consumKbn");
		Integer money = 0;
		if(!UtilCheck.isEmpty(consum)) {
			money = Integer.valueOf(consum);
		}
				
		Customer role = findCustomer(custId);
		Date sysdate = new Date(System.currentTimeMillis());
		
		HttpSession session = req.getSession();
		String adminCode = (String) session.getAttribute("adminCode");		
		if (consum==null||consum.equals("")) {
			req.setAttribute("error", "请输入金额。");
			req.setAttribute("sysdate", sysdate);
			req.setAttribute("role", role);
			req.getRequestDispatcher("WEB-INF/role/addCustomer.jsp").forward(req, res);
		}else {
			Cust b=new Cust();
			b.setCustId(custId);
			b.setConsum(money);
			b.setKbn(role.getKbn());
			b.setSex(role.getSex());
			b.setTel(role.getTel());
			b.setAppendUserId(adminCode);
			b.setContent(content);
			b.setConsumKbn(consumKbn);
			System.out.println("save:"+b.toString());
			new CustomerInfoDao().saveCust(b);
			res.setContentType("text/html");
			res.sendRedirect("toCustDetail.do?custMoId="+custId);
		}
		
	}
	
	
	//转到详细页面
	protected void toDetail(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		//获取数据
		String roleId = req.getParameter("custMoId");
		System.out.println("custMoId:"+roleId);
		session.setAttribute("custMoId",roleId);
		//String name = req.getParameter("name");
		CustomerInfoDao dao = new CustomerInfoDao();
		CustomerDao custdao = new CustomerDao();
		Customer role = custdao.findByPk(roleId);
		List<Cust> list = dao.findById(roleId);
		int custTotal = 0;
		for(Cust cu:list) {
			custTotal+=cu.getConsum();
		}
		req.setAttribute("custTotal", custTotal);
		req.setAttribute("custList", list);
		req.setAttribute("custMoId", roleId);
		req.setAttribute("role", role);
		req.getRequestDispatcher("WEB-INF/role/detail.jsp").forward(req, res);
	}
	//转到修改顾客页面
	protected void toUpdateRole(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		//获取数据
		String custId = req.getParameter("custMoId");
		System.out.println("custMoId:"+custId);
		session.setAttribute("custMoId",custId);
		//String name = req.getParameter("name");
		CustomerDao dao = new CustomerDao();
		Customer role = dao.findByPk(custId);
		req.setAttribute("custMoId", custId);
		req.setAttribute("role", role);
		req.getRequestDispatcher("WEB-INF/role/updateRole.jsp").forward(req, res);
	}
	//修改顾客
	protected void updateRole(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String roleId = req.getParameter("custMoId");
		String name=req.getParameter("custName");
		String tel=req.getParameter("telephone");
		String sex=req.getParameter("custSex");
		String ageStr=req.getParameter("custAge");
		String addr=req.getParameter("custAddr");
		String content=req.getParameter("custContent");
		String bodyInfo=req.getParameter("bodyInfo");
		Integer age = 0;
		if(!UtilCheck.isEmpty(ageStr)) {
			age = Integer.valueOf(ageStr);
		}
		
		HttpSession session = req.getSession();
		 session.setAttribute("custMoId",roleId);
		
		if (name==null||name.equals("")) {
			req.setAttribute("error", "名称不能为空。");
			req.getRequestDispatcher("WEB-INF/role/updateRole.jsp").forward(req, res);
		}else {
			Customer cust = new Customer();
			cust.setCustId(roleId);
			cust.setAge(age);
			cust.setName(name);
			cust.setTel(tel);
			cust.setSex(sex);
			cust.setAddr(addr);
			cust.setBodyInfo(bodyInfo);
			cust.setContent(content);
			System.out.println(cust.toString());
			new CustomerDao().updateCust(cust);
			req.setAttribute("custMoId", roleId);
			res.sendRedirect("toUpdateRole.do?custMoId="+roleId);
		}
	}
	
	protected List<Customer> findAllCust(String addNo,String custId)throws ServletException, IOException {
		
		CustomerDao dao = new CustomerDao();
		List<Customer> custList = dao.findCust(addNo,custId);
		return custList;
	}
	
	protected Admin findAdmin(String adminCode) throws IOException{
		AdminDao adminDao = new AdminDao();
		Admin admin = adminDao.findByCode(adminCode,null);
		return admin;
	}
	
	
	//查询顾客列表
	protected void findRoleList(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		//接收数据
		String page = req.getParameter("page");
		String size = req.getServletContext().getInitParameter("sizeA");
		String custId = req.getParameter("custId");
		String consumDateFrom = req.getParameter("da1");
		String consumDateTo = req.getParameter("da2");
		String searchType = req.getParameter("search_type");
		System.out.println(custId+"--"+consumDateFrom+"--"+consumDateTo);
		//接收数据
		HttpSession session = req.getSession();
		String adminCode = (String) session.getAttribute("adminCode");
		
		Admin admin = findAdmin(adminCode);
		//处理数据
		if (page==null||page.equals("")) {
			page="1";
		}
		String addNo = admin.getAddNo();
		if(addNo==null) {
			addNo = req.getParameter("addNo");
		}
		CustomerInfoDao dao = new CustomerInfoDao();
		CustomerDao custdao = new CustomerDao();
		List<Cust> list = new ArrayList<Cust>();
		List<Customer> custList = new ArrayList<Customer>();
		int rows = 1;
		int total = 1;
		list = dao.findByPage(addNo,custId, consumDateFrom, consumDateTo);
		if("1".equals(searchType)) {
			custList = findAllCust(addNo,custId);
		}else {
//			 list = dao.findBycond(new Integer(page), new Integer(size), addNo,custId, consumDateFrom, consumDateTo);
			 custList = custdao.findBycond(new Integer(page), new Integer(size),addNo,custId);
			 rows = custdao.findRows(addNo,custId,consumDateFrom,consumDateTo);
			 total = rows/new Integer(size);
		}
		if (rows%new Integer(size)!=0) {
			total++;
		}
		int curTotal = 0;
		for(Cust cu:list) {
			curTotal+=cu.getConsum();
		}
		req.setAttribute("search_type", searchType);
		req.setAttribute("curTotal", curTotal);
		req.setAttribute("addNo", addNo);
		req.setAttribute("adminInfo", admin);
		List<Add> addList = findAdd();
//		System.out.println(addList.toString());
		//绑定数据，转发到查询页面
		req.setAttribute("addList", addList);
		req.setAttribute("page", page);
		req.setAttribute("total", total);
		req.setAttribute("roles", custList);
		req.setAttribute("custId", custId);
		req.setAttribute("da1", consumDateFrom);
		req.setAttribute("da2", consumDateTo);
		req.getRequestDispatcher("WEB-INF/role/roleList.jsp").forward(req, res);
		
	}
	
	protected List<Add> findAdd()throws ServletException, IOException {
		
		AddDao addDao = new AddDao();
		List<Add> addList = addDao.findAdd();
		return addList;
	}
	
	

}
