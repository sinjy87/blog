<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<script type="text/javascript">
function incheck(f) {
	if(f.passwd.value==""){
		alert("패스워드를 입력하세요.");
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
<link href="${pageContest.request.contextPath }/css/style.css" rel="Stylesheet" type="text/css">
</head>
<!-- *********************************************** -->
<body>
	<!-- *********************************************** -->

	<DIV class="title">삭제</DIV>

	<div class="content">
	<c:choose>
		<c:when test="${flag}">
		답변이 있는 글이므로 삭제 할 수 없습니다.<br>
		<input type ='button' value='다시시도' onclick='history.go(-2)'>
		</c:when>
		<c:otherwise>
		
		<FORM name='frm' method='POST' action='./delete' onsubmit="return incheck(this)">
		<input type = "hidden" name = "bbsno" value="${param.bbsno }">
		<input name="col" value="${param.col }" type="hidden">
		<input name="word" value="${param.word }" type="hidden">
		<input name="nowPage" value="${param.nowPage }" type="hidden">
		<input name="oldfile" value="${param.oldfile }" type="hidden">
		삭제되면 복구할수없습니다.<br><br>
			<TABLE>
				<TR>
					<TH>패스워드</TH>
					<TD><input type="password" name = "passwd"></TD>
				</TR>
			</TABLE>

			<DIV class='bottom'>
				<input type='submit' value='확인'> 
				<input type='button' value='취소' onclick="history.back()">
			</DIV>
		</FORM>
		
		</c:otherwise>
	</c:choose>
	</div>


	<!-- *********************************************** -->
</body>
<!-- *********************************************** -->
</html>
