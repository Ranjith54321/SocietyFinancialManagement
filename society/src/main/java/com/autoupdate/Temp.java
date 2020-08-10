package com.autoupdate;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Temp implements Job {

	private String driver = "com.mysql.jdbc.Driver"; 
	private String url = "jdbc:mysql://localhost:3306/project";
	private String user_name = "root";
	private String pass = "toor"; 
	static Connection con;
	static String D_no;  //D_no | month_charge | extra_charge | fine | pay_stage | paid_date
	static String month_charge;
	static String extra_charge;
	static String fine;
	
	public void startconnection() throws ClassNotFoundException, SQLException {
		//System.out.println("connected to DB successfully");
				Class.forName(driver);
				con = (Connection) DriverManager.getConnection(url,user_name,pass);
	}
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException{
		//System.out.println("this is my first job using simple trgger"); 
		try {
			startconnection();
			Statement st = (Statement) con.createStatement();
			
			long millis=System.currentTimeMillis();  
		    java.sql.Date date=new java.sql.Date(millis);
		    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		    
		    String curr_date = df.format(date);
		    
		    int day = Integer.parseInt(curr_date.substring(8,10));
		    
		   // System.out.println("today is this day : "+day);
		    
			ResultSet rs = st.executeQuery("select * from date where day="+day);
			rs.next();
			String str="no";
			//System.out.println("status from db "+rs.getString("state")); 
			//System.out.println("str equals:  "+str.equals(rs.getString("state")));
			if((str.equals(rs.getString("state")))) {
				//System.out.println("get in");
				prepare_mail();
				Statement st3 = (Statement) con.createStatement();
				st3.executeUpdate("update date set state='yes' where day="+day);
				return;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public void prepare_mail() throws SQLException {
		  final String from = "17euit125@skcet.ac.in"; 
		  final String password = "8300662861";  

		 // System.out.println("from mail");
		  
		  Properties prop = new Properties();
		  prop.put("mail.smtp.host", "smtp.gmail.com");
		  prop.put("mail.smtp.port", "465");
		  prop.put("mail.smtp.auth", "true");
		  prop.put("mail.smtp.socketFactory.port", "465");
		  prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		  Session session = Session.getInstance(prop, new javax.mail.Authenticator() {
		   protected PasswordAuthentication getPasswordAuthentication() {
		    return new PasswordAuthentication(from, password);
		   }
		  });

		  try {
		   
		   Statement st1 = (Statement) con.createStatement();
		   ResultSet rs2 = st1.executeQuery("select * from temp where pay_stage='requested'");
		   while(rs2.next()) {
			   
				D_no = rs2.getString(1);
				month_charge = rs2.getString(2);
				extra_charge = rs2.getString(3);
				fine = rs2.getString(4);
				
				/*System.out.println("d no get change"+D_no);
				System.out.println("month_charge  "+month_charge);
				System.out.println(" extra_charge "+extra_charge);
				System.out.println("fine  "+fine);*/
				
				Statement st2 = (Statement) con.createStatement();
				ResultSet rs3 = st2.executeQuery("select * from user where D_no='"+rs2.getString(1)+"'");
				
				rs3.next();
				if(rs3==null) {
					continue;
				}
				String to = rs3.getString("email");
				
				//String to = "sranjith54321@gmail.com";
				
				//System.out.println("to  "+to);
				
				Message message = new MimeMessage(session);
				   message.setFrom(new InternetAddress(from));
				   message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
				   message.setSubject("Monthly Bill");
				   
				   int total = Integer.parseInt(month_charge)+Integer.parseInt(extra_charge)+Integer.parseInt(fine);
				   
				//message.setText("Mail From Java Code Using Gmail...");
				   String htmlcode = "<h1> Bill Detail's</h1><table>\r\n" + 
				   		"	<tr><td>Door Number : </td>\r\n" + 
				   		"	<td>"+ D_no +"</td></tr>\r\n" + 
				   		"	<tr><td>Monthly Charge : </td>\r\n" + 
				   		"	<td>"+ month_charge +"</td></tr>\r\n" +  
				   		"	<tr><td>Extra Charge : </td>\r\n" + 
				   		"	<td>"+ extra_charge +"</td></tr>\r\n" +
				   		"	<tr><td>Fine : </td>\r\n" + 
				   		"	<td>"+ fine +"</td></tr>\r\n" +
				   		"	<tr><td>Total : </td>\r\n" + 
				   		"	<td>"+ total +"</td></tr>\r\n" +
				   		"	</table>";
				   message.setContent(htmlcode,"text/html");

				   Transport.send(message);
				
			}
		   
		   

		  // System.out.println("Mail Sent...\n");

		  } catch (MessagingException e) {
		   e.printStackTrace();
		  }
	}
}