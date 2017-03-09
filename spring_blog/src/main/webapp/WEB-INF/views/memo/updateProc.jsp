<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>

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
}
</style>
<script type="text/javascript">
function mlist() {
	var url = "list.do";
	url += "?col=${param.col}";
	url += "&word=${param.word}";
	url += "&nowPage=${param.nowPage}";
	
	location.href = url;
}

</script>
</head>
<body>
	<div>
	<c:choose>
		<c:when test="${flag}">
		메모를 수정하였습니다.
		</c:when>
		<c:otherwise>
		수정에 실패하였습니다
		</c:otherwise>
	</c:choose>
		<br> <input type="button" value="목록" onclick="mlist()">
	</div>

</body>
</html>