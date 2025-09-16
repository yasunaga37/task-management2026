<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:import url="layout.jsp">
	<c:param name="content">

		<div class="row align-items-start">

			<%-- 1列目 --%>
			<div class="col bg-success p-1 text-dark bg-opacity-10 m-1">
				<form action="task-detail" method="post">
					<table class="table table-striped  table-bordered  table-layout-fixed">
						<thead class="table-active">
							<tr>
								<th scope="col" style="width: 120px;">タスクID</th>
								<th scope="col"><c:out value="${task.id}" /></th>
								<th scope="col" style="width: 90px;">カテゴリ</th>
								<th scope="col"><c:out value="${task.categoryName}" /></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<th scope="row">内容</th>
								<td colspan="3"><c:out value="${task.name}" /></td>
							</tr>
							<tr>
								<th scope="row">担当者</th>
								<td colspan="3"><c:out value="${task.userName}" /></td>
							</tr>
							<tr>
								<th scope="row">状況</th>
								<td colspan="3"><c:out value="${task.statusName}" /></td>
							</tr>
							<tr>
								<th scope="row">期限</th>
								<td colspan="3"><fmt:formatDate value="${task.limitDate}" pattern="yyyy年MM月dd日  (EE)" /></td>
							</tr>
							<tr>
								<th scope="row">メモ</th>
								<td colspan="3"><c:out value="${task.memo}" /></td>
							</tr>
							<tr>
								<th scope="row">登録日</th>
								<td colspan="3"><fmt:formatDate value="${task.createDatetime}" pattern="yyyy年MM月dd日  (EE)" /></td>
							</tr>
							<tr>
								<th scope="row">更新日</th>
								<td colspan="3"><fmt:formatDate value="${task.updateDatetime}" pattern="yyyy年MM月dd日  (EE)" /></td>
							</tr>
						</tbody>
					</table>

					<div class="d-flex justify-content-center">
					<input type="hidden" name="task_id" value="${task.id}">
						<button type="submit" class="btn btn-primary me-5" name="action" value="task_edit">編集</button>
						<button type="submit" class="btn btn-danger" name="action" value="task_delete">削除</button>
					</div>
				</form>
			</div>
			<%-- 1列目終わり --%>

			<%-- 2列目 --%>
			<div class="col bg-success p-1 text-dark bg-opacity-10 m-1">2列目</div>
			<%-- 2列目終わり --%>
		</div>

	</c:param>
</c:import>