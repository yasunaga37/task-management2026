<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page import="java.util.Date" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>task_list.jsp</title>
</head>
<body>
	<h3>task_list</h3>
	${ fn:length(task_list) }
	
<%-- 		<fmt:formatNumber value="1000" pattern="#,##0" /><br>
		<fmt:formatDate value="<%=new Date() %>" pattern="yyyy年MM月dd日  EE曜日  HH時mm分ss秒"/> --%>
</body>
</html>