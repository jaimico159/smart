<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="structure.Vehicle" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Car Position</title>
</head>
<body>
<h1>Add Car Position</h1>
<% List<Vehicle> lista = (List<Vehicle>)request.getAttribute("cars"); %>
<br>
<form method="post" action="/AddVehiclePosition">
<select name="carKey">
<% for(Vehicle car: lista){ %>
  <option value="<%= car.getId() %>"><%= car.getName() %></option>
<% } %>
</select><br>
<label>Speed: </label><br>
<input type="text" name="speed"><br>
<label>Direction: </label><br>
<input type="text" name="direction"><br>
<label>Position: </label><br>
<input type="text" name="position"><br>
<input type="submit" name = "submit" value="ENVIAR">
</form>

</body>
</html>