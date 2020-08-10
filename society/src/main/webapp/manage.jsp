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
	        url : 'delete_user_meth',
	        success : function(result) {
	            var str = "success";
	            if(str.localeCompare(result)==0){
	            	alert("success");
	            	location.reload();
	                }
	            else{
	                alert("fail");
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
body {
background-color:#6abadeba;

}
</style>
</head>
<body>
<body>
	<%@page import="com.database.UserDatabaseDao,com.database.User,java.util.*,com.autoupdate.*"%>  
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	 
	 <h1 align="center">Users List</h1> 
	 
	 <%  
	 /*try{
		 Database_Upadate.start_prg();
	 }
	 catch(Exception e){
		 
	 }*/
	 
	List<User> list=UserDatabaseDao.getAllRecords();  
	request.setAttribute("list",list);  
	%>  
	
	<div class="holder" align="center">
	
	<table border="1" width="90%">  
	
	<tr><th>Door_Num</th><th>Fam_Name</th><th>DOJ</th><th>Members</th>  
	<th>Email</th><th>Phone</th><th>User_name</th><th>Pass</th><th>Edit</th><th>Delete</th></tr>  
	<c:forEach items="${list}" var="u">  
	<tr><td style="text-align:center">${u.getDoor_num()}</td><td style="text-align:center">${u.getFam_head()}</td><td style="text-align:center">${u.getDOJ()}</td>  
	<td style="text-align:center">${u.getFam_num()}</td><td style="text-align:center">${u.getEmail()}</td><td style="text-align:center">${u.getPhone()}</td><td style="text-align:center">${u.getUser_name()}</td><td style="text-align:center">${u.getPass()}</td>  
	<td style="text-align:center"><form action="edit_user_cont">
	<input type="hidden" name="D_no" value="${u.getDoor_num()}">
	<button type="submit">Edit</button>
	</form></td>  
	<td style="text-align:center">
	<button type="submit" id="delete" onclick="onclickEvent('${u.getDoor_num()}');">Delete</button>
	</td></tr>  
	</c:forEach>  
	
	</table>  

	<form action="insert_user"> 
	<input type="submit" value="Add New User">
	</form>
	
	</div>
	 
</body>
</html>