<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>1DProj_JN</title>
</head>
<body>
	<form action="/submitUserThenCoupon" method="post">
	Choose user:<br>
	<select name="userId">
	<c:forEach items="${usersFromDB}" var="userFromDB"> 
	<option value="${userFromDB.userId}">UserId: ${userFromDB.userId}; First name: ${userFromDB.firstName}; Last name: ${userFromDB.lastName}</option>
	</c:forEach>
	</select><br><br>
	<input type="submit" value="Submit">
	</form>

</body>
</html>