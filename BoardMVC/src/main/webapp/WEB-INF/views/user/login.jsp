<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<div>${msg}</div>
	<form action="/user/loginPost" method="post">
		<div>아이디<input type="text" name="uid"placeholder="아이디"></div>
		<div>비밀번호<input type="password" name="upw"placeholder="비밀번호"></div>
		<div><input type="submit" value="로그인"></div>
	</form>
	<div><a href="/user/join">회원가입</a></div>
	<div>
		<a href="/user/loginKAKAO">카카오톡 로그인</a>
	</div>
</body>
</html>    