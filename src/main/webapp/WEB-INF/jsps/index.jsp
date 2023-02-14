<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1DProc_JN</title>
</head>
<body>
	<c:forEach items="${users}" var="user"> 
	<form action="/checkUserId" method="post">
	Choose userId:<br>
	<select name="userId" value="${users.userId }"></select><br><br>
	</form>
	</c:forEach>

</body>
</html>