package model;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReviewDAO {
	Connection con=Database.CON;
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	//리뷰삭제
		public void update(ReviewVO vo) {
			try {
				String sql="update review set content=? where rid=?";
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setString(1, vo.getContent());
				ps.setInt(2, vo.getRid());
				ps.execute();
			}catch(Exception e) {
				System.out.println("리뷰수정:"+e.toString());
			}
		}
		
		
	//리뷰삭제
	public void delete(int rid) {
		try {
			String sql="delete from review where rid=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, rid);
			ps.execute();
		}catch(Exception e) {
			System.out.println("리뷰삭제:"+e.toString());
		}
	}
	//전체리뷰수
	public int total(String gid) {
		int total=0;
		try {
			String sql="select count(*) from review where gid=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, gid);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) total=rs.getInt("count(*)");
		}catch(Exception e) {
			System.out.println("전체리뷰수:"+e.toString());
		}
		
		return total;
	}
	
	//리뷰목록
	public ArrayList<ReviewVO> list(QueryVO vo, String gid){
		ArrayList<ReviewVO> array=new ArrayList<ReviewVO>();
		try {
			String sql="select * from review where gid=?";
			sql += " order by rid desc";
			sql += " limit ?,?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, gid);
			ps.setInt(2, (vo.getPage()-1) * vo.getSize());
			ps.setInt(3, vo.getSize());
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				ReviewVO rvo=new ReviewVO();
				rvo.setRid(rs.getInt("rid"));
				rvo.setGid(rs.getString("gid"));
				rvo.setUid(rs.getString("uid"));
				rvo.setContent(rs.getString("content"));
				rvo.setRevDate(sdf.format(rs.getTimestamp("revDate")));
				array.add(rvo);
				System.out.println(rvo.toString());
			}
			
			}catch(Exception e) {
			System.out.println("리뷰목록:"+e.toString());
		}
		return array;
	}
	
	
	//리뷰등록
	public void insert(ReviewVO vo) {
		try {
			String sql="insert into review(gid, uid, content) values(?,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,vo.getGid());
			ps.setString(2, vo.getUid());
			ps.setString(3, vo.getContent());
			ps.execute();
			}catch(Exception e) {
			System.out.println("리뷰등록:"+e.toString());
		}
	}
}
