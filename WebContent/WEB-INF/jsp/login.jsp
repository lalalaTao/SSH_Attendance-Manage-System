<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>学生考勤管理系统</title>
<link href="css/styles.css" rel="stylesheet" type="text/css" />
<style type="text/css">
img, div { behavior: url(iepngfix.htc) }
</style>
</head>

<body>
<body id="login">
		<div id="wrappertop" style="margin-top:200px;"></div>
			<div id="wrapper">
					<div id="content">
						<div id="header" align="center" style="color:#FF00FF">
							<h1><h2>学生考勤管理系统</h2></h1>
						</div>
							
						<div id="darkbannerwrap">
						</div>
						<form action="login.html" method="post">
						<fieldset class="form">
							<p><div  style="font-size:18px; color:#2E2E2E">${errorMessage}</div></p>
                        	<p>	<div style="margin-top:17px">					
								<label for="user_name" style="font-size:20px; color:#086A87; margin-top:-7px" >账号:</label>
								<input name="username" id="user_name" type="text">
							
							</p>
							<p>
								<label for="user_password" style="font-size:20px; color:#086A87 ; margin-top:-7px"">密码:</label>
								<input name="password" id="user_password" type="password">
							</p></div>	
							<button type="submit" class="positive" name="Submit" style="font-size:20px; color:#008080; width:100px; margin-left:120px">
								                   登录</button>
                            </fieldset>	
					    </form>
					   </div>
				    </div>   

<div id="wrapperbottom_branding"><div id="wrapperbottom_branding_text" style="font-size:20px; color:#848484">登录页面</div></div>

</body>
</html>