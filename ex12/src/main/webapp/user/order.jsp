<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div>
	<h1>주문목록</h1>
	<div id="div_purchase"></div>
	<jsp:include page="modal_orders.jsp"/>
</div>
<script id="temp_purchase" type="x-handlebars-template">
	<table class="table table-bordered table-hover">
		<tr class="text-center">
			<td>주문번호</td>
			<td>전화</td>
			<td>주문일</td>
			<td>금액</td>
			<td>상태</td>
			<td>주문상품</td>
		</tr>
		{{#each .}}
		<tr>
			<td>{{pid}}</td>
			<td>{{phone}}</td>
			<td>{{pdate}}</td>
			<td class="text-end">{{sum sum 1}}</td>
			<td>{{status status}}</td>
			<td><button class="btn btn-primary btn-sm orders" pid="{{pid}}" address1="{{address1}}" address2="{{address2}}">주문상품</td>
		</tr>
		{{/each}}
	</table>
</script>

<script>
	Handlebars.registerHelper("status",function(status){
		switch(status){
		case 0:
			return "결제대기";
		case 1:
			return "결제확인";
		case 2:
			return "배송준비";
		case 3:
			return "배송완료";
		case 4:
			return "주문완료";
		}
	});
</script>

<script>
	getData();
	
	//주문상품 버튼을 클릭한 경우
	$("#div_purchase").on("click",".orders",function(){
		const pid=$(this).attr("pid");
		const address1=$(this).attr("address1");
		const address2=$(this).attr("address2");
		$("#modalOrders").modal("show");
		$("#pid").html("주문번호:"+pid);
		$("#address").html("배송지:"+ address1 + " " + address2);
		getOrders(pid);
	});
	
	function getData(){
		$.ajax({
			type:"get",
			url:"/purchase/list.json",
			data:{uid},
			dataType:"json",
			success:function(data){
				console.log(data);
				const temp=Handlebars.compile($("#temp_purchase").html());
				$("#div_purchase").html(temp(data));
			}
		});	
	}
</script>