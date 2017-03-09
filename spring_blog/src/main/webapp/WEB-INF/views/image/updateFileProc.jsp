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

	function read(){
		var url = "./read";
		url += "?col=${param.col}";
		url += "&word=${param.word}";
		url += "&nowPage=${param.nowPage}";
		url += "&no=${param.no}";
		
		location.href=url;
		
	}


</script>


<link href="${pageContext.request.contextPath }/css/style.css" rel="Stylesheet" type="text/css">
</head> 
<!-- *********************************************** -->
<body>

<!-- *********************************************** -->
 
<DIV class="title">수정 결과</DIV>

<div class="content">
<c:choose>
	<c:when test="${flag}">
	수정이 성공적으로 되었습니다.
	</c:when>
	<c:otherwise>
	수정이 실패 했습니다.
	</c:otherwise>
	${oldfile }
</c:choose>
	
</div>
  
  <DIV class='bottom'>
  <c:choose>
  	<c:when test="${flag}">
  	<input type='button' value='확인' onclick="javascript:read()">
  	</c:when>
  	<c:otherwise>
  	    <input type='button' value='다시 시도' onclick="history.back()">
    	<input type='button' value='취소' onclick="javascript:read()">
  	</c:otherwise>
  </c:choose>
  </DIV>

 
 
<!-- *********************************************** -->

</body>
<!-- *********************************************** -->
</html> 
