package model;
import java.sql.*;
import java.util.*;

public class UserDAO { //Database Access Object
	Connection con=Database.CON;
	//사용자목록
	public ArrayList<UserVO> list(){
		ArrayList<UserVO> array=new ArrayList<UserVO>();
		try {
			String sql="select * from users order by jdate desc";
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				UserVO vo=new UserVO();
				vo.setUid(rs.getString("uid"));
				vo.setUname(rs.getString("uname"));
				vo.setPhoto(rs.getString("photo"));
				vo.setPhone(rs.getString("phone"));
				vo.setAddress1(rs.getString("address1"));
				vo.setAddress2(rs.getString("address2"));
				vo.setJdate(rs.getTimestamp("jdate"));
				array.add(vo);
				System.out.println(vo.toString());
			}
		}catch(Exception e) {
			System.out.println("사용자목록:"+e.toString());
		}
		return array;
	}
	//회원가입
	public void insert(UserVO vo) {
		try {
			String sql="insert into users(uid,upass,uname) values(?,?,?)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, vo.getUid());
			ps.setString(2, vo.getUpass());
			ps.setString(3, vo.getUname());
			ps.execute();
		}catch(Exception e) {
			System.out.println("회원가입:"+e.toString());
		}
	}
	//사진수정
	public void updatePhoto(String uid, String photo) {
		try {
			String sql="update users set photo=? where uid=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1,photo);
			ps.setString(2, uid);
			ps.execute();
		}catch(Exception e) {
			System.out.println("사진수정:"+e.toString());
		}
	}
	//비밀번호수정
	public void update(String uid, String npass) {
		try {
			String sql="update users set upass=? where uid=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(2, uid);
			ps.setString(1, npass);
			ps.execute();
		}catch(Exception e) {
			System.out.println("비밀번호수정:" + e.toString());
		}
	}
	
	//정보수정
	public void update(UserVO vo) {
		try {
			String sql="update users set udate=now(),uname=?,phone=?,address1=?,address2=? where uid=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, vo.getUname());
			ps.setString(2, vo.getPhone());
			ps.setString(3, vo.getAddress1());
			ps.setString(4, vo.getAddress2());
			ps.setString(5, vo.getUid());
			ps.execute();
		}catch(Exception e) {
			System.out.println("update:" + e.toString());
		}
	}
	
	public UserVO read(String uid) {
		UserVO vo=new UserVO();
		try {
			String sql="select * from users where uid=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, uid);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				vo.setUid(rs.getString("uid"));
				vo.setUpass(rs.getString("upass"));
				vo.setUname(rs.getString("uname"));
				vo.setPhone(rs.getString("phone"));
				vo.setAddress1(rs.getString("address1"));
				vo.setAddress2(rs.getString("address2"));
				vo.setPhoto(rs.getString("photo"));
				vo.setJdate(rs.getTimestamp("jdate"));
				vo.setUdate(rs.getTimestamp("udate"));
			}
		}catch(Exception e) {
			System.out.println("read:" + e.toString());
		}
		return vo;
	}
}