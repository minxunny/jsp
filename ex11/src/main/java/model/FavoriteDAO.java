package model;

import java.sql.*;

public class FavoriteDAO {
	Connection con=Database.CON;
	
	//좋아요 추가
	public void insert(String uid, String gid) {
		try {
			String sql="insert into favorite(uid, gid) values(?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, gid);
			ps.execute();
		}catch(Exception e) {
			System.out.println("좋아요 추가:" + e.toString());
		}
	}
	
	//좋아요 취소
	public void delete(String uid, String gid) {
		try {
			String sql="delete from favorite where uid=? and gid=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, gid);
			ps.execute();
		}catch(Exception e) {
			System.out.println("좋아요 취소:" + e.toString());
		}
	}
}