<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="Write" />
<%@ include file="../common/head.jsp" %>
<%@ include file="../common/toastUiEditorLib.jsp" %>
	<section class="mt-8 text-xl">
		<div class="container mx-auto px-3">
			<form action="doWrite" method="POST" onsubmit="submitForm(this); return false;">
				<input type="hidden" name="body" />
				<div class="table-box-type-1">
					<table class="table table-zebra w-full">
						<colgroup>
							<col width="200" />
						</colgroup>
						<tbody>
							<tr>
								<th>게시판</th>
								<td>
									<label>
										<input type="radio" name="boardId" value="1" checked/>
										&nbsp;공지사항
									</label>
									&nbsp;&nbsp;&nbsp;
									<label>
										<input type="radio" name="boardId" value="2"/>
										&nbsp;자유
									</label>
								</td>
							</tr>
							<tr>
								<th>제목</th>
								<td><input class="input input-bordered w-full" type="text" name="title" placeholder="제목을 입력해주세요"/></td>
							</tr>
							<tr>
								<th>내용</th>
								<td>
									<div class="toast-ui-editor">
      									<script type="text/x-template"></script>
    								</div>
								</td>
							</tr>
							<tr>
								<td colspan="2"><button class="btn-text-link btn btn-active">작성</button></td>
							</tr>
						</tbody>
					</table>
				</div>
			</form>
			<div class="btns mt-2">
				<button class="btn-text-link btn btn-active" type="button" onclick="history.back();">뒤로가기</button>
			</div>
		</div>
	</section>
<%@ include file="../common/foot.jsp" %>