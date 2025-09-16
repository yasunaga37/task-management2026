<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:import url="layout.jsp">
	<c:param name="content">

		<%-- 	${fn:length(category_list) } --%>
		<form action="task-edit" method="post">
			<table class="table table-striped  table-bordered  table-layout-fixed">
				<thead class="table-active">
					<tr>
						<th scope="col" style="width: 90px;">タスクID</th>
						<th scope="col" style="width: 300px;"><c:out value="${task.id}" /></th>
						<th scope="col" style="width: 90px;">カテゴリ</th>
						<th scope="col" style="width: 300px;"><select name="category">
								<c:forEach var="category" items="${category_list}">
									<c:choose>
										<c:when test="${task.categoryName == category.name }">
											<option value="${category.id }" selected="selected"><c:out value="${category.name }" /></option>
										</c:when>
										<c:otherwise>
											<option value="${category.id }"><c:out value="${category.name }" /></option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
						</select></th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<th scope="row">内容</th>
						<td><input type="text" name="task_name" value="${task.name}"></td>
						<th scope="row">担当者</th>
						<td><select name="user">
								<c:forEach var="user" items="${user_list}">
									<c:choose>
										<c:when test="${task.userName == user.name }">
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
									<c:choose>
										<c:when test="${task.statusName == status.name }">
											<option value="${status.code}" selected="selected"><c:out value="${status.name}" /></option>
										</c:when>
										<c:otherwise>
											<option value="${status.code}"><c:out value="${status.name}" /></option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
						</select></td>
						<th scope="row">期限</th>
						<td><fmt:formatDate value="${task.limitDate}" pattern="yyyy年MM月dd日  (EE)" /></td>
					</tr>
					<tr>
						<th scope="row">メモ</th>
						<td colspan="3"><textarea class="form-control" name="memo" rows="3"><c:out value="${task.memo}" /></textarea></td>
					</tr>
					<tr>
						<th scope="row">登録日</th>
						<td><fmt:formatDate value="${task.createDatetime}" pattern="yyyy年MM月dd日  (EE)" /></td>
						<th scope="row">更新日</th>
						<td><fmt:formatDate value="${task.updateDatetime}" pattern="yyyy年MM月dd日  (EE)" /></td>
					</tr>
				</tbody>
			</table>
			<div class="d-flex justify-content-center">
				<a href="task-detail?task_id=${task.id}" class="btn btn-secondary me-5">戻る</a> 
				<input type="hidden" name="task_id" value="${task.id}">
<!-- 				<button type="submit" class="btn btn-primary me-5" name="action" value="execute_edit">編集</button> -->
				<a class="btn btn-primary me-5" data-bs-toggle="modal" 	data-bs-target="#myModal" href="#">編集</a>
<!-- 				<a class="navbar-brand" data-bs-toggle="modal" 	data-bs-target="#myModal" href="#">タスク管理システム</a> -->
				
				
			</div>

			<!-- Modal -->
			<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<!-- 			<form id="modalForm" action="index.html" method="get"> -->
					<div class="modal-content">
						<div class="modal-header">
							<!-- 						<h1 class="modal-title fs-5" id="exampleModalLabel">Modal title</h1> -->
							<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
						</div>

						<div class="modal-body">
							この内容で編集してもよろしいですか？
							<!-- 							<input type="hidden" class="form-control"  name="action" value="logout"> -->
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
							<input type="hidden" name="task_id" value="${task.id}">
							<button type="submit" class="btn btn-primary" name="action" value="execute_edit">編集</button>
						</div>
					</div>
					<!-- 			</form> -->
				</div>
			</div>
		</form>


	</c:param>
</c:import>