<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:import url="layout.jsp">
	<c:param name="content">

		<%-- 		<fmt:formatNumber value="1000" pattern="#,##0" /><br>
		<fmt:formatDate value="<%=new Date() %>" pattern="yyyy年MM月dd日  EE曜日  HH時mm分ss秒"/> --%>

		<%-- ログイン成功時のアラート --%>
		<c:if test="${loginSuccess != null}">
			<div id="success" class="alert alert-success alert-dismissible fade show text-center" role="alert">
				<c:set var="msg">${login_user.name}　様が${loginSuccess}。</c:set>
				<c:out value="${msg}" />
				<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
			</div>
		</c:if>
		
		<%-- タスク削除完了時のアラート --%>
		<c:if test="${deleteSuccess != null}">
			<div id="success" class="alert alert-danger alert-dismissible fade show text-center" role="alert">
				<c:out value="${deleteSuccess}" />
				<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
			</div>
		</c:if>

		<div class="scroll-container">
			<table class="table table-striped">
				<thead class="sticky-top bg-white">
					<tr>
						<th scope="col">タスク名</th>
						<th scope="col">カテゴリ名</th>
						<th scope="col">ユーザー名</th>
						<th scope="col">状況</th>
						<th scope="col">期限&nbsp;<a href="task-list?action=asc" class="text-decoration-none">▲</a> <a
							href="task-list?action=desc" class="text-decoration-none">▼</a>
						</th>
						<th scope="col"></th>
					</tr>
				</thead>
				<tbody class="table-group-divider">
					<c:choose>
						<c:when test="${fn:length(task_list) == 0 }">
							<tr>
								<td colspan="6" class="text-center">登録されているタスクはありません。</td>
							</tr>
						</c:when>
						<c:otherwise>
							<c:forEach var="task" items="${task_list}">
								<c:if test="${task.deleteFlag == 0 }">
									<tr>
										<th scope="row"><c:out value="${task.name }" />	</th>
										<td><c:out value="${task.categoryName}" /></td>
										<td><c:out value="${task.userName }" /></td>
										<td><c:out value="${task.statusName }" /></td>
										<td><fmt:formatDate value="${task.limitDate }" pattern="yyyy年MM月dd日" /></td>
										<td><a href="task-detail?task_id=${task.id }">詳細</a></td>
									</tr>
								</c:if>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>
		</div>
	</c:param>
</c:import>