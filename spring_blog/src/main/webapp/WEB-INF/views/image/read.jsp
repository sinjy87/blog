<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script>
	function read(no) {
		var url = "./read";
		url += "?no="+no;
		url +="&col=${col}";
		url +="&word=${word}";
		url +="&nowPage=${nowPage}";
		
		location.href = url;
	}
	function ilist() {
		var url ="list";
		
		url +="?col=${col}";
		url +="&word=${word}";
		url +="&nowPage=${nowPage}";
		
		location.href = url;
	}
	function update() {
		var url ="update";
		
		url += "?no=${dto.no}";
		url +="&col=${col}";
		url +="&word=${word}";
		url +="&nowPage=${nowPage}";
		
		location.href = url;
	}
	function idelete() {
		var url ="delete";
		
		url +="?col=${col}";
		url +="&word=${word}";
		url +="&nowPage=${nowPage}";
		url +="&no=${dto.no}";
		url +="&filename=${dto.filename}";
		
		location.href = url;
	}
	function reply() {
		var url ="reply";
		
		url +="?col=${col}";
		url +="&word=${word}";
		url +="&nowPage=${nowPage}";
		url +="&no=${dto.no}";
		
		location.href = url;
	}

	
</script>


<style type="text/css">
* {
	font-family: gulim;
	font-size: 20px;
}
.curImg{
	margin-right:0;
	border-style:solid;
	border-width: 3px;
	border-color: red;
}
.td_padding{
	padding:5px 5px;
}
</style>
<link href="${pageContext.request.contextPath }/css/style.css" rel="Stylesheet" type="text/css">
</head>
<!-- *********************************************** -->
<body>
	<!-- *********************************************** -->

	<DIV class="title">조회</DIV>

	<TABLE>

		<TR>
			<TD colspan="2">
			<img src="${pageContext.request.contextPath }/image/storage/${dto.filename}" style=" width: 80%">
			</TD>
		</TR>

	</TABLE>

	<br>

	<TABLE>
		<TR>
			<TD>번호 : ${dto.no}</TD>
		</TR>

		<TR>
			<TD>이름 : ${dto.name}</TD>
		</TR>

	</TABLE>

	<br>

	<table>
		<TR>
			<TH width="30%">내용</TH>
			<td>${dto.content}</td>
		</TR>
	</table>
	<div class="bottom">
	<input type="button" value="수정" onclick="update()">
	<input type="button" value="삭제" onclick="idelete()">
	<input type="button" value="목록" onclick="ilist()">
	<input type="button" value="답변" onclick="reply()">
	</div>

	<table>
	<tr>
	<c:forEach var="i" begin="0" end="4">
		<c:choose>
			<c:when test="${empty files[i]}">
				<td>
				<img src = "${pageContext.request.contextPath}/image/storage/1.png" width="90px" >
				</td>
			</c:when>
				<c:otherwise>
					<c:choose>
						<c:when test="${noArr[i] == dto.no }">
						<td class="td_padding">
						<a href="javascript:read('${noArr[i]}')">
						<img class = "curImg" src = "${pageContext.request.contextPath }/image/storage/${files[i]}" width="90px" border="0">
						</a></td>
						</c:when>
						<c:otherwise>
							<td class="td_padding">
							<a href ="javascript:read('${noArr[i]}')">
							<img src="${pageContext.request.contextPath }/image/storage/${files[i]}" width="80px" border="0">
							</a></td>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
		</c:choose>
		
	</c:forEach>
	</tr>
	</table>



	<!-- *********************************************** -->
</body>
<!-- *********************************************** -->
</html>
