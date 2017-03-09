<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<style type="text/css">
* {
	font-family: gulim;
	font-size: 24px;
}

div {
	text-align: center;
	margin-bottom: 20px;
	margin-top: 20px;
}

table {
	width: 60%;
	margin: auto;
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
	color: #ff0000;
	text-decoration: none;
}
img{
width: 200px;
height: 200px;
}
</style>
<script type="text/javascript">
	function read(no) {
		var url = "read";
		url += "?no=" + no;
		url += "&col=${col}";
		url += "&word=${word}";
		url += "&nowPage=${nowPage}";
		location.href = url;
	}
	function update(no) {
		var url = "update";
		url+= "?no=" + no;
		url += "&col=${col}";
		url += "&word=${word}";
		url += "&nowPage=${nowPage}";
		
		location.href = url;
	}
	function del(no,oldfile) {
		if(confirm("정말삭제하시겠습니까?")){
			var url = "delete";
			url += "?no="+no;
			url += "&col=${col}";
			url += "&word=${word}";
			url += "&nowPage=${nowPage}";
			url += "&oldfile="+oldfile;
			location.href=url;
		}
	}
</script>
</head>
<body>
	<div>팀목록</div>
	<div class="search">
	<form method="post" action="./list">
	<select name = "col">
	
	<option value="name"
	<c:if test="${col == name }">selected='selected'</c:if>
	>이름</option>
	
	<option value="phone"
	<c:if test="${col == phone }">selected='selected'</c:if>
	>전화번호</option>
	
	<option value="skill"
	<c:if test="${col == skill }">selected='selected'</c:if>
	>보유기술</option>
	
	<option value="total">전체</option>
	
	</select>
	<input type = "text" name = "word" value="${word }">
	<input type = "submit" value = "검색">
	<input type = "button" value = "등록" onclick="location.href='create.do'">
	</form>
	</div>
	
	
	<table>
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>전화번호</th>
			<th>보유기술</th>
			<th>사진</th>
			<th>수정/삭제</th>

		</tr>

		<c:choose>
			<c:when test="${fn:length(list) == 0 }">
					<tr>
			<td colspan="6">등록된 팀정보가 없습니다.</td>
		</tr>
			</c:when>
		<c:otherwise>
			<c:forEach items="${list }" var="dto">
		<tr>
			<td>${dto.no}</td>
			<td><a href="javascript:read('${dto.no}')"> ${dto.name}</a></td>
			<td>${dto.phone}</td>
			<td>${dto.skillstr}</td>
			<td><img src="${pageContext.request.contextPath }/team/storage/${dto.filename}"></td>
			<td><a href="javascript:update('${dto.no}')">수정</a> /
			<a href="javascript:del('${dto.no}','${dto.filename}')">삭제</a></td>
		</tr>

		</c:forEach>
		</c:otherwise>
	</c:choose>
	</table>
	<div>
	${paging}
	</div>
</body>
</html>
