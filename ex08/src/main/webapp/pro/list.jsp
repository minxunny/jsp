<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	<h1>교수관리</h1>
	<div class="row mt-5 mb-3">
		<form class="col" name="frm">
			<div class="input-group">
				<select class="form-select me-3" name="key">
					<option value="pcode">교수번호</option>
					<option value="pname" selected>교수이름</option>
					<option value="dept">교수학과</option>
				</select>
				<input placeholder="검색어" class="form-control" name="word">
				<button class="btn btn-primary">검색</button>
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
	<div id="div_pro"></div>
	<div id="pagination" class="pagination justify-content-center mt-5"></div>
</div>
<script id="temp_pro" type="x-handlebars-template">
	<table class="table table-border table-hover">
		<tr>
			<td>교수번호</td>
			<td>교수이름</td>
			<td>교수학과</td>
			<td>교수직급</td>
			<td>교수급여</td>
			<td>임용일자</td>
		</tr>
		{{#each .}}
		<tr>
			<td>{{pcode}}</td>
			<td><a href="/pro/read?pcode={{pcode}}">{{pname}}</a></td>
			<td>{{dept}}</td>
			<td>{{title}}</td>
			<td>{{salary}}</td>
			<td>{{hiredate}}</td>
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
		key=$(frm.key).val();
		word=$(frm.word).val();
		size=$("#size").val();
		page=1;
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
			url:"/pro/list.json",
			data:{page, size, key, word},
			dataType:"json",
			success:function(data){
				console.log(data);
				const temp=Handlebars.compile($("#temp_pro").html());
				$("#div_pro").html(temp(data));
			}
		});
	}
	
	function getTotal(){
		$.ajax({
			type:"get",
			url:"/pro/total",
			data:{key, word},
			success:function(data){
				const totalPage=Math.ceil(data/size);
				$("#pagination").twbsPagination("changeTotalPages", totalPage, page);
				
			}
		});
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