<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.javaex.vo.GuestbookVO"%>
<%
System.out.println("dform ready to go");
int no = Integer.parseInt(request.getParameter("no"));
GuestbookVO guestbookvo = (GuestbookVO)request.getAttribute("guestbookvo");
System.out.println(no);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="http://localhost:8080/guestbook2/gbc" method="get">
		<input type="hidden" name="action" value="delete">
		<input type="hidden" name="no" value="<%= no %>">
		<table>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="password" value=""></td>
				<td>
					<button>삭제</button>
				</td>
			</tr>
		</table>
	</form>

	<br>
	<br>
	<a href="http://localhost:8080/guestbook2/gbc?action=list">메인으로 돌아가기</a>
</body>
</html>