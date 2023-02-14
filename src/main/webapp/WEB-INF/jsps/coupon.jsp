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
	
	Value of Cart: ${cart}
	
	

	<!-- <form action="/submitUserThenCoupon" method="post">  -->
	Choose coupon:<br>
	
	<c:forEach items="${thisUserCoupons}" var="userCoupon"> 
	<option value="${userCoupon.couponId}">Value of discount: ${userCoupon.theValue}; Left times to use: ${userCoupon.counter}</option>
	<form action="/goToTransaction" method="post">
	<input type="hidden" name="couponId" value="${userCoupon.couponId}">
	<input type="submit" value="Submit">
	</form><br><br>
	
	</c:forEach>
	
	
	
	
</body>
</html>