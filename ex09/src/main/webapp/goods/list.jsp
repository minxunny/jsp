<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<h1>상품목록</h1>
	<div id="div_shop"></div>
</div>
<script id="temp_shop" type="x-handlebars-template">
   <table class="table table-borderd table-hover">
      <tr class="text-center">
         <td><input type="checkbox" id="all"></td>
         <td>상품코드</td><td colspan=2>상품명</td><td>가격</td><td>삭제</td>
      </tr>
      {{#each .}}
         <td class="text-center"><input type="checkbox" class="chk"></td>
         <td>{{gid}}</td>
         <td><img src={{image}} width="100"></td>
         <td>
            <div class="ellipsis">{{{title}}}</div>
            <div>{{regDate}}</div>
         </td>
         <td>{{price}}</td>
         <td><button class="btn btn-outline-danger delete" gid="{{gid}}">삭제</button></td>
      </tr>
      {{/each}}
   </table>
</script>
<script>
	getData();
	//삭제버튼을 누른 경우
	   $("#div_shop").on("click",".delete",function(){
	      const gid=$(this).attr("gid");
	      if(confirm(gid + "번 상품을 삭제하실래요?")){
	         //삭제하기
	         $.ajax({
	            type:"post",
	            url:"/goods/delete",
	            data:{gid},
	            success:function(data){
	               if(data=="true"){
	                  alert("삭제성공!");
	                  getData();
	               }else{
	                  alert("삭제실패!");
	               }
	            }
	         });
	      }
	   });
	
	function getData(){
		$.ajax({
			type:"get",
			url:"/goods/list.json",
			dataType:"json",
			success:function(data){
				const temp=Handlebars.compile($("#temp_shop").html());
				$("#div_shop").html(temp(data));
			}
		});
	}
</script>