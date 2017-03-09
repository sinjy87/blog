<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원</title>
<style type="text/css">
* {
	font-family: gulim;
	font-size: 20px;
}


</style>
<link href="${pageContext.request.contextPath }/css/style.css" rel="Stylesheet" type="text/css">
</head>
<!-- *********************************************** -->
<body>
	<!-- *********************************************** -->

	<DIV class="title">글생성</DIV>

	<FORM name='frm' method='POST' action='./create'
		enctype="multipart/form-data">
		<TABLE>
			<TR>
				<TH>이름</TH>
				<TD><input type="text" name="name"></TD>
			</TR>
			<TR>
				<TH>제목</TH>
				<TD><input type="text" name="title"></TD>
			</TR>
			<TR>
				<TH>내용</TH>
				<TD><textarea name="content"></textarea></TD>
			</TR>
			<TR>
				<TH>비밀번호</TH>
				<TD><input type="password" name="passwd"></TD>
			</TR>
			<TR>
				<TH>사진</TH>
				<TD><input type="file" name="fileMF" accept=".jpg , .gif, .png"></TD><!-- 액셉트 -->
			</TR>
		</TABLE>
		`

		<DIV class='bottom'>
			<input type='submit' value='작성하기'> 
			<input type='button' value='취소' onclick="history.back()">
		</DIV>
	</FORM>


	<!-- *********************************************** -->
</body>
<!-- *********************************************** -->
</html>
