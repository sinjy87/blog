<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

div {
	text-align: center;
	margin-top: 20px;
}

table {
	width: "60%";
	margin: 0 auto;
}

table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

th, td {
	padding: 10px;
}

a:LINK {
	color: #000000;
	text-decoration: none;
}

a:VISITED {
	color: #000000;
	text-decoration: none;
}

a:HOVER {
	color: #ff0000;
	text-decoration: none;
}

a:ACTIVE {
	color: #000000;
	text-decoration: none;
}

.search {
	width: 80%;
	text-align: center;
	margin: 10px auto;
}
</style>
<script type="text/javascript">
	function read(memono) {
		var url = "read";
		url += "?memono=" + memono;
		url += "&col=${col}";
		url += "&word=${word}";
		url += "&nowPage=${nowPage}";
		location.href = url;
	}
</script>
</head>
<body>

	<div class="container">
		<form method="post" action="./list">
			<select name="col">
				<!-- 검색할 컬럼 -->

				<option value="title"
				<c:if test="${col == 'title' }">selected='selected'</c:if>
				>제목</option>
				<option value="content"
				<c:if test="${col == 'content' }">selected='selected'</c:if>
				>내용</option>
				<option value="total">전체출력</option>
			</select> <input type="text" name="word" value="${word}">
			<!-- 검색어 -->
			<input type="submit" value="검색"> <input type='button'
				value='계속등록' onclick="location.href='./create'">

		</form>

<h2><span class="glyphicon glyphicon-th-large"></span>메모목록</h2>
	 <table class="table table-hover">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>날짜</th>
			<th>조회수</th>
		</tr>
		<c:choose>
		<c:when test="${fn:length(list) ==0 }">
		<tr>
			<td colspan="4">등록된 메모가 없습니다</td>
		</tr>
		</c:when>
		<c:otherwise>
			<c:forEach items="${list}" var="dto">
		<tr>
			<td>${dto.memono}</td>
			<td>
			<c:set var="rcount" value="${util:rcount(dto.memono, irdao) }"/>
			
			<a href="javascript:read('${dto.memono}')">${dto.title}
			
			  <c:if test="${rcount>0 }">
            <span style="color:red;">(${rcount})</span>
          </c:if>
          
			</a></td>
			<td>${dto.wdate}</td>
			<td>${dto.viewcnt}</td>

		</tr>
			</c:forEach>
		</c:otherwise>
		</c:choose>
	</table>
		</div>

	<div>
	${paging}
	</div>

	
</body>
</html>