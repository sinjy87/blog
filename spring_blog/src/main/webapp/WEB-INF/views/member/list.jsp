<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<script>
function read(id) {
	var url = "${pageContext.request.contextPath}/member/read";
	url += "?id="+id;
	url += "&nowPage=${nowPage}";
	url += "&col=${col}";
	url += "&word=${word}";
	location.href=url;
}

</script>
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
 
<DIV class="title">회원목록</DIV>

<div class = "search"> 
<FORM name='frm' method='POST' action='./list'>
<select name = "col">
<option value ="mname"
<c:if test="${col == mname }">selected='selected'</c:if>
 >이름</option>

<option value ="email"
<c:if test="${col == email }">selected='selected'</c:if>
>이메일</option>

<option value ="id"
<c:if test="${col == id }">selected='selected'</c:if>
>아이디</option>

<option value ="total">전체출력</option>

</select>
<input type = "text" name = "word" value='${word}'>
<input type = "submit" value = "검색">
<input type = "button" value = "회원가입" 
onclick="location.href='${pageContext.request.contextPath}/member/agree'">
</FORM>
</div>
	<c:forEach items="${list}" var="dto">

  <TABLE>
  
    <TR>
      <TD rowspan = '5' width="30%">
      <img src ='${pageContext.request.contextPath}/member/storage/${dto.fname}'>
      </TD>
      <TH width="20%">아이디</TH>
      <TD width="50%">
      <a href = "javascript:read('${dto.id}')">${dto.id}</a>
      </TD>
    </TR>
    
    <TR>
      <TH>이름</TH>
      <TD>${dto.mname}</TD>
    </TR>
    
    <TR>
      <TH>전화번호</TH>
      <TD>${dto.tel}</TD>
    </TR>
    
    <TR>
      <TH>이메일</TH>
      <TD>${dto.email}</TD>
    </TR>
    
    <TR>
      <TH>주소</TH>
      <TD>
      ${dto.address1}
      ${dto.address2}
      
      </TD>
    </TR>

  </TABLE>
  	</c:forEach>
  <DIV class='bottom'>
	${paging}
	
  </DIV>
 
 
<!-- *********************************************** -->
</body>
<!-- *********************************************** -->
</html> 