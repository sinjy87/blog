<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html> 
<head>
<meta charset="UTF-8">
<title> 등록 </title>
<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css">
</head>

<!-- *********************************************** -->
<body style="margin: 0px;">
<!-- *********************************************** -->

<div class="title">
  등록
</div>
    
<FORM name="frmData" method="POST" action="./create">
  <TABLE>
    <tr>
      <th width='20%'>출력 날짜</th>
      <td width='80%'>
        <input type="date" name='labeldate' size='10'>
       </td>
    </tr>  
    <tr>
      <th>출력 레이블</th>
      <td colspan='5' align='left'>
        <input type='text' name='label' value='' size='20'>
        형식: 최대 20자
        </td>
    </tr>  
    <tr>
      <th>제목</th>
      <td>
      <input type='text' name='title' value='' size='40'></td>      
    </tr>
    <tr>
      <th>내용</th>
      <td>
      <TEXTAREA name='content' rows='10' cols='40'></TEXTAREA>
      </td>
    </tr>      
     
  </table>

  <div class="bottom">
    <input type="submit" value="저장">
    <input type="button" value="취소(목록)" onclick="history.back();">
  </div>
  
</FORM>

<!-- *********************************************** -->
</body>
</html>
<!-- *********************************************** -->

