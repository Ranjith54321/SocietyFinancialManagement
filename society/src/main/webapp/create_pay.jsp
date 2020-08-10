<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create pay</title>
<script src="https://code.jquery.com/jquery-1.12.3.min.js"></script>
<script type="text/javascript">

	function onclickEvent(){
		var monthly = document.getElementById("month").value;
		var extra = document.getElementById("extra").value;
		$.ajax({
	        type : 'POST',
	        data: { monthly : monthly,
		        extra : extra},
	        url : 'insert_monthly_pay',
	        success : function(result) {
	            var str = "success";
	            if(str.localeCompare(result)==0){
	            	alert("successfully updated");
	                }
	            else{
	                alert("fail to update");
	            }
	        }
	    });
		}

</script>
<style type="text/css">
@import url(https://fonts.googleapis.com/css?family=Open+Sans:400,600,700);

/* General Buttons */
button {
  width: 130px;
  height: 40px;
  background: linear-gradient(to bottom, #4eb5e5 0%,#389ed5 100%); /* W3C */
  border: none;
  border-radius: 5px;
  position: relative;
  border-bottom: 4px solid #2b8bc6;
  color: #fbfbfb;
  font-weight: 600;
  font-family: 'Open Sans', sans-serif;
  text-shadow: 1px 1px 1px rgba(0,0,0,.4);
  font-size: 15px;
  text-align: left;
  text-indent: 5px;
  box-shadow: 0px 3px 0px 0px rgba(0,0,0,.2);
  cursor: pointer;

/* Just for presentation */  
  display: block;
  margin: 0 auto;
  margin-bottom: 20px;
}
button:active {
  box-shadow: 0px 2px 0px 0px rgba(0,0,0,.2);
  top: 1px;
}

button:after {
  content: "";
  width: 0;
  height: 0;
  display: block;
  border-top: 20px solid #187dbc;
  border-bottom: 20px solid #187dbc;
  border-left: 16px solid transparent;
  border-right: 20px solid #187dbc;
  position: absolute;
  opacity: 0.6; 
  right: 0;
  top: 0;
  border-radius: 0 5px 5px 0;  
}


/* Presentation stuff */
.holder {
  width: 400px;
  background: #efefef;
  padding: 30px 10px;
  box-sizing: border-box;
  margin: 0 auto;
  margin-top: 20px; 
  text-align: center; 
}

h1 {
  font: 400 16px 'Open Sans';
  text-transform: uppercase;
  color: #999;
  text-shadow: 1px 1px 1px #fff;
  margin-bottom: 30px;
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
	<!-- <form action="insert_pay_dao" method="post"> -->
	<label>Enter the Monthly Charge : </label>
	<input type="text" name="mon_charge" id="month" required="required"><br> 
	<label>Enter the Extra Charge : </label>
	<input type="text" name="extra" id="extra" ><br>
	<!-- <label>Enter the Fine Amount : </label>
	<input type="text" name="fine" id="fine" ><br>
	 <label>Enter the Payment Stage : </label>
	<input type="text" name="check" id="check" ><br> -->
	<button type="submit" onclick="onclickEvent();" class="button">Enter</button>
	<!-- </form> -->
	</div>
</body>
</html>