
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="Write" />
<%@ include file="../common/head.jsp" %>
	<section class="mt-8 text-xl">
		<div class="container mx-auto px-3">
			<form action="doWrite" method="POST">
				<div class="table-box-type-1">
					<table>
						<colgroup>
							<col width="200" />
						</colgroup>
							<select name="boardId" >
							  <option value="2">자유게시판</option>
							  <option value="1">공지사항</option>
							</select>				
						<tbody>
							<tr>
								<th>제목</th>
								<td><input type="text" name="title" placeholder="제목을 입력해주세요"/></td>
							</tr>
							<tr>
								<th>내용</th>
								<td><textarea c name="body" placeholder="내용을 입력해주세요"></textarea></td>
							</tr>
							<tr>
								<td colspan="2"><button>작성</button></td>
							</tr>
						</tbody>
					</table>
				</div>
			</form>
			<div>
				<button  type="button" onclick="history.back();">뒤로가기</button>
			</div>
		</div>
	</section>
<%@ include file="../common/foot.jsp" %>