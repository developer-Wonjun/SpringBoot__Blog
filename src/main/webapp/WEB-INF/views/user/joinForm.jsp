<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form>
		<div class="form-group">
			<label for="username">아이디</label>
			<input type="text" class="form-control" placeholder="아이디를 입력하세요." id="username">
		</div>
		<div class="form-group">
			<label for="email">이메일</label>
			<input type="email" class="form-control" placeholder="이메일을 입력하세요." id="email">
		</div>
		<div class="form-group">
			<label for="password">비밀번호</label>
			<input type="password" class="form-control" placeholder="비밀번호를 입력하세요." id="password">
		</div>


	</form>
	<button id="btn-save" class="btn btn-primary">회원가입</button>
	<br />
</div>

<script src="/js/user.js"></script>
<%@ include file="../layout/footer.jsp"%>