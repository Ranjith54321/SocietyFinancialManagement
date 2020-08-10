<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
body {
 background-image: url("https://images.pexels.com/photos/518245/pexels-photo-518245.jpeg?cs=srgb&dl=pexels-photomix-company-518245.jpg&fm=jpg");
 background-size: cover;
 background-attachment: fixed;
}
</style>
</head>
<body>
<form action="show_data_range">
	<table>
	<tr><td>Start : </td>
	<td><input type="text" name="start" placeholder="yyyy-mm-dd" id="start"></td>
		<td>End : </td>
	<td><input type="text" name="end" placeholder="yyyy-mm-dd" id="end"></td>
	<td><button type="submit">Enter</button></td>
	</tr>
	</table>
	</form>
</body>
</html>