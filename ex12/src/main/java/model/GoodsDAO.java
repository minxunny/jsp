package model;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class GoodsDAO {
	Connection con=Database.CON;
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy년MM월dd일 HH:mm:ss");
	
	//상품목록(좋아요)
	public ArrayList<GoodsVO> list(QueryVO query, String uid){
		ArrayList<GoodsVO> array=new ArrayList<GoodsVO>();
		try {
			String sql="select *,";
			sql +="(select count(*) from favorite f where uid=? and f.gid=g.gid) ucnt,";
			sql +="(select count(*) from favorite f where f.gid=g.gid) fcnt,";
			sql +="(select count(*) from review r where r.gid=g.gid) rcnt";
			sql +=" from goods g";
			sql +=" where title like ?";
			sql +=" order by regdate desc";
			sql +=" limit ?, ?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, "%" + query.getWord() + "%");
			ps.setInt(3, (query.getPage()-1) * query.getSize());
			ps.setInt(4, query.getSize());
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				GoodsVO vo=new GoodsVO();
				vo.setGid(rs.getString("gid"));
				vo.setTitle(rs.getString("title"));
				vo.setImage(rs.getString("image"));
				vo.setPrice(rs.getInt("price"));
				vo.setBrand(rs.getString("brand"));
				vo.setRegDate(rs.getString("regDate"));
				vo.setUcnt(rs.getInt("ucnt"));
				vo.setFcnt(rs.getInt("fcnt"));
				vo.setRcnt(rs.getInt("rcnt"));
				array.add(vo);
				System.out.println(vo.toString());
			}
		}catch(Exception e){
			System.out.println("상품목록:" + e.toString());
		}
		return array;
	}
	
	//상품정보
	public GoodsVO read(String gid, String uid) {
		GoodsVO vo=new GoodsVO();
		try {
			String sql="select *,";
			sql +="(select count(*) from favorite f where uid=? and f.gid=g.gid) ucnt,";
			sql +="(select count(*) from favorite f where f.gid=g.gid) fcnt";
			sql +=" from goods g";
			sql +=" where g.gid=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, gid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				vo.setGid(rs.getString("gid"));
				vo.setTitle(rs.getString("title"));
				vo.setImage(rs.getString("image"));
				vo.setPrice(rs.getInt("price"));
				vo.setBrand(rs.getString("brand"));
				vo.setRegDate(sdf.format(rs.getTimestamp("regDate")));
				vo.setFcnt(rs.getInt("fcnt"));
				vo.setUcnt(rs.getInt("ucnt"));
				System.out.println(vo.toString());
			}
		}catch(Exception e){
			System.out.println("상품정보:" + e.toString());
		}
		return vo;
	}
	
	//검색수
	public int total(String word) {
		int total=0;
		try {
			String sql="select count(*) from goods";
			sql +=" where title like ?";			
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, "%" + word + "%");
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				total=rs.getInt("count(*)");
			}
		}catch(Exception e){
			System.out.println("검색수:" + e.toString());
		}
		return total;
	}
	
	//상품삭제
	public boolean delete(String gid) {
		try {
			String sql="delete from goods";
			sql += " where gid=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, gid);
			ps.execute();
			return true;
		}catch(Exception e) {
			System.out.println("상품삭제:" + e.toString());
			return false;
		}
	}
	
	//상품목록
	public ArrayList<GoodsVO> list(QueryVO query){
		ArrayList<GoodsVO> array=new ArrayList<GoodsVO>();
		try {
			String sql="select * from goods";
			sql +=" where title like ?";
			sql +=" order by regdate desc";
			sql +=" limit ?, ?";
			
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, "%" + query.getWord() + "%");
			ps.setInt(2, (query.getPage()-1) * query.getSize());
			ps.setInt(3, query.getSize());
			
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				GoodsVO vo=new GoodsVO();
				vo.setGid(rs.getString("gid"));
				vo.setTitle(rs.getString("title"));
				vo.setImage(rs.getString("image"));
				vo.setPrice(rs.getInt("price"));
				vo.setBrand(rs.getString("brand"));
				vo.setRegDate(rs.getString("regDate"));
				array.add(vo);
			}
		}catch(Exception e){
			System.out.println("상품목록:" + e.toString());
		}
		return array;
	}
	
	//상품등록
	public boolean insert(GoodsVO vo) {
		try {
			String sql="insert into goods(gid, title, price, brand, image)";
			sql += " values(?,?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, vo.getGid());
			ps.setString(2, vo.getTitle());
			ps.setInt(3, vo.getPrice());
			ps.setString(4, vo.getBrand());
			ps.setString(5, vo.getImage());
			ps.execute();
			return true;
		}catch(Exception e) {
			System.out.println("상품등록:" + e.toString());
			return false;
		}
	}
}