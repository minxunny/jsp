package model;

import java.util.ArrayList;
import java.sql.*;
import java.text.*;

public class CommentDAOImpl implements CommentDAO{
	Connection con=Database.CON;
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	@Override
	public ArrayList<CommentVO> list(int bid, int page, int size) {
		ArrayList<CommentVO> array=new ArrayList<CommentVO>();
		try {
			String sql="select * from view_comments";
			sql+= " where bid=?";
			sql+= " order by cid desc";
			sql+= " limit ?,?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, bid);
			ps.setInt(2, (page-1)*size);
			ps.setInt(3, size);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				CommentVO vo=new CommentVO();
				vo.setCid(rs.getInt("cid"));
				vo.setBid(rs.getInt("bid"));
				vo.setCdate(sdf.format(rs.getTimestamp("cdate")));
				vo.setContents(rs.getString("contents"));
				vo.setWriter(rs.getString("writer"));
				vo.setUname(rs.getString("uname"));
				vo.setPhoto(rs.getString("photo"));
				array.add(vo);
				System.out.println(vo.toString());
			}
		}catch(Exception e) {
			System.out.println("목록:" + e.toString());
		}
		return array;
	}

	@Override
	public void insert(CommentVO vo) {
		try {
			String sql="insert into comments(bid,writer,contents) values(?,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, vo.getBid());
			ps.setString(2, vo.getWriter());
			ps.setString(3, vo.getContents());
			ps.execute();
		}catch(Exception e) {
			System.out.println("입력:" + e.toString());
		}
	}

	@Override
	public void update(CommentVO vo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(int cid) {
		try {
			String sql="delete from comments where cid=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, cid);
			ps.execute();
		}catch(Exception e) {
			System.out.println("삭제:" + e.toString());
		}
		
	}

	@Override
	public int total(int bid) {
		int total=0;
		try {
			String sql="select count(*) total from comments where bid=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, bid);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) total=rs.getInt("total");
		}catch(Exception e) {
			System.out.println("전체갯수:" + e.toString());
		}
		return total;
	}
}