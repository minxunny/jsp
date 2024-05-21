<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<ul class="nav nav-tabs my-5" id="myTab" role="tablist">
  <li class="nav-item" role="presentation">
    <button class="nav-link active" id="home-tab" data-bs-toggle="tab" data-bs-target="#home-tab-pane" type="button" role="tab" aria-controls="home-tab-pane" aria-selected="true">담당과목</button>
  </li>
  <li class="nav-item" role="presentation">
    <button class="nav-link" id="profile-tab" data-bs-toggle="tab" data-bs-target="#profile-tab-pane" type="button" role="tab" aria-controls="profile-tab-pane" aria-selected="false">지도학생</button>
  </li>
</ul>
<div class="tab-content" id="myTabContent">
  <div class="tab-pane fade show active" id="home-tab-pane" role="tabpanel" aria-labelledby="home-tab" tabindex="0">
  	<h1>담당과목</h1>
  	<div id="div_cou"></div>
  </div>
  <div class="tab-pane fade" id="profile-tab-pane" role="tabpanel" aria-labelledby="profile-tab" tabindex="0">
  	<h1>지도학생</h1>
  	<div id="div_stu"></div>
  </div>
</div>
<script id="temp_cou" type="x-handlebars-template">
	<table class="table table-bordered table-hover">
		<tr class="text-center">
			<td>강좌번호</td>
			<td>강좌이름</td>
			<td>강의시간</td>
			<td>강의실</td>
			<td>신청인원</td>
		</tr>
		{{#each .}}
		<tr class="text-center">
			<td>{{lcode}}</td>
			<td><a href="/cou/read?lcode={{lcode}}">{{lname}}</a></td>
			<td>{{hours}}</td>
			<td>{{room}}</td>
			<td>{{persons}}/{{capacity}}</td>
		</tr>
		{{/each}}
	</table>
</script>
<script id="temp_stu" type="x-handlebars-templage">
	<table class="table table-bordered table-hover">
		<tr class="text-center">
			<td>학생번호</td>
			<td>학생이름</td>
			<td>학생학과</td>
			<td>학생학년</td>
			<td>생년월일</td>
		</tr>
		{{#each .}}
		<tr class="text-center">
			<td>{{scode}}</td>
			<td><a href="/stu/read?scode={{scode}}">{{sname}}</a></td>
			<td>{{sdept}}</td>
			<td>{{year}}학년</td>
			<td>{{birthday}}</td>
		</tr>
		{{/each}}
	</table>
</script>

<script>
	let page=1;
	let size=100;
	let word="${pro.pcode}";
	
	getCou();
	function getCou(){
		let key="instructor";
		$.ajax({
			type:"get",
			url:"/cou/list.json",
			data:{page, size, key, word},
			dataType:"json",
			success:function(data){
				console.log("담당과목", data);
				const temp=Handlebars.compile($("#temp_cou").html());
				$("#div_cou").html(temp(data));
			}
		});
	}
	
	getStu();
	function getStu(){
		let key="advisor";
		$.ajax({
			type:"get",
			url:"/stu/list.json",
			data:{page, size, key, word},
			dataType:"json",
			success:function(data){
				console.log("지도학생", data);
				const temp=Handlebars.compile($("#temp_stu").html());
				$("#div_stu").html(temp(data));
			}
		});
	}
</script>