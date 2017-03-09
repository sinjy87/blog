<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style type="text/css"> 
.rcreate{
  font-size: 20px;
  font-weight:bold;
  text-align: left;
  border-style: solid;   /* 실선 */
  border-width: 1px;     /* 선 두께 */
  border-color: #AAAAAA; /* 선 색깔 */
  color: #000000;        /* 글자 색깔 */
  width: 35%;            /* 화면의 30% */ 
  padding: 10px;         /* 위 오른쪽 아래 왼쪽: 시간 방향 적용 */
  
  /* padding: 50px 10px;  50px: 위 아래, 10px: 좌우 */
  /* padding-top: 30px;  상단만 간격을 30px 지정   */
  
  margin: 20px auto; /* 가운데 정렬 기능, 20px: 위 아래, auto: 오른쪽 왼쪽*/
}
.rlist{
  line-height:1.2em;
  font-size: 15px;
  font-weight:bold;
  text-align: left;
  border-style: solid;   /* 실선 */
  border-width: 1px;     /* 선 두께 */
  border-color: #AAAAAA; /* 선 색깔 */
  color: #000000;        /* 글자 색깔 */
  width: 35%;            /* 화면의 30% */ 
  padding: 10px;         /* 위 오른쪽 아래 왼쪽: 시간 방향 적용 */
  
  /* padding: 50px 10px;  50px: 위 아래, 10px: 좌우 */
  /* padding-top: 30px;  상단만 간격을 30px 지정   */
  
  margin: 20px auto; /* 가운데 정렬 기능, 20px: 위 아래, auto: 오른쪽 왼쪽*/
}
hr{
  text-align: center;
  border-style: solid;   /* 실선 */
  border-width: 1px;     /* 선 두께 */
  border-color: #AAAAAA; /* 선 색깔 */
  width: 45%;            /* 화면의 30% */ 
}
 
 
</style>  
<script type="text/javascript">
	function blist() {
		var url = "list";
		url += "?col=${param.col}";
		url += "&word=${param.word}";		
		url += "&nowPage=${param.nowPage}";		
		location.href = url;
	}
	function bupdate() {
		var url = "update"
		url += "?bbsno=${dto.bbsno}";
		url += "&col=${param.col}";
		url += "&word=${param.word}";	
		url += "&nowPage=${param.nowPage}";		
		location.href = url;
	}
	function bdelete() {
		var url = "delete"
		url += "?bbsno=${dto.bbsno}";
		url += "&col=${param.col}";
		url += "&word=${param.word}";	
		url += "&nowPage=${param.nowPage}";		
		url += "&oldfile=${dto.filename}";				
		location.href = url;
	}
	function breply() {
		var url = "reply"
		url += "?bbsno=${dto.bbsno}";
		url += "&col=${param.col}";
		url += "&word=${param.word}";
		url += "&nowPage=${param.nowPage}";		
		location.href = url;
	}
	function down(filename) {
		var url="${pageContext.request.contextPath}/download";
		url += "?dir=/bbs/storage";
		url += "&filename="+filename;
		location.href = url;
	}
	/**댓글 관련 시작
		no -> bbsno의 값
		ino -> bbsno라는 식별자
	**/
	function rcheck(tarea){
		if('${sessionScope.id}'==""){
		if(confirm("로그인후 댓글를 쓰세요")){
		var url = "../member/login";
		url = url + "?no=${dto.bbsno}";
		url = url + "&nowPage=${param.nowPage}";
		url = url + "&nPage=${nPage}";
		url = url + "&col=${param.col}";
		url = url + "&word=${param.word}";
		url = url + "&ino=bbsno";
		url = url + "&bflag=../bbs/read";
		location.href=url;
		}else{
		tarea.blur();
		}
	}
}
		 
		function input(f){
		if('${sessionScope.id}'==""){
		if(confirm("로그인후 댓글를 쓰세요")){
		var url = "../member/login";
		url = url + "?no=${dto.bbsno}";
		url = url + "&nowPage=${param.nowPage}";
		url = url + "&nPage=${nPage}";
		url = url + "&col=${param.col}";
		url = url + "&word=${param.word}";
		url = url + "&ino=bbsno";
		url = url + "&bflag=../bbs/read";
		location.href=url;
		return false;
		}else{
		 
		return false;
		}
		}else if(f.content.value==""){
		alert("댓글 내용을 입력하세요.");
		f.content.focus();
		return false;
		}
		}
		function rupdate(rnum,rcontent){
		var f = document.rform;
		f.content.value = rcontent;
		f.rnum.value = rnum;
		f.rsubmit.value="수정";
		f.action="./rupdate"
		}
		function rdelete(rnum){
		if(confirm("정말삭제 하겠습니까?")){ 
		var url = "./rdelete";
		url = url + "?rnum="+rnum;
		url = url + "&bbsno=${dto.bbsno}";
		url = url + "&nowPage=${param.nowPage}";
		url = url + "&nPage=${nPage}";
		url = url + "&col=${param.col}";
		url = url + "&word=${param.word}";
		location.href=url; 
		}
		}
	/**댓글관련 끝**/
