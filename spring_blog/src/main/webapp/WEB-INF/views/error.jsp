<%@ page contentType="text/html; charset=UTF-8" %> 
<%  request.setCharacterEncoding("utf-8");
    String root = request.getContextPath();
    boolean flag = (Boolean) request.getAttribute("flag");
%> 
 
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
 
<DIV class="title">글쓰기</DIV>
 
<div class="content">
데이터베이스 처리중 오류가 발생했습니다.<br>
잠시후에 다시한번 시도하세요.
</div>
<DIV class='bottom'>
  <input type='button' value='다시시도' onclick="history.back()">
</DIV>


</body>
</html> 