<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:import url="layout.jsp">
	<c:param name="content">
		<h3>task_list</h3>

		<%-- 		<fmt:formatNumber value="1000" pattern="#,##0" /><br>
		<fmt:formatDate value="<%=new Date() %>" pattern="yyyy年MM月dd日  EE曜日  HH時mm分ss秒"/> --%>

		<c:if test="${loginSuccess != null}">
			<div id="success" class="alert alert-success text-center" role="alert">
				<c:out value="${loginSuccess}" />
			</div>
		</c:if>

		<div class="scroll-container">
			<table class="table table-striped">
				<thead class="sticky-top bg-white">
					<tr>
						<th scope="col">タスク名</th>
						<th scope="col">カテゴリ名</th>
						<th scope="col">ユーザー名</th>
						<th scope="col">状態</th>
						<th scope="col">期限</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody class="table-group-divider">
					<c:forEach var="task" items="${task_list}">
						<tr>
							<th scope="row"><c:out value="${task.name }" />
							</td>
							<td><c:out value="${task.categoryName}" /></td>
							<td><c:out value="${task.userName }" /></td>
							<td><c:out value="${task.statuName }" /></td>
							<td><fmt:formatDate value="${task.limitDate }" pattern="yyyy年MM月dd日" /></td>
							<td><a href="">詳細</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</c:param>
</c:import>