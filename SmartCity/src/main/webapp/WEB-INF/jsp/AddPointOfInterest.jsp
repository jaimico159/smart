<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Car Position</title>
</head>
<body>
<h1>Add Car Position</h1>
<br>
<form method="post" action="/AddPointOfInterest">
<label>Name: </label><br>
<input type="text" name="name"><br>
<label>Description: </label><br>
<input type="text" name="description"><br>
<label>Position: </label><br>
<input type="text" name="position"><br>
<input type="submit" name = "submit" value="ENVIAR">
</form>
</body>
</html>