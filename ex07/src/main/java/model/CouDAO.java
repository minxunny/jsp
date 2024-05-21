package model;

import java.util.*;

public interface CouDAO {
	//강좌등록
	public void insert(CouVO vo);
	
	//새로운 강좌 코드
	public String getCode();
	
	//강좌목록출력
	public ArrayList<CouVO> list(QueryVO vo);
	
	//검색수
	public int total(QueryVO vo);
}
