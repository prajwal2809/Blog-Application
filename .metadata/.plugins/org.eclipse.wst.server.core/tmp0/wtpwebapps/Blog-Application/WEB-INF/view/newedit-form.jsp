<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
    
 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New/Edit post</title>
</head>
<body>

	<h2>New/Edit post</h2>	
	<form:form action="savePost" modelAttribute="post" method="POST"><br>
		<input type="hidden" name="id" value="${post.id}"/>
		<label>Name</label>
		<form:input path="author"/>
		<form:input path="title"/>	
		<label>Title</label><br>			
		<label>Excerpt</label><br>
		<form:input path="excerpt"/>
		<label>enter the tags</label>
		<input type="text" name="tagNames"><br><br>
		<label>Content</label><br>
		<form:input path="content"/>
		
			
		
		<button type="submit" name="publish" value="Save">Publish</button><br>
		</form>
	</form:form>
</body>
</html> 