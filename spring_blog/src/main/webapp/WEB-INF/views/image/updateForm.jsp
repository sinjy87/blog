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
	
	if(f.name.value==""){
		alert("이름을 입력해주세요");
		f.name.focus();
		return false;
		
	}
	
	if(f.title.value==""){
		alert("제목을 입력해주세요");
		f.title.focus();
		return false;
		
	}
	
	if(f.content.value==""){
		alert("내용을 입력해주세요");
		f.content.focus();
		return false;
		
	}
	
	if(f.passwd.value==""){
		alert("비밀번호을 입력해주세요");
		f.passwd.focus();
		return false;
		
	}
	if(f.filename.value==""){
		alert("파일을 입력해주세요");
		f.filename.focus();
		return false;
		
	}
}


</script>


<link href="${pageContext.request.contextPath }/css/style.css" rel="Stylesheet" type="text/css">
</head> 
<!-- *********************************************** -->
<body>

<!-- *********************************************** -->
 
<DIV class="title">정보 수정</DIV>
 
<FORM name='frm' method='POST' action='./update' enctype="multipart/form-data" onsubmit="return checkInput(this)">

<input type= "hidden" name="col" value =${param.col }>
<input type= "hidden" name="word" value =${param.word }>
<input type= "hidden" name="nowPage" value =${param.nowPage }>
<input type= "hidden" name="no" value =${param.no }>
<input type= "hidden" name="oldfile" value =${dto.filename }>


  <TABLE>
    <TR>
      <TH>이름</TH>
      <TD><input type="text" name="name" value="${dto.name}"></TD>
    </TR>
    <TR>
      <TH>제목</TH>
      <TD><input type="text" name="title" value="${dto.title}"></TD>
    </TR>
    <TR>
      <TH>내용</TH>
      <TD><textarea name="content">${dto.content}</textarea></TD>
    </TR>
    
    <TR>
      <TH>비밀번호</TH>
      <TD><input type="password" name="passwd" value="${dto.passwd}"></TD>
    </TR>
    <TR>
      <TH>사진</TH>
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
