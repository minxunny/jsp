<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
   <h1>도서검색</h1>
   <div class="row mb-3">
      <div class="col-8 col-md-6 col-lg-4">
         <form name="frm">
            <div class="input-group">
               <input  class="form-control">
               <button class="btn btn-dark">검색</button>
            </div>
         </form>
      </div>
   </div>
   <div id="div_book" class="row"></div>
</div>
<script id="temp_book" type="x-handlebars-template">
	{{#each documents}}
		<div class="col-6 col-md-4 col-lg-2">
			<div class="card">
				<div class="card-body">
					<img src="{{thumbnail}}">
				</div>
				<div class="card-footer">
					<div class="ellipsis">{{title}}</div>
				</div>
			</div>
		</div>
	{{/each}}
</script>
<script>
	getData();
	function getData(){
		$.ajax({
			type:"get",
			url:"https://dapi.kakao.com/v3/search/book?target=title",
			headers:{"Authorization":"KakaoAK d38f2d221a9be4580957340c539f01ab"},
			dataType:"json",
			data:{query:"JSP", page:1, size:6},
			success:function(data){
				console.log(data);
				const temp=Handlebars.compile($("#temp_book").html());
				$("#div_book").html(temp(data));
			}
		});
	}
</script>