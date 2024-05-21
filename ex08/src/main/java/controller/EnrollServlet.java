package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import model.*;

@WebServlet(value={"/enroll/update","/enroll/list.json", "/enroll/slist.json", "/enroll/insert", "/enroll/delete"})
public class EnrollServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    EnrollDAO dao=new EnrollDAO();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		Gson gson=new Gson();
		
		switch(request.getServletPath()) {
		case "/enroll/list.json": //테스트 /enroll/list.json?scode=92414029	
			out.print(gson.toJson(dao.list(request.getParameter("scode"))));
			break;
		case "/enroll/slist.json": //테스트 /enroll/slist.json?lcode=C301	
			out.print(gson.toJson(dao.slist(request.getParameter("lcode"))));
			break;
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		switch(request.getServletPath()) {
		case "/enroll/insert":
			String scode=request.getParameter("scode");
			String lcode=request.getParameter("lcode");
			out.print(dao.insert(scode, lcode));
			break;
		case "/enroll/delete":
			scode=request.getParameter("scode");
			lcode=request.getParameter("lcode");
			out.print(dao.delete(scode, lcode));
			break;
		case "/enroll/update":
			scode=request.getParameter("scode");
			lcode=request.getParameter("lcode");
			int grade=Integer.parseInt(request.getParameter("grade"));
			dao.update(lcode, scode, grade);
			break;
		}
	}
}