<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
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
	text-align: center;
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
		url += "?no=" + no;
		url += "&col=${col}";
		url += "&word=${word}";
		url += "&nowPage=${nowPage}";
		location.href = url;
	}
	function del(no) {
		if (confirm("정말삭제하시겠습니까?")) {
			var url = "delete";
			url += "?no=" + no;
			url += "&col=${col}";
			url += "&word=${word}";
			url += "&nowPage=${nowPage}";
			location.href = url;
		}
	}
</script>
</head>
<body>
	<div>정보목록</div>
	<div class="search">
	<form method="post" action="./list">
	<select name = "col">
	
	<option value="name"
	<c:if test="${col == 'name' }">selected='selected'</c:if>
	>이름</option>
	
	<option value="phone"
	<c:if test="${col == 'phone' }">selected='selected'</c:if>
	>전화번호</option>
	
	<option value="total">전체</option>
	
	</select>
	<input type = "text" name = "word" value="${word }">
	<input type = "submit" value = "검색">
	<input type = "button" value = "등록" onclick="location.href='create'">
	</form>
	</div>
	<div>개인정보</div>
	<table>
		<tr>
			<th>번호</th>
			<th>이름</th>
			<th>전화번호</th>
			<th>입력날짜</th>
			<th>수정/삭제</th>
		</tr>
		<c:choose>
			<c:when test="${fn:length(list1) ==0 }">
		<tr> 
			<td colspan="8">등록된 정보가 없습니다.</td> 
 		</tr> 
 		</c:when>
 		<c:otherwise>
 			<c:forEach items="${iter }" var="dto">
		<tr>
			<td>${dto.no }</td>
			<td><a href="javascript:read('${dto.no }')"> ${dto.name }</a></td>
			<td>${dto.phone}</td>
			<td>${dto.wdate}</td>
			<td><a href="javascript:update('${dto.no }')">수정</a><br>
				/<a href="javascript:del('${dto.no }')">삭제</a></td>
		</tr>
				</c:forEach>
 		</c:otherwise>
		</c:choose>

	</table>
	<div>
	${paging }
	</div>

</body>
</html>