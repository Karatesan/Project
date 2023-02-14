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
	<form action="/" method="get">
	Choose userId:<br>
	<select name="userId">
	<c:forEach items="${usersFromDB}" var="userFromDB"> 
	<option value="${userFromDB.userId}">${userFromDB.userId}</option>
	</c:forEach>
	</select><br><br>
	</form>
	

</body>
</html>