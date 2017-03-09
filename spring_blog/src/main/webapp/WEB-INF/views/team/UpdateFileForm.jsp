<%@ page contentType="text/html; charset=UTF-8"%>


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
img{
	width: 400px;
	height: 400px;
}
</style>
<script type="text/javascript">
function limit(f) {
	if(f.filename.value==""){
		alert("사진을 등록하세요.");
		f.filename.focus();
		return false;
	}
	
}
</script>
<link href="${pageContext.request.contextPath }/css/style.css" rel="Stylesheet" type="text/css">
</head>
<!-- *********************************************** -->
<body>
	<!-- *********************************************** -->

	<DIV class="title">사진수정</DIV>

	<FORM name='frm' method='POST' action='./UpdateFileProc.do'
		enctype="multipart/form-data"
		onsubmit="return limit(this)">
	<input type = "hidden" name = "no" value="${param.no}">
	<input type = "hidden" name = "col" value="${param.col}">
	<input type = "hidden" name = "word" value="${param.word}">
	<input type = "hidden" name = "nowPage" value="${param.nowPage}">
	<input type = "hidden" name = "oldfile" value="${oldfile}">

		<TABLE>
			<TR>
				<TH>원본파일</TH>
				<TD><img src="${pageContext.request.contextPath}/views/team/storage/${oldfile}">
				<br>
				원본파일명:${oldfile}
				</TD>
			</TR>
			<TR>
				<TH>수정파일</TH>
				<TD><input type = "file" name = "filename" accept=".gif,.jpg,.png"></TD>
			</TR>
		</TABLE>

		<DIV class='bottom'>
			<input type='submit' value='변경'> <input type='button' value='취소'
				onclick="history.back()">
		</DIV>
	</FORM>


	<!-- *********************************************** -->
</body>
<!-- *********************************************** -->
</html>
