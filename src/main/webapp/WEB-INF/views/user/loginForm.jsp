<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form action="/action_page.php">
		<div class="form-group">
			<label for="username">아이디</label> <input type="text" class="form-control" placeholder="아이디를 입력하세요." id="username">
		</div>
		<div class="form-group">
			<label for="password">비밀번호</label> <input type="password" class="form-control" placeholder="비밀번호를 입력하세요." id="password">
		</div>
		<div class="form-group form-check">
			<label class="form-check-label"> <input class="form-check-input" type="checkbox"> 계정 정보 저장
			</label>
		</div>
		<button type="submit" class="btn btn-primary">로그인</button>
	</form>
	<br />
</div>

<%@ include file="../layout/footer.jsp"%>