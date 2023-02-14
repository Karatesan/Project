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
	Choose coupon:<br>
	<select name="coupon">
	<c:forEach items="${thisUserCoupons}" var="userCoupon"> 
	<option value="${userCoupon.couponId}">Value of discount: ${userCoupon.theValue}; Left times to use: ${userCoupon.counter}</option>
	</c:forEach>
	</select><br>
	<input type="submit" value="Submit">
	</form>
	
</body>
</html>