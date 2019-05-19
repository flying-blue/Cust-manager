package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Role;
import util.DBUtil;

public class RoleDao {

	//根据id查询
	public Role findById(String roleId) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from costumer where cust_id=?";
			PreparedStatement p = conn.prepareStatement(sql);
			p.setString(1, roleId);
			ResultSet rs = p.executeQuery();
			Role r = new Role();
			if (rs.next()) {
				r.setRoleId(rs.getInt("role_id"));
				r.setName(rs.getString("name"));
			}
			return r;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Not found the admin",e);
		}finally {
			DBUtil.close(conn);
		}
	}
	
	
	//查询所有
	public List<Role> findRole() {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from customer";
			PreparedStatement p = conn.prepareStatement(sql);
			ResultSet rs = p.executeQuery();
			List<Role> list = new ArrayList<Role>();
			while (rs.next()) {
				Role role = new Role();
				role.setRoleId(rs.getInt("role_id"));
				role.setName(rs.getString("name"));
				list.add(role);
			}
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Found the role failed",e);
		}finally {
			DBUtil.close(conn);
		}
	}
	
	//分页查询
	public List<Role> findByPage(int page,int size) {
		
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
//						select * from (select c.*,rownum r from (select * from cost_xf order by cost_id) c ) where r between ? and ?";
			String sql = "select * from(select c.*,rownum r from(select * from customer order by role_id )c)where r between ? and ? ";
			PreparedStatement p = conn.prepareStatement(sql);
			p.setInt(1, (page-1)*size+1);
			p.setInt(2, size*page);
			ResultSet rs = p.executeQuery();
			List<Role> list = new ArrayList<Role>();
			while(rs.next()){
				Role r = new Role();
				r.setRoleId(rs.getInt("cust_id"));
				r.setName(rs.getString("name"));
				list.add(r);
			}
		return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Found the role by page failed",e);
		}finally {
			DBUtil.close(conn);
		}
	}

	//查询总行数
	public int findRows() {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = " select count(*) from role_info_xf";
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
	
	//修改角色信息
	public void updateRole(int role_id,String name) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "update role_info_xf set name=? where role_id=?";
			PreparedStatement p = conn.prepareStatement(sql);
			p.setString(1, name);
			p.setInt(2, role_id);
			p.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Update role dailed",e);
		}finally {
			DBUtil.close(conn);
		}
	}
	
	//删除角色
	public void deleteRole(int roleId) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "delete role_info_xf where role_id=?";
			PreparedStatement p = conn.prepareStatement(sql);
			p.setInt(1, roleId);
			p.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Delete the role failed",e);
		}finally {
			DBUtil.close(conn);
		}
		
	}
	//增加角色
	public void saveRole(String name) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into role_info_xf values(role_seq_xf.nextval,?)";
			PreparedStatement p = conn.prepareStatement(sql);
			p.setString(1, name);
			p.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Save the role failed",e);
		}finally {
			DBUtil.close(conn);
		}
		
		
		
		
	}
	//增加
	public void saveAdminRole(Integer adminId) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "insert into admin_role values(?,'400')";
			PreparedStatement p = conn.prepareStatement(sql);
			p.setInt(1, adminId);
			p.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Save the role failed",e);
		}finally {
			DBUtil.close(conn);
		}
				
		
		
		
	}
	
	
	
	public static void main(String[] args) {
		RoleDao dao = new RoleDao();
		List<Role> list = dao.findByPage(3, 3);
		for (Role role : list) {
			System.out.println(role.toString());
		}
		
	}

	
}
