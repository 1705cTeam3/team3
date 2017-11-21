<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html lang="en" class="no-js">
<jsp:include page="/base.jsp"></jsp:include>
    <head>
        <meta charset="utf-8">
        <title>1705C-3组登录页面</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- CSS -->
        <link rel='stylesheet' href='http://fonts.googleapis.com/css?family=PT+Sans:400,700'>
        <link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/reset.css">
        <link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/supersized.css">
        <link rel="stylesheet" href="<%=request.getContextPath() %>/assets/css/style.css">
    </head>

    <body>
    
  

        <div class="page-container">
            <h1>1705C-3组登录页面</h1>
            <form id="login-form">
                <input type="text" name="cLoginid" class="username" placeholder="1705C-3组">
                <input type="password" name="cPassword" class="password" placeholder="Password">
                
               	 验证码:<input class="easyui-textbox" data-options="required:true,validType:'codelength'" style="width:150px" name="authImage" >
				<a href="javascript:F5Img('<%=request.getContextPath()%>')">
					<img src="<%=request.getContextPath()%>/authImage" id="img"/>
				</a>
                <button type="button" onclick="login()">Sign me in</button>
                <div class="error"><span>+</span></div>
            </form>
            <div class="connect">
                <p>Or connect with:</p>
                <p>
                    <a class="facebook" href=""></a>
                    <a class="twitter" href=""></a>
                </p>
            </div>
        </div>
     
        <!-- Javascript -->
        <script src="<%=request.getContextPath() %>/assets/js/jquery-1.8.2.min.js"></script>
        <script src="<%=request.getContextPath() %>/assets/js/supersized.3.2.7.min.js"></script>
        <script src="<%=request.getContextPath() %>/assets/js/supersized-init.js"></script>
        <script src="<%=request.getContextPath() %>/assets/js/scripts.js"></script>

<script type="text/javascript">
function F5Img(path){
	//Math.random() 保证每次的请求地址都不一样
	document.getElementById("img").src = path+"/authImage?"+Math.random();
}
	function login(){
		$.ajax({													   
			url:"<%=request.getContextPath()%>/loginUser.do",
			type:"post",
			data:$("#login-form").serialize(),
			dataType:"json",
			async:true,
			success:function(){
					alert("成功")
					//location.href="<%=request.getContextPath()%>/toHomePage.do";
			}
		});
	}
</script>
    </body>

</html>