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
<input type="hidden" id="postUpdateName" value="${sessionScope.user.uiId}">
<table class="table table-bordered">
	<tr>
		<td>번호</td><td><input type="text" id="postNo"  disabled readonly></td> 
	</tr>
	<tr>
		<td>작성자</td><td><input type="text" id="postCrename"  disabled readonly></td> 
	</tr>
	<tr>
		<td>마지막 수정자</td><td><input type="text" id="postUpName" disabled readonly></td> 
	</tr>
	<tr>
		<td>제목</td><td><input type="text" id="postTitle"></td> 
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
	<tr>
		<td>수정 횟수</td><td><input type="text" id="postUpdateCnt" disabled readonly></td> 
	</tr>
	<tr>
		<td>접근제한</td><td><input type="text" id="postAdmin" disabled readonly></td> 
	</tr>
</table>
<button class="btn btn-primary" onclick="updatePost()">수정하기</button>
<button class="btn btn-primary" onclick="deletePost()">삭제하기</button>
<a href="/views/borde/list"><button class="btn btn-primary">목록으로</button></a>
<script>
$(document).ready(loadPost);
function loadPost(){
	var postNo = ${param.postNo};
	$.ajax({
		url : '/ajax/post',
		method : 'GET',
		data : {cmd:'post',postNo:postNo},
		success : function(res){
			callBack(res);
		},
		error : function(err){
			console.log(err.responseText);
		}
	})
}
function callBack(resObj){
	var post = resObj.result;
	$('input[id]').each(function(idx,td){
		var id = td.getAttribute('id')
		$('#'+id).val(post[id]);
	})
	$('#postContent').val(post.postContent);
	$('#op'+post.postLocation).attr('selected',true);
	
}
function updatePost(){
	var param = {
			cmd : 'update',
			postContent : $('#postContent').val(),
			postTitle: $('#postTitle').val(),
			postUpName : $('#postUpName').val(),
			postLocation : $('option:selected').val(),
			postNo : $('#postNo').val(),
			postUpdateCnt : $('#postUpdateCnt').val()
	}
	$.ajax({
		url : '/ajax/post',
		method : 'POST',
		data : JSON.stringify(param),
		success : function(res){
			if(res.result){
				alert('수정이 되었습니다!');
				location.href = '/views/borde/list'
			}else{
				alert('수정되지 않았습니다.');
			}
		}
	})
}
function deletePost(){
	var postNo = $('#postNo').val();
	var param = {cmd:'delete',postNo:postNo};
	$.ajax({
		url : '/ajax/post',
		method : 'POST',
		data : JSON.stringify(param),
		success : function(res){
			if(res.result){
				alert('삭제가 완료 되었습니다.');
				location.href = '/views/borde/list';				
			}else{
				alert('삭제를 실패했습니다...');
			}
		}
	})
}
</script>
</body>
</html>