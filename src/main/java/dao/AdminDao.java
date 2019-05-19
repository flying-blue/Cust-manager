package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.management.RuntimeErrorException;

import entity.Module;

import entity.Admin;
import util.DBUtil;
import util.UtilCheck;

public class AdminDao implements Serializable {
//		admin_id 	number(8) primary key not null,
//	   	admin_code 	varchar2(30) not null,
//	   	password 	varchar2(30) not null,
//	   	name 		varchar2(30) not null,
//	   	telephone 	varchar2(15),
//	   	email 		varchar2(50),
//	   	enrolldate 	date default sysdate not null

	
	public void save(Admin a) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into admin_info_xf values(admin_seq.nextval,?,?,?,?,?,sysdate,?)";
			PreparedStatement p = conn.prepareStatement(sql);
			p.setString(1, a.getAdminCode());
			p.setString(2, a.getPassword());
			p.setString(3, a.getName());
			p.setString(4, a.getTelephone());
			p.setString(5, a.getEmail());
			p.setString(6, a.getAddNo());
			p.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Save the admin failed",e);
		}finally {
			DBUtil.close(conn);
		}
	}
	
	
	
	public List<Admin> findByPage(int page,int size) {
		
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
//						select * from (select c.*,rownum r from (select * from cost_xf order by cost_id) c ) where r between ? and ?";
			String sql = "select * from(select c.*,rownum r from(select * from admin_info_xf order by admin_id )c)where r between ? and ? ";
			PreparedStatement p = conn.prepareStatement(sql);
			p.setInt(1, (page-1)*size+1);
			p.setInt(2, size*page);
			ResultSet rs = p.executeQuery();
			List<Admin> list = new ArrayList<Admin>();
			while(rs.next()){
				Admin a = createAmin(rs);
				list.add(a);
			}
		return list;
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Found the admin by page failed",e);
		}finally {
			DBUtil.close(conn);
		}
		
		
	}
	
	public int findRows() {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = " select count(*) from admin_info_xf";
			PreparedStatement p = conn.prepareStatement(sql);
			ResultSet rs = p.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Found rows failed",e);
		}finally {
			DBUtil.close(conn);
		}
	}
	
	public List<Admin> findAll() {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from admin_info_xf";
			PreparedStatement p = conn.prepareStatement(sql);
			ResultSet rs = p.executeQuery();
			List<Admin> list = new ArrayList<Admin>();
			while(rs.next()){
				Admin a = createAmin(rs);
				list.add(a);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Found the admin failed",e);
		}finally {
			DBUtil.close(conn);
		}
		
	}

	private Admin createAmin(ResultSet rs) throws SQLException {
		Admin a = new Admin();
		a.setAdminCode(rs.getString("admin_code"));
		a.setAdminId(rs.getInt("admin_id"));
		a.setPassword(rs.getString("password"));
		a.setName(rs.getString("name"));
		a.setTelephone(rs.getString("telephone"));
		a.setEmail(rs.getString("email"));
		a.setAddNo(rs.getString("add_no"));
		a.setEnrolldate(rs.getTimestamp("enrolldate"));
		return a;
	}
	
	
	public Admin findByCode(String code,String addNo) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from admin_info_xf where(1 = ? or admin_code=? ) and (1 = ? or add_no = ?)";
			PreparedStatement p = conn.prepareStatement(sql);
			p.setInt(1, UtilCheck.isEmptyStr(code));
			p.setString(2, code);
			p.setInt(3, UtilCheck.isEmptyStr(addNo));
			p.setString(4, addNo);
			ResultSet rs = p.executeQuery();
			Admin a = null;
			while (rs.next()) {
				a = new Admin();
				a.setAdminId(rs.getInt("admin_id"));
				a.setPassword(rs.getString("password"));
				a.setName(rs.getString("name"));
				a.setTelephone(rs.getString("telephone"));
				a.setEmail(rs.getString("email"));
				a.setAddNo(rs.getString("add_no"));
				a.setEnrolldate(rs.getTimestamp("enrolldate"));
				return a;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Not found the admin",e);
		}finally {
			DBUtil.close(conn);
		}
		return null;
	}
	
	public void update(Admin admin) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "update admin_info_xf set name=?,telephone=?,email=?,add_no=? where admin_code=?";
			PreparedStatement p = conn.prepareStatement(sql);
			p.setString(1, admin.getName());
			p.setString(2, admin.getTelephone());
			p.setString(3, admin.getEmail());
			p.setString(4, admin.getAddNo());
			p.setString(5, admin.getAdminCode());
			p.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Update admin dailed",e);
		}finally {
			DBUtil.close(conn);
		}
	}
	
	public void updateUseInfo(Admin admin) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "update admin_info_xf set name=?,telephone=?,email=? where admin_code=?";
			PreparedStatement p = conn.prepareStatement(sql);
			p.setString(1, admin.getName());
			p.setString(2, admin.getTelephone());
			p.setString(3, admin.getEmail());
			p.setString(4, admin.getAdminCode());
			p.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Update admin dailed",e);
		}finally {
			DBUtil.close(conn);
		}
	}
	
	
	
		public void updatePwd(String adminCode,String newPwd) {
			Connection conn = null;
			try {
				conn = DBUtil.getConnection();
				String sql = "update admin_info_xf set password=? where admin_code=?";
				PreparedStatement p = conn.prepareStatement(sql);
				p.setString(1, newPwd);
				p.setString(2, adminCode);
				p.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("Update admin dailed",e);
			}finally {
				DBUtil.close(conn);
			}
		
		
	}
	
		
		public void deleteAdmin(int adminId) {
			Connection conn = null;
			try {
				conn = DBUtil.getConnection();
				String sql = "delete admin_info_xf where admin_id=?";
				PreparedStatement p = conn.prepareStatement(sql);
				p.setInt(1, adminId);
				p.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("Delete the admin failed",e);
			}finally {
				DBUtil.close(conn);
			}
			
		}
		
		public List<Module> findModulesByAdmin(int adminId){
			Connection conn = null;
			try {
				conn = DBUtil.getConnection();
				String sql = "select * from module_info where module_id in (" + 
						"			select rm.module_id " + 
						"			from admin_role ar " + 
						"			inner join role_info_xf ri on ri.role_id=ar.role_id " + 
						"			inner join role_module rm on rm.role_id=ri.role_id " + 
						"			where ar.admin_id= ? " + 
						"		) order by module_id ";
				PreparedStatement p = conn.prepareStatement(sql);
				p.setInt(1, adminId);
				ResultSet rs = p.executeQuery();
				List<Module> modules = new ArrayList<Module>();
				while (rs.next()) {
					Module m = new Module();
					m.setModuleId(rs.getInt("module_id"));
					m.setName(rs.getString("name"));
					modules.add(m);
				}
				return modules;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("Delete the admin failed",e);
			}finally {
				DBUtil.close(conn);
			}
			
		}
		
	
	public static void main(String[] args) {
		
		Admin a = new Admin();
		AdminDao dao = new AdminDao();
		a.setAdminCode("gongjing");
		a.setEmail("zhouyu@xinlang.com");
		a.setName("zhouyu");
		a.setPassword("1234");
		a.setTelephone("1314666");
		dao.save(a);
//		List<Admin> list = dao.findByPage(2, 4);
//		for (Admin admin : list) {
//			System.out.println(admin.toString());
//		}
	
		
		
	}
	
	
	
}
