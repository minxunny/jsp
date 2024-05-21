package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.QueryVO;
import model.ReviewDAO;
import model.ReviewVO;


@WebServlet(value={"/review/insert","/review/list.json","/review/total","/review/delete","/review/update"})
public class ReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    ReviewDAO dao=new ReviewDAO();
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		switch(request.getServletPath()) { 
		case "/review/list.json": //테스트 /review/list.json?page=1&size=5&gid=2600087029
			QueryVO vo=new QueryVO();
			vo.setPage(Integer.parseInt(request.getParameter("page")));
			vo.setSize(Integer.parseInt(request.getParameter("size")));
			String gid=request.getParameter("gid");
			Gson gson=new Gson();
			out.print(gson.toJson(dao.list(vo, gid)));
			break;
		case "/review/total":
			gid=request.getParameter("gid");
			out.print(dao.total(gid));
			break;
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		switch(request.getServletPath()) {
		case "/review/insert":
			ReviewVO vo=new ReviewVO();
			vo.setGid(request.getParameter("gid"));
			vo.setUid(request.getParameter("uid"));
			vo.setContent(request.getParameter("content"));
			dao.insert(vo);
			break;
		case "/review/delete":
			int rid=Integer.parseInt(request.getParameter("rid"));
			dao.delete(rid);
			break;
			
		case "/review/update":
			vo=new ReviewVO();
			vo.setRid(Integer.parseInt(request.getParameter("rid")));
			vo.setContent(request.getParameter("content"));
			dao.update(vo);
			break;
		}
	}
}
