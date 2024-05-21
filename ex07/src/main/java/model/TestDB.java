package model;

public class TestDB {

	public static void main(String[] args) {
		CouDAOImpl dao=new CouDAOImpl();
		QueryVO vo=new QueryVO();
		vo.setKey("lname");
		vo.setWord("리");
		vo.setPage(1);
		vo.setPage(2);
		dao.list(vo);
		System.out.println("검색수:"+dao.total(vo));
	}

}
