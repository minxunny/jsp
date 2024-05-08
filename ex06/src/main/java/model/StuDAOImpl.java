package model;

import java.sql.*;
import java.util.ArrayList;

public class StuDAOImpl implements StuDAO{
	Connection con=Database.CON;

	@Override
	public ArrayList<StuVO> list(QueryVO vo) {
		ArrayList<StuVO> array=new ArrayList<StuVO>();
		try {
			String sql="select * from view_stu";
			sql+= " where " + vo.getKey() + " like ?";
			sql+= " order by scode desc";
			sql+= " limit ?,?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, "%" + vo.getWord() + "%");
			ps.setInt(2, (vo.getPage()-1)*vo.getSize());
			ps.setInt(3, vo.getSize());
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				StuVO stu=new StuVO();
				stu.setScode(rs.getString("scode"));
				stu.setSname(rs.getString("sname"));
				stu.setSdept(rs.getString("dept"));
				stu.setAdvisor(rs.getString("advisor"));
				stu.setPname(rs.getString("pname"));
				stu.setBirthday(rs.getString("birthday"));
				stu.setYear(rs.getInt("year"));
				System.out.println(stu.toString());
				array.add(stu);
			}
		}catch(Exception e) {
			System.out.println("학생목록:"+e.toString());
		}
		return array;
	}

	@Override
	public int total(QueryVO vo) {
		int total=0;
		try {
			String sql="select count(*) from view_stu";
			sql+= " where " + vo.getKey() + " like ?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, "%" + vo.getWord() + "%");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				total=rs.getInt("count(*)");
			}
		}catch(Exception e) {
			System.out.println("검색수:"+e.toString());
		}
		return total;
	}

	@Override
	public String getCode() {
		String code="";
		try {
			String sql="select max(scode)+1 code from students";
			PreparedStatement ps=con.prepareStatement(sql);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				code=rs.getString("code");
			}
		}catch(Exception e) {
			System.out.println("세로운코드"+e.toString());
		}
		return code;
	}
	
}




