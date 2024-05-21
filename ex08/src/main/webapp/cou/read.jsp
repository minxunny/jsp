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
		<div><h1>강좌정보</h1></div>
		<table class="table table-bordered">
			<tr class="text-center">
				<td class="title">강좌번호</td>
				<td>${cou.lcode}</td>
				<td class="title">강좌이름</td>
				<td colspan="3">${cou.lname}</td>
			</tr>
			<tr class="text-center">
				<td class="title">강의실</td>
				<td>${cou.room}</td>
				<td class="title">신청인원</td>
				<td>${cou.persons}/${cou.capacity}</td>
				<td class="title">담당교수</td>
				<td>${cou.pname}(${cou.instructor})</td>
			</tr>
		</table>
		<div class="text-center my-5">
			<button class="btn btn-promary" id="update">강좌수정</button>
			<button class="btn btn-danger" id="delete">강좌삭제</button>
		</div>
	</div>
</div>
<jsp:include page="/cou/info.jsp"/>
<script>
//수정버튼을 클릭한 경우
	$("#update").on("click",function(){
		const lcode="${cou.lcode}";
		location.href="/cou/update?lcode="+lcode;
	});

//삭제버튼을 클릭한 경우
	$("#delete").on("click",function(){
		const lcode="${cou.lcode}";
		if(confirm(lcode + "번 강좌를 삭제하실래요?")){
			//강좌삭제
		$.ajax({
			type:"post",
			url:"/cou/delete",
			data:{lcode},
			success:function(data){
				if(data=="true"){
					alert("삭제성공!");
					location.href="/cou/list";
				}else{
					alert("이 강좌를 신청한 학생들이 존재합니다.");
				}
			}
		});
	}
});
</script>