<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입</title>
<script src="/resources/js/axios.min.js"></script>
</head>
<body>
	<div>${msg}</div>
	<div><a href="/user/login"></a></div>
	<form action="/user/joinPost" method="post">
		<div>아이디:<input type="text" name="uid" placeholder="아이디"></div>
		<div>비밀번호:<input type="password" name="upw" placeholder="비밀번호"></div>
		<div>확인 비밀번호:<input type="password" name="upwConfirm" placeholder="비밀번호 확인"></div>
		<div>
			Phone:010 - <input type="text" name="ph" id="ph">
			<button type="button" onclick="sendPhAuthNumber()">인증번호 보내기</button>
		</div>
		<div>인증번호: <input type="text" name="phAuthNumber"></div>
		<div id="phAuthResult"></div>
		<div>이름:<input type="text" name="nm" placeholder="이름"></div>
		<div>주소<input type="text" name="addr" placeholder="주소"></div>
		<div><input type="submit" value="회원가입"></div>
	</form>

	<script>
		function sendPhAuthNumber(){
			if(ph.value.length < 9){
				alert('Phone 번호를 확인해 주세요')
				return
			}
			console.log('ph:' +ph.value)
			axios.get('/user/phAuth',{
				params:{
					ph: ph.value
				}
			}).then(function(res) {
	
				if(res.data.result == 1){
					alert('통신완료')
				}else{
					alert('에러발생')
				}
			})
		}
	</script>
</body>
</html>