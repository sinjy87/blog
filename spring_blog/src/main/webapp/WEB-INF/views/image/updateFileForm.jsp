<%@ page contentType="text/html; charset=UTF-8" %> 
 
<!DOCTYPE html> 
<html> 
<head> 
<meta charset="UTF-8"> 
<title></title> 
<style type="text/css"> 
*{ 
  font-family: gulim; 
  font-size: 20px; 
} 
</style> 

<script type="text/javascript">




function checkInput(f){
	
	if(f.filename.value==""){
		alert("파일을 변경되지 않았습니다. 변경해주세요");
		f.filename.focus();
		return false;
	}
	
}


</script>


<link href="${pagecontext.request.contextPath }/css/style.css" rel="Stylesheet" type="text/css">
</head> 
<!-- *********************************************** -->
<body>

<!-- *********************************************** -->
 
<DIV class="title">정보 수정</DIV>
 
<FORM name='frm' method='POST' action='./updateFile' enctype="multipart/form-data" onsubmit="return checkInput(this)">
 		 <input type= "hidden" name="col" value =${param.col }>
		<input type= "hidden" name="word" value =${param.word }>
		<input type= "hidden" name="nowPage" value =${param.nowPage }>
		<input type= "hidden" name="no" value =${param.no }>
		<input type= "hidden" name="oldfile" value =${dto.filename }>
  <TABLE>
  
    <TR>
      <TH>변경전 파일</TH>
      <TD><img src="${pageContext.request.contextPath }/image/storage/${dto.filename}" ></TD>
    </TR>
  
  
    <TR>
      <TH>변경 파일</TH>
      <TD><input type="file" name="fileMF"></TD>
    </TR>
  
  </TABLE>
  
  <DIV class='bottom'>
    <input type='submit' value='수정'>
    <input type='button' value='취소' onclick="history.back()">
  </DIV>
</FORM>
 
 
<!-- *********************************************** -->

</body>
<!-- *********************************************** -->
</html> 
