<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:import url="layout.jsp">
	<c:param name="content">
		<%-- 		<h3>タスク詳細</h3>
		${task.id }<br>
		${task.name }<br> --%>

		<div class="row align-items-start">
			<div class="col bg-success p-1 text-dark bg-opacity-10 m-1">
				<%-- 				<div class="d-flex justify-content-center">
					<h7>タスク番号：<c:out value="${task.id}"/></h7>
				</div> --%>

					<table class="table table-striped  table-bordered  table-layout-fixed">
						<thead class="table-active">
							<tr>
								<th scope="col" style="width: 120px;">タスクID</th>
								<th scope="col"><c:out value="${task.id}" /></th>
								<th scope="col" style="width: 90px;">カテゴリ</th>
								<th scope="col"><c:out value="${task.categoryName}" /></th>
							</tr>
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
					<button type="submit" class="btn btn-primary me-5" name="action" value="task_edit">編集</button>
					<button type="submit" class="btn btn-danger" name="action" value="task_delete">削除</button>
				</div>


			</div>

			<div class="col bg-success p-1 text-dark bg-opacity-10 m-1">2列目</div>
		</div>

	</c:param>
</c:import>