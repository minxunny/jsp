<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
    	table.title{
    		background:blue;
    		color:white;
    	}
</style>
<div class="row">
	<div class="col">
		<div><h1>학생정보</h1></div>
		<table class="table table-bordered">
			<tr>
				<td class="title">학생번호</td>
				<td>${stu.scode}</td>
				<td class="title">학생이름</td>
				<td>${stu.sname}</td>
				<td class="title">학생학과</td>
				<td>${stu.sdept}</td>
			</tr>
			<tr>
				<td class="title">생년월일</td>
				<td>${stu.birthday}</td>
				<td class="title">학생학년</td>
				<td>${stu.year}</td>
				<td class="title">지도교수</td>
				<td>${stu.pname}(${stu.advisor})</td>
			</tr>
		</table>
		<div class="text-center my-5">
			<button class="btn btn-promary" id="update">학생수정</button>
			<button class="btn btn-danger" id="delete">학생삭제</button>
		</div>
	</div>
</div>
<script>
//수정버튼을 클릭한 경우
	$("#update").on("click",function(){
		const pcode="${pro.pcode}";
		location.href="/pro/update?pcode="+pcode;
	});

//삭제버튼을 클릭한 경우
	$("#delete").on("click",function(){
		const pcode="${pro.pcode}";
		if(confirm(pcode + "번 교수를 삭제하실래요?")){
			//교수삭제
			$.ajax({
				type:"post",
				url:"/pro/delete",
				data:{pcode},
				success:function(data){
					if(data==1){
					alert("삭제완료!");
					location.href="/pro/list";
					}else{
						alert("지도학생과 담당과목이 존재합니다.");
					}
				}
			});
		}
	});
</script>