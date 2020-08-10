<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
.holder {
  width: 400px;
  background: #efefef;
  padding: 30px 10px;
  box-sizing: border-box;
  margin: 0 auto;
  margin-top: 20px; 
  text-align: center; 

}

body {
 background-image: url("https://images.pexels.com/photos/518245/pexels-photo-518245.jpeg?cs=srgb&dl=pexels-photomix-company-518245.jpg&fm=jpg");
 background-size: cover;
 background-attachment: fixed;
}
</style>
</head>
<body>
	<div class="holder">
		<table>
	<tr><td>Door Number : </td>
	<td>${D_no}</td></tr>
	<tr><td>Family Head : </td>
	<td>${Fam_head}</td></tr>
	<tr><td>Date Of Join : </td>
	<td>${DOJ}</td></tr>
	<tr><td>Number Of Members  : </td>
	<td>${noofmem}</td></tr>
	<tr><td>Email-Id : </td>
	<td>${email}</td></tr>
	<tr><td>Mobile Number : </td>
	<td>${phone}</td></tr>
	<tr><td>User Name : </td>
	<td>${username}</td></tr>
	<!-- <tr><td>Enter the Password : </td>
	<td></td></tr> -->
	</table>
	</div>
</body>
</html>