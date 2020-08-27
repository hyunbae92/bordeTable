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
<input type="hidden" id="postCrename" value="${sessionScope.user.uiId}">
<input type="hidden" id="postAdmin" value="${sessionScope.user.uiAdmin}">
<table border="1" class="table talbe-bordered">
	<tr>
		<td>제목</td>
		<td><input type="text" id="postTitle"></td>
	</tr>
	<tr>
		<td>내용</td>
		<td><textarea id="postContent"></textarea></td>
	</tr>
	<tr>
		<td>개시판 위치</td>
		<td>
			<select>
				<option id="op0" value="0">0</option>
				<option id="op1" value="1">1</option>
				<option id="op2" value="2">2</option>
			</select>
		</td>
	</tr>
</table>
<button class="btn btn-primary" onclick="insert()">글쓰기</button>
<a href="/views/borde/list"><button class="btn btn-primary">취소</button></a>
<script>
function insert(){
	var params = {
			cmd : 'insert',
			postTitle : $('#postTitle').val(),
			postContent : $('#postContent').val(),
			postLocation : $('option:selected').val(),
			postCrename : $('#postCrename').val(),
			postAdmin : $('#postAdmin').val()			
	} 
	$.ajax({
		url : '/ajax/post',
		method : 'POST',
		data : JSON.stringify(params),
		success : function(res){
			if(res.result){
				alert('등록이 완료 되었습니다.');
				location.href = '/views/borde/list'
			}
		},
		error : function(err){
			console.log(err.responseTest);
		}
	})
	
}

</script>
</body>
</html>










