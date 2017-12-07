<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html lang="en" class="no-js">

    <head>
      <jsp:include page="/base.jsp"></jsp:include>
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
                <input type="text" name="name" class="username" placeholder="1705C-3组">
                <input type="password" name="password" class="password" placeholder="Password">
                
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
			url:"<%=request.getContextPath()%>/queryLogin.do",
			type:"post",
			data:$("#login-form").serialize(),
			dataType:"json",
			async:true,
			success:function(text){
				 if(text==2){
		            	alert("用户名不存在");
				   }
		            if(text==3){
		            	alert("请输入用户名和密码");
					   }
		            if(text==4){
		            	alert("密码错误");
					   }
		            if(text==5){
		            	alert("登录成功！");
		            	location.href="<%=request.getContextPath()%>/index.jsp";
					   } 
	 	            if(text==6){
		            	alert("验证码错误");
		            	
					   } 
			},
			error:function(){
				alert("登陆异常!!");
			}
		});
	}
</script>
    </body>

</html>