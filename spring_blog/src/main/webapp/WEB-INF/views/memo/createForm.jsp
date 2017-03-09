<%@ page contentType="text/html; charset=UTF-8"%>

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
	margin-top: 20px;
	margin-bottom: 20px;
}

table {
	width: "50%";
	margin: 0 auto;
}

table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}
</style>
<script type="text/javascript">
	function input(frm) {
		if (frm.title.value == "") {
			alert("제목을 입력하시오");
			frm.title.focus();
			return false;
		}

		if (frm.content.value == "") {
			alert("내용을 입력하시오");
			frm.content.focus();
			return false;
		}
	}
</script>
</head>
<body>
	<div>등록</div>
	<form name="frm" method="post" action="./create"
		onsubmit="return input(this)">
		<table>
			<tr>
				<th>제목</th>
				<td><input type="text" name="title" size="30" value="화요일"></td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="content" rows="10" cols="30">SQL 수업</textarea></td>
			</tr>
		</table>

		<div>
			<input type="submit" value="전송">
		</div>

	</form>
</body>
</html>