<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:import url="layout.jsp">
	<c:param name="content">
	<h6 class="text-center">新規タスク登録</h6>

		<%-- 	${fn:length(category_list) } --%>
		<form action="task-insert" method="post">
			<table class="table table-striped  table-bordered  table-layout-fixed">
				<thead class="table-active">
					<tr>
						<th scope="col" style="width: 90px;">タスクID</th>
						<th scope="col" style="width: 300px;"><c:out value="${task_id}" /></th>
						<th scope="col" style="width: 90px;">カテゴリ</th>
						<th scope="col" style="width: 300px;"><select name="category">
								<option value="" selected="selected" disabled>選択してください</option>
								<c:forEach var="category" items="${category_list}">
									<option value="${category.id }"><c:out value="${category.name }" /></option>
								</c:forEach>
						</select></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th scope="row">内容</th>
						<td><input type="text" name="task_name" class="w-100"></td>
						<th scope="row">担当者</th>
						<td><select name="user">
								<c:forEach var="user" items="${user_list}">
									<c:choose>
										<c:when test="${login_user.name == user.name }">
											<option value="${user.id }" selected="selected"><c:out value="${user.name }" /></option>
										</c:when>
										<c:otherwise>
											<option value="${user.id }"><c:out value="${user.name }" /></option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
						</select></td>
					</tr>
					<tr>
						<th scope="row">状況</th>
						<td><select name="status">
								<c:forEach var="status" items="${status_list}">
									<option value="${status.code}"><c:out value="${status.name}" /></option>
<%-- 									<c:choose>
										<c:when test="${task.statusName == status.name }">
											<option value="${status.code}" selected="selected"><c:out value="${status.name}" /></option>
										</c:when>
										<c:otherwise>
											<option value="${status.code}"><c:out value="${status.name}" /></option>
										</c:otherwise>
									</c:choose> --%>
								</c:forEach>
						</select></td>
						<th scope="row">期限</th>
						<td><input type="date" name="limit_date" value="${task.limitDate}"></td>
					</tr>
					<tr>
						<th scope="row">メモ</th>
						<td colspan="3"><textarea class="form-control" name="memo" rows="3"><c:out value="${task.memo}" /></textarea></td>
					</tr>
					<tr>
						<th scope="row">登録日</th>
						<td><fmt:formatDate value="${today}" pattern="yyyy年MM月dd日  (EE)" /></td>
<%-- 						<th scope="row">更新日</th>
						<td><fmt:formatDate value="${task.updateDatetime}" pattern="yyyy年MM月dd日  (EE)" /></td> --%>
					</tr>
				</tbody>
			</table>
			<div class="d-flex justify-content-center">
				<input class="btn btn-secondary me-5"" type="reset" value="Reset">
				<input type="hidden" name="task_id" value="${task_id}"> 
				<a class="btn btn-primary me-5" data-bs-toggle="modal" data-bs-target="#myModal" href="#">作成</a>
			</div>

			<!-- Modal -->
			<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						</div>

						<div class="modal-body">この内容で作成してもよろしいですか？</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
							<input type="hidden" name="task_id" value="${task_id}">
							<input type="hidden" name="today" value="${today}">
							<button type="submit" class="btn btn-primary" name="action" value="execute_insert">作成</button>
						</div>
					</div>
				</div>
			</div>
		</form>

<!-- 		<script type="text/javascript">
			window.onload = function() {
				var today = new Date();
				today.setDate(today.getDate());
				var yyyy = today.getFullYear();
				var mm = ("0" + (today.getMonth() + 1)).slice(-2);
				var dd = ("0" + today.getDate()).slice(-2);
				document.getElementById("today").value = yyyy + '-' + mm + '-'
						+ dd;
			}
		</script> -->

	</c:param>
</c:import>