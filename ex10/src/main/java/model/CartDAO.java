package model;
import java.sql.*;
import java.util.*;

public class CartDAO {
	Connection con=Database.CON;
	//장바구니목록
	public ArrayList<CartVO> list(String uid){
		ArrayList<CartVO> array=new ArrayList<CartVO>();
		try {
			String sql="select * from view_cart where uid=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, uid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				CartVO vo=new CartVO();
				vo.setGid(rs.getString("gid"));
				vo.setUid(rs.getString("uid"));
				vo.setTitle(rs.getString("title"));
				vo.setPrice(rs.getInt("price"));
				vo.setQnt(rs.getInt("qnt"));
				vo.setImage(rs.getString("image"));
				System.out.println(vo.toString());
				array.add(vo);
			}
		}catch(Exception e) {
			System.out.println("장바구니목록:" + e.toString());
		}
		return array;
	}
	
	//장바구니에 넣기
	public boolean insert(CartVO vo) {
		try {
			String sql="insert into cart(uid, gid, qnt) values(?, ?, 1)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, vo.getUid());
			ps.setString(2, vo.getGid());
			ps.execute();
			return true;
		}catch(Exception e) {
			System.out.println("장바구니에 넣기:" + e.toString());
			return false;
		}
	}
}