package controller;

import java.awt.image.RescaleOp;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/")
public class indexServelet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("/ do get.................");
		RequestDispatcher dis=request.getRequestDispatcher("/home.jsp");
		request.setAttribute("pageName", "/about.jsp");
		dis.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
