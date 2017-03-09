<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script type="text/javascript">
function tlist() {
	var url = "list";
	url += "?col=${param.col}";
	url += "&word=${param.word}";
	url += "&nowPage=${param.nowPage}";
	location.href=url;
}

</script>
<style type="text/css">
* {
	font-family: gulim;
	font-size: 24px;
}
div{
text-align: center;
margin-top: 20px;
margin-bottom: 20px;
}
table{
width: 60%;
margin: auto;
}
table,th,td{
border: 1px solid black;
border-collapse: collapse;
}

img{
width: 500px;
height: 500px;
}
#ftd{
	text-align: center;
}
</style>

</head>
<body>
	<div>조회</div>
	<table>
		<tr>
			<td colspan="2" id ="ftd">
			<img
				src='${pageContext.request.contextPath}/team/storage/${dto.filename}'></td>
		</tr>
		<tr>
			<td>이름</td>
			<td>${dto.name}</td>
		</tr>

		<tr>
			<td>성별</td>
			<td>${dto.gender}</td>
		</tr>

		<tr>
			<td>전화번호</td>
			<td>${dto.phone}</td>
		</tr>

		<tr>
			<td>주소</td>
			<td>${dto.address1} <br>
				${dto.address2}
				(${dto.zipcode})
				</td>
		</tr>

		<tr>
			<td>보유기술</td>
			<td>${dto.skillstr}</td>
		</tr>

		<tr>
			<td>취미</td>
			<td>${dto.hobby}</td>
		</tr>



	</table>
	<div>
	<input type="button" value="생성" onclick="location.href='./create'">
	<input type="button" value="목록" onclick="tlist()">
	<input type="button" value="다운로드" onclick="location.href='${pageContext.request.contextPath }/download?dir=/team/storage&filename=${dto.filename}'">
	
</div>
</body>
</html>
