<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.javaex.vo.GuestbookVO" %>

<%
@SuppressWarnings("unchecked")
List<GuestbookVO> guestList = (List<GuestbookVO>)request.getAttribute("gList");
System.out.println("list.jsp opened");
System.out.println(guestList);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="http://localhost:8080/guestbook2/gbc?" method="get">
		<input type="hidden" name="action" value="insert">
		<table border="1" width="540px">
			<tr>
				<td>이름</td>
				<td><input type="text" name="name" value=""></td>

				<td>비밀번호</td>
				<td><input type="password" name="password" value=""></td>
			</tr>
			<tr>
				<td colspan="4">
				<textarea name="content" cols="72" rows="5">
				
				
				</textarea>
				</td>
			</tr>
			<tr>
				<td colspan="4">
					<button>등록</button>
				</td>
			</tr>
		</table>
	</form>
	<br>
	
	<%
	for(int i = 0 ; i < guestList.size() ; i++) {
		GuestbookVO guestbookvo = guestList.get(i);
	%>
	
	<table border="1" width="540px">
		<tr>
			<td>[<%= guestList.get(i).getNo() %>]</td>
			<td><%= guestList.get(i).getName() %></td>
			<td><%= guestList.get(i).getRegDate() %>1</td>
			<td><a href="http://localhost:8080/guestbook2/gbc?action=dform&no=<%= guestbookvo.getNo() %>">삭제</a></td>
		</tr>
		<tr>
			<td colspan="4"><%= guestList.get(i).getContent() %></td>
		</tr>
	</table>
	<br>
	<%} %>
</body>
</html>