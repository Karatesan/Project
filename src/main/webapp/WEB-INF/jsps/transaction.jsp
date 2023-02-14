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
<span style="color:red">${errorMessage}</span>
	<!-- <form action="/submitUserThanCoupon" method="post"> -->
	The old price was: ${oldCart}      The price after using the coupon is ${cart} 
	You choose this coupon : ${usedCoupon.couponId} with value ${usedCoupon.theValue} <br>
	the number of use of this coupon is ${counter}
	
	
	
	

	
	
	
	
	
</body>
</html>