package model;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class OrderDAO {
	Connection con=Database.CON;
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//주문의 상태변경
	public void update(String pid, int status) {
		try {
			String sql="update purchase set status=? where pid=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setInt(1, status);
			ps.setString(2, pid);
			ps.execute();
		}catch(Exception e) {
			System.out.println("주문상태변경:"+e.toString());
		}
	}
	
	//전체 유저의 주문목록
		public ArrayList<PurchaseVO> list(QueryVO query){
			ArrayList<PurchaseVO> array=new ArrayList<PurchaseVO>();
			try {
				String sql="select * from purchase ";
				sql += " where " + query.getKey()+" like ?";
				sql += " order by pdate desc";
				sql += " limit ?,?";
				PreparedStatement ps=con.prepareStatement(sql);
				ps.setString(1, "%" + query.getWord()+"%");
				ps.setInt(2, (query.getPage()-1)* query.getSize());
				ps.setInt(3, query.getSize());
				ResultSet rs=ps.executeQuery();
				while(rs.next()) {
					PurchaseVO vo=new PurchaseVO();
					vo.setPid(rs.getString("pid"));
					vo.setUid(rs.getString("uid"));
					vo.setPhone(rs.getString("rphone"));
					vo.setAddress1(rs.getString("raddress1"));
					vo.setAddress2(rs.getString("raddress2"));
					vo.setSum(rs.getInt("sum"));
					vo.setStatus(rs.getInt("status"));
					vo.setPdate(sdf.format(rs.getTimestamp("pdate")));
					System.out.println(vo.toString());
					array.add(vo);
				}
				}catch(Exception e) {
				System.out.println("유저의 주문목록:"+e.toString());
			}
			return array;
		}
		
	
	//특정 주문의 상품목록
	public ArrayList<OrderVO> olist(String pid){
		ArrayList<OrderVO> array=new ArrayList<OrderVO>();
		try {
			String sql="select * from view_orders where pid=?";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, pid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				OrderVO vo=new OrderVO();
				vo.setPid(rs.getString("pid"));
				vo.setGid(rs.getString("gid"));
				vo.setPrice(rs.getInt("price"));
				vo.setQnt(rs.getInt("qnt"));
				vo.setTitle(rs.getString("title"));
				vo.setImage(rs.getString("image"));
				array.add(vo);
			}
			}catch(Exception e) {
			System.out.println("주문한 주문목록:"+e.toString());
		}
		return array;
	}
	
	//특정 유저의 주문목록
	public ArrayList<PurchaseVO> list(String uid){
		ArrayList<PurchaseVO> array=new ArrayList<PurchaseVO>();
		try {
			String sql="select * from purchase where uid=? order by pdate desc";
			PreparedStatement ps=con.prepareStatement(sql);
			ps.setString(1, uid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				PurchaseVO vo=new PurchaseVO();
				vo.setPid(rs.getString("pid"));
				vo.setPhone(rs.getString("rphone"));
				vo.setAddress1(rs.getString("raddress1"));
				vo.setAddress2(rs.getString("raddress2"));
				vo.setSum(rs.getInt("sum"));
				vo.setStatus(rs.getInt("status"));
				vo.setPdate(sdf.format(rs.getTimestamp("pdate")));
				array.add(vo);
			}
			}catch(Exception e) {
			System.out.println("유저의 주문목록:"+e.toString());
		}
		return array;
	}
	
	//주문상품 등록하기
	public void insert(OrderVO vo) {
		try {
		String sql="insert into orders(pid,gid,price,qnt) values(?,?,?,?)";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1,vo.getPid());
		ps.setString(2,vo.getGid());
		ps.setInt(3,vo.getPrice());
		ps.setInt(4,vo.getQnt());
		ps.execute();
		}catch(Exception e){
			System.out.println("주문상품 정보 등록");
		}
	}
	
	//주문자 정보 등록하기
	public void insert(PurchaseVO vo) {
		try {
		String sql="insert into purchase(pid,uid,rname,rphone,raddress1,raddress2,sum) values(?,?,?,?,?,?,?)";
		PreparedStatement ps=con.prepareStatement(sql);
		ps.setString(1,vo.getPid());
		ps.setString(2,vo.getUid());
		ps.setString(3,vo.getUname());
		ps.setString(4,vo.getPhone());
		ps.setString(5,vo.getAddress1());
		ps.setString(6,vo.getAddress2());
		ps.setInt(7,vo.getSum());
		ps.execute();
		}catch(Exception e){
			System.out.println("주문자 정보 등록");
		}
	}
}
