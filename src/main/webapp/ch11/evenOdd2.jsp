<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
    int num = (int) Math.ceil(Math.random()*10);
 	String result = (num % 2 == 0)? "짝수" : "홀수" ;
 %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>홀수 짝수 구분</title>
</head> 
<body>
	<h1>홀수 짝수 구분</h1>
	<hr>
	<h3>난수를 통해 얻은 숫자: <%= num %></h3>
		<h3> <%= result %> 입니다.</h3>
</body>
</html>