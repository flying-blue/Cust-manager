package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Cust;
import entity.Customer;
import util.DBUtil;
import util.UtilCheck;

public class CustomerDao {

	public Customer findByPk(String custId) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from customer where cust_id=? ";
			PreparedStatement p = conn.prepareStatement(sql);
			p.setString(1, custId);
			ResultSet rs = p.executeQuery();
			Customer r =null;
			if (rs.next())  {
				r = new Customer();
				r.setCustId(rs.getString("cust_id"));
				r.setName(rs.getString("name"));
				r.setKbn(rs.getString("kbn"));
				r.setAge(rs.getInt("age"));
				r.setTel(rs.getString("tel"));
				r.setSex(rs.getString("sex"));
				r.setBodyInfo(rs.getString("body_info"));
				r.setAddr(rs.getString("addr"));
				r.setAppendDate(rs.getDate("append_date"));
				r.setContent(rs.getString("content"));
			}
			return r;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Not found the admin",e);
		}finally {
			DBUtil.close(conn);
		}
	}
	//根据id查询
		public List<Customer> findById(String custId) {
			Connection conn = null;
			try {
				conn = DBUtil.getConnection();
				String sql = "select * from customer where cust_id=? ";
				PreparedStatement p = conn.prepareStatement(sql);
				p.setString(1, custId);
				ResultSet rs = p.executeQuery();
				List<Customer> custList = new ArrayList<Customer>();;
				while (rs.next())  {
					Customer r = new Customer();
					r.setCustId(rs.getString("cust_id"));
					r.setName(rs.getString("name"));
					r.setKbn(rs.getString("kbn"));
					r.setTel(rs.getString("tel"));
					r.setSex(rs.getString("sex"));
					r.setAge(rs.getInt("age"));
					r.setAddr(rs.getString("addr"));
					r.setBodyInfo(rs.getString("body_info"));
					r.setAppendDate(rs.getDate("append_date"));
					r.setContent(rs.getString("content"));
					custList.add(r);
				}
				return custList;
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("Not found the admin",e);
			}finally {
				DBUtil.close(conn);
			}
		}
		
		
		//查询所有
		public List<Customer> findCust(String addNo, String custId) {
			Connection conn = null;
			try {
				conn = DBUtil.getConnection();
				String sql = "select * from customer where (1 = ? or kbn = ?) and (1 = ? or cust_id = ?) order by cust_id";
				PreparedStatement p = conn.prepareStatement(sql);
				p.setInt(1, UtilCheck.isEmptyStr(addNo));
				p.setString(2, addNo);
				p.setInt(3, UtilCheck.isEmptyStr(custId));
				p.setString(4, custId);
				ResultSet rs = p.executeQuery();
				List<Customer> list = new ArrayList<Customer>();
				while (rs.next()) {
					Customer role = new Customer();
					role.setCustId(rs.getString("cust_id"));
					role.setName(rs.getString("name"));
					role.setKbn(rs.getString("kbn"));
					role.setTel(rs.getString("tel"));
					role.setSex(rs.getString("sex"));
					role.setAddr(rs.getString("addr"));
					role.setBodyInfo(rs.getString("body_info"));
					role.setAge(rs.getInt("age"));
					role.setAppendDate(rs.getDate("append_date"));
					role.setContent(rs.getString("content"));
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
		public List<Customer> findByPage(String addNo,String custId,String consumeFrom,String consumeTo) {
			
			Connection conn = null;
			try {
				conn = DBUtil.getConnection();
//							select * from (select c.*,rownum r from (select * from cost_xf order by cost_id) c ) where r between ? and ?";
				String sql = "select * from(select c.*,rownum r from(select cust.* from customer cust where ( 1 = ? or cust.cust_id like ? || '%') and (1 = ? or cust.cust_id = ?) and( 1 = ? or cust.consum_date>= to_date(?,'yyyy-mm-dd') )and( 1 = ? or cust.consum_date<=to_date(?,'yyyy-mm-dd') ) order by cust_id )c) ";
				PreparedStatement p = conn.prepareStatement(sql);
				p.setInt(1, UtilCheck.isEmptyStr(addNo));
				p.setString(2, addNo);
				p.setInt(3, UtilCheck.isEmptyStr(custId));
				p.setString(4, custId);
				p.setInt(5, UtilCheck.isEmptyStr(consumeFrom));
				p.setString(6, consumeFrom);
				p.setInt(7, UtilCheck.isEmptyStr(consumeTo));
				p.setString(8, consumeTo);
				ResultSet rs = p.executeQuery();
				List<Customer> list = new ArrayList<Customer>();
				while(rs.next()){
					Customer r = new Customer();
					r.setCustId(rs.getString("cust_id"));
					r.setName(rs.getString("name"));
					r.setKbn(rs.getString("kbn"));
					r.setTel(rs.getString("tel"));
					r.setSex(rs.getString("sex"));
					r.setAge(rs.getInt("age"));
					r.setAddr(rs.getString("addr"));
					r.setBodyInfo(rs.getString("body_info"));
					r.setAppendDate(rs.getDate("append_date"));
					r.setContent(rs.getString("content"));
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
		//分页查询
				public List<Customer> findBycond(int page,int size,String addNo,String custId) {
					
					Connection conn = null;
					try {
						conn = DBUtil.getConnection();
//									select * from (select c.*,rownum r from (select * from cost_xf order by cost_id) c ) where r between ? and ?";
						String sql = "select * from(select c.*,rownum r from(select * from customer order by cust_id )c)where r between ? and ? ";
						String sql1 = "select * from(select c.*,rownum r from(select cust.* from customer cust where ( 1 = ? or cust.cust_id like ? || '%') and (1 = ? or cust.cust_id = ?)  order by cust_id )c)where r between ? and ? ";
						PreparedStatement p = conn.prepareStatement(sql1);
						
						p.setInt(1, UtilCheck.isEmptyStr(addNo));
						p.setString(2, addNo);
						p.setInt(3, UtilCheck.isEmptyStr(custId));
						p.setString(4, custId);
//						p.setInt(5, UtilCheck.isEmptyStr(consumeFrom));
//						p.setString(6, consumeFrom);
//						p.setInt(7, UtilCheck.isEmptyStr(consumeTo));
//						p.setString(8, consumeTo);
						p.setInt(5, (page-1)*size+1);
						p.setInt(6, size*page);
						ResultSet rs = p.executeQuery();
						List<Customer> list = new ArrayList<Customer>();
						while(rs.next()){
							Customer r = new Customer();
							r.setCustId(rs.getString("cust_id"));
							r.setName(rs.getString("name"));
							r.setKbn(rs.getString("kbn"));
							r.setTel(rs.getString("tel"));
							r.setSex(rs.getString("sex"));
							r.setAge(rs.getInt("age"));
							r.setAddr(rs.getString("addr"));
							r.setBodyInfo(rs.getString("body_info"));
							r.setAppendDate(rs.getDate("append_date"));
							r.setContent(rs.getString("content"));
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
		public int findRows(String addNo,String custId,String consumeFrom,String consumeTo) {
			Connection conn = null;
			try {
				conn = DBUtil.getConnection();
				String sql = " select count(*) from customer cust where ( 1 = ? or cust.cust_id like ? || '%') and (1 = ? or cust.cust_id = ?) ";
				PreparedStatement p = conn.prepareStatement(sql);
				p.setInt(1, UtilCheck.isEmptyStr(addNo));
				p.setString(2, addNo);
				p.setInt(3, UtilCheck.isEmptyStr(custId));
				p.setString(4, custId);
				/*p.setInt(5, UtilCheck.isEmptyStr(consumeFrom));
				p.setString(6, consumeFrom);
				p.setInt(7, UtilCheck.isEmptyStr(consumeTo));
				p.setString(8, consumeTo);*/
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
		public void updateCust(Customer cust) {
			Connection conn = null;
			try {
				conn = DBUtil.getConnection();
				String sql = "update customer set name=? ,age = ?,tel = ?,addr = ?,content = ?,body_info = ?,update_date = sysdate where cust_id=?";
				PreparedStatement p = conn.prepareStatement(sql);
				p.setString(1, cust.getName());
				p.setInt(2, cust.getAge());
				p.setString(3, cust.getTel());
				p.setString(4, cust.getAddr());
				p.setString(5, cust.getContent());
				p.setString(6, cust.getBodyInfo());
				p.setString(7, cust.getCustId());
				p.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("Update role dailed",e);
			}finally {
				DBUtil.close(conn);
			}
		}
		
		//删除角色
		public void deleteCust(String roleId) {
			Connection conn = null;
			try {
				conn = DBUtil.getConnection();
				String sql = "delete customer where cust_id=?";
				PreparedStatement p = conn.prepareStatement(sql);
				p.setString(1, roleId);
				p.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("Delete the role failed",e);
			}finally {
				DBUtil.close(conn);
			}
			
		}
		//增加角色
		public void saveCustomer(Customer cust) {
			Connection conn = null;
			try {
				conn = DBUtil.getConnection();
				String sql = "insert into customer values(?,?,?,?,?,?,?,?,sysdate,?,?,?)";
				List<Customer> list = findById(cust.getCustId());
				PreparedStatement p = conn.prepareStatement(sql);
				p.setString(1, cust.getCustId());
				p.setString(2, cust.getName());
				p.setString(3, cust.getTel());
				p.setString(4, cust.getSex());
				p.setString(5, cust.getKbn());
				p.setInt(6, cust.getAge());
				p.setString(7, cust.getBodyInfo());
				p.setString(8, cust.getAddr());
				p.setDate(9, (Date) cust.getUpdateDate());
				p.setString(10, cust.getAppendUserId());
				p.setString(11, cust.getContent());
				p.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("Save the role failed",e);
			}finally {
				DBUtil.close(conn);
			}
			
		}

}
