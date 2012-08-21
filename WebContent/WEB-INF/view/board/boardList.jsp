<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<style type="text/css">
#board{
	font-family: 'NanumGothicWeb';
	width: 1000px;
}
#board h2{
	font-family: 'Sansita One';
	border-bottom: 1px #ddd solid;
	padding-bottom: 5px;
	width: 6em;
}
#headDiv{
	height: 18px;
	margin-top: 20px;
	padding-bottom: 5px;
	overflow: hidden;
	zoom: 1;
	border-bottom: 1px #ccc solid;
	margin-bottom: 5px;;
}
#headDiv div{
	text-align: center;
	float : left; 
	padding-bottom: 5px;
	color: #a49edd;
	font-weight: bold;
	font-size: 13px;
}
#contentLine{
	font-size: 12px;
	overflow: hidden;
	zoom: 1;
	height: 35px;
}
#contentLine div{
	float : left;
	text-align: center;
	height: 35px;
	padding-top: 8px;
}
.lineColor1{
	background: #efefff;
	opacity : 0.8;
}
.lineColor0{
	background: #f9f9f9;
	opacity : 0.8;
}
#menuDiv{
	width:1000px;
	border-top: 1px #ccc solid;
	margin-top: 5px;
	padding-top : 10px;
	text-align: right;
}
#pagingDiv{
	width:1000px;
	margin-top: 5px;
	padding-top : 10px;
	text-align: center;
}
#searchDiv{
	margin-top : 20px;
	text-align: center;
}
#button{
	font-family: 'NanumGothicWeb';
	border: 1px #aaa solid;
	box-shadow:inset 0px 0px 3px #f0f0f0;
	border-radius:5px;
	width: 60px;
	height: 25px;
	background: #fff;
}
#button:focus{
	background: #eee;
}
#searchDateDiv{
	margin : 0 auto;
	margin-top : 10px;
	width : 250px;
	height: 30px;
	background: #abd;
	border-radius : 10px;
	padding-top:10px;
	opacity : 0.7;
	visibility: hidden;
}
#column0{
	width: 18px;
}
#column1{
	width: 75px;
}
#column2{
	width: 480px;
}
#column3{
	width:150px;
}
#column4{
	width: 200px;
}
#column5{
	width: 75px;
}
</style>
<div id="board">
	<h2>Free Board</h2>
	<form id="boardForm" method="post">
		<div id="headDiv">
			<div id="column0"><input type="checkbox" id="checkBoxAll"  /></div>
			<div id="column1">글번호</div>
			<div id="column2">제목</div>
			<div id="column3">작성자</div>
			<div id="column4">날짜</div>
			<div id="column5">조회수</div>
		</div>
		<div>
		<c:forEach items="${boardList }" var="board" varStatus="lineCount" >
			<div id="contentLine" class="lineColor${lineCount.count%2}"> 
				<div id="column0"><input type="checkbox" id="checkBox" name="seq"  value="${board.seq }" /></div>
				<div id="column1">${board.seq }</div>
				<div id="column2" style="text-align: left;" onclick="viewItem('${board.seq }')">
					${board.title } <c:if test="${board.reply !=0}"> <b>[${board.reply }]</b> </c:if> 
				</div>
				<div id="column3">${board.writer}</div>
				<div id="column4"><fmt:formatDate value="${board.indate }" pattern="yyyy-MM-dd HH:mm EEE"/> </div>
				<div id="column5">${board.hit }</div>
			</div>
		</c:forEach>
		<div id="menuDiv">	
			<input id="button" type="button" onclick="goBoardWritePage()" value="쓰기"/>
			<input id="button" type="button" onclick="goBoardModifyPage()" value="수정" />
			<input id="button" type="button" onclick="deleteItem()" value="삭제" />
		</div>
		<div id="pagingDiv">
			<input type="hidden" id="page" name="page" value="1" />
			<a href="javascript:goPage(1)">처음</a> 
			<a href="javascript:goPrevPage()"><<</a>
			<c:forEach begin="${paging.pageBegin}" end="${paging.pageEnd }" var="idx">	
				<c:if test="${paging.currentPage == idx}"><b>${idx }</b></c:if>
				<c:if test="${paging.currentPage != idx}" ><a href="javascript:goPage(${idx})">${idx }</a> </c:if>
			</c:forEach>
			<a href="javascript:goNextPage()">>></a>
			<a href="javascript:goPage(${paging.pageCount })"> 끝</a> 
		</div>
		<div id="searchDiv">
			<select id="searchDate">
				<option>기간</option>
			</select>
			<select id="searchCondition" name="search">
				<option value="searchTitle">제목</option>
				<option value="searchContent">내용</option>
				<option value="searchWriter">아이디</option>
			</select>
			<input type="text" name="searchData" />
			<input id="button" type="button" onclick="search()" value="검색" />
			<div id="searchDateDiv">
				<input type="text" id="preDate" name="preDate" disabled="disabled"> ~ <input type="text" id="postDate" name="postDate" disabled="disabled">
				<b onclick="closeDateDiv()">x</b>
			</div>
		</div>
	</div>
	<input type="hidden" name="boardNum" value="${boardNum }"/>
	<input type="hidden" name="principle" value='<sec:authentication property="name"/>'/>
	</form>
