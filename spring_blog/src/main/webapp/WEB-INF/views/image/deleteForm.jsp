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
<script type="text/javascript">
function a(f) {
	if(f.passwd.value==""){
		alert("비밀번호를 입력해 주세요");
		f.passwd.focus();
		return false;
	}
	
}
function b() {
	var url="list";
	url+="?col=${param.col}";
	url+="&word=${param.word}";
	url+="&nowPage=${param.nowPage}";
	location.href=url;
	
			}

</script>
 
 
 
</head> 
 

<!-- *********************************************** -->

<body>

<!-- *********************************************** -->
 
 
 
<DIV class="title">삭제 비밀번호 확인</DIV>
 <c:choose>
 	<c:when test="${flag }">
 	답변이 있는 글이므로 삭제 할 수 없습니다.<br>
 	<input type ='button' value='다시시도' onclick='history.go(-2)'>
 	</c:when> 
 	<c:otherwise>
 <div class="content">삭제 하실려면 비밀번호를 입력해 주세요
<FORM name='frm' method='POST' action='./delete' onsubmit="return a(this)">
 <input type="hidden" name="no" value="${param.no}">
 <input type="hidden" name="col" value="${param.col}">
 <input type="hidden" name="word" value="${param.word}">
 <input type="hidden" name="nowPage" value="${param.nowPage}">
 <input type="hidden" name="filename" value="${filename}">

   <TABLE>
  
    <TR>
 
      <TH>비밀번호</TH>
 
      <TD><input type="password" name="passwd" placeholder="비밀번호 입력"></TD>
      
 
    </TR>

 
  </TABLE>
 
  
 
  <DIV class='bottom'>

    <input type='submit' value='삭제' >
    <input type="button" value='목록' onclick="b()">

  </DIV>
     </c:otherwise>
 </c:choose>
 </div>
 </form>
 

 


<!-- *********************************************** -->

</body>

<!-- *********************************************** -->
 
</html> 
 