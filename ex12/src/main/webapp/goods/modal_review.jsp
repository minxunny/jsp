<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!-- Modal -->
<div class="modal fade" id="modalreview" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog modagl-lg">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="staticBackdropLabel">리뷰작성</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
      	<input id="rid">
        <textarea id="content" rows="10" class="form-control" placeholder="내용을 입력하세요."></textarea>
      </div>
      <div class="text-center mt-3 mb-5">
        <button type="button" class="btn btn-success px-5 insert" id="btn-update">수정</button>
        <button type="button" class="btn btn-secondary px-5 insert" id="btn-insert">등록</button>
        <button type="button" class="btn btn-primary px-5" data-bs-dismiss="modal">취소</button>
      </div>
    </div>
  </div>
</div>

<script>
	$("#btn-insert").on("click",function(){
		const content=$("#content").val();
		if(content==""){
			alert("리뷰 내용을 작성하세요.");
			$("#content").focus();
		}else{
			$.ajax({
	            type:"post",
	            url:"/review/insert",
	            data:{uid,gid,content},
	            success:function(){
	               alert("등록성공!");
	               $("#content").val("");
	               $("#modalreview").modal("hide");
	               getTotal();
	            }
	         });
		}
	});
</script>




