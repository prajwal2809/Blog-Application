<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Blog Appication</title>
</head>
<body>

	<h2> My Blog Application</h2>
	<form:form action="search">
    	Search: <input type="text" name="searchName" />       
        <input type="submit" value="Search"  />
    </form:form>
    	<label>sort by</label>
    	<form:form action="sortPost">
    		<select name="sortBy">
			<option value="asc">ASC</option>
			<option value="desc">DESC</option>
			<input type="submit" value="sort"> 
			</select>
		</form:form>
    <br>
	<br>	
	
	<form>
	<label>Filter By</label><br>
	<select name="select" multiple>
		<option>item1</option>
		<option>item2</option>
		<option>item3</option>
		<option>item4</option>
		<option>item5</optiom><br>
		<p><input type="submit" value="submit"></p><br>
	</select>
	</form>
		
	<c:forEach items="${posts}" var="element">
	<div>
		<td>${element.title }</td>
		<td>${element.excerpt }</td>
		<td>${element.created_at}</td>
		
		<form:form action="showPost"  method="GET">
		<button type="submit" name="postId" value="${element.id}">display</button>
		</form:form>
		
	</div>
	</c:forEach>
			<form:form action="createPost">
		<button type="submit">create post</button>	
		</form:form>
</body>
</html>