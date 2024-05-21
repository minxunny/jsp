package model;

public class DBTest {
	public static void main(String[] arg) {
		OrderDAO dao=new OrderDAO();
		QueryVO vo=new QueryVO();
		vo.setKey("uid");
		vo.setWord("black");
		vo.setPage(1);
		vo.setSize(3);
		dao.list(vo);
		
		
	}
}
