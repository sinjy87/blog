<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
<table border="0" cellpadding="0" cellspacing="0" width="50%" align='center' style="margin-top: 0px">
  <tr>
    <td align="center"><a
      href="${pageContext.request.contextPath }/?y=${prevYear }&m=${prevMonth}">[이전달]</a> 
      ${year}년 ${month+1}월 <a
      href="${pageContext.request.contextPath }/?y=${nextYear}&m=${nextMonth}">[다음달]</a></td>
  </tr> 
  <tr>
    <td>
      <table class='calendar' cellpadding="0" cellspacing="0" width='100%' align='center'>
        <tr>
          <td class='calendar_td' width='14%' align='center'>일</td>
          <td class='calendar_td' width='14%' align='center'>월</td>
          <td class='calendar_td' width='14%' align='center'>화</td>
          <td class='calendar_td' width='14%' align='center'>수</td>
          <td class='calendar_td' width='14%' align='center'>목</td>
          <td class='calendar_td' width='14%' align='center'>금</td>
          <td class='calendar_td' width='16%' align='center'>토</td>
        </tr>
        <!-- ******************** 날짜 출력 시작 ******************** -->
        ${calendar }
        <!-- ******************** 날짜 출력 종료 ******************** -->
        </table>
      </td>
    </tr>
  </table>
</body>
</html>

