<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title></title>
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/jquery-3.6.3.min.js"></script>
</head>
<script type="text/javascript">
	
	function showReportContent() {
		
	}
</script>
<body>
<h1>�Ű�۸���Ʈ(������) ������</h1>
 <table align="center" width="500" border="1" cellspacing="0" cellpadding="1">
 	<tr>
 		<th>�Ű��ھ��̵�</th>
 		<th>�ǽŰ��ھ��̵�</th>
 		<th>�Ű�¥</th>
 		<th></th>
 	</tr>
 	<c:forEach items="${ requestScope.list }" var="r">
 		<tr>
<%--  			<td>${  }</td> --%>
<%--  			<td>${  }</td> --%>
<%--  			<td>${  }</td> --%>
 			<td><button onclick="showReportContent();">�� ����</button></td>
 		</tr>
 	</c:forEach>
 </table>
 <form name=""  id="">
 	
<%--  	<textarea rows="" cols="" readonly>${  }</textarea><br> --%>
 	&nbsp; <button onclick="">�ݱ�</button>
 </form>
 
 
 	<!-- Footer -->
<footer id="footer">
	<p class="copyright">&copy; Untitled. All rights reserved. Demo Images: <a href="https://unsplash.com">Unsplash</a>. Design: <a href="https://html5up.net">HTML5 UP</a>.</p>
</footer>
</body>
</html>