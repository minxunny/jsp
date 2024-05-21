
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
	#info img {
		border-radius: 10px;
	}
	
	.bi-heart, .bi-heart-fill{
		color: red;
		cursor:pointer;
	}
</style>
<div>
	<div class="row my-5" id="info">
		<div class="col-md-6 mb-5">
			<img src="${goods.image}" width="80%">
		</div>
		<div class="col">
			<h4>
				${goods.title}
				<span class="bi bi-heart-fill" id="heart">
					<span id="fcnt" style="font-size:15px;"></span>
				</span>
			</h4><hr>
			<div class="mb-3">상품코드:${goods.gid}</div>
			<div class="mb-3">가격: <fmt:formatNumber value="${goods.price}" pattern="#,###원"/></div>
			<div class="mb-3">브랜드: ${goods.brand}</div>
			<div class="mb-3">등록일: ${goods.regDate}</div>
			<div class="mb-3">배송정보: 한진택배</div>
			<div class="mb-3">카드할인: 하나카드 무이자 최대12개월</div>
			<div class="my-5">
				<button class="px-5 btn btn-warning">바로구매</button>
				<button class="px-5 btn btn-success" id="cart">장바구니</button>
			</div>
		</div>
	</div>
</div>
<script>
	const gid="${goods.gid}";
	const ucnt="${param.ucnt}";
	const fcnt="${param.fcnt}";
	
	$("#fcnt").html(fcnt);
	if(ucnt=="0"){
		$("#heart").removeClass("bi-heart-fill");
		$("#heart").addClass("bi-heart");
	}else{
		$("#heart").removeClass("bi-heart");
		$("#heart").addClass("bi-heart-fill");
	}
	
	//빈하트를 클릭한 경우
	$(".bi-heart").on("click",function(){
		const gid=$(this).attr("gid");
		if(uid){
			$.ajax({
				type:"post",
				url:"/favorite/insert",
				data:{uid, gid},
				success:function(){
					alert("좋아요 등록")
					location.href="/goods/read/gid"+gid;
				}
			});
		}else{
			sessionStorage.setItem("target","/goods/read/gid"+gid);
			location.href="/user/login";
		}
	});
	
	//채워진 하트를 클릭한 경우
	$(".bi-heart-fill").on("click",function(){
		const gid=$(this).attr("gid");
			$.ajax({
				type:"post",
				url:"/favorite/delete",
				data:{uid, gid},
				success:function(){
					alert("좋아요 취소")
					location.href="/goods/read/gid"+gid;
			});
		}
	});
	
	//장바구니 버튼을 클릭한 경우
	$("#cart").on("click", function(){
		if(uid){
			//장바구니 넣기
			$.ajax({
				type:"post",
				url:"/cart/insert",
				data:{uid, gid},
				success:function(data){
					let message="";
					if(data=="true"){
						message ="장바구에 넣었습니다!";
					}else{
						message ="장바구에 있는 상품입니다!";
					}
					if(confirm(message + "\n장바구니로 이동하실래요?")){
						location.href="/cart/list";
					}else{
						location.href="/";
					}
				}
			});
		}else{
			sessionStorage.setItem("target", "/goods/read?gid=" + gid);
			location.href="/user/login";
		}
	});
</script>
