<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta charset="utf-8">
    <title>登记作者信息</title>
    <script scr="js/checkauthor.js"></script>
    <link href="mystyle.css" rel="stylesheet" type="text/css" />
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css" />
  </head>
  <body class="body1"> 
    <div class="center">
    	<div class="word">
    		<h1 style="color:#F08080">作者信息不存在，需要登记作者信息</h1>
    	</div>
    	<form class="form-horizontal" action="AuthorRegistPro" method="post" >
		  <div class="control-group">
		    <label class="control-label" for="inputEmail">AuthorID</label>
		    <div class="controls">
		      <input type="text"  placeholder="AuthorID" name="author.AuthorID" readonly="readonly " value="${book.authorID }" required/>
		    </div>
		  </div>
		  <div class="control-group">
		    <label class="control-label" for="inputEmail">name</label>
		    <div class="controls">
		      <input type="text" id="name" placeholder="Name" name="author.name" required /><span><</span>
		    </div>
		  </div>
		  <div class="control-group">
		    <label class="control-label" for="inputEmail">Age</label>
		    <div class="controls">
		      <input type="text" id="age" placeholder="Age" name="author.Age" required/><span><</span>
		    </div>
		  </div>
		  <div class="control-group">
		    <label class="control-label" for="inputEmail">"Country"</label>
		    <div class="controls">
		      <input type="text" id="country" placeholder="Country" name="author.Country" required/><span><</span>
		    </div>
		  </div>
		  <div class="control-group">
		    <div class="controls">
		      <button type="submit" class="btn">Sign in</button>    
		    </div>
		  </div>
		</form>
    </div>
    
  </body>
</html>
