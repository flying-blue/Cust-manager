package web;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.RoleDao;
import entity.Role;

public class RoleServlet extends HttpServlet {

	
	//ɾ����ɫ
	protected void deleteRole(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String roleId = req.getParameter("roleId");
		new RoleDao().deleteRole(new Integer(roleId));
		res.sendRedirect("findRoleList.do");
	}

		
	//ת�����ӽ�ɫҳ��
	protected void toAddRole(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		req.getRequestDispatcher("WEB-INF/role/addRole.jsp").forward(req, res);
	}

	//���ӽ�ɫ
	protected void addRole(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String name = req.getParameter("name");
		if (name==null||name.equals("")) {
			req.setAttribute("error", "���Ʋ���Ϊ�ա�");
			req.getRequestDispatcher("WEB-INF/role/addRole.jsp").forward(req, res);
		}else {
			new RoleDao().saveRole(name);
			res.setContentType("text/html");
			res.sendRedirect("findRoleList.do");
		}
		
	}
	
	
	//ת���޸Ľ�ɫҳ��
	protected void toUpdateRole(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		HttpSession session = req.getSession();
		//��ȡ����
		String roleId = req.getParameter("roleId");
		System.out.println("roleId:"+roleId);
		session.setAttribute("roleId",roleId);
		//String name = req.getParameter("name");
		RoleDao dao = new RoleDao();
		Role role = dao.findById(roleId);
		req.setAttribute("roleId", roleId);
		req.setAttribute("role", role);
		req.getRequestDispatcher("WEB-INF/role/updateRole.jsp").forward(req, res);
	}
	//�޸Ľ�ɫ
	protected void updateRole(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String roleId = req.getParameter("roleId");
		HttpSession session = req.getSession();
		 session.setAttribute("roleId",roleId);
		System.out.println(roleId);
		
		String name = req.getParameter("name");
//		Role role = new Role();
//		role.setName(name);
//		role.setRoleId(new Integer(roleId));
		if (name==null||name.equals("")) {
			req.setAttribute("error", "���Ʋ���Ϊ�ա�");
			req.getRequestDispatcher("WEB-INF/role/updateRole.jsp").forward(req, res);
		}else {
			req.setAttribute("roleId", roleId);
			new RoleDao().updateRole(new Integer(roleId), name);
			res.sendRedirect("toUpdateRole.do");
		}
		
		
	}
	
	
	//��ѯ��ɫ�б�
	protected void findRoleList(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		//��������
		String page = req.getParameter("page");
		String size = req.getServletContext().getInitParameter("sizeA");
		//��������
		if (page==null||page.equals("")) {
			page="1";
		}
		RoleDao dao = new RoleDao();
		List<Role> list = dao.findByPage(new Integer(page), new Integer(size));
		int rows = dao.findRows();
		int total = rows/new Integer(size);
		if (rows%new Integer(size)!=0) {
			total++;
		}
		//�����ݣ�ת������ѯҳ��
		req.setAttribute("page", page);
		req.setAttribute("total", total);
		req.setAttribute("roles", list);
		
		req.getRequestDispatcher("WEB-INF/role/roleList.jsp").forward(req, res);
		
	}

	
	
}
