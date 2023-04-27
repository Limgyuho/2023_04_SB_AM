<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="${board.name } 게시판" />
<%@ include file="../common/head.jsp" %>
	<section class="mt-8 text-xl">
		<div class="container mx-auto px-3">
			<c:if test="${rq.getLoginedMemberId() != 0  }">
				<div class="mb-2 flex justify-end">
					<a class="btn-text-link btn btn-active btn-ghost" href="write">WRITE</a>
				</div>
			</c:if>
			<div class="table-box-type-1">
				<table class="table w-full">
					<thead>
						<tr>
							<th>번호</th>
							<th>날짜</th>
							<th>제목</th>
							<th>작성자</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="article" items="${articles }">
							<tr class="hover">
								<td>${article.id }</td>
								<td>${article.regDate.substring(2, 16) }</td>
								<td><a class="hover:underline" href="detail?id=${article.id }">${article.title }</a></td>
								<td>${article.writerName }</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</section>
<%@ include file="../common/foot.jsp" %>