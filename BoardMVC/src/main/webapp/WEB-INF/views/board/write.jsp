<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
</head>
<body>
	<form action="/board/${data != null ? 'upd' : 'write' }" method="post">
		<div><input type="hidden" name="i_board" value="${data != null ? data.i_board : 0 }"></div>
		<div>제목: <input type="text" name="title" value="${data.title}"></div>
		<div>내용: <textarea name="ctnt">${data.ctnt}</textarea></div>
		<div><input type="submit" value="${data != null ? '수정' : '등록' }"></div>
	</form>
</body>
</html>