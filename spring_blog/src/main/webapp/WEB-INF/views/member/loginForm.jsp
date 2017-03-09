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
 
<DIV class="title">로그인</DIV>
 
<FORM name='frm' method='POST' action='${pageContext.request.contextPath }/member/login'>
   <input type="hidden" name="bflag" value="${param.bflag}">
  <input type="hidden" name="nowPage" value="${nowPage}">
  <input type="hidden" name="nPage" value="${nPage}">
  <input type="hidden" name="col" value="${param.col}">
  <input type="hidden" name="word" value="${param.word}">
  <!-- no-> pk(bbsno값,memono값...) -->
  <input type="hidden" name="no" value="${param.no}">
  <!-- ino->식별자(pk이름: 'bbsno',memono -->
  <input type="hidden" name="ino" value="${param.ino}">
  <TABLE>
  
    <TR>
      <TH>아이디</TH>
      <TD>
      <input type = "text" name="id" value='${c_id_val}'>
      <c:choose>
      	<c:when test="${c_id == 'Y' }">
      	         <input type='checkbox' name='c_id' value='Y' checked='checked'> ID 저장 
      	</c:when>
      	<c:otherwise>
      	         <input type='checkbox' name='c_id' value='Y' > ID 저장 
      	</c:otherwise>
      </c:choose>
      </TD>
    </TR>
    
    <TR>
      <TH>비밀번호</TH>
      <TD><input type = "password" name = "passwd"></TD>
    </TR>
    
  </TABLE>
  
  <DIV class='bottom'>
    <input type='submit' value='로그인'>
    <input type='button' value='회원가입' onclick="location.href='agree'">
  </DIV>
</FORM>
 
 
<!-- *********************************************** -->
</body>
<!-- *********************************************** -->
</html> 