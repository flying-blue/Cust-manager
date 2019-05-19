package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AddDao;
import dao.AdminDao;
import dao.RoleDao;
import entity.Add;
import entity.Admin;

public class AdminServlet extends HttpServlet {
	
	
	
	//转发到增加管理员页面
	protected void toAddAdmin(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		List<Add> addList = findAdd();
		req.setAttribute("addList", addList);
		req.getRequestDispatcher("WEB-INF/admin/addAdmin.jsp").forward(req, res);

	}
	
	//增加管理员
	protected void addAdmin(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		//接收数据
		String adminCode = req.getParameter("adminCode");
		String password = req.getParameter("password");
		String pwd = req.getParameter("pwd");
		String name = req.getParameter("name");
		String telephone = req.getParameter("telephone");
		String email = req.getParameter("email");
		String addNo = req.getParameter("addNo");
		//处理数据
		AdminDao dao = new AdminDao();
		Admin a = new Admin();
		
		if (adminCode==null||adminCode.equals("")||password==null
				||password.equals("")||name==null||name.equals("")) {
			req.setAttribute("error", "内容不能为空");
			req.getRequestDispatcher("WEB-INF/admin/addAdmin.jsp").forward(req, res);
		}else if (!password.equals(pwd)) {
			req.setAttribute("error", "两次密码不相同");
			req.getRequestDispatcher("WEB-INF/admin/addAdmin.jsp").forward(req, res);
		}else {
			a.setAdminCode(adminCode);
			a.setName(name);
			a.setPassword(password);
			a.setEmail(email);
			a.setTelephone(telephone);
			a.setAddNo(addNo);
			dao.save(a);
			addRole(adminCode);
			res.sendRedirect("toAdmin.do");
		}
	}
	
	protected void addRole(String adminCode)throws ServletException, IOException {
		AdminDao dao = new AdminDao();
		RoleDao roleDao = new RoleDao();
		Admin admin = dao.findByCode(adminCode, null);
		if(admin!=null) {
			roleDao.saveAdminRole(admin.getAdminId());
		}
	}
	
	
	//删除管理员
	protected void deleteAdmin(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
 		String adminId = req.getParameter("adminId");
		AdminDao dao = new AdminDao();
		dao.deleteAdmin(new Integer(adminId));
 		
		res.sendRedirect("toAdmin.do");
	}
	
	protected Admin findAdmin(String adminCode) throws IOException{
		AdminDao adminDao = new AdminDao();
		Admin admin = adminDao.findByCode(adminCode,null);
		return admin;
	}
	//修改管理员资料
	protected void modifyAdmin(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String adminCode = req.getParameter("adminCode");
		String name = req.getParameter("name");
		String telephone = req.getParameter("telephone");
		String email = req.getParameter("email");
		String addNo = req.getParameter("addNo");
		 
		Admin a = new Admin();
		
		AdminDao dao = new AdminDao();
		a.setAdminCode(adminCode);
		a.setName(name);
		a.setTelephone(telephone);
		a.setEmail(email);
		a.setAddNo(addNo);
		dao.update(a);
		//res.sendRedirect("findCost.do");
		res.sendRedirect("toAdmin.do");
	}
	//转发到修改管理员资料页面
	protected void toModifyAdmin(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String adminCode = req.getParameter("adminCode");
		System.out.println(adminCode);
		List<Add> addList = findAdd();
		AdminDao dao = new AdminDao();
		Admin admin = dao.findByCode(adminCode,null);
		req.setAttribute("code", adminCode);
		req.setAttribute("admin", admin);
		req.setAttribute("addList", addList);
		
		req.getRequestDispatcher("WEB-INF/admin/updateAdmin.jsp").forward(req, res);
		
	}

	//转发到管理员页面
	protected void toAdmin(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		//接收数据
		String page = req.getParameter("page");
		String size = req.getServletContext().getInitParameter("sizeA");
		//处理数据
		if (page==null||page.equals("")) {
			page="1";
		}
		List<Add> addList = findAdd();
		AdminDao dao = new AdminDao();
		List<Admin> list = dao.findByPage(new Integer(page), new Integer(size));
		int rows = dao.findRows();
		int total = rows/new Integer(size);
		if (rows%new Integer(size)!=0) {
			total++;
		}
		req.setAttribute("addList", addList);
		req.setAttribute("page", page);
		req.setAttribute("total", total);
		req.setAttribute("admins", list);
		req.getRequestDispatcher("WEB-INF/admin/admin.jsp").forward(req, res);
		
		
	}
	
	//转发到修改密码页面
	protected void toUpdatePwd(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/admin/updatePwd.jsp").forward(req, res);
	}
	//修改密码
	protected void updatePwd(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		//接收数据
		String adminCode = req.getParameter("adminCode");
		String oldPwd = req.getParameter("oldPwd");
		String newPwd = req.getParameter("newPwd");
		String newRpwd = req.getParameter("newRpwd");
		AdminDao dao = new AdminDao();
		Admin a = dao.findByCode(adminCode,null);
		String password = a.getPassword();
		if (newPwd==null||newRpwd==null||newPwd.equals("")||newRpwd.equals("")||oldPwd==null||oldPwd.equals("")) {
			req.setAttribute("error", "密码不能为空");
			req.getRequestDispatcher("WEB-INF/admin/updatePwd.jsp").forward(req, res);
		}else if (!newPwd.equals(newRpwd)) {
			req.setAttribute("error", "新密码输入不相同");
			req.getRequestDispatcher("WEB-INF/admin/updatePwd.jsp").forward(req, res);
		}else if(!oldPwd.equals(password)){
			req.setAttribute("error", "旧密码错误");
			req.getRequestDispatcher("WEB-INF/admin/updatePwd.jsp").forward(req, res);
		}else {
			dao.updatePwd(adminCode, newPwd);
			res.sendRedirect("logout.do");
		}
		
		
	}
	
	//修改保存个人信息
	protected void toUpdateAdmin(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String adminCode = req.getParameter("adminCode");
		String name = req.getParameter("name");
		String telephone = req.getParameter("telephone");
		String email = req.getParameter("email");
		Admin a = new Admin();
		AdminDao dao = new AdminDao();
		a.setAdminCode(adminCode);
		a.setName(name);
		a.setTelephone(telephone);
		a.setEmail(email);
		dao.updateUseInfo(a);
		//res.sendRedirect("findCost.do");
		res.sendRedirect("toUser.do");
		
	}
	
	
	
	//查看个人信息
	protected void toUser(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
//		name="adminCode"
		HttpSession session = req.getSession();
		String adminCode = (String) session.getAttribute("adminCode");
		System.out.println(adminCode);
		
		AdminDao dao = new AdminDao();
		Admin admin = dao.findByCode(adminCode,null);
		req.setAttribute("admin", admin);
		req.getRequestDispatcher("WEB-INF/admin/userInfo.jsp").forward(req, res);
		
	}
	
protected List<Add> findAdd()throws ServletException, IOException {
		
		AddDao addDao = new AddDao();
		List<Add> addList = addDao.findAdd();
		return addList;
	}
}