</div>

<script type="text/javascript">
function goPage(page){
	var boardForm = document.getElementById('boardForm');
	document.getElementById('page').value = page;
	boardForm.action='${pageContext.request.contextPath}/board/boardList.do';
	boardForm.submit();
}
function goNextPage(){
	var page = ${paging.currentPage};
	var nextPage = ${paging.nextPage};
	var totalPage = ${paging.pageCount};
	goPage(Math.min(totalPage,page+nextPage ));
}
function goPrevPage(){
	var page = ${paging.currentPage};
	var nextPage = ${paging.nextPage};
	goPage(Math.max(1, page - nextPage ));
 }
function search(){
	var boardForm = document.getElementById('boardForm');
	boardForm.action='${pageContext.request.contextPath}/board/boardList.do';
	boardForm.submit();
}
function closeDateDiv(){
	$("#searchDateDiv").attr("style","visibility :hidden");
	$("#preDate").attr("disabled",true);
	$("#postDate").attr("disabled",true);
}
function viewItem(seq){
	var boardForm = document.getElementById('boardForm');
	boardForm.action='${pageContext.request.contextPath}/board/boardView.do?b_seq='+seq;
	boardForm.submit();
}
function goBoardWritePage(){
	var boardForm = document.getElementById('boardForm');
	boardForm.action='${pageContext.request.contextPath}/board/boardWrite.do';
	boardForm.submit();
}
function goBoardModifyPage(){
	if($("#checkBox:checked").length == 0 ){
		alert("체크한 후에 다시 시도해주십시오.");
	}else if($("#checkBox:checked").length != 1 ){
		alert("수정은 하나만 체크 해야합니다.");
	}else{
		var boardForm = document.getElementById('boardForm');
		boardForm.action='${pageContext.request.contextPath}/board/boardModify.do';
		boardForm.submit();
	}
}
function deleteItem(){
	if($("#checkBox:checked").length == 0 ){
		alert("체크한 후에 다시 시도해주십시오.");
	}else if(confirm("정말로 삭제 하시겠습니까?")){
		var boardForm = document.getElementById('boardForm');
		boardForm.action='${pageContext.request.contextPath}/board/deleteBoardItem.do';
		boardForm.submit();
	}
}
 $("#checkBoxAll").click(function(){
	if($("#checkBoxAll").attr('checked') ){
		$(":checkbox").attr('checked',true);
	}else{
		$(":checkbox").attr('checked',false);
	}
 });
 $("#preDate").datepicker({
	 showAnim: "slideDown",
	 dateFormat: 'yy-mm-dd',
	 autoSize: true,
	 changeMonth: true, //월변경가능
	 changeYear: true, //년변경가능
	 showMonthAfterYear: true,
	 monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	 dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'] // 요일의 한글 형식.
 });
 $("#postDate").datepicker({
	 showAnim: "slideDown",
	 dateFormat: 'yy-mm-dd',
	 autoSize: true,
	 changeMonth: true, //월변경가능
	 changeYear: true, //년변경가능
	 showMonthAfterYear: true,
	 monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
	 dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'] // 요일의 한글 형식.
 });
$("#searchDate").click(function(){
	$("#searchDateDiv").attr("style","visibility :visible");
	$("#preDate").attr("disabled",false);
	$("#postDate").attr("disabled",false);
});

</script>