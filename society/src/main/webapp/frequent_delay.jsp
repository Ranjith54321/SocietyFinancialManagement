<%@page import="com.database.Delay_Payment"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
    	body  
{  
    margin: 0;  
    padding: 0;  
    background-color:#6abadeba;  
    font-family: 'Arial';  
}
</style>
</head>
<body>
		<%@page import="com.database.UserDatabaseDao,com.database.User,java.util.*"%>  
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		
		<%  
		List<Delay_Payment> list=UserDatabaseDao.get_delayed_pay();  
		request.setAttribute("list",list);  
		%>
		<h1 align="center">Frequent Delay Families</h1>
		
		<table border="1" width="90%" align="center">  
	<tr><th>Door_Num</th><th>Monthly charge</th><th>Extra charge</th><th>Fine</th>  
	<th>Paid Date</th><th>Mail</th><th>Phone</th></tr>  
	<c:forEach items="${list}" var="u">  
	<tr><td style="text-align:center">${u.getD_no()}</td><td style="text-align:center">${u.getMonth_charge()}</td><td style="text-align:center">${u.getExtra_charge()}</td>  
	<td style="text-align:center">${u.getFine()}</td><td style="text-align:center">${u.getPaid_date()}</td><td style="text-align:center">${u.getMail()}</td><td style="text-align:center">${u.getPhone()}</td>  
	</tr>  
	</c:forEach>  
	
	</table>
</body>
</html>