<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
	#div_user img{
		border-radius:50%;
		border: 1px solid gray;
	}
</style>    
<div>
	<h1>사용자목록</h1>
	<div id="div_user"></div>
</div>
<script id="temp_user" type="x-handlebars-template">
		{{#each .}}
		<div class="card mb-2 mx-5">
			<div class="row card-body">
				<div class="col-3 col-md-2 col-lg-1">
					<img src="{{photo photo}}" width="90%"/>
				</div>
				<div class="col">
					<div>{{uname}} ({{uid}})</div>
					<div>전화:{{phone}}</div>
					<div>주소: {{address1}} {{address2}}</div>
				</div>
			</div>
		</div>
		{{/each}}
</script>
<script>
	Handlebars.registerHelper("photo", function(photo){
		if(photo){
			return photo;
		}else{
			return "http://via.placeholder.com/50x50";
		}
	});
</script>
<script>
	$.ajax({
		type:"get",
		url:"/user/list.json",
		dataType:"json",
		success:function(data){
			console.log(data);
			const temp=Handlebars.compile($("#temp_user").html());
			$("#div_user").html(temp(data));
		}
	});
</script>