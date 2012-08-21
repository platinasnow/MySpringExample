<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<style type="text/css">
#boardWrite{
	width:720px;
	height: 600px;
	font-family: 'NanumGothicWeb';
	box-shadow:inset 0px 0px 2px #aaa;
	border-radius : 5px;
	padding : 45px;
 }
 #title{
 	width:650px;
	border: 1px #cdcdcd solid;
	padding: 0.5em 0.5em 0.5em 1.0em;
	box-shadow:inset 0px 0px 3px #dcdcdc;
	border-radius:5px;
	background: #fdfdfd;
	margin-left: 5px;
 }
 #title:focus{
 	box-shadow:inset 0px 0px 3px #fff000;
 	background: #fff;
 }
 #content{
 	width:680px;
 	height:450px;
	border: 1px #cdcdcd solid;
	padding: 0.5em 0.5em 0.5em 1.0em;
	box-shadow:inset 0px 0px 3px #dcdcdc;
	border-radius:5px;
	background: #fdfdfd;
	margin-left: 5px;
 }
 #content:focus {
 	box-shadow:inset 0px 0px 3px #fff000;
 	background: #fff;
 }
 #contentDiv{
 	margin-top: 20px;
 }
 #fileUploadDiv{
 	margin-top: 20px;
 }
 #buttonDiv{
 	margin-top: 20px;
 	text-align: center;
 }
 #button{
 	font-family: 'NanumGothicWeb';
	border: 1px #aaa solid;
	box-shadow:inset 0px 0px 3px #f0f0f0;
	border-radius: 5px;
	background: #fff;
	width: 65px;
	height: 30px;
 	margin-left: 20px;
 	margin-right: 20px; 
 }	
 #button:focus{
 	background: #eee;
 }
 </style>
<div id="boardWrite">
	<form id="boardWriteForm" method="post">
		<div>
			<label>제목</label>
			<input type="text" id="title" name="title" value="${boardItem.title}" maxlength="100"/>
		</div>
		<div id="contentDiv">
			<textarea id="content" name="content" rows="" cols="" maxlength="2048">${boardItem.content}</textarea>
		</div>
		<div id="fileUploadDiv">
			<label>파일업로드</label>
			<input type="file" name="fileupload" />
		</div>
		<div id="buttonDiv">		
			<input id="button" type="button" onclick="validation('${boardType}')" value="확인" /><input id="button" type="button" onclick="cancel()" value="취소"/>
		</div>
		<input type="hidden" name="boardNum" value="${boardNum }" />
		<input type="hidden" name="seq" value="${seq}" />
		<input type="hidden" name="principle" value='<sec:authentication property="name"/>' />
	</form>
</div>

<script type="text/javascript">
function validation(boardType){
	if($("#title").val().length == 0 ){
		alert("제목을 입력해야 합니다.");
	}else if($("#title").val().length > 100 ){
		alert("제목은 100자를 넘을 수 없습니다.");
	}else if($("#content").val().length == 0){
		alert("본문을 입력해야 합니다.");
	}else if($("#content").val().length > 2048){
		alert("본문은 2048자를 넘을 수 없습니다.");
	}else{
		writeSubmit(boardType);
	}
}
function writeSubmit(boardType){
	var boardWriteForm = document.getElementById("boardWriteForm");
	if(boardType == 'modify' ){
		boardWriteForm.action="${pageContext.request.contextPath}/board/boardModifySubmit.do";
	}else{
		boardWriteForm.action="${pageContext.request.contextPath}/board/boardWriteSubmit.do";
	}
	boardWriteForm.submit();
}
function cancel(){
	history.back();
}

</script>