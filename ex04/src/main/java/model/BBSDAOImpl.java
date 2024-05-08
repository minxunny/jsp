package model;

import java.sql.*;
import java.text.*;
import java.util.*;

public class BBSDAOImpl implements BBSDAO{
	Connection con=Database.CON;
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy년MM월dd일 HH:mm:ss");

	@Override
	public ArrayList<BBSVO> list() {
		ArrayList<BBSVO> array=new ArrayList<BBSVO>();
		try {
			String sql="select * from view_bbs order by bid desc";
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				BBSVO vo=new BBSVO();
				vo.setBid(rs.getInt("bid"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				vo.setBdate(sdf.format(rs.getTimestamp("bdate")));
				vo.setUname(rs.getString("uname"));
				vo.setPhoto(rs.getString("photo"));
				vo.setContents(rs.getString("contents"));
				array.add(vo);
				//System.out.println(vo.toString());
			}
		}catch(Exception e) {
			System.out.println("게시판목록:"+e.toString());
		}
		return array;
	}

	@Override
	public void insert(BBSVO vo) {
		try {
			String sql="insert into bbs(title,contents,writer) values(?,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getContents());
			ps.setString(3, vo.getWriter());
			ps.execute();
		}catch(Exception e) {
			System.out.println("등록:"+e.toString());
		}
		
	}

	@Override
	public BBSVO read(int bid) {
		BBSVO vo=new BBSVO();
		try {
			String sql="select * from view_bbs where bid=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, bid);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				vo.setBid(rs.getInt("bid"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				vo.setBdate(sdf.format(rs.getTimestamp("bdate")));
				vo.setUname(rs.getString("uname"));
				vo.setPhoto(rs.getString("photo"));
				vo.setContents(rs.getString("contents"));
				System.out.println(vo.toString());
			}
		}catch(Exception e) {
			System.out.println("게시글정보:"+e.toString());
		}
		return vo;
	}

	@Override
	public void update(BBSVO vo) {
		try {
			String sql="update bbs set title=?, contents=?, bdate=now() where bid=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, vo.getTitle());
			ps.setString(2, vo.getContents());
			ps.setInt(3, vo.getBid());
			ps.execute();
		}catch(Exception e) {
			System.out.println("수정:"+e.toString());
		}		
	}

	@Override
	public void delete(int bid) {
		try {
			String sql="delete from bbs where bid=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, bid);
			ps.execute();
		}catch(Exception e) {
			System.out.println("삭제:"+e.toString());
		}
		
	}

	@Override
	public ArrayList<BBSVO> list(int page, int size) {
		ArrayList<BBSVO> array=new ArrayList<BBSVO>();
		try {
			String sql="select * from view_bbs order by bid desc";
			sql+=" limit ?, ?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, (page-1)*size);
			ps.setInt(2, size);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				BBSVO vo=new BBSVO();
				vo.setBid(rs.getInt("bid"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				vo.setBdate(sdf.format(rs.getTimestamp("bdate")));
				vo.setUname(rs.getString("uname"));
				vo.setPhoto(rs.getString("photo"));
				vo.setContents(rs.getString("contents"));
				array.add(vo);
				//System.out.println(vo.toString());
			}
		}catch(Exception e) {
			System.out.println("게시판목록:"+e.toString());
		}
		return array;
	}

	@Override
	public int total() {
		int total=0;
		try {
			String sql="select count(*) total from bbs";
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) total=rs.getInt("total");
		}catch(Exception e) {
			System.out.println("전체갯수:"+e.toString());
		}
		return total;
	}

	@Override
	public ArrayList<BBSVO> list(int page, int size, String query) {
		ArrayList<BBSVO> array=new ArrayList<BBSVO>();
		query = "%" + query + "%";
		try {
			String sql="select * from view_bbs";
			sql+=" where title like ? or contents like ? or writer like ?";
			sql+=" order by bid desc";
			sql+=" limit ?, ?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,query);
			ps.setString(2, query);
			ps.setString(3, query);
			ps.setInt(4, (page-1)*size);
			ps.setInt(5, size);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				BBSVO vo=new BBSVO();
				vo.setBid(rs.getInt("bid"));
				vo.setTitle(rs.getString("title"));
				vo.setWriter(rs.getString("writer"));
				vo.setBdate(sdf.format(rs.getTimestamp("bdate")));
				vo.setUname(rs.getString("uname"));
				vo.setPhoto(rs.getString("photo"));
				vo.setContents(rs.getString("contents"));
				array.add(vo);
				System.out.println(vo.toString());
			}
		}catch(Exception e) {
			System.out.println("게시판목록:"+e.toString());
		}
		return array;
	}

	@Override
	public int total(String query) {
		int total=0;
		query = "%" + query + "%"; //부분일치하는 검색결과 나오게
		try {
			String sql="select count(*) total from bbs";
			sql+=" where title like ? or contents like ? or writer like ?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, query);
			ps.setString(2, query);
			ps.setString(3, query);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) total=rs.getInt("total");
		}catch(Exception e) {
			System.out.println("전체갯수:"+e.toString());
		}
		return total;
	}

}
