package controller;

import java.io.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.*;


@WebServlet(value= {"/user/login", "/user/mypage", "/user/join"})
public class UserServlet extends HttpServlet {
   private static final long serialVersionUID = 1L;
   UserDAO dao = new UserDAO();
   UserVO vo = new UserVO();

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      RequestDispatcher dis = request.getRequestDispatcher("/home.jsp");
      switch(request.getServletPath()) {
      case "/user/logout" :
    	 HttpSession session=request.getSession();
    	 session.invalidate();
    	 response.sendRedirect("/");
    	 break;
      case "/user/login" :
         request.setAttribute("pageName", "/user/login.jsp");
         dis.forward(request, response);
         System.out.println("로그인");
         break;
      case "/user/mypage" :
         break;
      case "/user/join" :
         break;
         
      }
   
   }

   
   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      PrintWriter out = response.getWriter();
      
      switch(request.getServletPath()) {
      case "/user/login" :
         String uid = request.getParameter("uid");
         String upass = request.getParameter("upass");
         System.out.println(uid + ":" + upass);

         int result = 0;
         UserVO vo = dao.read(uid);
         if(vo.getUid() != null) {
            if(upass.equals(vo.getUpass())) { //성공한경우
            	HttpSession session=request.getSession();
            	session.setAttribute("uid", uid);
               result = 1;
            }
            else {
               result = 2;
            }
         }
         out.print(result);
         break;
      }
   }
   
}