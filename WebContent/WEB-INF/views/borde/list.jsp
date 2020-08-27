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
<a href="/views/borde/post"><button class="btn btn-primary">글쓰기</button></a>
${sessionScope.user.uiId} 님 반갑습니다.  등급 : ${sessionScope.user.uiAdmin}   ( 관리자 : 1 회원 : 0 )
<c:if test="${sessionScope.user.uiId==null}"><a href="/"><button class="btn btn-primary">로그인</button></a></c:if>
<table class="table table-bordered table-hover"> 
	<tr>
		<th data-col="postNo"></th>
		<th data-col="postTitle">제목</th>
		<th data-col="postCrename">작성자</th>
		<th data-col="postCredat">작성일</th>
		<th data-col="postUpdateDat">최종수정일</th> 
	</tr>
	<tbody id="tbody">
	</tbody>
</table>
<script>
function select(tdObj){
	location.href = '/views/borde/update?postNo='+tdObj.getAttribute('data-postNo');
}
function callBack(res){
	var posts = res.result;
	var html = '';
	for(var i=0;i<posts.length;i++){
		var post = posts[i];
		html += '<tr>';
		$('th[data-col]').each(function(idx,td){
			var col = td.getAttribute('data-col');
			if('postTitle'==col){
				var postNo = post.postNo;
				html += '<td onclick="select(this)" data-postNo="'+postNo+'">'+post[col]+'</td>';
			}else{
				html += '<td>'+(post[col]?post[col]:'-')+'</td>';
			}
		})
		html += '</tr>'
	}
	$('#tbody').html(html);
}
function loadList(){
	
	$.ajax({
		url : '/ajax/post',
		method : 'GET',
		contentType : 'application/json',
		data : {cmd:'list'},
		success : function(res){
			callBack(res);
		},
		error : function(err){
			console.log(err.responseText);
		}
	})
}
$(document).ready(loadList);
</script>
</body>
</html>








