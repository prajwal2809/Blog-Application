<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New/Edit Comment</title>
</head>
<body>
	
	<h2>New/Edit Comment</h2>
	<form:form action="addComment" modelAttribute="comment" >
		<input type="hidden" name="id" value="${param.id}">
		<input type="hidden" name="commentId" value="${comment.id}"/>
		<label>Name</label>
		<form:input path="name"/><br>
		<label>Email</label>
		<form:input path="email"/><br>
		<label>Comment</label>
		<form:textarea path="comment"/><br>
		<button type="submit" name="comment" value="Save">Submit</button>
	</form:form>





</body>
</html>