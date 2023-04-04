
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title></title>
  <link rel="stylesheet" href="<c:url value="/resources/css/main.css"/>" />
  <style>
    textarea {
      all: inherit;
      border: 1px solid #f56a6a;
    }
  </style>
</head>
<body>
<c:import url="/WEB-INF/views/common/menubar.jsp" />
<br>
<br>


  <center>
    <form action="req.do" method="POST">
      <input type="hidden" name="user_id" value="${sessionScope.loginUser.user_id}"/>
      <input type="hidden" name="communityid" value="${communityid}"/>
      <label for="request_des">${communityname} &nbsp; 가입 신청</label><br>
      <textarea id="request_des" name="requestdes" rows="5" cols="50"></textarea><br><br>
      <input type="submit" value="신청">
    </form>
  </center>

<br>
<br>
<c:import url="/WEB-INF/views/common/footer.jsp" />
</body>
</html>
