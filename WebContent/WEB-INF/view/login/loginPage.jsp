<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<style type="text/css">
body{
	font-family: 'NanumGothicWeb';
}
#loginDiv h2{
	font-family: 'Sansita One';
	border-bottom: 1px #ddd solid;
	padding-bottom: 5px;
}
#loginDiv{
	position:relative;
	margin-top:150px;
	left: 50%;
	margin-left: -200px;
	width :400px;
	
}
#messageDiv{
	text-align:center;
	font-weight:bold;
	color:#ffffff;
	font-size: 11px;
	background: #000000;
	height: 20px;
	padding-top: 10px;
	border-radius:5px;
}
#loginDiv input{
	width:22em;
	border: 1px #cdcdcd solid;
	padding: 0.7em 0.5em 0.7em 1.5em;
	box-shadow:inset 0px 0px 3px #dcdcdc;
	border-radius:5px;
	background: #fafafa;
}
#loginDiv input:focus{
	width:23em;
	background: #fff;
	box-shadow:inset 0px 0px 3px #fff000;
}

#buttonDiv{
	position: relative;
}
#loginDiv button{
	font-size: 13px;
	font-family: 'Sansita One';
	border: 1px #aaa solid;
	box-shadow:inset 0px 0px 3px #dcdcdc;
	border-radius:10px;
	width: 90px;
	height: 40px;
	background: -moz-linear-gradient(90deg,#fff,#aaa 0px,#ccc 11px,hsla(0,0%,100%,0.6) 92% );
	background: -ms-linear-gradient(90deg,#fff,#aaa 0px,#ccc 11px,hsla(0,0%,100%,0.6) 92% );
	background: -webkit-linear-gradient(90deg,#fff,#aaa 0px,#ccc 11px,hsla(0,0%,100%,0.6) 92% );
}
</style>
<body>	
<form id="loginForm" method="post" >
	<div id="loginDiv">
		<h2>Login</h2>
		<div id="messageDiv">
			로그인 하시려면 아이디와 비밀번호를 입력해주십시오.
		</div>
		<p><input type="text" id="inputId" name="j_username" placeholder="ID" required/></p>
		<p><input type="password" id="inputPw" name="j_password" placeholder="Password" required/></p>
		<div id="buttonDiv">
			<Button onclick="loginValidation()">Login</Button>
			<Button onclick="makeAccount()">Application</Button>
		</div>
	</div>
</form>

<script type="text/javascript">
function login(){
	var loginForm = document.getElementById('loginForm');
	loginForm.action = "${pageContext.request.contextPath}/j_spring_security_check";
	loginForm.submit();
}
function makeAccount(){
	location.href='${pageContext.request.contextPath}/accountPage.do';
}
function loginValidation(){
	if( $("#inputId").val() == "" ){
		alert("아이디를 입력해주십시오.");
	}else if( $("#inputPw").val() =="" ) {
		alert("비밀번호를 입력해주십시오." );
	}else{
		login();
	}
}
/* Login Exception */
if('${errorCheck}'=='true' ){ 
	if('${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}' == ''){
		alert('로그아웃 하셨습니다.');
	}else if('${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}' == 'Bad credentials' ){
		alert('비밀번호가 일치하지 않습니다.');
	}else if('${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}' == 'User is disabled' ){
		alert('승인되지 않은 사용자입니다.');
	}else{
		alert( '${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}' );		
	}
}
</script>
</body>
