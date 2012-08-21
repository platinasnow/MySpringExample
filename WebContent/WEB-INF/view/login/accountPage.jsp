<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<style type="text/css">
#reg{
	position:relative;
	margin-left : -260px;
	margin-top:120px;
	left:50%;
	width: 520px;
	height: 420px;
	border: #eee 1px solid;
	border-radius : 25px;
	padding: 20px;
	box-shadow:inset 0px 0px 3px #cdcdcd;
	background: -moz-linear-gradient(90deg,#f2f2f2,#fff 2px,#f0f0f0 5px,hsla(0,0%,100%,0.8) 62% );
	background: -webkit-linear-gradient(90deg,#f2f2f2,#fff 2px,#f0f0f0 5px,hsla(0,0%,100%,0.8) 62% );
	background: -ms-linear-gradient(90deg,#f2f2f2,#fff 2px,#f0f0f0 5px,hsla(0,0%,100%,0.8) 62% );
	
}
#reg h2{
	font-family: 'Sansita One';
	padding-bottom: 7px;
	border-bottom: 1px #ddd solid;
	padding-left:5px;
}
body{
	font-family: 'NanumGothicWeb';
}
#preFont{
	color:red;
	margin: 3px;
}
#reg input{
	width:16em;
	border: 1px #cdcdcd solid;
	padding: 0.7em 0.5em 0.7em 1.5em;
	box-shadow:inset 0px 0px 3px #dcdcdc;
	border-radius:5px;
	background: #eee;
	margin-left: 5px;
}
#reg input:focus{
	width:17em;
	background: #fff;
}
#buttonDiv{
	text-align: center;
	margin-top: 15px;
}
#agreeButton{
	font-size: 15px;
	font-family: 'Sansita One';
	border: 1px #aaa solid;
	box-shadow:inset 0px 0px 3px #dcdcdc;
	border-radius:15px;
	width: 100px;
	height: 50px;
	background: -moz-linear-gradient(90deg,#fff,#aaa 0px,#ccc 11px,hsla(0,0%,100%,0.6) 92% );
	background: -ms-linear-gradient(90deg,#fff,#aaa 0px,#ccc 11px,hsla(0,0%,100%,0.6) 92% );
	background: -webkit-linear-gradient(90deg,#fff,#aaa 0px,#ccc 11px,hsla(0,0%,100%,0.6) 92% );
	margin-right: 15px;
}
#agreeButton:focus{
	background: -moz-linear-gradient(90deg,#fff,#caa 0px,#ecc 11px,hsla(0,0%,100%,0.6) 92% );
	background: -webkit-linear-gradient(90deg,#fff,#caa 0px,#ecc 11px,hsla(0,0%,100%,0.6) 92% );
	background: -ms-linear-gradient(90deg,#fff,#caa 0px,#ecc 11px,hsla(0,0%,100%,0.6) 92% );
}
#backButton{
	font-size: 15px;
	font-family: 'Sansita One';
	border: 1px #aaa solid;
	box-shadow:inset 0px 0px 3px #dcdcdc;
	border-radius:15px;
	width: 100px;
	height: 50px;
	background: -moz-linear-gradient(90deg,#fff,#aaa 0px,#ccc 11px,hsla(0,0%,100%,0.6) 92% );
	background: -webkit-linear-gradient(90deg,#fff,#aaa 0px,#ccc 11px,hsla(0,0%,100%,0.6) 92% );
	background: -ms-linear-gradient(90deg,#fff,#aaa 0px,#ccc 11px,hsla(0,0%,100%,0.6) 92% );
}
#backButton:focus{
	background: -moz-linear-gradient(90deg,#fff,#caa 0px,#ecc 11px,hsla(0,0%,100%,0.6) 92% );
	background: -webkit-linear-gradient(90deg,#fff,#caa 0px,#ecc 11px,hsla(0,0%,100%,0.6) 92% );
	background: -ms-linear-gradient(90deg,#fff,#caa 0px,#ecc 11px,hsla(0,0%,100%,0.6) 92% );
}
#attention{
	font-size: 11px;
	float:right;
	padding-top: 8px;
}
.errorMessage{
	color:red;
	font-size:11px;
	visibility: hidden;
}
</style>
<body>
	
	<div id="reg">
		<h2> Registration </h2>
		<form id="regForm" method="post">
			<p>
				<input type="text" id="userId" name="userId" placeholder="아이디" maxlength="16" required />
				<label id="preFont">*</label>
				<label id="attention">아이디는 8자에서 16자 사이만 가능합니다.
					<br/><span id="userIdErrorMessage" class="errorMessage"></span>
				</label>
			</p>
			<p>
				<input type="password" id="password" name="password" placeholder="비밀번호" maxlength="16" required/>
				<label id="preFont">*</label><label id="attention">비밀번호는 8자에서 16자 사이만 가능합니다.</label>
			</p>
			<p>
				<input type="password" id="repassword" placeholder="비밀번호 재입력" maxlength="16" required/>
				<label id="preFont">*</label>
				<label id="attention"><span id="passwordErrorMessage" class="errorMessage">비밀번호가 일치하지 않습니다.</span></label>
			</p>
			<p>
				<input type="text" id="name" name="name" placeholder="이름" maxlength="16" required/>
				<label id="preFont">*</label>
				<label id="attention"><span id="nameErrorMessage" class="errorMessage">이름을 입력해 주십시오.</span></label>
			</p>
			<p>
				<label id="preFontNone"> </label><input type="text" id="email" maxlength="16" placeholder="E-mail" />
			</p>
			<div>
				<label id="preFont">* 항목은 필수로 입력하셔야 합니다.</label>
			</div>
			<div id="buttonDiv">
				<Button id="agreeButton" onclick="validation()">Application</Button>
				<Button id="backButton" onclick="back()">Back</Button>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript">
var idCheck = 0;
function back(){
	history.back();
}
function makeAccount(){
	var regForm = document.getElementById('regForm');
	regForm.action='${pageContext.request.contextPath}/makeAccount.do';
	regForm.submit();
	alert("아이디가 생성되었습니다.");
}
function validation(){
	if($('#userId').val().length <8){
		alert("아이디의 길이가 8자 미만입니다.");
	}else if($('#userId').val().length >16 ){
		alert("아이디의 길이가 16자를 초과합니다");
	}else if($('#password').val().length <8){
		alert("비밀번호의 길이가 8자 미만입니다.");
	}else if($('#password').val().length >16){
		alert("비밀번호의 길이가 16자를 초과합니다.");
	}else if(idCheck == 0){
		alert("중복된 아이디입니다.");
	}else if(!($("#password").val() == $("#repassword").val()) ){
		alert("비밀번호가 일치하지 않습니다. 다시 확인해주십시오.");
	}else if( $("#name").val() =="" ){
		alert("이름을 입력해주십시오.");
	}else{
		makeAccount();
	}
	
}
$("#userId").focusout(function(){
	var userId = $("#userId").val(); 
	$.ajax({
		type:"POST",
		url: "${pageContext.request.contextPath}/system/validationUserId.do",
		data:{ userId:userId},
		success: function(data,textStatus){
			if(data=="EMPTY"){
				if($('#userId').val().length <8){
					idCheck = 0;
					$("#userIdErrorMessage").css("visibility","visible");
					$("#userIdErrorMessage").css("color","red");
					$("#userIdErrorMessage").text("아이디가 너무 짧습니다.");
				}else{
					idCheck = 1;
					$("#userIdErrorMessage").css("visibility","visible");
					$("#userIdErrorMessage").css("color","green");
					$("#userIdErrorMessage").text("이 아이디는 사용 가능합니다.");
				}
			}else{
				idCheck = 0;
				$("#userIdErrorMessage").css("visibility","visible");
				$("#userIdErrorMessage").css("color","red");
				$("#userIdErrorMessage").text("아이디가 이미 존재합니다.");
			}
		}
	});
});
$("#repassword").focusout(function(){
	if( !($("#password").val() == $("#repassword").val() ) ){
		$("#passwordErrorMessage").css("visibility","visible");
	}else{
		$("#passwordErrorMessage").css("visibility","hidden");
	}
});
$("#name").focusout(function(){
	if( $("#name").val() =="" ){
		$("#nameErrorMessage").css("visibility","visible");
	}else{
		$("#nameErrorMessage").css("visibility","hidden");
	}
});
</script>
