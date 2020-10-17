
# Society-Financial-Management :

	This application Handles two kind of users
	
		1.) Manager
		2.) Appartment Family Member
	


 ## Application Specification :

   ### -> Appartment Manager:
		
		- Can able to login
		- Can able to Manage the Appartment Family :
			
				1.) Add Family 
				2.) Remove Family
				3.) Edit Family Details 
		
		- Can able to set monthly Payment with the help of simple maker

		- Can able to contact rejected pay users

		- Can able to see all the payment reports based on gien date range


   ### -> Family Member :

		- Can able to login after creating an account with the help of Appartment Manager

		- Can See how much Monthly payment he need to pay and he can pay the bill with the help of simple checker 

		- Can able to see the payment history of that user

   ## Automatic functionalities :

		1.) Every month day 6 to day 10 the payment alert mail will send to each user until he/she pay the bill

		2.) After day 10 un-payed user should pay the fine amount of 100-RS this will automatically updated in database

		3.) The payment delayed user deatails also automatically recorded


   ## Technology Stack Used:
   
			     FrameWork -> Spring MVC 
			     Front-End -> JSP,JSTL,HTML,CSS,JavaScript(JQuery,AJAX).
			     Back-End  -> Java (Programming Language)
			     DataBase  -> MySql
			     Automatic-Functionalites -> QuartsScheduler
			     Project-Management-Tool -> Maven


		
## DataBase Schema :

	mysql> use project;
	mysql> show tables;
          +-------------------+
          | Tables_in_project |
          +-------------------+
          | admin             |
          | date              |
          | main              |
          | temp              |
          | user              |
          +-------------------+

	This application has Five tables :

	1.) admin - to handle admin login details
	2.) user  - to handle user data
	3.) temp  - it contains that on-going particular month data 
	4.) main  - it contains whole payment data (after every first day of the month temp data records copy to here and temp gets empty)
	5.) data  - to handle automatic functionalities we need this table


## To make Database-Tables DataBase Comments :

	  1.) create Database :
          
            create database project;
            
          2.) create Tables :

		2.1) create admin table :
		
			create table admin(name varchar(),pass varchar(10));

		2.2) create user tabel :

			create table user(D_no varchar(5),Fam_head varchar(20),DOJ varchar(20),noofmem varchar(20),email varchar(30),phone varchar(15),username varchar(20),pass varchar(15));

		2.3) create temp table :

			create table temp(D_no varchar(20),month_charge varchar(20),extra_charge varchar(20),fine varchar(20),pay_stage varchar(20),paid_date varchar(20));

		2.4) create main table :
	
			create table main(D_no varchar(20),month_charge varchar(20),extra_charge varchar(20),fine varchar(20),pay_stage varchar(20),paid_date varchar(20));

		2.5) create date table :

			create table date(day int, state varchar(10))
               
   In proerties file I used to store DataBase deatils


 ## Application Modules:
 
	   -UserLogin 

	   -Adminwork 

	   -userwork

	   -Autoupdate 

	   -database

	   -session


## Auromatic Functionality Libaries :

	- For mail we have JavaMail API in this application you no need to import any jar file,
		because I create this app using Maven so it hande package for us

	- For Automatic Fucntionality Quartz Scheduler is used, here crons as a input argument 
	
[For more about crons](https://www.freeformatter.com/cron-expression-generator-quartz.html)


  #### Note : 
  
   The sender mail shoud be Enable signin from less secure apps in Gmail: by click this [link](https://myaccount.google.com/security)


	
