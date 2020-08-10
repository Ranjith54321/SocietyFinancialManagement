<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-1.12.3.min.js"></script>
<script type="text/javascript">

	function pay_fun(D_no){
		$.ajax({
	        type : 'POST',
	        data: { D_no : D_no},
	        url : 'accept_pay',
	        success : function(result) {
	            var str = "success";
	            if(str.localeCompare(result)==0){
	            	alert("payed success");
	            	location.reload();
	                }
	            else{
	                alert("fail to pay");
	                location.reload();
	            }
	        }
	    });
		}

	function reject_fun(D_no){
		$.ajax({
	        type : 'POST',
	        data: { D_no : D_no},
	        url : 'reject_pay',
	        success : function(result) {
	            var str = "success";
	            if(str.localeCompare(result)==0){
	            	alert("Rejected success");
	            	location.reload();
	                }
	            else{
	                alert("fail to reject try  again");
	                location.reload();
	            }
	        }
	    });
		}
</script>
<style type="text/css">



@import url(https://fonts.googleapis.com/css?family=Open+Sans:400,600,700);

/* General Buttons */
button {
  width: 150px;
  height: 50px;
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
	 	Door Number : ${D_no} <br>
	 	Monthly Charge : ${mon_charge} <br>
	 	Extra Charge : ${extra_charge} <br>
	 	Fine Amount : ${fine} <br>
	 	
	<button type="submit" onClick="pay_fun('${D_no}');">Accept the PayMent</button>

	<button type="submit" onClick="reject_fun('${D_no}');">Decline the PayMent</button>
	</div>
</body>
</html>