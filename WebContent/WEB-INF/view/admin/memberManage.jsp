<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<style type="text/css">
#admin{
	font-family: 'NanumGothicWeb';
	width: 1000px;
}
#admin h2{
	font-family: 'Sansita One';
	border-bottom: 1px #ddd solid;
	padding-bottom: 5px;
	width: 13em;
}
#menuDiv{
	margin-top: 20px;
	height: 30px;
}
#rightMenu{
	float: right;
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
	width: 140px;
	float : left; 
	padding-bottom: 5px;
	color: #a49edd;
	font-weight: bold;
	font-size: 13px;
}
#menuDiv button{
	font-size: 13px;
	font-family: 'NanumGothicWeb';
	border: 1px #aaa solid;
	box-shadow:inset 0px 0px 3px #f0f0f0;
	border-radius:5px;
	width: 70px;
	height: 25px;
	background: #fff;
}
#menuDiv button:focus{
	background: #eee;
}
#contentLine{
	font-size: 12px;
	overflow: hidden;
	zoom: 1;
	height: 35px;
}
#contentLine div{
	float : left;
	width : 140px;
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
#attention{
	color:red;
}
#pagingDiv{
	width:1000px;
	border-top: 1px #ccc solid;
	margin-top: 5px;
	padding-top : 10px;
	text-align: center;
}
#searchDiv{
margin-top : 20px;
	text-align: center;
}
#searchDiv Button{
	font-family: 'NanumGothicWeb';
	border: 1px #aaa solid;
	box-shadow:inset 0px 0px 3px #f0f0f0;
	border-radius:5px;
	width: 60px;
	height: 24px;
	background: #fff;
}
#searchDiv Button:focus{
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
</style>

<div id="admin">
	<h2>Admin - Member Manage</h2>
	<form id="adminForm" method="post">
	<div id="menuDiv">
		<select id="viewGroup" name="viewGroup">
			<option value="all">전체</option>
			<option value="block">차단</option>
			<option value="permit">미승인</option>
		</select>
		<div id="rightMenu">
		<button onclick="JoinAcception()">가입승인</button>
		<button onclick="doBlock()">차단</button>
		<button onclick="doUnblock()">차단해제</button>
		<select id="authority" name="authority">
			<option value="">등급설정</option>
			<sec:authorize ifAnyGranted="ROLE_ADMIN"><option value="ROLE_ADMIN">운영자</option></sec:authorize> 
			<sec:authorize ifAnyGranted="ROLE_ADMIN"><option value="ROLE_SUBADMIN">부운영자</option></sec:authorize>
			<option value="ROLE_SPUSER">정회원</option>
			<option value="ROLE_USER">일반회원</option>
		</select>
		</div>
	</div>
	<div id="headDiv">
		<div style="width:18px"><input type="checkbox" id="checkBoxAll"  /></div>
		<div>아이디</div>
		<div>이름</div>
		<div>신청일</div>
		<div>E-Mail</div>
		<div>승인여부</div>
		<div>차단</div>
		<div>등급</div>
	</div>
	<div>
		<c:forEach items="${userInfoList }" var="user" varStatus="lineCount" >
			<div id="contentLine" class="lineColor${lineCount.count%2}"> 
				<div style="width:18px"><input type="checkbox" id="checkBox" name="checkBox" class="${user.principle }" value="${user.userId }" /></div>
				<div>${user.userId }</div>
				<div>${user.name}</div>
				<div><fmt:formatDate value="${user.indate }" pattern="yyyy-MM-dd HH:mm EEE"/> </div>
				<div>${user.email }</div>
				<div>
					<c:if test="${user.permit =='1' }">승인</c:if>
					<c:if test="${user.permit =='0' }"><span id="attention">미승인</span></c:if>
				</div>
				<div>
					<c:if test="${user.block =='1' }"><span id="attention">차단</span></c:if>
					<c:if test="${user.block =='0' }">-</c:if>
				</div>
				<div>
					<c:if test="${user.authority =='ROLE_USER'}">일반회원</c:if>
					<c:if test="${user.authority =='ROLE_SPUSER'}">정회원</c:if>
					<c:if test="${user.authority =='ROLE_SUBADMIN'}">부운영자</c:if>
					<c:if test="${user.authority =='ROLE_ADMIN'}">운영자</c:if>
				</div>
			</div>
		</c:forEach>
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
			<option value="searchId">아이디</option>
			<option value="searchName">이름</option>
		</select>
		<input type="text" name="searchData" />
		<button>검색</button>
		<div id="searchDateDiv">
			<input type="text" id="preDate" name="preDate" disabled="disabled"> ~ <input type="text" id="postDate" name="postDate" disabled="disabled">
			<b onclick="closeDateDiv()">x</b>
		</div>
	</div>
	<input type="hidden" id="principal" name="principal" value='<sec:authentication property="name" />' />
	</form>
