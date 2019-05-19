package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Add;
import util.DBUtil;

public class AddDao {

	//根据id查询
		public Add findById(String addNo) {
			Connection conn = null;
			try {
				conn = DBUtil.getConnection();
				String sql = "select * from add_info where add_no=?";
				PreparedStatement p = conn.prepareStatement(sql);
				p.setString(1, addNo);
				ResultSet rs = p.executeQuery();
				Add r = new Add();
				if (rs.next()) {
					r.setAddNo(rs.getString("add_no"));
					r.setAddName(rs.getString("add_name"));
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
		public List<Add> findAdd() {
			Connection conn = null;
			try {
				conn = DBUtil.getConnection();
				String sql = "select * from add_info order by sort_no";
				PreparedStatement p = conn.prepareStatement(sql);
				ResultSet rs = p.executeQuery();
				List<Add> list = new ArrayList<Add>();
				while (rs.next()) {
					Add Add = new Add();
					Add.setAddNo(rs.getString("add_no"));
					Add.setAddName(rs.getString("add_name"));
					Add.setSortNo(rs.getInt("sort_no"));
					list.add(Add);
				}
				return list;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("Found the Add failed",e);
			}finally {
				DBUtil.close(conn);
			}
		}
		
		//分页查询
		public List<Add> findByPage(int page,int size) {
			
			Connection conn = null;
			try {
				conn = DBUtil.getConnection();
//							select * from (select c.*,rownum r from (select * from cost_xf order by cost_id) c ) where r between ? and ?";
				String sql = "select * from(select c.*,rownum r from(select * from add_info order by add_no )c)where r between ? and ? ";
				PreparedStatement p = conn.prepareStatement(sql);
				p.setInt(1, (page-1)*size+1);
				p.setInt(2, size*page);
				ResultSet rs = p.executeQuery();
				List<Add> list = new ArrayList<Add>();
				while(rs.next()){
					Add a = new Add();
					a.setAddNo(rs.getString("add_no"));
					a.setAddName(rs.getString("add_name"));
					list.add(a);
				}
			return list;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("Found the Add by page failed",e);
			}finally {
				DBUtil.close(conn);
			}
		}

		//查询总行数
		public int findRows() {
			Connection conn = null;
			try {
				conn = DBUtil.getConnection();
				String sql = " select count(*) from Add_info";
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
		public void updateAdd(Add addInfo) {
			Connection conn = null;
			try {
				conn = DBUtil.getConnection();
				String sql = "update add_info set add_name=?,sort_no=?, where add_no=?";
				PreparedStatement p = conn.prepareStatement(sql);
				p.setString(1, addInfo.getAddName());
				p.setInt(2, addInfo.getSortNo());
				p.setString(3, addInfo.getAddNo());
				p.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("Update Add dailed",e);
			}finally {
				DBUtil.close(conn);
			}
		}
		
		//删除分店
		public void deleteAdd(int addNo) {
			Connection conn = null;
			try {
				conn = DBUtil.getConnection();
				String sql = "delete add_info where add_no=?";
				PreparedStatement p = conn.prepareStatement(sql);
				p.setInt(1, addNo);
				p.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("Delete the Add failed",e);
			}finally {
				DBUtil.close(conn);
			}
			
		}
		//增加分店
		public void saveAdd(Add addInfo) {
			Connection conn = null;
			try {
				conn = DBUtil.getConnection();
				String sql = "insert into Add_info values(?,?,?)";
				PreparedStatement p = conn.prepareStatement(sql);
				List<Add> list = findAdd();
				Add add = list.get(list.size()-1);
				p.setString(1, addInfo.getAddNo());
				p.setInt(2, add.getSortNo()+1);
				p.setString(3, addInfo.getAddName());
				p.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("Save the Add failed",e);
			}finally {
				DBUtil.close(conn);
			}
		}


}
