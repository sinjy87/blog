<%@page import="java.util.Iterator"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="util" uri="/ELFunctions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>

<style type="text/css">
* {
	font-family: gulim;
	font-size: 20px;
}
.search{
	width: 80%;
	text-align: center;
	margin: 10px auto;
}
</style>
<script type="text/javascript">
	function blist() {
		var url = "list";
		url += "?col=${col}" ;
		url += "&word=${word}" ;
		location.href = url;
	}	
	function read(bbsno) {
		var url = "read";
		url += "?bbsno=" + bbsno;
		url += "&col=${col}" ;
		url += "&word=${word}" ;
		url += "&nowPage=${nowPage}" ;
		location.href = url;
	}
	function down(filename) {
		var url="${pageContext.request.contextPath}/download";
		url += "?dir=/bbs/storage";
		url += "&filename="+filename;
		location.href = url;
	}
</script>
<link href="${pageContext.request.contextPath }/css/style.css" rel="Stylesheet" type="text/css">
</head>
<!-- *********************************************** -->
<body>
	<!-- *********************************************** -->

	<DIV class="title">게시판 목록</DIV>
	<div class="search">
		<form method="post" action="./list">
			<select name="col">
				<!-- 검색할 컬럼 -->
				<option value="wname"
				<c:if test="${col == 'wname' }">selected='selected'</c:if>
				>이름</option>
				
				<option value="title"
				<c:if test="${col == 'title' }">selected='selected'</c:if>
				>제목</option>
				
				<option value="content"
				<c:if test="${col == 'content' }">selected='selected'</c:if>
				>내용</option>
				
				<option value="total">전체출력</option>
				
			</select> 
			<input type="text" name="word" value="${word }">
			<!-- 검색어 -->
			<input type="submit" value="검색"> 
			<input type='button'
				value='계속등록' onclick="location.href='./create'">

		</form>
	</div>


	<TABLE>
		<TR>
			<TH>번호</TH>
			<TH>제목</TH>
			<TH>이름</TH>
			<TH>조회수</TH>
			<TH>등록일</TH>
			<th>파일명</th>
		</TR>
	<c:choose>
		<c:when test="${fn:length(list) == 0 }">
			<tr>
				<td colspan="8">등록된 글이 없습니다.</td>
			</tr>
		</c:when>
		<c:otherwise>
			<c:forEach items="${list}" var="dto">
		<tr>
			<td>${dto.bbsno}</td>
			<td>
				<c:forEach begin="1" end="${dto.indent}">
					<c:out value="&nbsp;&nbsp;" escapeXml="false"/>
				</c:forEach>
					<c:if test="${dto.indent > 0 }">
					<img src="${pageContext.request.contextPath }/images/re.jpg" width="30%" height="30%">
					</c:if>
					
					<c:set var="rcount" value="${util:rcount(dto.bbsno,irdao) }"/>
					
	     <a href="javascript:read('${dto.bbsno}')"> ${dto.title}
		
		          <c:if test="${rcount>0 }">
            <span style="color:red;">(${rcount})</span>
          </c:if>
		
		
			</a>
				<c:if test="${util:newImg(fn:substring(dto.wdate,0,10))}">
					<img src = "${pageContext.request.contextPath}/images/new.gif">
			 	</c:if> 
			</td>
			<td>${dto.wname}</td>
			<td>${dto.viewcnt}</td>
			<td>${fn:substring(dto.wdate,0,10)}</td>
			<td>
			<c:choose>
				<c:when test="${empty dto.filename}">
				파일 없음
				</c:when>
				<c:otherwise>
					<a href='javascript:down("${dto.filename}")'>
					<span class='glyphicon glyphicon-file'></span>
					</a>
				</c:otherwise>
			</c:choose>
			</td>
		</tr>
	  </c:forEach>
	 </c:otherwise>
	</c:choose>

	</TABLE>

	<DIV class='bottom'>
		${paging}
	</DIV>



	<!-- *********************************************** -->
</body>
<!-- *********************************************** -->
</html>
