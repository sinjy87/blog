<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="el" uri="/ELFunctions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script>
function mlist() {
	var url = "${pageContext.request.contextPath}/admin/list";
	url += "?col=${param.col}";
	url += "&word=${param.word}";
	url += "&nowPage=${param.nowPage}";
	
	location.href=url;
}
function update() {
	var url = "update";
	url += "?id=${id}";
	url += "&col=${param.col}";
	url += "&word=${param.word}";
	url += "&nowPage=${param.nowPage}";
	
	location.href=url;
}
function updateFile() {
	var url = "updateFile";
	url += "?id=${id}";
	url += "&oldfile=${dto.fname}";
	
	location.href = url;
}
function updatePw() {
	var url = "updatePw";
	url += "?id=${id}";
	
	location.href=url;
}


</script>
<style type="text/css">
* {
	font-family: gulim;
	font-size: 20px;
}

</style>
<link href="${pageContext.request.contextPath}/css/style.css" rel="Stylesheet" type="text/css">
</head>
<!-- *********************************************** -->
<body>
	<!-- *********************************************** -->

	<DIV class="title">${dto.mname} 의 회원정보
	</DIV>

	<TABLE>
		<tr>
		<td colspan="2">
		<img src = "${pageContext.request.contextPath}/member/storage/${dto.fname}">
		</td>
		</tr>
	
		<TR>
			<TH>아이디</TH>
			<TD>${dto.id}</TD>
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
			<TH>우편번호</TH>
			<TD>${dto.zipcode}</TD>
		</TR>
		
		<TR>
			<TH>주소</TH>
			<TD>
			${dto.address1}
			${dto.address2}
			
			</TD>
		</TR>
		
		<TR>
			<TH>직업</TH>
			<TD>
			직업코드 : ${dto.job}
			(${el:reJob(dto.job)})
			</TD>
		</TR>
		
		<TR>
			<TH>가입날짜</TH>
			<TD>${dto.mdate}</TD>
		</TR>
		
		
	</TABLE>

	<DIV class='bottom'>
	<input type='button' value='정보수정' onclick="update()">
	<c:choose>
		<c:when test="${id != null && grade == 'A' }">
	<input type='button' value='회원목록' onclick="mlist()">
		</c:when>
		<c:otherwise>
	<input type='button' value='사진수정' onclick="updateFile()">
	<input type='button' value='비밀번호수정' onclick="updatePw()">
	<input type='button' value='다운로드' 
	onclick="location.href='${pageContext.request.contextPath }/download?dir=/member/storage&filename=${dto.fname}'"> 
		</c:otherwise>
		</c:choose>
	</DIV>


	<!-- *********************************************** -->
</body>
<!-- *********************************************** -->
</html>
