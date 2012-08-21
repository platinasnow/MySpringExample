<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<script type="text/javascript">
function home(){
	location.href='${pageContext.request.contextPath}/index.do';
}
</script>
<div style="float:left ;width:180px; height:60px; border: 1px #eee solid" onclick="home()">
	Go HOME 1280x720
</div>

<div style="width: 1300px;height: 60px; border: 1px #eee solid">
<a href ="${pageContext.request.contextPath}/board/boardList.do?boardNum=1">게시판1</a> 채팅
<sec:authorize ifAnyGranted="ROLE_ADMIN,ROLE_SUBADMIN"> 
<a href ="${pageContext.request.contextPath}/admin/memberManage.do">관리자</a>
</sec:authorize>
<a href="${pageContext.request.contextPath}/logout.do">Logout</a>
</div>