<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:import url="layout.jsp">
	<c:param name="content">

		<c:if test="${loginFailure != null}">
			<div id="failure" class="alert alert-danger text-center">
				<c:out value="${loginFailure}" />
			</div>
		</c:if>

		<form action="index.html" method="post">
			
			<div class="input-group mb-3">
			  <span class="input-group-text" id="inputGroup-sizing-default">ユーザーID</span>
			  <input type="text"  name="user_id" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
			</div>
			
			<div class="input-group mb-3">
			  <span class="input-group-text" id="inputGroup-sizing-default">パスワード</span>
			  <input type="password" name="password" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default">
			</div>
			
			<div class="d-grid gap-2">
				<button type="submit" class="btn btn-primary" type="button">ログイン</button>
			</div>
			
		</form>

	</c:param>
</c:import>
