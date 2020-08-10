<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>user payment history</title>

<style type="text/css">
body {

 background-size: cover;
 background-attachment: fixed;
}
</style>
</head>
<body>
		<%@page import="com.user.process.UserProcess,com.database.Payment_history,java.util.*"%>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
	<%
	out.println("user Payment History");
	String D_no= (String)request.getAttribute("D_no");  
	out.println("door number : "+D_no);
	List<Payment_history> list = UserProcess.getAllRecords_accepted_byid(D_no);
	request.setAttribute("list", list);
	%>  
	<h1 align="center">Payment History</h1>  
	<table border="1" width="90%" align="center">  
	
	<tr><th>Monthly Charge</th><th>Extra Charge</th><th>Fine</th><th>Paid Date</th></tr>  
	<c:forEach items="${list}" var="u">  
	<tr><td style="text-align:center">${u.getMonth_charge()}</td><td style="text-align:center">${u.getExtra_charge()}</td><td style="text-align:center">${u.getFine()}</td>  
	<td style="text-align:center">${u.getPaid_date()}</td>  
	</tr>  
	</c:forEach>  
	
	</table> 
</body>
</html>