</div>
<script type="text/javascript">
function goPage(page){
	var adminForm = document.getElementById('adminForm');
	document.getElementById('page').value = page;
	adminForm.action='${pageContext.request.contextPath}/admin/memberManage.do';
	adminForm.submit();
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
function JoinAcception(){
	if($("#checkBox:checked").val() == null){
		alert("선택 후에 다시 시도해주시기 바랍니다.");
	}
	var adminForm = document.getElementById('adminForm');
	adminForm.action='${pageContext.request.contextPath}/admin/joinAcception.do';
	adminForm.submit();
}
function doBlock(){
	if($("#checkBox:checked").val() == null){
		alert("선택 후에 다시 시도해주시기 바랍니다.");
	}
	 if( $("#checkBox:checked").attr('class') == $("#principal").val()  ){
			alert("자기 자신은 선택 할 수 없습니다.");
	 }else{
		var adminForm = document.getElementById('adminForm');
		adminForm.action='${pageContext.request.contextPath}/admin/blockUser.do';
		adminForm.submit();
	 }
}
function doUnblock(){
	if($("#checkBox:checked").val() == null){
		alert("선택 후에 다시 시도해주시기 바랍니다.");
	}
	var adminForm = document.getElementById('adminForm');
	adminForm.action='${pageContext.request.contextPath}/admin/unblockUser.do';
	adminForm.submit();
}
function closeDateDiv(){
	$("#searchDateDiv").attr("style","visibility :hidden");
	$("#preDate").attr("disabled",true);
	$("#postDate").attr("disabled",true);
}
$("#viewGroup").val('${viewGroup}');
$("#viewGroup").change(function(){
	var adminForm = document.getElementById('adminForm');
	adminForm.action='${pageContext.request.contextPath}/admin/memberManage.do';
	adminForm.submit();
 });
 $("#checkBoxAll").click(function(){
	if($("#checkBoxAll").attr('checked') ){
		$(":checkbox").attr('checked',true);
	}else{
		$(":checkbox").attr('checked',false);
	}
 });
 $("#authority").change(function(){
	 if($("#checkBox:checked").val() == null){
		 alert("선택 후에 다시 시도해주시기 바랍니다.");
	 }else{
		 var adminForm = document.getElementById('adminForm');
		 if($("#authority").val() == 'ROLE_ADMIN'){ //운영자 일때
			if($("#checkBox:checked").length != 1){
				alert("운영자는 한명만 선택해야 합니다.");
			}else if( $("#checkBox:checked").attr('class') == $("#principal").val()  ){
				alert("자기 자신은 선택 할 수 없습니다.");
			}else if(confirm("운영자를 선택하면 기존의 운영자는 부운영자로 강등됩니다. 운영자를 물려주시겠습니까?")){
				adminForm.action='${pageContext.request.contextPath}/admin/changeAuthorityAdmin.do';
			 	adminForm.submit();
			}
		 }else if($("#authority").val() == ''){
			 //아무동작 x
		 }else{ //나머지
			 if( $("#checkBox:checked").attr('class') == $("#principal").val()  ){
					alert("자기 자신은 선택 할 수 없습니다.");
			 }else{
			 	adminForm.action='${pageContext.request.contextPath}/admin/changeAuthority.do';
			 	adminForm.submit();
			 }
		 }
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
 
/* TODO 
 * 
 차단 ->운영자는 x 
 */

</script>