package model;
import java.util.*;

public interface ProDAO {
	//새로운 교수 번호
	public String getCode();
	
	//교수 목록 출력하는 메소드
	public ArrayList<ProVO> list(QueryVO vo);
	
	//검색결과 숫자
	public int total(QueryVO vo);
	
	//교수 등록하는 메소드
	public void insert(ProVO vo);
	
	//교수 정보 출력하는 메소드
	public ProVO read(String pcode);
	
	//교수 정보 수정하는 메소드
	public void update(ProVO vo);
	
	//교수 정보 삭제하는 메소드
	public int delete(String pcode);
}
