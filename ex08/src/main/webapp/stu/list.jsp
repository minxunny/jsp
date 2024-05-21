<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
   #size { 
      width:100px;
      float: right;
   }
   .page-link {
     color: #000; 
     background-color: #fff;
     border: 1px solid #fff;
     border-radius : 15%; 
   }
   
   .page-item.active .page-link {
    z-index: 1;
    color: #4A6BD6;
    background-color: #f1f1f1;
    border-color: #ccc;
    
   }
   
   .page-link:focus, .page-link:hover {
     color: #000;
     background-color: #fafafa; 
     border-color: #ccc;
   }
</style>
<div>
	<h1>학생관리</h1>
	<div class="row mt-5 mb-3">
		<form class="col" name="frm">
			<div class="input-group">
				<select class="form-select me-3" name="key">
					<option value="scode">학생번호</option>
					<option value="sname" selected>학생이름</option>
					<option value="dept">학생학과</option>
					<option value="pname">지도교수</option>
				</select>
				<input placeholder="검색어" class="form-control" name="word">
				<button class="btn btn-primary">검색</button>
				<span id="total" class="mt-2 ms-3"></span>
			</div>
		</form>
		<div class="col">
			<select class="form-select" id="size">
				<option value="2">2행</option>
				<option value="3">3행</option>
				<option value="4">4행</option>
				<option value="5" selected>5행</option>
			</select>
		</div>
	</div>
	<hr>
	<div id="div_stu"></div>
	<div id="pagination" class="pagination justify-content-center mt-5"></div>
</div>
<script id="temp_stu" type="x-handlebars-templage">
	<table class="table table-borderd table-hover text-center">
		<tr>
			<td>학생번호</td>
			<td>학생이름</td>
			<td>학생학과</td>
			<td>학생학년</td>
			<td>생년월일</td>
			<td>지도교수</td>
		</tr>
		{{#each .}}
		<tr>
			<td>{{scode}}</td>
			<td><a href="/stu/read?scode={{scode}}">{{sname}}</a></td>
			<td>{{sdept}}</td>
			<td>{{year}}학년</td>
			<td>{{birthday}}</td>
			<td>{{pname}}({{advisor}})</td>
		</tr>
		{{/each}}
	</table>
</script>
<script>
	let page=1;
	let size=$("#size").val();
	let key=$(frm.key).val();
	let word=$(frm.word).val();
	
	$(frm).on("submit", function(e){
		e.preventDefault();
		page=1;
		size=$("#size").val();
		key=$(frm.key).val();
		word=$(frm.word).val();
		getData();
	});
	
	$("#size").on("change", function(){
		size=$("#size").val();
		page=1;
		//getData();
		getTotal();
	});
	
	//getData();
	getTotal();
	function getData(){
		$.ajax({
			type:"get",
			url:"/stu/list.json",
			dataType:"json",
			data:{page, size, key, word},
			success:function(data){
				console.log(data);
				const temp=Handlebars.compile($("#temp_stu").html());
				$("#div_stu").html(temp(data));
			}
		});
	}
	

	function getTotal(){
		$.ajax({
			type:"get",
			url:"/stu/total",
			data:{key,word},
			success:function(data){
				let total=parseInt(data);
				$("#total").html("검색수:"+total);
				if(total==0){
					alert("검색내용이 없습니다.");
					return;
				}
				const totalPage=Math.ceil(total/size);
				$("#pagination").twbsPagination("changeTotalPages", totalPage, page);
				if(total>size){
					$("#pagination").show();
				}else{
					$("#pagination").hide();
				}
			}
		})
	}
	$('#pagination').twbsPagination({
		totalPages:10, 
		visiblePages: 5, 
		startPage : 1,
		initiateStartPageClick: false, 
		first:'<i class="bi bi-caret-left-fill"></i>', 
		prev :'<i class="bi bi-caret-left"></i>',
		next :'<i class="bi bi-caret-right"></i>',
		last :'<i class="bi bi-caret-right-fill"></i>',
		onPageClick: function (event, clickPage) {
			 page=clickPage; 
			 getData();
		}
	});
	
	
	
</script>