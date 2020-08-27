<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/WEB-INF/views/common/head.jsp"></jsp:include>
</head>
<body>
<table class="table table-bordered"> 
	<tr>
		<th>ID</th><td><input type="text" id="uiId" placeholder="아이디를 입력해주세요!"></td>
	</tr>
	<tr>
		<th>PASSWORD</th><td><input type="password" id="uiPassword" placeholder="비밀번호를 입력해주세요!"></td>
	</tr>
</table>
<button class="btn btn-primary" onclick="signIn()">Sign In</button> 
<script> 
function signIn(){
	var uiId = $('#uiId').val();
	var uiPassword = $('#uiPassword').val();
	var param = {
			uiId : uiId,
			uiPassword : uiPassword,
			cmd : 'signin'
	}
	console.log(param);
	$.ajax({
		url : '/ajax/user',
		method : 'POST',
		data : JSON.stringify(param),
		success : function(res){
			if(res.result){
				alert('로그인 완료');
				location.href = '/views/borde/list';
			}else{
				alert('로그인 실패');
			}
		},
		error : function(res){
			console.log(res.responseText);
		}
	})
}
</script>
</body>
</html>