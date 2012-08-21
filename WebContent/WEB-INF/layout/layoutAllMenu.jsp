<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<tiles:insertAttribute name="include" />
<style type="text/css">
#leftMenu{
	width: 120px;
	height: 640px;
	padding: 30px;
	padding-top: 50px;
	
	border: #eee 1px solid
}
#main{
	width:1060px ;
	height:660px; 
	padding: 30px; 
	border-right: #eee 1px solid;
	border-bottom: #eee 1px solid;
}
</style>
</head>
<body>
	<table cellpadding="0" cellspacing="0">
		<tr><td><div><tiles:insertAttribute name="top" /></div></td></tr>
	</table>
	<table cellpadding="0" cellspacing="0">
		<tr>
			<td>
				<div id="leftMenu">
					<tiles:insertAttribute name="leftMenu" />
				</div>
			</td>
			<td>
			<div id="main"> 
				<tiles:insertAttribute name="main" />
			</div>
			</td>
		</tr>
	</table>
</body>
</html>