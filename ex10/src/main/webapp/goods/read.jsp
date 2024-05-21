<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div>
	<div class="row">
		<div class="col">
			<img src="${goods.image}" width="90%">
		</div>
		<div class="col">
			<h4>${goods.title}</h4>
			<hr>
			<div class="mb-3">가격:${goods.price}</div>
			<div class="mb-3">브랜드:${goods.brand}</div>
			<div class="mb-3">등록일:${goods.regDate}</div>
			<div class="mb-3">배송정보:우체국택배</div>
			<div class="mb-3">카드할인:현대카드 무이자 최대 3개월</div>
			<div class="my-3">
				<button class="px-5 btn btn-warning">바로구매</button>
				<button class="px-5 btn btn-success" id="cart">장바구니</button>
				
			</div>
		</div>
	</div>
</div>

<script>
	const gid="${goods.gid}";
	$("#cart").on("click",function(){
		if(uid){
			//장바구니 넣기
			$.ajax({
				type:"post",
				url:"/cart/insert",
				data:{uid,gid},
				success:function(data){
					if(data=="true"){
						alert("장바구니에 넣었습니다.");
					}else{
						alert("장바구니에 있는 상품입니다.");
					}
				}
			});
		}else{
			sessionStorage.setItem("target","/goods/read?gid="+gid);
			location.href="/user/login";
		}
	});
</script>

