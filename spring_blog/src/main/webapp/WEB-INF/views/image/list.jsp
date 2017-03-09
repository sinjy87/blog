<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="el" uri="/ELFunctions" %>
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
function read(no){
	var url = "read";
	url += "?no="+no;
	url += "&col=${col}";
	url += "&word=${word}";
	url += "&nowPage=${nowPage}";
	
	location.href=url;
}
function down(filename){
	var url ="${pageContext.request.contextPath}/download";
	url += "?dir=/image/storage";
	url += "&filename="+filename;
	location.href = url;
}

</script>
<link href="${pageContext.request.contextPath }/css/style.css" rel="Stylesheet" type="text/css">
<meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="${pageContext.request.contextPath }/css/style.css" rel="Stylesheet" type="text/css">
</head> 
<!-- *********************************************** -->
<body>
<!-- *********************************************** -->
 
<DIV class="title">게시판 목록</DIV>
 
 <div class="search">
<FORM name='frm' method='POST' action='./list'>
	<select name="col">
		<option value="name"
		<c:if test="${col == 'name' }">selected='selected' </c:if>
		>이름</option>
		<option value="title"
		<c:if test="${col == 'title' }">selected='selected'</c:if>
		>제목</option>
		<option value="total">전체출력</option>
	</select>
	<input type="text" name="word" value="${word}">
	<input type="submit" value="검색">
</FORM>
</div>
  <TABLE>
    <TR>
      <TH>번호</TH>
      <TH>이름</TH>
      <TH>제목</TH>
      <TH>조회수</TH>
      <TH>등록일</TH>
      <TH>파일</TH>
      
    </TR>
    <c:choose>
    	<c:when test="${fn:length(list) == 0}">
    <tr>
    	<td colspan='6'>등록된 글이 없습니다.</td>
    </tr>
    </c:when>
    <c:otherwise>
    	<c:forEach items="${list}" var="dto">
    		<tr>
	    	<td>${dto.no}</td>
	    	<td>${dto.name}</td>
	    	<td>
	    	<c:forEach begin="0" end="${dto.ino}">
	    		<c:out value="&nbsp;&nbsp;" escapeXml="false"/>
	    	</c:forEach>
	    		<c:if test="${dto.ino > 0 }">
	    		<img src="${pageContext.request.contextPath}/images/re.jpg"; width="30px"; height="30px">
	    		</c:if>
	    		<a href="javascript:read('${dto.no}')">
	    		${dto.title}
    			</a>
    			
    			<c:if test="${el:newImg(dto.wdate) }">
    			<img src="${pageContext.request.contextPath }/images/new.gif">
	    		</c:if>
	    		</td>
	    		<td>${dto.viewcnt}</td>
	    		<td>${dto.wdate}</td>
	    		<td>
	    <c:choose>
	    	<c:when test="${empty dto.filename}">
	    	파일없음
	    	</c:when>
	    	<c:otherwise>
	    	   <a href="javascript:down('${dto.filename}')">
    		<img src="${pageContext.request.contextPath}/image/storage/${dto.filename}" 
    		style="width: 80px; height: 80px ;" >
    		</a>
	    	</c:otherwise>
	    	</c:choose>
    		</c:forEach>
    	</c:otherwise>
      </c:choose>
  </TABLE>
  
  <DIV class='bottom'>
  	<input type="button" value="등록" onclick="location.href='create'">
    ${paging}
  </DIV>
 
 
<!-- *********************************************** -->
</body>
<!-- *********************************************** -->
</html>