<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>ログインフォーム</h3>

	<c:if test="${loginFailure != null}">
		<div id="failure">	<c:out value="${loginFailure}" /></div>
	</c:if>

	<form action="index.html" method="post">
		<input type="text" name="user_id"><br> <input
			type="password" name="password"><br>
		<button>ログイン</button>
	</form>
</body>
</html>