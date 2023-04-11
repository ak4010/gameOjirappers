<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="currentPage" value="${ requestScope.currentPage }"/>

<!DOCTYPE html>
<html>
<head>

	<meta charset="UTF-8">

	<title></title>

	<style>
		table {
			border-collapse: collapse;
			width: 40%;
			margin: auto;
		}

		th, td {
			padding: 10px;
			text-align: center;
			border: 1px solid #ddd;
		}

		th {
			background-color: #f2f2f2;
		}

		td:first-child {
			font-weight: bold;
		}

		button {
			-moz-appearance: none;
			-webkit-appearance: none;
			-ms-appearance: none;
			appearance: none;
			-moz-transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
			-webkit-transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
			-ms-transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
			transition: background-color 0.2s ease-in-out, color 0.2s ease-in-out;
			background-color: transparent;
			border-radius: 0.375em;
			border: 0;
			box-shadow: inset 0 0 0 2px #f56a6a;
			color: #f56a6a !important;
			cursor: pointer;
			display: inline-block;
			font-family: "Roboto Slab", serif;
			font-size: 0.8em;
			font-weight: 700;
			height: 3.5em;
			letter-spacing: 0.075em;
			line-height: 3.5em;
			padding: 0 2.25em;
			text-align: center;
			text-decoration: none;
			text-transform: uppercase;
			white-space: nowrap;
		}


		a {
			background-color: #008CBA;
			color: white;
			border: none;
			padding: 10px 20px;
			text-align: center;
			text-decoration: none;
			display: inline-block;
			font-size: 14px;
			margin: 4px 2px;
			cursor: pointer;
		}

	</style>
	<script>

	</script>
</head>
<body>
<c:import url="/WEB-INF/views/common/menubar.jsp"/>
<br>
<br>
<center>

	<h2>${boardTar.board_no}번 게시물</h2>
	<h4>조회수 : [${boardTar.board_count}]</h4>
	<h4>좋아요 수 : [${boardTar.board_like}]</h4> &nbsp;
	<br>
	<form action="boardTarUpdate.do" method="post" enctype="multipart/form-data">
		<input type="hidden" name="board_no" value="${boardTar.board_no}">
		<input type="hidden" name="name" value="${name}">
		<input type="hidden" name="user_id" value="${boardTar.user_id}">
		<input type="hidden" name="board_date" value="${boardTar.board_date}">
		<input type="hidden" name="appid" value="${appid}">
	<table>

		<tr>
			<th>제목</th>
			<td><input value="${boardTar.board_title}" name="board_title"/></td>
		</tr>

		<tr>
			<th>게임이름</th>
			<td>${name}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${boardTar.user_id}</td>
		</tr>
		<tr>
			<th>작성날짜</th>
			<td>${boardTar.board_date}</td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td>
				<c:if test="${!empty boardTar.board_orifile}">
					${boardTar.board_orifile} &nbsp; <input type="checkbox" name="delflag" value="yes"> 파일삭제<br>
					<input type="hidden" name="refile" value="${boardTar.board_refile}">
				</c:if>
				<input type="file" name="upfile" value="새로 첨부">
			</td>
		</tr>
		<tr>
			<th>내용</th>
			<td><textarea name="board_content"></textarea></td>
		</tr>
		<tr>
			<td colspan="2">
				<button type="submit">수정</button> &nbsp; &nbsp;
				<c:url var="bdt" value="/movetarboarddetail.do">
					<c:param name="board_no" value="${boardTar.board_no}"/>
					<c:param name="page" value="${page}"/>
					<c:param name="appid" value="${appid}"/>
					<c:param name="name" value="${name}"/>
				</c:url>
				<button type="reset" onclick="javascript:location.href='${bdt}'">취소</button> &nbsp; &nbsp;
				<c:url var="btl" value="/movegameboard.do">
					<c:param name="page" value="${page}"/>
					<c:param name="appid" value="${appid}"/>
				</c:url>
				<a href="${btl}">목록으로</a>
			</td>
		</tr>
	</table>
</form>
</center>


</form>
<br>
<br>
<c:import url="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>