package model;

public class DBtest {
	public static void main(String[] args) {
		//BBSDAOImpl dao=new BBSDAOImpl();
		//dao.list();
		//dao.read(1);
		//System.out.println("갯수....."+dao.total());
		//dao.list(1,3,"리액트");
		//System.out.println("리액트갯수:"+dao.total("red"));
		
		CommentDAOImpl dao=new CommentDAOImpl();
		//dao.list(1011,1,5);
		//System.out.println("1011의 댓글수"+dao.total(1011));
		CommentVO vo=new CommentVO();
		vo.setBid(1011);
		vo.setWriter("brown");
		vo.setContents("새로운 댓글입니다~");
		dao.insert(vo);
		
	}

}
