<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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

h1 {
	border: 2px solid black;
	width: 45%;
	margin: auto;
	text-align: center;
}

table {
	border: 1px solid black;
	width: 100%;
	margin: 20px auto;
}

td {
	font-size: 100%;
	text-align: center;
}

table#td1 td {
	font-size: 100%;
	text-align: center;
}

#static1 {
	position: relative;
	top: 0px;
	left: 350px;
}

fieldset {
	margin: auto;
}

img {
	width: 300px;
	height: 300px;
}

#img1 {
	text-align: center;
}
</style>
<script type="text/javascript">
	function input(f) {
		if (f.phone.value == "") {
			alert("전화번호를 입력하세요");
			f.phone.focus();
			return false;
		}

		if (f.zipcode.value == "") {
			alert("우편번호를 입력하세요");
			f.zipcode.focus();
			return false;
		}
		if (f.address1.value == "") {
			alert("주소를 입력하세요");
			f.address1.focus();
			return false;
		}
		if (f.address2.value == "") {
			alert("상세주소를 입력하세요");
			f.address2.focus();
			return false;
		}

		var cnt = 0;

		for (var i = 0; i < f.skill.length; i++) {
			if (f.skill[i].checked) {
				cnt += 1;
			}
		}
		if (cnt == 0) {
			alert("보유 기술을 체크하세요");
			f.skill[0].focus();
			return false;
		}

		if (f.hobby.selectedIndex == 0) {
			alert("취미를 선택하세요");
			f.hobby.focus();
			return false;
		}

	}
</script>
<script src="https://spi.maps.daum.net/imap/map_js_init/postcode.v2.js"></script>
<script>
	function sample6_execDaumPostcode() {
		new daum.Postcode(
				{
					oncomplete : function(data) {
						// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

						// 각 주소의 노출 규칙에 따라 주소를 조합한다.
						// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
						var fullAddr = ''; // 최종 주소 변수
						var extraAddr = ''; // 조합형 주소 변수

						// 사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
						if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
							fullAddr = data.roadAddress;

						} else { // 사용자가 지번 주소를 선택했을 경우(J)
							fullAddr = data.jibunAddress;
						}

						// 사용자가 선택한 주소가 도로명 타입일때 조합한다.
						if (data.userSelectedType === 'R') {
							//법정동명이 있을 경우 추가한다.
							if (data.bname !== '') {
								extraAddr += data.bname;
							}
							// 건물명이 있을 경우 추가한다.
							if (data.buildingName !== '') {
								extraAddr += (extraAddr !== '' ? ', '
										+ data.buildingName : data.buildingName);
							}
							// 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
							fullAddr += (extraAddr !== '' ? ' (' + extraAddr
									+ ')' : '');
						}

						// 우편번호와 주소 정보를 해당 필드에 넣는다.
						document.getElementById('sample6_postcode').value = data.zonecode; //5자리 새우편번호 사용
						document.getElementById('sample6_address').value = fullAddr;

						// 커서를 상세주소 필드로 이동한다.
						document.getElementById('sample6_address2').focus();
					}
				}).open();
	}
</script>
</head>
<body>

	<fieldset style="width: 50%">
		<legend>팀정보수정</legend>
		<form name="frm" action="update" method="post"
			enctype="multipart/form-data"
			onsubmit="return input(this)">
			<input type="hidden" name="no" value="${param.no}">
			<input type="hidden" name="col" value="${param.col}">
			<input type="hidden" name="word" value="${param.word}">
			<input type="hidden" name="nowPage" value="${param.nowPage}">
			<input type="hidden" name="oldfile" value="${dto.filename}">
			
			<h1>정보</h1>
			<br>
			<table border="1">

				<tr>
					<td colspan="3">
					<img src="${pageContext.request.contextPath}/team/storage/${dto.filename}">
						<br> <br></td>
				</tr>

				<tr>

					<th colspan="2">이름:${dto.name} 성별:${dto.gender}<br>
					</th>



				</tr>

				<tr>
					<td>전화번호</td>
					<th><input type="text" name="phone"
						value="${dto.phone}"></th>
				</tr>

				<tr>
					<td>우편번호<br>주소<br> 상세주소
					</td>

					<th><input type="text" name="zipcode" size="40%"
						id="sample6_postcode" placeholder="우편번호"
						value="${dto.zipcode}"> <input type="button"
						onclick="sample6_execDaumPostcode()" value="우편번호 찾기"> <input
						type="text" name="address1" size="60%" id="sample6_address"
						placeholder="주소" value="${dto.address1}"> <input
						type="text" name="address2" size="80%" id="sample6_address2"
						placeholder="상세주소" value="${dto.address2}"></th>
				</tr>

				<tr>
					<td>보유기술</td>
					<th><label for="s1"> <input id="s1" type="checkbox"
							name="skill" value="의학적지식"
							<c:if test="${fn:indexOf(skill,'의학적지식') != -1 }">checked</c:if>
							>의학적지식
					</label> <label for="s2"> <input id="s2" type="checkbox"
							name="skill" value="심리학적지식"
							<c:if test="${fn:indexOf(skill,'심리학적지식') != -1 }">checked</c:if>
							>심리학적지식
					</label> <label for="s3"> <input id="s3" type="checkbox"
							name="skill" value="간호학적지식"
							<c:if test="${fn:indexOf(skill,'간호학적지식') != -1 }">checked</c:if>
							>간호학적지식
					</label></th>
				</tr>

				<tr>
					<td>취미</td>
					<th><select name="hobby">
							<option>취미를 선택하세요</option>
							<option value="의학서적읽기">의학서적읽기
							<option value="간호학서적읽기">간호학서적읽기
							<option value="심리학서적읽기">심리학서적읽기</option>

					</select></th>
					<script>
					document.frm.hobby.value='${dto.hobby}';
					</script>
			</table>


			<div id="static1">
			사진변경:<input type="file" name="fileMF"><br>
				<input type="submit" value="수정"> 
				<input type="reset" value="Reset">
			</div>
		</form>
	</fieldset>

</body>
</html>