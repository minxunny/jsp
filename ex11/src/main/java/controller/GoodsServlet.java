package controller;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import model.NaverAPI;
import model.*;
import model.*;

@WebServlet(value={"/goods/list.json","/goods/search", "/goods/search.json", 
		"/goods/insert", "/goods/list", "/goods/delete", "/goods/total", 
		"/goods/read"})
public class GoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	GoodsDAO dao=new GoodsDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		HttpSession session = request.getSession();
		
		RequestDispatcher dis=request.getRequestDispatcher("/home.jsp");
		switch(request.getServletPath()) {
		case "/goods/read":
			String uid=(String)session.getAttribute("uid");
			request.setAttribute("goods", dao.read(request.getParameter("gid"), uid));
			request.setAttribute("pageName", "/goods/read.jsp");
			dis.forward(request, response);
			break;
		case "/goods/total":
			out.print(dao.total(request.getParameter("word")));
			break;
		case "/goods/list.json": //테스트 /goods/list.json?word=&page=1&size=3
			Gson gson=new Gson();
			QueryVO query=new QueryVO();
			uid=request.getParameter("uid");
			query.setWord(request.getParameter("word"));
			query.setPage(Integer.parseInt(request.getParameter("page")));
			query.setSize(Integer.parseInt(request.getParameter("size")));
			out.print(gson.toJson(dao.list(query, uid)));
			break;
		case "/goods/list":
			request.setAttribute("pageName", "/goods/list.jsp");
			dis.forward(request, response);
			break;
		case "/goods/search":
			request.setAttribute("pageName", "/goods/search.jsp");
			dis.forward(request, response);
			break;
		case "/goods/search.json":
			QueryVO vo=new QueryVO();
			vo.setWord(request.getParameter("query"));
			vo.setPage(Integer.parseInt(request.getParameter("page")));
			vo.setSize(Integer.parseInt(request.getParameter("size")));
			out.print(NaverAPI.main(vo));
			break;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		PrintWriter out=response.getWriter();
		switch(request.getServletPath()) {
		case "/goods/insert":
			GoodsVO vo=new GoodsVO();
			vo.setGid(request.getParameter("gid"));
			vo.setTitle(request.getParameter("title"));
			vo.setPrice(Integer.parseInt(request.getParameter("price")));
			vo.setBrand(request.getParameter("brand"));
			vo.setImage(request.getParameter("image"));
			out.print(dao.insert(vo));
			break;
		case "/goods/delete":
			String gid=request.getParameter("gid");
			out.print(dao.delete(gid));
			break;
		}
	}
}