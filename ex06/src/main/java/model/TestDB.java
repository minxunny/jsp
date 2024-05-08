package model;
import java.sql.*;

public class TestDB {

	public static void main(String[] args) {
		
		StuDAOImpl dao=new StuDAOImpl();
		QueryVO vo=new QueryVO();
		vo.setPage(1);
		vo.setSize(2);
		vo.setKey("dept");
		vo.setWord("건축");
		dao.list(vo);
		System.out.println("검색수:"+dao.total(vo));
		System.out.println("새로운학번:"+dao.getCode());
	}

}
