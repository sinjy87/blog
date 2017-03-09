<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script type="text/javascript">
	function incheck(f) {
		if (f.wname.value == "") {
			alert("이름을 입력하세요");
			f.wname.focus();
			return false;
		}
		if (f.title.value == "") {
			alert("제목을 입력하세요");
			f.title.focus();
			return false;
		}
	    if (CKEDITOR.instances['content'].getData() == '') {
	        window.alert('내용을 입력해 주세요.');
	        CKEDITOR.instances['content'].focus();
	        return false;
	      }
// 		if (f.content.value == "") {
// 			alert("내용을 입력하세요");
// 			f.content.focus();
// 			return false;
// 		}
		if (f.passwd.value == "") {
			alert("비밀번호을 입력하세요");
			f.passwd.focus();
			return false;
		}

	}
</script>
<style type="text/css">
* {
	font-family: gulim;
	font-size: 20px;
}
</style>
<link href="${pageContext.request.contextPath}/css/style.css" rel="Stylesheet" type="text/css">
<script type="text/javascript" src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>
 
<script type="text/JavaScript">
  window.onload=function(){
   CKEDITOR.replace('content');
  };
</script>

</head>
<!-- *********************************************** -->
<body>
	<!-- *********************************************** -->

	<DIV class="title">수정</DIV>

	<FORM name='frm' method='POST' action='./update'
		onsubmit="return incheck(this)"
		enctype="multipart/form-data"
		>
		<input name="bbsno" value="${dto.bbsno}" type="hidden">
		<input name="col" value="${param.col}" type="hidden">
		<input name="word" value="${param.word}" type="hidden">
		<input name="nowPage" value="${param.nowPage}" type="hidden">
		<input name="oldfile" value="${dto.filename}" type="hidden">
		<TABLE>
			<TR>
				<TH>이름</TH>
				<TD><input type="text" name="wname" value="${dto.wname}"></TD>
			</TR>
			<TR>
				<TH>제목</TH>
				<TD><input type="text" name="title" value="${dto.title}"></TD>
			</TR>
			<TR>
				<TH>내용</TH>
				<TD><textarea rows="10" cols="50" name="content">${dto.content}</textarea>
				</TD>
			</TR>
			<TR>
				<TH>비밀번호</TH>
				<TD><input type="password" name="passwd"></TD>
			</TR>
			<TR>
				<TH>파일</TH>
				<TD><input type = "file" name="fileMF">${dto.filename}</TD>
			</TR>
		</TABLE>

		<DIV class='bottom'>
			<input type='submit' value='수정'> 
			<input type="reset" value='재입력'> 
			<input type="button" value='취소'
				onclick="history.back()">
		</DIV>
	</FORM>


	<!-- *********************************************** -->
</body>
<!-- *********************************************** -->
</html>
