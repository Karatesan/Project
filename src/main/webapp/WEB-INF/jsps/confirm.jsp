<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	User : ${user.firstName}<br>
	Cart value: ${ cart.theValue}
	Coupon used: ${coupon.theValue }


<span style="color:red">${errorMessage}</span><br>

<form action="/createTransaction" method="post">
	<input type="hidden" name="userId" value="${user.userId }">
	<input type="hidden" name="couponId" value="${coupon.couponId }">
	<input type="hidden" name="cartId" value="${cart.cartId }">
	<input type="submit" name="submit" value="Submit Coupon Redeem">
</form>

</body>
</html>