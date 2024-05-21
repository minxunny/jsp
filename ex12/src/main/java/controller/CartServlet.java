package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.*;

@WebServlet(value={"/cart/insert", "/cart/list.json", "/goods/cart", "/cart/update", "/cart/delete","/favorite/insert","/favorite/delete"
		,"/purchase/insert","/orders/insert","/order/list","/purchase/list.json","/order/list.json"})
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    CartDAO dao=new CartDAO();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		RequestDispatcher dis=request.getRequestDispatcher("/home.jsp");
		Gson gson=new Gson();
		OrderDAO odao=new OrderDAO();
		
		switch(request.getServletPath()) {
		case "/order/list.json": //테스트 /order/list.json?pid=cdfbcd70-2e58
			out.print(gson.toJson(odao.olist(request.getParameter("pid"))));
			break;
		case "/cart/list.json": //테스트 /cart/list.json?uid=red
			out.print(gson.toJson(dao.list(request.getParameter("uid"))));
			break;
		case "/goods/cart":
			request.setAttribute("pageName", "/goods/cart.jsp");
			dis.forward(request, response);
			break;
		case "/order/list":
			request.setAttribute("pageName", "/user/order.jsp");
			dis.forward(request, response);
			break;
		case "/purchase/list.json": //테스트 /purchase/list.json?uid=black
			out.print(gson.toJson(odao.list(request.getParameter("uid"))));
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		FavoriteDAO fdao=new FavoriteDAO();
		OrderDAO odao=new OrderDAO();
		switch(request.getServletPath()){
		case "/cart/insert":
			CartVO vo=new CartVO();
			vo.setUid(request.getParameter("uid"));
			vo.setGid(request.getParameter("gid"));
			out.print(dao.insert(vo));
			break;
		case "/cart/update":
			vo=new CartVO();
			vo.setUid(request.getParameter("uid"));
			vo.setGid(request.getParameter("gid"));
			vo.setQnt(Integer.parseInt(request.getParameter("qnt")));
			dao.update(vo);
			break;
		case "/cart/delete":
			vo=new CartVO();
			vo.setUid(request.getParameter("uid"));
			vo.setGid(request.getParameter("gid"));
			dao.delete(vo);
			break;
		case "/favorite/insert":
			String uid=request.getParameter("uid");
			String gid=request.getParameter("gid");
			fdao.insert(uid,gid);
			break;
		case "/favorite/delete":
			uid=request.getParameter("uid");
			gid=request.getParameter("gid");
			fdao.delete(uid,gid);
			break;
		case "/purchase/insert": //주문자정보등록
			UUID uuid=UUID.randomUUID();
			String pid=uuid.toString().substring(0,13);
			PurchaseVO pvo=new PurchaseVO();
			pvo.setPid(pid);
			pvo.setUid(request.getParameter("uid"));
			pvo.setUname(request.getParameter("rname"));
			pvo.setPhone(request.getParameter("rphone"));
			pvo.setAddress1(request.getParameter("raddress1"));
			pvo.setAddress2(request.getParameter("raddress2"));
			pvo.setSum(Integer.parseInt(request.getParameter("sum")));
			System.out.println(pvo.toString());
			odao.insert(pvo);
			out.print(pid);
			break;
		case "/orders/insert": //주문상품등록
			OrderVO ovo=new OrderVO();
			ovo.setPid(request.getParameter("pid"));
			ovo.setGid(request.getParameter("gid"));
			ovo.setPrice(Integer.parseInt(request.getParameter("price")));
			ovo.setQnt(Integer.parseInt(request.getParameter("qnt")));
			System.out.println(ovo.toString());
			dao.insert(ovo);
			break;
		}
	}
}



