<%@ page contentType="text/html; charset=UTF-8" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<html>
<head>
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<style type="text/css">
/* ul#menu li{ */
/* display : inline; */
/* } */
/* ul#menu li a{ */
/* background-color: black; */
/* color:white; */
/* pading : 10px 20px; */
/* text-decoration:none; */
/* border-radius:4px 4px 0 0; */
/* } */
/* ul#menu li a:hover{ */
/* background-color: orange; */
/* } */
.table{
	width: 100%;
} 
.table,.td{
	border-style: none;
}
.img{
	width :100%;
	height :20%;
}

/* li#admin */
/* { */
/* float:right; */
/* padding-right: 30px */
/* } */
</style>
<script type="text/javascript">
	$(document).ready(function() {
		if('${sessionScope.id}' != '' && '${sessionScope.grade}' != 'A'){
			$("#login").hide();
			$("#logout").show();
			$("#admin").hide();
		}else if('${sessionScope.id}' != null && '${sessionScope.grade}' == 'A'){
			$("#admin").show();
			$("#login").hide();
			$("#logout").show();
		}else{
			$("#login").show();
			$("#logout").hide();
			$("#admin").hide();
		}
	});
</script>
</head>
<body>
<!-- 상단 메뉴 -->
<div style="background-color: #EEEEEE;">
<table class="table">
  <tr>
    <td class="td">
        <img class="img" src="${pageContext.request.contextPath }/images/main-03.jpg">
    </td>
  </tr>
  
  <tr>
    <td class="td">
   <nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="${pageContext.request.contextPath}/">Spring MVC</a>
    </div>
    <ul class="nav navbar-nav">
      <li class="active"><a href="${pageContext.request.contextPath}/">Home</a></li>
      <li  class="dropdown"><a class="dropdown-toggle" data-toggle="dropdown" href="#">Membership <span class="caret"></span></a>
        <ul class="dropdown-menu">
        <c:choose>
        	<c:when test="${not empty sessionScope.id}">
          <li><a href="${pageContext.request.contextPath }/member/update">정보수정</a></li>
          <li><a href="${pageContext.request.contextPath }/member/delete">회원탈퇴</a></li>
        	</c:when>
        	<c:otherwise>
          <li><a href="${pageContext.request.contextPath }/member/agree">회원가입</a></li>
        	</c:otherwise>       
        </c:choose>
        </ul>
      </li>
      <li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Community<span class="caret"></span></a>
          <ul class="dropdown-menu">
          <li><a href="${pageContext.request.contextPath }/memo/list">Memo</a></li>
          <li><a href="${pageContext.request.contextPath }/bbs/list">BBS</a></li>
          <li><a href="${pageContext.request.contextPath }/image/list">Img</a></li>
        </ul>
      </li>
		<li id="admin" class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">Admin<span class="caret"></span></a>
          <ul class="dropdown-menu">
          <li><a href="${pageContext.request.contextPath}/admin/list">회원목록</a></li>
          <li><a href="${pageContext.request.contextPath}/admin/cal/calendar">일정관리</a></li>
          <li><a href="${pageContext.request.contextPath }/admin/team/list">팀목록</a></li>
        </ul>
      </li>
    </ul>
    <ul id="login" class="nav navbar-nav navbar-right">
      <li><a href="${pageContext.request.contextPath }/member/agree"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
      <li><a href="${pageContext.request.contextPath }/member/login"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
    </ul>
     <ul id="logout" class="nav navbar-nav navbar-right">
      <li><a href="${pageContext.request.contextPath }/member/read"><span class="glyphicon glyphicon-user"></span> Signed</a></li>
      <li><a href="${pageContext.request.contextPath}/member/logout"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
    </ul>
  </div>
</nav>
    </td> 
  </tr>
 
</table>
</div>
<!-- 상단 메뉴 끝 -->
 
 
 
<!-- 내용 시작 -->
<div style="width: 100%; padding-top: 10px;">