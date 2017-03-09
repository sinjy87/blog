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
<link href="${pageContext.request.contextPath }/css/style.css" rel="Stylesheet" type="text/css">
</head> 
<!-- *********************************************** -->
<body>
<!-- *********************************************** -->
 
<DIV class="title">회원탈퇴</DIV>
 
<div class = "content">
	<c:choose>
		<c:when test="${flag}">
		탈퇴되었습니다.
		</c:when>
		<c:otherwise>
		탈퇴를 실패했습니다
		</c:otherwise>
	</c:choose>
</div>
  
  <DIV class='bottom'>
    <input type="button" value='홈' 
    onclick="location.href='${pageContext.request.contextPath}/index.jsp'">
<!--     <input type='button' value='다시시도' onclick="history.back()"> -->
  </DIV>

 
 
<!-- *********************************************** -->
</body>
<!-- *********************************************** -->
</html> 