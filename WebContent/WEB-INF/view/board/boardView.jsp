<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<style>
#boardView{
	width:720px;
	font-family: 'NanumGothicWeb';
	box-shadow:inset 0px 0px 2px #aaa;
	border-radius : 5px;
	padding : 25px;
	font-size: 13px;
	overflow: hidden;
	zoom: 1;
}
#title{
	border-bottom: 1px dashed #dfdfee;
	padding-bottom: 5px;
	padding-left: 5px;
	padding-right: 5px;
}
#writer{
	margin-top: 5px;
	padding-left: 5px;
}
#content{
	margin-top: 50px;
}
#writer{
	float: left;
	width: 585px;
}
#date{
	float: left;
	margin-top: 5px;
}
#fileDiv{
	margin: 5px;
	text-align: right;
}
#hitDiv{
	margin-top: 50px;
}
#reply{
	margin-top: 10px;
	background: #efeffe;
	border-radius: 10px;
	padding-top : 10px;
	padding-left: 15px;
	padding-right : 15px;
	padding-bottom: 5px;
	opacity: 0.7;
	word-wrap : break-word;
}
#reply textarea{
	margin-top: 10px;
	width: 610px;
	height: 100px;
	border-radius: 5px;
}
#buttonDiv{
	margin-top: 5px;
	text-align: right;
}
#button{
 	font-family: 'NanumGothicWeb';
	border: 1px #aaa solid;
	box-shadow:inset 0px 0px 3px #f0f0f0;
	border-radius: 5px;
	background: #fff;
	width: 65px;
	height: 30px;
 }	
#button:focus{
 	background: #eee;
 }
 #replyDiv{
 	min-height: 30px;
 	zoom: 1;
 	overflow: hidden;
 	border-bottom: 2px #faa dotted;
 	margin-top: 10px;
 	margin-bottom: 10px;
 	padding-bottom: 5px;
 }
 #replyUserId{
 	float: left;
 	width: 90px;
 	padding-left: 10px;
 }
 #replyContent{
 	float: left;
 	width: 570px;
 }
 #replyDate{
 	font-size: 9px;
 	color: #a0a;
 	margin-bottom: 5px;
 }
</style>
<div id="boardView">
<form id="viewForm" method="post">

	<div id="title">
		<b>${boardItem.title }</b>
	</div>	
	<div id="writer">
		<b>${boardItem.writer }</b>
	</div>
	<div id="date">
		<fmt:formatDate value="${boardItem.indate }" pattern="yyyy-MM-dd HH:mm EEE"/>
	</div>		
	<div id="fileDiv">
		<c:if test="${boardItem.fileData !=null}">
			첨부파일${boardItem.fileData }	
		</c:if>
		첨부파일
	</div>
	<div id="content">
		${boardItem.content }
	</div>
	<div id="hitDiv">
		<b>조회수 ${boardItem.hit } | 덧글 ${boardItem.reply }</b>
	</div>
	<div id="reply">
		<div id="replyTable">
			<c:forEach  items="${replyList}" var="replyItem">
				<div id="replyDiv">
					<div id="replyUserId"> <b>${replyItem.userId }</b>
						<div id="replyDate"><fmt:formatDate value="${boardItem.indate }" pattern="yyyy-MM-dd HH:mm"/></div>
						<div>수정|삭제</div>  
					</div> 
					<div id="replyContent">${replyItem.content } </div>
				</div>
			</c:forEach>
		</div>
		<textarea rows="" cols="" name="content"></textarea>	
		<input type="button" id="button" onclick="reply()" value="덧글"/>
	</div>
	
	<div id="buttonDiv">
		<input type="button" id="button" onclick="goModify()" value="수정"/>
		<input type="button" id="button" onclick="goDelete()" value="삭제"/>
		<input type="button" id="button" onclick="goList()" value="목록"/>
	</div>		
	
	<input type="hidden" name="seq" value="${boardItem.seq }"/>
	<input type="hidden" name="boardNum" value="${boardItem.boardNum }"/>
	<input type="hidden" id="principle" name="principle" value="<sec:authentication property="name"/>"/>
	<input type="hidden" id="writePrinciple" value="${boardItem.principle }"/>
</form>
</div>

<script type="text/javascript">
function goList(){
	location.href="${pageContext.request.contextPath}/board/boardList.do?boardNum="+'${boardItem.boardNum}';
}
function goModify(){
	if($("#principle").val() != $("#writePrinciple").val() ){
		alert("권한이 없습니다.");
	}else{
		var viewForm = document.getElementById("viewForm");
		viewForm.action="${pageContext.request.contextPath}/board/boardModify.do";
		viewForm.submit();
	}
}
function goDelete(){
	if($("#principle").val() != $("#writePrinciple").val() ){
		alert("권한이 없습니다.");
	}else{
		var viewForm = document.getElementById("viewForm");
		viewForm.action="${pageContext.request.contextPath}/board/deleteBoardItem.do";
		viewForm.submit();
	}
}
function reply(){
	var viewForm = document.getElementById("viewForm");
	viewForm.action="${pageContext.request.contextPath}/board/replyBoard.do";
	viewForm.submit();
}
function deleteReply(){
	
}
function modifyReply(){
	
}
</script>