<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<style type="text/css"> 
*{ 
  font-family: gulim; 
  font-size: 20px; 
} 
</style> 
<link href="${pageContext.request.contextPath}/css/style.css" rel="Stylesheet" type="text/css">
</head> 
<!-- *********************************************** -->
<body>
<!-- *********************************************** -->
 
<DIV class="title">로그인처리</DIV>
 
<div class = "content">
<c:choose>
	<c:when test="${flag}">
	로그인 되었습니다.<br><br>
	    <input type="button" value='홈으로' 
	    onclick ="location.href='${pageContext.request.contextPath}/index.jsp'">
	</c:when>
	<c:otherwise>
	아이디/비밀번호가 일치하지 않습니다.<br><br>
	    <input type="button" value='회원가입' 
    	onclick ="location.href='agree.do'">
        <input type='button' value='다시시도' onclick="history.back()">
	</c:otherwise>
</c:choose>

</div>
  

 
 
<!-- *********************************************** -->
</body>
<!-- *********************************************** -->
</html> 