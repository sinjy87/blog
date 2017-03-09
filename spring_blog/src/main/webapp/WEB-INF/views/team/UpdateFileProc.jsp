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
<script type="text/javascript">
function tread() {
	var url = "read.do";
	url += "?no=${no}";
	url += "&col=${col}";		
	url += "&word=${word}";		
	url += "&nowPage=${nowPage}";		
	location.href=url;
}
</script>
<link href="${pageContext.request.contextPath}/css/style.css" rel="Stylesheet" type="text/css">
</head> 
<!-- *********************************************** -->
<body>
<!-- *********************************************** -->
 
<DIV class="title">사진변경</DIV>
 <div class="content">
 <c:choose>
 	<c:when test="${flag}">
 	 out.print("사진을 변경했습니다.<br>");%>
	 <img src = "${pageContext.request.contextPath }/views/team/storage/${filename}">
 	</c:when>
 	<c:otherwise>
 		 out.print("사진번경을 실패했습니다.");
 	</c:otherwise>
 </c:choose>

 </div>

  
  <DIV class='bottom'>
    <input type='button' value='조회' onclick="tread()">
    <input type='button' value='다시시도' onclick="history.back()">
  </DIV>

 
 
<!-- *********************************************** -->
</body>
<!-- *********************************************** -->
</html> 