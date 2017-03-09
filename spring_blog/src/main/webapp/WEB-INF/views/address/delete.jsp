<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	margin-top: 50px;
}
</style>
<script type="text/javascript">
function alist() {
	var url = "list";
	url += "?col=${param.col}";
	url += "&word=${param.word}";
	url += "&nowPage=${param.nowPage}";
	location.href=url;
}
</script>
</head>
<body>

	<div>
	<c:choose>
		<c:when test="${flag }">
		삭제되었습니다.
		</c:when>
		<c:otherwise>
		실패했습니다.
		</c:otherwise>
	</c:choose>
		<br> <br> <input type="button" value="목록으로..."
			onclick="alist()">
	</div>


</body>
</html>
