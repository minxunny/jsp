package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model.*;

@WebServlet(value={"/user/login", "/user/logout", "/user/mypage", "/user/update", "/user/update/pass", "/user/upload", "/user/join" ,"/user/list","/user/list.json"})
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    UserDAO dao=new UserDAO();
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		RequestDispatcher dis=request.getRequestDispatcher("/home.jsp");
		HttpSession session=request.getSession();
		PrintWriter out=response.getWriter();
		
		switch(request.getServletPath()) {
		case "/user/list.json":
			Gson gson=new Gson();
			out.print(gson.toJson(dao.list()));
			break;
		case "/user/list":
			request.setAttribute("pageName", "/user/list.jsp");
			dis.forward(request,response);
			break;
		case "/user/join":
			request.setAttribute("pageName", "/user/join.jsp");
			dis.forward(request, response);
			break;
		case "/user/mypage":
			String uid=(String)session.getAttribute("uid");
			request.setAttribute("user",dao.read(uid));
			request.setAttribute("pageName", "/user/mypage.jsp");
			dis.forward(request, response);
			break;
		case "/user/logout":
			session.invalidate();
			response.sendRedirect("/");
			break;
		case "/user/login":
			request.setAttribute("pageName", "/user/login.jsp");
			dis.forward(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		switch(request.getServletPath()) {
		case "/user/join":
			UserVO user=new UserVO();
			user.setUid(request.getParameter("uid"));
			user.setUpass(request.getParameter("upass"));
			user.setUname(request.getParameter("uname"));
			System.out.println(user.toString());
			dao.insert(user);
			break;
		case "/user/upload":
			String path="/upload/photo/";
			MultipartRequest multi=new MultipartRequest(
					request,
					"c:" + path,
					1024*1024*10,
					new DefaultFileRenamePolicy());
			String fileName=multi.getFilesystemName("photo");
			String uid2=multi.getParameter("uid");
			System.out.println("fileName:"+fileName+"\nuid:"+uid2);
			String photo=path+fileName;
			dao.updatePhoto(uid2, photo);
			break;
		case "/user/update/pass":
			String uid1=request.getParameter("uid");
			String npass=request.getParameter("npass");
			dao.update(uid1, npass); //비밀번호변경
			break;
		case "/user/update":
			UserVO vo=new UserVO();
			String uid=request.getParameter("uid");
			vo.setUid(uid);
			vo.setUname(request.getParameter("uname"));
			vo.setPhone(request.getParameter("phone"));
			vo.setAddress1(request.getParameter("address1"));
			vo.setAddress2(request.getParameter("address2"));
			System.out.print(vo.toString());
			dao.update(vo); //데이터베이스에 업데이트
			break;
		case "/user/login":
			uid=request.getParameter("uid");
			String upass=request.getParameter("upass");
			System.out.println(uid + ".........." + upass);
			
			int result=0;
			vo=dao.read(uid);
			if(vo.getUid() != null) {
				if(vo.getUpass().equals(upass)) {
					HttpSession session=request.getSession();
					session.setAttribute("user", vo);
					session.setAttribute("uid", uid);
					result=1;
				}else {
					result=2;
				}
			}
			out.print(result);
			break;
		}
	}
}