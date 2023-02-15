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
	User list:
	<c:forEach items="${userList}" var="user">
		${user}<br>
	</c:forEach>
	
	<c:forEach items="${couponList}" var="coupon">
		${coupon}<br>
	</c:forEach>
	Cart: ${cart.theValue}

	<span style="color:red">${errorMessage}</span><br>
<span style="color:green">${message}</span><br>
<form action="/confirmTransaction" method="get">
	<input type="number" name="userId" placeholder="User id">	
	<input type="number" name="couponId" placeholder="Coupon id">
	<input type="hidden" name="cartId" value="${cart.cartId }">
	<input type="submit" name="submit" value="go to checkout">
</form>

<form action="/showAllUserCoupons" method="get">
	<input type="text" name="userId" placeholder="User id">	
	<input type="submit" name="submit" value="Show all Coupons">
</form>

	<c:forEach items="${usersCoupons}" var="coupon">
	${coupon}
	<form action="/confirmTransaction" method="get">
	<input type="hidden" name="userId" value="${callingUser.userId }">	
	<input type="hidden" name="couponId" value="${coupon.couponId }">
	<input type="hidden" name="cartId" value="${cart.cartId }">
	<input type="submit" name="submit" value="Use this coupon">
</form>
	
		
	</c:forEach>

<form action="/showAllUserTransactions" method="get">
	<input type="text" name="userId" placeholder="User id">	
	<input type="submit" name="submit" value="Show all transactions">
</form>

	<c:forEach items="${usersTransaction}" var="transaction">
		${transaction}<br>
	</c:forEach>

</body>
</html>