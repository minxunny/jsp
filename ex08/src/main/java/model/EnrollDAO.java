package model;
import java.util.*;
import java.sql.*;

public class EnrollDAO {
	Connection con=Database.CON;
	//점수수정
	public void update(String lcode, String scode, int grade) {
		try {
			String sql="update enrollments set grade=?";
			sql += " where lcode=? and scode=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1,  grade);
			ps.setString(2, lcode);
			ps.setString(3, scode);
			ps.execute();
		}catch(Exception e){
			System.out.println("점수수정:" + e.toString());
		}
	}
	
	//수강취소
	public boolean delete(String scode, String lcode) {
		try {
			String sql="delete from enrollments where scode=? and lcode=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, scode);
			ps.setString(2, lcode);
			ps.execute();
			
			sql="update courses set persons=persons-1 where lcode=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, lcode);
			ps.execute();
			return true;
		}catch(Exception e){
			System.out.println("수강취소:" + e.toString());
			return false;
		}
	}
	
	//수강신청
	public boolean insert(String scode, String lcode) {
		try {
			String sql="insert into enrollments(scode, lcode, edate, grade)";
			sql += " values(?, ?, now(), 0)";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, scode);
			ps.setString(2, lcode);
			ps.execute();
			
			sql="update courses set persons=persons+1 where lcode=?";
			ps=con.prepareStatement(sql);
			ps.setString(1, lcode);
			ps.execute();
			return true;
		}catch(Exception e){
			System.out.println("수강신청:" + e.toString());
			return false;
		}
	}
	//특정강좌의 수강신청목록
	public ArrayList<GradeVO> slist(String lcode){
		ArrayList<GradeVO> array=new ArrayList<GradeVO>();
		try {
			String sql="select * from view_enr_stu where lcode=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, lcode);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				GradeVO vo=new GradeVO();
				vo.setScode(rs.getString("scode"));
				vo.setSname(rs.getString("sname"));
				vo.setSdept(rs.getString("dept"));
				vo.setYear(rs.getInt("year"));
				vo.setEdate(rs.getString("edate"));
				vo.setGrade(rs.getInt("grade"));
				array.add(vo);
				System.out.println(vo.toString());
			}
		}catch(Exception e) {
			System.out.println("특정강좌의 수강신청목록:" + e.toString());
		}
		return array;
	}
	
	//특정학생의 수강신청목록
	public ArrayList<EnrollVO> list(String scode){
		ArrayList<EnrollVO> array=new ArrayList<EnrollVO>();
		try {
			String sql="select * from view_enr_cou where scode=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, scode);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				EnrollVO vo=new EnrollVO();
				vo.setScode(rs.getString("scode"));
				vo.setLcode(rs.getString("lcode"));
				vo.setLname(rs.getString("lname"));
				vo.setHours(rs.getInt("hours"));
				vo.setRoom(rs.getString("room"));
				vo.setPcode(rs.getString("instructor"));
				vo.setPersons(rs.getInt("persons"));
				vo.setCapacity(rs.getInt("capacity"));
				vo.setEdate(rs.getString("edate"));
				vo.setGrade(rs.getInt("grade"));
				vo.setPname(rs.getString("pname"));
				System.out.println(vo.toString());
				array.add(vo);
			}
		}catch(Exception e) {
			System.out.println("수강신청목록:" + e.toString());
		}
		return array;
	}
}