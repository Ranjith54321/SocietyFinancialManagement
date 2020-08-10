<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<script src="https://code.jquery.com/jquery-1.12.3.min.js"></script>
<script>
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
                url : 'add_user_war',
                success : function(result) {
                    var str = "success";
                    if(str.localeCompare(result)==0){
                    	alert("success");
                    	//$('#form').children('input').val('');
                    	document.getElementById("form").reset();
                        }
                    else
                        alert("fail");
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
 	 <form id="form"> <!-- action="adduser_meth" method="post"-->
 	<table>
	<tr><td>Enter the Door Number : </td>
	<td><input type="text" name="door_num" id="D_no"></td></tr>
	<tr><td>Enter the Family Head Name : </td>
	<td><input type="text" name="fam_head" id="fam_head"></td></tr>
	<tr><td>Enter Date Of Joining : </td>
	<td><input type="text" name="DOJ" placeholder="yyyy-mm-dd" id="DOJ"></td></tr>
	<tr><td>Enter Number Of Members  : </td>
	<td><input type="text" name="fam_num" id="fam_num"></td></tr>
	<tr><td>Enter the Email-Id : </td>
	<td><input type="email" name="email" id="email"></td></tr>
	<tr><td>Enter the Mobile Number : </td>
	<td><input type="text" name="phone" id="phone"></td></tr>
	<tr><td>Enter the User Name : </td>
	<td><input type="text" name="user_name" placeholder="start with Door no" id="user_name"></td></tr>
	<tr><td>Enter the Password : </td>
	<td><input type="text" name="pass" id="pass"></td></tr>
	<tr><td colspan="2"><input type="button" id="submit" value="Enter"> </td></tr>
	</table>
	</form>  
	
	
</body>
</html>