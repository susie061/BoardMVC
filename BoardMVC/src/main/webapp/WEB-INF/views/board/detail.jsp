<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>디테일</title>
</head>
<body>
	<div>
		<a href="/board/list">리스트로 돌아가기</a>
		<a href="/board/delete?i_board=${data.i_board}"><button>삭제</button></a>
		<a href="/board/upd?i_board=${data.i_board}"><button>수정</button></a>
	</div>
	<table>
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>내용</th>
			<th>등록일시</th>
		</tr>
		<tr>
			<td>${data.i_board }</td>
			<td>${data.title }</td>
			<td>${data.ctnt }</td>
			<td>${data.r_dt }</td>
		</tr>
	</table>
</body>
</html>