</script>

<link href="${pageContext.request.contextPath}/css/style.css" rel="Stylesheet" type="text/css">
</head>
<!-- *********************************************** -->
<body>
	<!-- *********************************************** -->

	<DIV class="title">조회</DIV>

	<TABLE style="width: 40%">
		<TR>
			<TH>번호</TH>
			<td>${dto.bbsno}</td>
		</TR>
		<TR>
			<TH>이름</TH>
			<td>${dto.wname}</td>
		</TR>
		<TR>
			<TH>제목</TH>
			<td>${dto.title}</td>
		</TR>
		<TR>
			
			<td colspan="2">${dto.content}</td>
		</TR>
		<TR>
			<TH>입력날짜</TH>
			<td>${dto.wdate}</td>
		</TR>
		<TR>
			<TH>조회수</TH>
			<td>${dto.viewcnt}</td>
		</TR>
		<TR>
			<TH>파일명</TH>
			<td>
			<c:choose>
				<c:when test="${empty dto.filename}">
				파일 없음				
				</c:when>
				<c:otherwise>
				${dto.filename}(${dto.filesize/1024 } KB)
							
				<a href = "javascript:down('${dto.filename}')">
				<span class='glyphicon glyphicon-file'></span>
				</a>
				</c:otherwise>
				</c:choose>
			</td>
		</TR>
	</TABLE>

	<DIV class='bottom'>
		<input type='button' value='등록'
			onclick="location.href='create'"> <input
			type="button" value='목록' onclick="blist()"> <input
			type="button" value='수정' onclick="bupdate()"> <input
			type="button" value='삭제' onclick="bdelete()"> <input
			type="button" value='답변' onclick="breply()">
	</DIV>


	<!-- *********************************************** -->
	<hr>
  <c:forEach var="rdto" items="${rlist}">
  <div class="rlist">
   ${rdto.id}<br/>
   <p>${rdto.content}</p>
   ${rdto.regdate}
   <c:if test="${sessionScope.id==rdto.id }">
   <span style="float: right;">
   <a href="javascript:rupdate('${rdto.rnum}','${rdto.content }')">
   수정</a>|<a href="javascript:rdelete('${rdto.rnum}')">삭제</a>
   </span>
   </c:if>
  </div>
  </c:forEach>
  <div class="rcreate">
  <form name="rform" action="./rcreate" method="post" onsubmit="return input(this)">
  <textarea rows="3" cols="28" name="content" onclick="rcheck(this)"></textarea>
  <input type="submit" name="rsubmit" value="등록">
  <input type="hidden" name="bbsno" value="${dto.bbsno}">
  <input type="hidden" name="id" value="${sessionScope.id}">
  <input type="hidden" name="nowPage" value="${param.nowPage}">
  <input type="hidden" name="nPage" value="${nPage}">
  <input type="hidden" name="col" value="${param.col}">
  <input type="hidden" name="word" value="${param.word}">
  <input type="hidden" name="rnum" value="0">
  
  
  </form>
  </div>
  <div class="bottom">
  ${paging}
  </div>
</body>
<!-- *********************************************** -->
</html>
