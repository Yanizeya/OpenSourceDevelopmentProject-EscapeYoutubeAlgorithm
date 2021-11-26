<!-- 실질적으로 사용자의 로그인 시도를 처리하는 페이지 -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 우리가 만들었던 클래스를 가져옴 -->
<%@ page import="user.UserDAO" %>
<%@ page import="java.io.PrintWriter" %>
<!-- 건너오는 데이터를 UTF-8로 바꿔줌 -->
<% request.setCharacterEncoding("UTF-8"); %>
<!--  User클래스를 자바beans로 사용, scope="page"는 현재 페이지 안에서만 사용한다고 알려줌 -->
<jsp:useBean id="user" class="user.User" scope="page"/>
<!-- 로그인페이지에서 넘겨준 name="userID"를 받아서 사용자의 userID에 넣어줌-->
<jsp:setProperty name="user" property="userID"/>
<jsp:setProperty name="user" property="userPassword"/>
<!DOCTYPE html> 

<html>
<head>
<meta charset="UTF-8">
<title>JSP 게시판 웹 사이트</title>
</head>
<body>
	<%
		UserDAO userDAO = new UserDAO();
		int result = userDAO.login(user.getUserID(), user.getUserPassword()); //login함수에 아이디와 패스워드를 넣어서 실행
		//-2에서 1까지
		if(result == 1){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("location.href = 'main.jsp'");
			script.println("</script>");
		}
		else if(result == 0){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('비밀번호가 틀립니다')");
			script.println("history.back()");
			script.println("</script>");
		}
		else if(result == -1){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('존재하지 않는 아이디입니다.')");
			script.println("history.back()");
			script.println("</script>");
		}
		else if(result == -2){
			PrintWriter script = response.getWriter();
			script.println("<script>");
			script.println("alert('데이터베이스 오류가 발생했습니다.')");
			script.println("history.back()");
			script.println("</script>");
		}
	%>
</body>
</html>