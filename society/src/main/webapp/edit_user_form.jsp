<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit User Form</title>

<script src="https://code.jquery.com/jquery-1.12.3.min.js"></script>
<script type="text/javascript">

jQuery(document).ready(function($) {

	$("#submit").click(function(){
	    var D_no = $("#D_no").val();
		var fam_head = $("#fam_head").val();
		var DOJ = $("#DOJ").val();
		var fam_no = $("#fam_num").val();
		var email = $("#email").val();
		var phone = $("#phone").val();
		var user_name = $("#user_name").val();
		var pass = $("#pass").val();

		$.ajax({
            type : 'POST',
            data: { D_no : D_no,
				fam_head : fam_head,
				DOJ : DOJ,
				fam_no : fam_no,
				email : email,
				phone : phone,
				user_name : user_name,
				pass : pass },
            url : 'edit_user_data',
            success : function(result) {
                var str = "success";
                if(str.localeCompare(result)==0){
                	alert("successfully updated");
                	//$('#form').children('input').val('');
                	//document.getElementById("form").reset();
                    }
                else
                    alert("fail to update");
            }
        });
		
	});

});

</script>
<style type="text/css">
body {
 background-image: url("https://images.pexels.com/photos/518245/pexels-photo-518245.jpeg?cs=srgb&dl=pexels-photomix-company-518245.jpg&fm=jpg");
 background-size: cover;
 background-attachment: fixed;
}
</style>
</head>
<body>
	<%@page import="com.database.UserDatabaseDao,com.database.User"%>
	
	<%
	out.println("user edit form");
	String D_no= (String)request.getAttribute("D_no");  
	out.println("door number : "+D_no);
	//User u=UserDatabaseDao.getRecordById(D_no); 
	User u= (User)request.getAttribute("user_obj");
	%>  
	<h1 align="center">Edit Form</h1>  

	<input type="hidden" name="D_no" value="<%=u.getDoor_num() %>" id="D_no"> 
 	<table>

	
	<tr><td>Enter the Family Head Name : </td>
	<td><input type="text" name="fam_head" value="<%= u.getFam_head()%>" id="fam_head"></td></tr>
	<tr><td>Enter Date Of Joining : </td>
	<td><input type="text" name="DOJ" value="<%= u.getDOJ()%>" id="DOJ"></td></tr>
	<tr><td>Enter Number Of Members  : </td>
	<td><input type="text" name="fam_no" value="<%= u.getFam_num()%>" id="fam_num"></td></tr>
	<tr><td>Enter the Email-Id : </td>
	<td><input type="email" name="email" value="<%= u.getEmail()%>" id="email"></td></tr>
	<tr><td>Enter the Mobile Number : </td>
	<td><input type="text" name="phone" value="<%= u.getPhone()%>" id="phone"></td></tr>
	<tr><td>Enter the User Name : </td>
	<td><input type="text" name="user_name" value="<%= u.getUser_name()%>" id="user_name"></td></tr>
	<tr><td>Enter the Password : </td>
	<td><input type="text" name="pass" value="<%= u.getPass()%>" id="pass"></td></tr>
	<tr><td colspan="2"><input type="button" id="submit" value="Enter"> </td></tr>
	</table>

</body>
</html>