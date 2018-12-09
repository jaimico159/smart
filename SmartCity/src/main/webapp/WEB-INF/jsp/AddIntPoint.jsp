<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Interest Point</title>
</head>
<body>
<h1>Add Interesting Point</h1>
<br>
<form method="post" action="/addPointOfInterest">
<input type="text" name="name"><br>
<input type="text" name="description"><br>
<input type="text" name="location"><br>
<input type="submit" name = "submit" value="ENVIAR">
</form>

</body>
</html>