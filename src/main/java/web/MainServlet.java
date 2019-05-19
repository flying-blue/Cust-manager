package web;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Module;

import dao.AdminDao;
import dao.CostDao;
import entity.Admin;
import entity.Cost;
import util.ImageUtil;

public class MainServlet extends HttpServlet {

	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//��ȡ��ǰ��·��
		String path = req.getServletPath();
		System.out.println(path);
		//���ݹ淶�ж�·��
		if ("/findCost.do".equals(path)) {
			findCost(req,res);
		}else if ("/toAddCost.do".equals(path)) {
			toAddCost(req,res);
		} else if ("/addCost.do".equals(path)) {
			addCost(req,res);	
		} else if ("/toUpdateCost.do".equals(path)) {
			toUpdateCost(req,res);	
		} else if ("/updateCost.do".equals(path)) {
			updateCost(req,res);	
		} else if ("/deleteCost.do".equals(path)) {
			deleteCost(req,res);	
		} else if ("/toLogin.do".equals(path)) {
			toLogin(req,res);	
		} else if ("/login.do".equals(path)) {
			login(req,res);	
		} else if ("/createImg.do".equals(path)) {
			createImg(req,res);	
		} else if ("/toIndex.do".equals(path)) {
			toIndex(req,res);	
		} else if ("/logout.do".equals(path)) {
			logout(req,res);
		} else if ("/toDetail.do".equals(path)) {
			toDetail(req,res);	
		} else {
			roleManager(req, res);
		}
		
		
		
		
	}
	
	
	
	//��ɫ�����ж�ģ��
	public void roleManager(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		String path = req.getServletPath();
		CustServlet rs = new CustServlet();
		if ("/findRoleList.do".equals(path)) {
			rs.findRoleList(req, res);
		}else if ("/toCustDetail.do".equals(path)) {
			rs.toDetail(req, res);
		}else if ("/toUpdateRole.do".equals(path)) {
			rs.toUpdateRole(req, res);
		}else if ("/updateRole.do".equals(path)) {
			rs.updateRole(req, res);
		}else if ("/toAddRole.do".equals(path)) {
			rs.toAddRole(req, res);
		}else if ("/addRole.do".equals(path)) {
			rs.addRole(req, res);
		}else if ("/toAddConsum.do".equals(path)) {
			rs.toAddConsum(req, res);
		}else if ("/addConsum.do".equals(path)) {
			rs.addConsum(req, res);
		}else if ("/deleteRole.do".equals(path)) {
			rs.deleteRole(req, res);
		}else {
			adminManager(req, res);
		}
	}
	
	//����Ա�����ж�ģ��
	public void adminManager(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		String path = req.getServletPath();
		System.out.println(path);
		AdminServlet as = new AdminServlet();
		if ("/toUser.do".equals(path)) {
			as.toUser(req, res);
		}else if ("/toUpdateAdmin.do".equals(path)) {
			as.toUpdateAdmin(req, res);
		}else if ("/toUpdatePwd.do".equals(path)) {
			as.toUpdatePwd(req, res);
		}else if ("/updatePwd.do".equals(path)) {
			as.updatePwd(req, res);
		}else if ("/toAdmin.do".equals(path)) {
			as.toAdmin(req, res);
		}else if ("/modifyAdmin.do".equals(path)) {
			as.modifyAdmin(req, res);
		}else if ("/toModifyAdmin.do".equals(path)) {
			as.toModifyAdmin(req, res);
		}else if ("/deleteAdmin.do".equals(path)) {
			as.deleteAdmin(req, res);
		}else if ("/toAddAdmin.do".equals(path)) {
			as.toAddAdmin(req, res);
		}else if ("/addAdmin.do".equals(path)) {
			as.addAdmin(req, res);
		}else {
			throw new RuntimeException("��Ч��·��");
		}
		
	}
	
	
	
	
	
	
	//�鿴�ʷ�����
	protected void toDetail(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		//��������
		req.setCharacterEncoding("utf-8");
		Integer id = Integer.valueOf(req.getParameter("id"));
		
		//��������
		CostDao dao = new CostDao();
		Cost cost = dao.findById(id);
		//�󶨲�ת����detail.jsp
		req.setAttribute("cost", cost);
		req.getRequestDispatcher("WEB-INF/cost/detail.jsp").forward(req, res);
		
	}
	
	
	//������֤��
	protected void createImg(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		//1.�����������֤��ͼƬ
		Object[] objs = ImageUtil.createImage();
		//2.����֤�����session
		String imageCode = (String) objs[0];
		HttpSession session = req.getSession();
		session.setAttribute("imgcode", imageCode);
		
		//3.��ͼƬ����������
		BufferedImage img = (BufferedImage) objs[1];
		res.setContentType("image/png");
		OutputStream os = res.getOutputStream();
		ImageIO.write(img, "png", os);
		
	}
	
	protected void logout(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		//����session
		req.getSession().invalidate();
		//�ض��򵽵�¼ҳ��
		res.sendRedirect(req.getContextPath()+"/toLogin.do");
	}
	
	protected void toIndex(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		
		
		
		req.getRequestDispatcher("WEB-INF/main/index.jsp").forward(req, res);
	}
	
	protected void toLogin(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/main/login.jsp").forward(req, res);
	}
	protected void login(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		//��������
		String code = req.getParameter("adminCode");
		String password = req.getParameter("password");
		String imgcode = req.getParameter("code");
		HttpSession session = req.getSession();
		String imagecode =  (String) session.getAttribute("imgcode");
		if (imgcode==null||!imgcode.equalsIgnoreCase(imagecode)) {
			req.setAttribute("error", "��֤�����");
			req.getRequestDispatcher("WEB-INF/main/login.jsp").forward(req, res);
			return ;
		}
		
		AdminDao dao = new AdminDao();
		Admin a = dao.findByCode(code,null);
		//��������
		if (a==null ) {
			//�˺Ŵ���
			req.setAttribute("error", "�˺Ŵ���");
			req.getRequestDispatcher("WEB-INF/main/login.jsp").forward(req, res);
		}else if (!a.getPassword().equals(password)) {
			//�������
			req.setAttribute("error", "�������");
			req.getRequestDispatcher("WEB-INF/main/login.jsp").forward(req, res);
		}else {
			//���˺Ŵ���cookie�����͸��������������Զ����棬������ҳ��ʹ��
			//Ҳ����ʹ��session�����˺�
			session.setAttribute("adminCode", code);
			
			List<Module> modules = 
					dao.findModulesByAdmin(a.getAdminId());
			session.setAttribute("allModules", modules);
//			System.out.println(modules.toString());
			
//			Cookie c = new Cookie("adminCode", code);
//			res.addCookie(c);
			//���ԣ��ض�����ҳ
			res.sendRedirect("toIndex.do");
		}
		
		
	}
	
	
	//ɾ���ʷ� 
	protected void deleteCost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		
		Integer id = Integer.valueOf(req.getParameter("id"));
		CostDao dao = new CostDao();
		dao.delete(id);
		res.sendRedirect("findCost.do");
	}

	
	
	//ת����update.jsp
	protected void toUpdateCost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		//��������
		Integer id = Integer.valueOf(req.getParameter("id"));
		//��������
		CostDao dao = new CostDao();
		Cost cost = dao.findById(id);
		//�󶨲�ת��
		req.setAttribute("cost", cost);
		req.getRequestDispatcher("WEB-INF/cost/update.jsp").forward(req, res);

	}
	//�޸��ʷ�
	protected void updateCost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		//��������
		req.setCharacterEncoding("UTF-8");
		Integer id = Integer.valueOf(req.getParameter("id"));
		Cost c = createCost(req);
		c.setCostId(id);
		//��������
		CostDao dao = new CostDao();
		dao.update(c);
		
		//�ض���findCost.so
		res.sendRedirect("findCost.do");
		
	}

	//ת����add.jsp
	protected void toAddCost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("WEB-INF/cost/add.jsp").forward(req, res);
	}
	//�����ʷ�
	protected void addCost(HttpServletRequest req, HttpServletResponse res)throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		//��������
		CostDao dao = new CostDao();
		Cost c = createCost(req);
		
		//��������
		dao.save(c);
		//�ض���findCost.do
		res.sendRedirect("findCost.do");
		
	}

	private Cost createCost(HttpServletRequest req) {
		Cost c = new Cost();
		String name = req.getParameter("name");
		String baseDuration =req.getParameter("baseDuration");
		String baseCost = req.getParameter("baseCost");
		String unitCost = req.getParameter("unitCost");
		if (baseDuration!=null&&!baseDuration.equals("")) {
			c.setBaseDuration(Integer.valueOf(baseDuration));
		}
		if (baseCost!=null&&!baseCost.equals("")) {
			c.setBaseCost(new Double(baseCost));
		}
		if (unitCost!=null&&!unitCost.equals("")) {
			c.setUnitCost(new Double(unitCost));
		}
		String costType = req.getParameter("radFeeType");
		String descr = req.getParameter("descr");
		c.setName(name);
		c.setCostType(costType);
		c.setDescr(descr);
		return c;
	}


	//��ѯ���е��ʷ�
	protected void findCost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//��ѯ�ʷ�
		String page = req.getParameter("page");
		String size = req.getServletContext().getInitParameter("size");
		if (page==null||page.equals("")) {
			page="1";
		}
		
		CostDao dao = new CostDao();
		List<Cost> list = dao.findByPage(new Integer(page),new Integer(size) );
		int rows = dao.findRows();
		int total = rows/new Integer(size);
		if (rows%new Integer(size)!=0) {
			total++;
		}
		//ת����jsp
		req.setAttribute("page", page);
		req.setAttribute("total", total);
		req.setAttribute("costs", list);
		//��ǰ��/netctoss/findCost
		//Ŀ�꣺/netctoss/WEB-INF/cost/find.jsp
		req.getRequestDispatcher("WEB-INF/cost/find.jsp").forward(req, res);
		
			
	}
}
