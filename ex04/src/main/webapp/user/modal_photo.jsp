<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<style>
	#modalPhoto {
		top: 30%;
	}
</style>    
<!-- Modal -->
<div class="modal fade" id="modalPhoto" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h1 class="modal-title fs-5" id="staticBackdropLabel">사진변경</h1>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body text-center">
      	<img id="photo" src="" width="200">
        <input id="file" type="file" style="display:none;">
      </div>
      <div class="text-center my-3">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button id="btnSave" type="button" class="btn btn-primary">Save</button>
      </div>
    </div>
  </div>
</div>
<script>
	$("#file").on("change", function(e){
		$("#photo").attr("src", URL.createObjectURL(e.target.files[0]));
	});
	
	$("#photo").on("click", function(){
		$("#file").click();
	});
	
	$("#modalPhoto").on("click", "#btnSave", function(){
		if($("#file").val()=="") {
			alert("변경할 사진을 선택하세요!")
		}else{
			if(confirm("사진을 수정하실래요?")){
				//사진 수정하기
				const file=$("#file")[0].files[0];
				const formData = new FormData();
				formData.append("photo", file);
				formData.append("uid", uid);
				$.ajax({
					type:"post",
					url:"/user/upload",
					data:formData,
					processData:false,
					contentType:false,
					success:function(){
						alert("업로드성공!");
						location.reload(true);
					}
				});
			}
		}
	});
</script>