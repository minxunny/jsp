package model;
import java.util.*;

public interface BBSDAO {
	public ArrayList<BBSVO> list(); //여러개 목록 출력
	public void insert(BBSVO vo); //입력
	public BBSVO read(int bid); //읽기
	public void update(BBSVO vo); //수정
	public void delete(int bid); //삭제
	public ArrayList<BBSVO> list(int page, int size); //페이징 목록
	//데이터갯수구하기
	public int total();
	//검색데이터갯수구하기
	public int total(String query);
	//페이징, 검색
	public ArrayList<BBSVO> list(int page, int size, String query);

}
