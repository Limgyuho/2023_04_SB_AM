<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="pageTitle" value="MODIFY MEMBER" />
<%@ include file="../common/head.jsp" %>
	<section class="mt-8 text-xl">
		<div class="container mx-auto px-3">
			<form action="doModify" method="POST">
				<div class="table-box-type-1">
					<table class="table table-zebra w-full">
						<colgroup>
							<col width="200" />
						</colgroup>
						<tbody>
							<tr>
								<th>가입일</th>
								<td>${rq.loginedMember.regDate }</td>
							</tr>
							<tr>
								<th>로그인 아이디</th>
								<td>${rq.loginedMember.loginId }</td>
							</tr>
							<tr>
								<th>이름</th>
								<td>${rq.loginedMember.name }</td>
							</tr>
							<tr>
								<th>닉네임</th>
								<td><input class="input input-bordered w-full" type="text" name="nickname" value="${rq.loginedMember.nickname }" placeholder="닉네임을 입력해주세요"/></td>
							</tr>
							<tr>
								<th>전화번호</th>
								<td><input class="input input-bordered w-full" type="text" name="cellphoneNum" value="${rq.loginedMember.cellphoneNum }" placeholder="전화번호를 입력해주세요"/></td>
							</tr>
							<tr>
								<th>이메일</th>
								<td><input class="input input-bordered w-full" type="text" name="email" value="${rq.loginedMember.email }" placeholder="이메일을 입력해주세요"/></td>
							</tr>
						</tbody>
					</table>
				</div>
				<div class="btns mt-2 flex justify-between">
					<button class="btn-text-link btn btn-active" type="button" onclick="history.back();">뒤로가기</button>
					<div>
						<a class="btn-text-link btn btn-active mr-2" href="passwordModify">비밀번호 변경</a>
						<button class="btn-text-link btn btn-active">수정</button>
					</div>
				</div>
			</form>
		</div>
	</section>
<%@ include file="../common/foot.jsp" %>