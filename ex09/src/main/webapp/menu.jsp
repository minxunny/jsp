<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-lg">
  <div class="container-fluid">
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
           <a class="nav-link active" aria-current="page" href="/">HOME</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            상품
          </a>
          <ul class="dropdown-menu">
            <li><a class="nav-link active" aria-current="page" href="/goods/search">상품검색</a></li>
            <li><a class="nav-link active" aria-current="page" href="/goods/list">상품목록</a></li>
          </ul>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            학생
          </a>
          <ul class="dropdown-menu">
            <li><a class="nav-link active" aria-current="page" href="/stu/list">학생관리</a></li>
            <li><a class="nav-link active" aria-current="page" href="/stu/insert">학생등록</a></li>
          </ul>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            강의
          </a>
          <ul class="dropdown-menu">
            <li><a class="nav-link active" aria-current="page" href="/cou/list">강의관리</a></li>
            <li><a class="nav-link active" aria-current="page" href="/cou/insert">강의등록</a></li>
          </ul>
        </li>
      </ul>
      <ul class="navbar-nav  mb-2 mb-lg-0">
        <li class="nav-item" id="login">
           <a class="nav-link" aria-current="page" href="/user/login">로그인</a>
        </li>
        <li class="nav-item" id="uid">
           <a class="nav-link active" aria-current="page" href="/user/mypage"></a>
        </li>
        <li class="nav-item" id="logout">
           <a class="nav-link active" aria-current="page" href="#">로그아웃</a>
        </li>
      </ul>
    </div>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
  </div>
</nav>
<script>
   const uid="${user.uid}";
   const uname="${user.uname}";
   if(uid){
      $("#login").hide();
      $("#logout").show();
      $("#uid a").html(uname);
   }else{
      $("#login").show();
      $("#logout").hide();
   }
   
   $("#logout").on("click", "a", function(e){
      e.preventDefault();
      if(confirm("정말로 로그아웃 하시겠습니까?")){
         location.href="/user/logout";
      }
   });
</script>