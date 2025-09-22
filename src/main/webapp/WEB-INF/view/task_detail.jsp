<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<c:import url="layout.jsp">
	<c:param name="content">

		<div class="row align-items-start">

			<%-- 1列目 --%>
			<div class="col bg-success p-1 text-dark bg-opacity-10 m-1">

				<%-- requestスコープに "successMessage" が存在する場合のみ、アラートを表示する --%>
				<c:if test="${not empty successMessage}">
					<div id="success" class="alert alert-success alert-dismissible fade show text-center" role="alert">
						<c:out value="${successMessage}" />
						<button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
					</div>
				</c:if>
				<%-- requestスコープに "successMessage" が存在する場合のみ、アラートを表示する 終わり --%>

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
								<td colspan="3" class="text-start" style="white-space: pre-wrap;"><c:out value="${task.memo}" /></td>
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
						<a class="btn btn-danger me-5" data-bs-toggle="modal" data-bs-target="#myModal" href="#">削除</a>
					</div>

					<!-- Modal -->
					<div class="modal fade" id="myModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									この情報を削除してもよろしいですか？
									<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
								</div>

								<div class="modal-body">
									タスクID：<c:out value="${task.id}" /><br>
									カテゴリ：<c:out value="${task.categoryName}" /><br>
									内容：<c:out value="${task.name}" /><br>
									担当者：<c:out value="${task.userName}" /><br>
									状況：<c:out value="${task.statusName}" /><br>
									期限：<fmt:formatDate value="${task.limitDate}" pattern="yyyy年MM月dd日  (EE)" /><br>
									メモ：<c:out value="${task.memo}" /><br>									
									登録日：<fmt:formatDate value="${task.createDatetime}" pattern="yyyy年MM月dd日  (EE)" /><br>
									更新日：<fmt:formatDate value="${task.updateDatetime}" pattern="yyyy年MM月dd日  (EE)" />
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
									<input type="hidden" name="task_id" value="${task.id}">
									<button type="submit" class="btn btn-danger" name="action" value="task_delete">削除</button>
								</div>
							</div>
						</div>
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