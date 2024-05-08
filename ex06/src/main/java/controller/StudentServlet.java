package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.QueryVO;
import model.StuDAOImpl;


@WebServlet(value={"/stu/list","/stu/list.json","/stu/total","/stu/insert"})
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StuDAOImpl dao=new StuDAOImpl();
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		RequestDispatcher dis=request.getRequestDispatcher("/home.jsp");
				
		switch(request.getServletPath()) {
		
		case "/stu/insert":
			request.setAttribute("pageName", "/stu/insert.jsp");
			dis.forward(request,response);
			break;
			case "/stu/total": //테스트 /stu/total?key=dept&word=전산
			QueryVO vo1=new QueryVO();
			vo1.setKey(request.getParameter("key"));
			vo1.setWord(request.getParameter("word"));
			out.print(dao.total(vo1));
			break;
		case "/stu/list.json": //테스트 /stu/list.json?page=1&size=2&key=dept&word=전산
			QueryVO vo=new QueryVO();
			vo.setPage(Integer.parseInt(request.getParameter("page")));
			vo.setSize(Integer.parseInt(request.getParameter("size")));
			vo.setKey(request.getParameter("key"));
			vo.setWord(request.getParameter("word"));
			Gson gson=new Gson();
			out.print(gson.toJson(dao.list(vo)));
			break;
			
		case "/stu/list":
			request.setAttribute("pageName", "/stu/list.jsp");
			dis.forward(request, response);
			break;
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
