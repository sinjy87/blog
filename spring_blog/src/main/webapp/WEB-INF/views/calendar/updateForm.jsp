<%@ page contentType="text/html; charset=UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title> 수정 </title>
<script type="text/javascript">
function f_delete(){  // 삭제
    var sw = confirm('정말로 삭제하시겠습니까?');
    if (sw == true){
      document.frm.action = './delete';
      document.frm.submit();
    }
} 
  
function f_list(){  // 목록 
  location.href='./list';
} 
</script>
<link href="${pageContext.request.contextPath }/css/style.css" rel="stylesheet" type="text/css">
</head>

<!-- *********************************************** -->
<body style="margin: 0px">
<!-- *********************************************** -->

<div class="title">
  조회 및 수정
</div>
    
<FORM name="frm" method="POST" action="./update">
  <input type='hidden' name='calendarno' value='${dto.calendarno }'>
  
  <table>
    <tr>
      <th width='20%'>레이블 날짜</th>
      <td width='80%' align='left'>
        <input type="date" name='labeldate' value='${dto.labeldate }' size='40'>
      </td>
    </tr>   
    <tr>
      <th>레이블</th>
      <td align='left'>
        <input type='text' name='label' value='${dto.label }' size='20'>
        형식: 최대 20자
        </td>
    </tr>  
    <tr>
      <th>제목</th>
      <td align='left'>
      <input type='text' name='title' value='${dto.title }'>    
      </td>
    </tr>
    <tr>
      <th>내용</th>
      <td align='left'>
      <TEXTAREA name='content' rows='10' cols='40'>${dto.content}</TEXTAREA>
      </td>
    </tr>      
     
  </table>
  
  <div class="bottom">
    <input type="submit" value="저장">
    <input type="button" value="취소(목록)" onclick="f_list();">
    <input type="button" value="삭제" onclick="f_delete()">
  </div>
  
</FORM>

<!-- *********************************************** -->
</body>
</html>
<!-- *********************************************** -->


