<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Show Post</title>
</head>
<body>

<div>
	<h2>${post.title}</h2>
	<h3>${post.published_at}</h3>
	<p>${post.content}<p>
	
	<form:form action="updatePost" >
		<button type="submit"  name="postId" value="${post.id}">update</button>
	</form:form>

	<form:form action="deletePost" var="post">
		<button type="submit" name="postId" value="${post.id}">delete</button>
	</form:form>
	
	<form:form action="comment" modelAttribute="comment"  var="comment">
	<button type ="submit" name="id" value="${post.id}">comment</button>
	</form:form>
	
	<h2>Comments</h2>
	<c:forEach var="comment" items="${post.comments}">
	<div>
		<h5>${comment.name}</h5>
		<p>${comment.comment}</p>
		<form action="updateComment" modelAttribute="update" var="comment">
		<input type="hidden" name="id" value="${post.id}"/>
		<button type="submit" name="commentId" value="${comment.id}" >Update</button>
		</form>
		<form action="deleteComment" modelAttribute="delete" var="comment">
		<input type="hidden" name="postId" value="${post.id}"/>
		<button type="submit"  name="commentId" value="${comment.id}">Delete</button>
		</form>
	</div>
	</c:forEach>
	

</div>




</body>
</html>