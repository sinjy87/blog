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
<script>
function use() {
	opener.frm.email.value='${email}';
	window.close();
}
</script> 
<link href="${pageContext.request.contextPath }/css/style.css" rel="Stylesheet" type="text/css">
</head> 
<!-- *********************************************** -->
<body>
<!-- *********************************************** -->
 
<DIV class="title">이메일 중복확인</DIV>
   <div class ="content">
   입력된 Email = ${email}<br><br>
   <c:choose>
   	<c:when test="${flag}">
   	중복된 이메일 입니다.<br><br>
   	</c:when>
   	<c:otherwise>
   	사용가능한 이메일 입니다.<br><br>
   	<input type = 'button' value='이메일 사용' onclick='use()'>
   	</c:otherwise>
   </c:choose>
   </div>
  <DIV class='bottom'>
    <input type='button' value='다시시도' onclick="location.href='email_form'">
    <input type='button' value='닫기' onclick="window.close()">
  </DIV>
 
 
<!-- *********************************************** -->
</body>
<!-- *********************************************** -->
</html> 