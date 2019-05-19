package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import entity.Cost;
import util.DBUtil;

public class CostDao implements Serializable {

	
	
	
	//分页查询
	public List<Cost> findByPage(int page,int size) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from (select c.*,rownum r from (select * from cost_xf order by cost_id) c ) where r between ? and ?";
			PreparedStatement p = conn.prepareStatement(sql);
			p.setInt(1, (page-1)*size+1);
			p.setInt(2, size*page);
			ResultSet rs = p.executeQuery();
			List<Cost> list = new ArrayList<Cost>();
			while (rs.next()) {
				Cost c = createCost(rs);
				list.add(c);
			}
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Not found th page",e);
		}finally {
			DBUtil.close(conn);
		}
	}
	
	//查询总行数
	public int findRows() {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select count(*) from cost_xf";
			PreparedStatement p = conn.prepareStatement(sql);
			ResultSet rs = p.executeQuery();
			if(rs.next()) {
				return  rs.getInt(1);
			}
			return 0;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Found rows failed",e);
		}finally {
			DBUtil.close(conn);
		}
	}
	
	
	
	//删除资费
	public void delete(int id) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "delete cost_xf where cost_id=?";
			PreparedStatement p = conn.prepareStatement(sql);
			p.setInt(1, id);
			p.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Delete the cost failed",e);
		}finally {
			DBUtil.close(conn);
		}
	}
	
	
	//修改资费
	public void update(Cost c) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			//update cost_xf set name=?,base_duration=?,base_cost=?,unit_cost=?,cost_type=?,descr=? where cost_id=?"
			String sql = "update cost_xf set name=?,base_duration=?,base_cost=?,unit_cost=?,descr=?,cost_type=? where cost_id=?";
			PreparedStatement p = conn.prepareStatement(sql);
			setCost(c, p);
			p.setInt(7, c.getCostId());
			p.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Update the cost failed",e);
		}finally {
			DBUtil.close(conn);
		}
		
	}
	
	//根据ID查询
	public Cost findById(int id) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from cost_xf where cost_id=?";
			PreparedStatement p = conn.prepareStatement(sql);
			p.setInt(1, id);
			ResultSet rs = p.executeQuery();
			while (rs.next()) {
				return createCost(rs);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Not found the id",e);
		}finally {
			DBUtil.close(conn);
		}
		return null;
		
	}
	
	//查询所有
	public List<Cost> findAll(){
		
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql = "select * from cost_xf order by cost_id";
			Statement smt = conn.createStatement();
			ResultSet rs = smt.executeQuery(sql);
			List<Cost> list = new ArrayList<Cost>();
			while(rs.next()){
				Cost c = createCost(rs);
				list.add(c);
			}
		
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("查询资费失败",e);
		}finally {
			DBUtil.close(conn);
		}
		
		
		
		
	}
	//复用方法
	private Cost createCost(ResultSet rs) throws SQLException {
		Cost c = new Cost();
		c.setCostId(rs.getInt("cost_id"));
		c.setName(rs.getString("name"));
		c.setBaseDuration(rs.getInt("base_duration"));
		c.setBaseCost(rs.getDouble("base_cost"));
		c.setUnitCost(rs.getDouble("unit_cost"));
		c.setStatus(rs.getString("status"));
		c.setDescr(rs.getString("descr"));
		c.setCreatime(rs.getTimestamp("creatime"));
		c.setStartime(rs.getTimestamp("startime"));
		c.setCostType(rs.getString("cost_type"));
		return c;
	}
//		cost_id			number(4) primary key,
//  	name 			varchar(50)  not null,
//  	base_duration 	number(11),
//  	base_cost 		number(7,2),
//  	unit_cost 		number(7,4),
//  	status 			char(1),
//  	descr 			varchar2(100),
//  	creatime 		date default sysdate ,
//  	startime 		date,
//		cost_type		char(1)		
	//增加
	public void save(Cost c){
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			String sql ="insert into cost_xf values(costs_seq_xf.nextval,?,?,?,?,'1',?,sysdate,null,? )";
			PreparedStatement p = conn.prepareStatement(sql);
			setCost(c, p);
			p.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Add cost failed",e);
		}finally {
			DBUtil.close(conn);
		}
		
		
		
	}
	//为sql添加6个参数
	private void setCost(Cost c, PreparedStatement p) throws SQLException {
		p.setString(1, c.getName());
		System.out.println(c.getBaseDuration());
		p.setObject(2, c.getBaseDuration());
		p.setObject(3, c.getBaseCost());
		p.setObject(4, c.getUnitCost());
		p.setString(5, c.getDescr());
		p.setString(6, c.getCostType());
	}
	
	
	public static void main(String[] args) {
		CostDao d = new CostDao();
//		List<Cost> list = d.findByPage(2, 4);
//		for (Cost cost : list) {
//			System.out.println(cost.toString());
//		}	
		System.out.println(d.findById(2));
	}
	
}

