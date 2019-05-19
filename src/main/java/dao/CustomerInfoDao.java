package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Cust;
import util.DBUtil;
import util.UtilCheck;

public class CustomerInfoDao {

	
	public Cust findByPk(String custId,Integer conNum) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from customer_info where cust_id=? and con_num = ?";
			PreparedStatement p = conn.prepareStatement(sql);
			p.setString(1, custId);
			p.setInt(2, conNum);
			ResultSet rs = p.executeQuery();
			Cust r = null;
			if (rs.next())  {
				r = new Cust();
				r.setCustId(rs.getString("cust_id"));
				r.setName(rs.getString("name"));
				r.setKbn(rs.getString("kbn"));
				r.setTel(rs.getString("tel"));
				r.setSex(rs.getString("sex"));
				r.setConsum(rs.getInt("consum"));
				r.setConNum(rs.getInt("con_num"));
				r.setContent(rs.getString("content"));
				r.setConsumDate(rs.getDate("consum_date"));
				r.setConsumKbn(rs.getString("consum_kbn"));
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
		public List<Cust> findById(String custId) {
			Connection conn = null;
			try {
				conn = DBUtil.getConnection();
				String sql = "select * from customer_info where cust_id=? order by con_num";
				PreparedStatement p = conn.prepareStatement(sql);
				p.setString(1, custId);
				ResultSet rs = p.executeQuery();
				List<Cust> custList = new ArrayList<Cust>();;
				while (rs.next())  {
					Cust r = new Cust();
					r.setCustId(rs.getString("cust_id"));
					r.setName(rs.getString("name"));
					r.setKbn(rs.getString("kbn"));
					r.setTel(rs.getString("tel"));
					r.setSex(rs.getString("sex"));
					r.setConsum(rs.getInt("consum"));
					r.setConNum(rs.getInt("con_num"));
					r.setContent(rs.getString("content"));
					r.setConsumDate(rs.getDate("consum_date"));
					r.setConsumKbn(rs.getString("consum_kbn"));
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
		public List<Cust> findCust() {
			Connection conn = null;
			try {
				conn = DBUtil.getConnection();
				String sql = "select * from customer_info";
				PreparedStatement p = conn.prepareStatement(sql);
				ResultSet rs = p.executeQuery();
				List<Cust> list = new ArrayList<Cust>();
				while (rs.next()) {
					Cust role = new Cust();
					role.setCustId(rs.getString("cust_id"));
					role.setName(rs.getString("name"));
					role.setKbn(rs.getString("kbn"));
					role.setTel(rs.getString("tel"));
					role.setSex(rs.getString("sex"));
					role.setConsum(rs.getInt("consum"));
					role.setConNum(rs.getInt("con_num"));
					role.setContent(rs.getString("content"));
					role.setConsumDate(rs.getDate("consum_date"));
					role.setConsumKbn(rs.getString("consum_kbn"));
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
		public List<Cust> findByPage(String addNo,String custId,String consumeFrom,String consumeTo) {
			
			Connection conn = null;
			try {
				conn = DBUtil.getConnection();
//							select * from (select c.*,rownum r from (select * from cost_xf order by cost_id) c ) where r between ? and ?";
				String sql = "select * from(select c.*,rownum r from(select cust.* from customer_info cust inner join customer cu on cu.cust_id = cust.cust_id where ( 1 = ? or cust.cust_id like ? || '%') and (1 = ? or cust.cust_id = ?) and( 1 = ? or cust.consum_date>= to_date(?,'yyyy-mm-dd') )and( 1 = ? or cust.consum_date<=to_date(?,'yyyy-mm-dd') ) order by cust.cust_id )c) ";
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
				List<Cust> list = new ArrayList<Cust>();
				while(rs.next()){
					Cust r = new Cust();
					r.setCustId(rs.getString("cust_id"));
					r.setName(rs.getString("name"));
					r.setKbn(rs.getString("kbn"));
					r.setTel(rs.getString("tel"));
					r.setSex(rs.getString("sex"));
					r.setConsum(rs.getInt("consum"));
					r.setConNum(rs.getInt("con_num"));
					r.setContent(rs.getString("content"));
					r.setConsumDate(rs.getDate("consum_date"));
					r.setConsumKbn(rs.getString("consum_kbn"));
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
				public List<Cust> findBycond(int page,int size,String addNo,String custId,String consumeFrom,String consumeTo) {
					
					Connection conn = null;
					try {
						conn = DBUtil.getConnection();
//									select * from (select c.*,rownum r from (select * from cost_xf order by cost_id) c ) where r between ? and ?";
						String sql = "select * from(select c.*,rownum r from(select * from customer order by cust_id )c)where r between ? and ? ";
						String sql1 = "select * from(select c.*,rownum r from(select cust.* from customer_info cust inner join customer cu on cu.cust_id = cust.cust_id where ( 1 = ? or cust.cust_id like ? || '%') and (1 = ? or cust.cust_id = ?) and( 1 = ? or cust.consum_date>= to_date(?,'yyyy-mm-dd') )and( 1 = ? or cust.consum_date<=to_date(?,'yyyy-mm-dd') ) order by cust.cust_id )c)where r between ? and ? ";
						PreparedStatement p = conn.prepareStatement(sql1);
						
						p.setInt(1, UtilCheck.isEmptyStr(addNo));
						p.setString(2, addNo);
						p.setInt(3, UtilCheck.isEmptyStr(custId));
						p.setString(4, custId);
						p.setInt(5, UtilCheck.isEmptyStr(consumeFrom));
						p.setString(6, consumeFrom);
						p.setInt(7, UtilCheck.isEmptyStr(consumeTo));
						p.setString(8, consumeTo);
						p.setInt(9, (page-1)*size+1);
						p.setInt(10, size*page);
						ResultSet rs = p.executeQuery();
						List<Cust> list = new ArrayList<Cust>();
						while(rs.next()){
							Cust r = new Cust();
							r.setCustId(rs.getString("cust_id"));
							r.setName(rs.getString("name"));
							r.setKbn(rs.getString("kbn"));
							r.setTel(rs.getString("tel"));
							r.setSex(rs.getString("sex"));
							r.setConsum(rs.getInt("consum"));
							r.setConNum(rs.getInt("con_num"));
							r.setContent(rs.getString("content"));
							r.setConsumDate(rs.getDate("consum_date"));
							r.setConsumKbn(rs.getString("consum_kbn"));
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
				String sql = " select count(*) from customer_info cust where ( 1 = ? or cust.cust_id like ? || '%') and (1 = ? or cust.cust_id = ?)  and( 1 = ? or cust.consum_date>= to_date(?,'yyyy-mm-dd') )and( 1 = ? or cust.consum_date<=to_date(?,'yyyy-mm-dd'))";
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
		public void updateCust(Cust cust) {
			Connection conn = null;
			try {
				conn = DBUtil.getConnection();
				String sql = "update customer_info set name=? where cust_id=?";
				PreparedStatement p = conn.prepareStatement(sql);
				p.setString(1, cust.getName());
				p.setString(2, cust.getCustId());
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
				String sql = "delete customer_info where cust_id=?";
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
		public void saveCust(Cust cust) {
			Connection conn = null;
			try {
				conn = DBUtil.getConnection();
				String sql = "insert into customer_info values(?,?,?,?,?,?,sysdate,sysdate,'',?,?,'',?,?)";
				Integer num = 0;
				List<Cust> list = findById(cust.getCustId());
				if(!list.isEmpty()||list.size()!=0) {
					num = list.get(list.size()-1).getConNum();
				}
				PreparedStatement p = conn.prepareStatement(sql);
				p.setString(1, cust.getCustId());
				p.setString(2, cust.getName());
				p.setString(3, cust.getTel());
				p.setString(4, cust.getSex());
				p.setString(5, cust.getKbn());
				p.setInt(6, cust.getConsum());
				p.setString(7, cust.getAppendUserId());
				p.setString(8, cust.getContent());
				p.setString(9, cust.getConsumKbn());
				p.setInt(10, num+1);
				p.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("Save the role failed",e);
			}finally {
				DBUtil.close(conn);
			}
					
			
			
			
		}
}
