package model;
import java.util.*;

public interface CommentDAO {
	//댓글목록출력
	public ArrayList<CommentVO> list(int bid, int page, int size);
	//댓글등록
	public void insert(CommentVO vo);
	//댓글수정
	public void update(CommentVO vo);
	//댓글삭제
	public void delete(int cid);
	//댓글수
	public int total(int bid);
}
