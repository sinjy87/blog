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
비밀번호 오류 입니다.<br>
비밀번호를 다시 입려하세요.
</div>
<DIV class='bottom'>
  <input type='button' value='다시시도' onclick="history.back()">
</DIV>


</body>
</html> 