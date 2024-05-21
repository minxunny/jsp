<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<h1>장바구니</h1>
	<div id="div_cart"></div>
</div>
<script id="temp_cart" type="x-handlebars-template">
	<table class="table table-bordered table-hover">
		<tr class="text-center">
			<td>상품번호</td>
			<td>상품이름</td>
			<td>가격</td>
			<td>수량</td>
		</tr>
		{{#each .}}
		<tr class="text-center">
			<td>{{gid}}</td>
			<td class="text-start">
				<img src="{{image}}" width="50px">
				<a href="/goods/read?gid={{gid}}">{{{title}}}</a>
			</td>
			<td>{{price}}</td>
			<td><input value="{{qnt}}" size=2></td>
		</tr>
		{{/each}}
	</table>
</script>
<script>
	getData();
	function getData(){
		$.ajax({
			type:"get",
			url:"/cart/list.json",
			dataType:"json",
			data:{uid},
			success:function(data){
				const temp=Handlebars.compile($("#temp_cart").html());
				$("#div_cart").html(temp(data));
			}
		});
	}
</script>