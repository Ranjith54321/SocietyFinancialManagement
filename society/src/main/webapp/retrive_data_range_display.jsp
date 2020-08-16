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
		<%@page import="com.database.UserDatabaseDao,com.database.Payment_Range,java.util.*"%>  
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		
			 <%  
				 String start = (String)request.getAttribute("start");
				String end = (String)request.getAttribute("end");
			List<Payment_Range> list=UserDatabaseDao.get_payment_range(start,end);  
			request.setAttribute("list",list);  
			%>  
			
			<h1 align="center">Payment History</h1>  
			<table border="1" width="90%" align="center">  
			
			<tr><th>Door No</th><th>Monthly Charge</th><th>Extra Charge</th><th>Fine</th><th>Paid Date</th></tr>  
			<c:forEach items="${list}" var="u">  
			<tr><td style="text-align:center">${u.getD_no()}</td><td style="text-align:center">${u.getMonth_charge()}</td><td style="text-align:center">${u.getExtra_charge()}</td><td style="text-align:center">${u.getFine()}</td>  
			<td style="text-align:center">${u.getPaid_date()}</td>  
			</tr>  
			</c:forEach> 
</body>
</html>