<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<h1>게시판</h1>
	<div class="row">
	<div class="col-6 col-md-4 col-lg-3 mb-2">
		<form name="frm">
			<div class="input-group">
				<input name="query" class="form-control">
				<button class="btn btn-primary">검색</button>
			</div>
		</form>
	</div>
	<div class="col">
		<span id="total"></span>
	</div>
	<div class="col text-end mb-2" id="div_write">
		<a href="/bbs/insert" class="btn btn-primary btn-sm px-3">글쓰기</a>
	</div>
	</div>
	<div id="div_bbs"></div>
	<div id="pagination" class="pagination justify-content-center mt-5"></div>
	</div>
<script id="temp_bbs" type="x-handlebars-template">
	<table class="table table-striped">
		<tr>
			<td>ID</td>
			<td>Title</td>
			<td>Writer</td>
			<td>Date</td>
		</tr>
		{{#each .}}
			<tr>
				<td>{{bid}}</td>
				<td>
				<td><a href="/bbs/read?bid={{bid}}">{{title}}</a></td>
				</td>
				<td>{{uname}}({{writer}})</td>
				<td>{{bdate}}</td>
			</tr>
		{{/each}}
	</table>
</script>
<script>
if(uid){
	$("#div_write").show();
}else{
	$("#div_write").hide();
}

let page=1;
const size=10;
let query="";

$(frm).on("submit",function(e){
	e.preventDefault();
	query=$(frm.query).val();
	page=1;
	getTotal();
});

getData();
	function getData(){
		$.ajax({
			type:"get",
			url:"/bbs/list.json",
			dataType:"json",
			data:{page,size,query},
			success:function(data){
				const temp=Handlebars.compile($("#temp_bbs").html());
				$("#div_bbs").html(temp(data));
				
			}
		});
	}
	
	getTotal();
	function getTotal(){
		$.ajax({
			type:"get",
			url:"/bbs/total",
			data:{query},
			success:function(data){
				if(data==0){
					alert("검색내용이 없습니다.");
					$(frm.query).val("");
				}else{
				const totalPage=Math.ceil(data/size);
				$("#pagination").twbsPagination("changeTotalPages", totalPage, page);
				$("#total").html("검색수:"+data+"건")
				}
			}
			
		});
	}
	
	//페이지네이션 출력
	   $('#pagination').twbsPagination({
		      totalPages:100, 
		      visiblePages: 10, 
		      startPage : 1,
		      initiateStartPageClick: false, 
		      first:'<i class="bi bi-skip-backward"></i>', 
		      prev :'<i class="bi bi-skip-start"></i>',
		      next :'<i class="bi bi-skip-end"></i>',
		      last :'<i class="bi bi-skip-forward"></i>',
		      onPageClick: function (event, clickPage) {
		          page=clickPage; 
		          getData();
		      }
		   });
	
	
</script>