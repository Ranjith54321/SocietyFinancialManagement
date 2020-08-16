<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.12.3.min.js"></script>
<script type="text/javascript">

	function onclickEvent(D_no){
		$.ajax({
	        type : 'POST',
	        data: { D_no : D_no},
	        url : 'make_request',
	        success : function(result) {
	            var str = "success";
	            if(str.localeCompare(result)==0){
	            	alert("request sent successfully");
	            	location.reload();
	                }
	            else{
	                alert("fail to send rquest");
	                location.reload();
	            }
	        }
	    });
		}

</script>
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
		<%@page import="com.database.UserPaymentDao,com.database.Payment,java.util.*"%>  
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		 
		 <h1 align="center">Rejected Users List</h1> 
		 
		 <%  
		List<Payment> list=UserPaymentDao.getAllRecords_rejected();  
		request.setAttribute("list",list);  
		%>  
		
	
		 <table border="1" width="90%" align="center">  
		<tr><th>Door_Num</th><th>Fam_Name</th>  
		<th>Email</th><th>Phone</th><th>Monthly_Pay</th><th>Extra_pay</th><th>Fine</th><th>Request</th></tr>  
		<c:forEach items="${list}" var="p">  
		<tr><td style="text-align:center">${p.getD_no()}</td><td style="text-align:center">${p.getFam_head()}</td>
		<td style="text-align:center">${p.getEmail()}</td><td style="text-align:center">${p.getPhone()}</td> <td style="text-align:center">${p.getMonthly_charge()}</td><td style="text-align:center">${p.getExtra_charge()}</td>  
  		<td style="text-align:center">${p.getFine()}</td>
  		
		<td style="text-align:center">
		<button type="submit" onclick="onclickEvent('${p.getD_no()}');">Request</button></td></tr>  
	
		</c:forEach>  
		</table>  
</body>
</html>