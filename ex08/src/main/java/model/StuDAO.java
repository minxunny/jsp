package model;

import java.util.*;

public interface StuDAO {
	//학생수정
	public void update(StuVO vo);
	
	//학생삭제
	public boolean delete(String scode);
	
	//학생목록
	public ArrayList<StuVO> list(QueryVO vo);
	
	//검색수
	public int total(QueryVO vo);
	
	//새로운 학번 구하기
	public String getCode();
	
	//학생등록
	public void insert(StuVO vo);
	
	//학생정보
	public StuVO read(String scode);
}
