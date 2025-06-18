package com.javaex.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestbookDAO;
import com.javaex.vo.GuestbookVO;

// deployment assembly에도 꼭 mysql.jar을 넣어줘야함!!!!!!

@WebServlet("/gbc")
public class Controller extends HttpServlet {
	//field
	private static final long serialVersionUID = 1L;

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("GuestbookController ready");
		
		String action = request.getParameter("action");
		System.out.println(action);
		
		if("list".equals(action)) {
			System.out.println("action=list activated");
			GuestbookDAO guestbookdao = new GuestbookDAO();
			List<GuestbookVO> guestList = guestbookdao.guestSelectAll();
			
			System.out.println(guestList);
			
			request.setAttribute("gList", guestList);
			
			RequestDispatcher rd = request.getRequestDispatcher("/list.jsp");
			rd.forward(request, response);
			
			
			System.out.println("action = list already to activated");
		} else if("dform".equals(action)) {
			System.out.println("delete form");
			int no = Integer.parseInt(request.getParameter("no"));
			
			RequestDispatcher rd = request.getRequestDispatcher("/dform.jsp");
			rd.forward(request, response);
		} else if("delete".equals(action)) {
			int no = Integer.parseInt(request.getParameter("no"));
			//비밀먼호 파라미터 꺼내기
			String password = request.getParameter("password");
			
			GuestbookDAO guestbookdao = new GuestbookDAO();
			//비밀번호 체크
			GuestbookVO guestbookvo = guestbookdao.guestSelectOne(no);
			if(guestbookvo != null && guestbookvo.getPassword().equals(password)) {
				//비밀번호가 맞을 때
				guestbookdao.guestDelete(no);
				response.sendRedirect("http://localhost:8080/guestbook2/gbc?action=list");
			} else {
				request.setAttribute("errorMsg", "비밀번호가 틀립니다.");
				RequestDispatcher rd = request.getRequestDispatcher("/dform.jsp?no=" + no);
		        rd.forward(request, response);
			}
			
		} else if("insert".equals(action)) {
			System.out.println("insert");
			//현재 시간 설정
			LocalDateTime regDate = LocalDateTime.now();
			//파라미터 꺼내기
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			String content = request.getParameter("content");
			//데이터 묶기
			GuestbookVO guestbookvo = new GuestbookVO(name, password, content, regDate);
			System.out.println(guestbookvo);
			//DAO를 통해 저장하기
			GuestbookDAO guestbookdao = new GuestbookDAO();
			guestbookdao.guestInsert(guestbookvo);
			//redirect
			response.sendRedirect("http://localhost:8080/guestbook2/gbc?action=list");
		}
		
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
