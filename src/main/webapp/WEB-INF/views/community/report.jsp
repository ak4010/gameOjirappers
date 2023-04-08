<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value='/resources/css/main.css'/>" />
<script type="text/javascript" src="${pageContext.servletContext.contextPath}/resources/js/jquery-3.6.3.min.js"></script>
    <style>

    </style>
</head>
<body>
<c:import url="/WEB-INF/views/common/menubar.jsp"/>
<br>
<br>

<center>
    <form action="reportcommu.do" method="post">
        <input type="hidden" name="user_id" value="${loginUser.user_id}">
        <input type="hidden" name="communityId" value="${communityid}">

        <h3 style="align-items: center">커뮤니티 신고</h3>
        <table>
            <tr><th>신고자 아이디</th><td>${loginUser.user_id}</td></tr>
            <tr><th>신고 대상 커뮤니티</th><td>${communityname}</td></tr>
            <tr><th>신고 유형</th>
                <td>
                    <select name="c_rep_type">
                        <option value="부적절한이름" selected>부적절한 이름</option>
                        <option value="폭력적">폭력적</option>
                        <option value="불법/음란물">불법/음란물</option>
                        <option value="지나친 광고">지나친 광고</option>
                    </select>
                </td>
            </tr>
            <tr><td colspan="2"><textarea rows="5" cols="30" name="c_report_desc"></textarea></td></tr>
            <tr><td colspan="2"><input type="submit" value="신고"></td></tr>
        </table>

    </form>
</center>

<br>
<br>
<c:import url="/WEB-INF/views/common/footer.jsp"/>
</body>
</html>