<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
	#modalOrders{
		top:20%;
	}
</style>
<!-- Modal -->
<div class="modal fade" id="modalOrders" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modal-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="pid"></h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <div id="address"></div>
        <hr>
        <div id="div_orders"></div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
<script id="temp_orders" type="x-handlebars-template">
	<table class="table table-bordered table-hover">
		<tr class="text-center">
			<td>상품번호</td>
			<td>상품이름</td>
			<td>가격</td>
			<td>수량</td>
			<td>금액</td>
		</tr>
		{{#each .}}
		<tr class="text-center" gid="{{gid}}">
			<td>{{gid}}</td>
			<td class="text-start">
				<img src="{{image}}" width="50px">
				<a href="/goods/read?gid={{gid}}">{{{title}}}</a>
			</td>
			<td>{{sum price 1}}</td>
			<td>{{qnt}}</td>
			<td>{{sum price qnt}}</td>
		</tr>
		{{/each}}
	</table>
</script>
<script>
	Handlebars.registerHelper("sum", function(price, qnt){
		const sum=price*qnt;
		return sum.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	});
</script>	
<script>
	function getOrders(pid){
		$.ajax({
			type:"get",
			url:"/order/list.json",
			dataType:"json",
			data: {pid},
			success:function(data){
				console.log(data);
				const temp=Handlebars.compile($("#temp_orders").html());
				$("#div_orders").html(temp(data));
			}
		});
	}
</script>