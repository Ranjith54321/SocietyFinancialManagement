package com.user.process;

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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

/*@Controller
public class AcceptMail{

	
	@Value("${database.driver}")
	private String driver; 
	@Value("${database.url}")
	private String url;
	@Value("${database.user_name}")
	private String user_name;
	@Value("${database.pass}")
	private String pass; 
	static Connection con;
	
	 //D_no | month_charge | extra_charge | fine | pay_stage | paid_date
	static int monthly_charge;
	static int extra_charge;
	static int fine;
	
	  @Value("${mail.from}")
	  private String from; 
	  @Value("${mail.pass}")
	  private String password;
	
	public void startconnection() throws ClassNotFoundException, SQLException {
		//System.out.println("connected to DB successfully");
				Class.forName(driver);
				con = (Connection) DriverManager.getConnection(url,user_name,pass);
	}
	

	/*public void prepare_mail(String D_no) throws SQLException, ClassNotFoundException {
		  startconnection();
		  
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
		   
			  System.out.println("Driver "+driver);
			  System.out.println("url "+url);
			  System.out.println("user_name "+user_name);
			  System.out.println("pass "+pass);
			  System.out.println("D_no"+D_no);
			  
			  
			// get mail from user
			    Statement st2 = (Statement) con.createStatement();
				ResultSet rs = st2.executeQuery("select * from user where D_no='"+D_no+"'");
				rs.next();
				String to = rs.getString(5);
				
				// get total amount from temp 
				Statement st3 = (Statement) con.createStatement();
				ResultSet rs2 = st3.executeQuery("select * from temp where D_no='"+D_no+"'");
				while(rs2.next()) {
				monthly_charge = Integer.parseInt(rs2.getString(2));
				extra_charge = Integer.parseInt(rs2.getString(3));
				fine = Integer.parseInt(rs2.getString(4));}
				
				// get total amount from main
				Statement st4 = (Statement) con.createStatement();
				ResultSet rs3 = st4.executeQuery("select * from main where D_no='"+D_no+"'");
				//rs3.next();
				while(rs3.next()) {
				monthly_charge += Integer.parseInt(rs3.getString(2));
				extra_charge += Integer.parseInt(rs3.getString(3));
				fine += Integer.parseInt(rs3.getString(4));}
				
				
				
				Message message = new MimeMessage(session);
				   message.setFrom(new InternetAddress(from));
				   message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
				   message.setSubject("Monthly Bill");
				   
				   int total = monthly_charge+extra_charge+fine;
				   
				   long millis=System.currentTimeMillis();  
				    java.sql.Date date=new java.sql.Date(millis);
				    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				    
				    String curr_date = df.format(date);
				   
				//message.setText("Mail From Java Code Using Gmail...");
				   String htmlcode = "<h1> Bill Detail's</h1><table>\r\n" + 
				   		"	<tr><td>Door Number : </td>\r\n" + 
				   		"	<td>"+ D_no +"</td></tr>\r\n" + 
				   		"	<tr><td>Monthly Charge : </td>\r\n" + 
				   		"	<td>"+ monthly_charge +"</td></tr>\r\n" +  
				   		"	<tr><td>Extra Charge : </td>\r\n" + 
				   		"	<td>"+ extra_charge +"</td></tr>\r\n" +
				   		"	<tr><td>Fine : </td>\r\n" + 
				   		"	<td>"+ fine +"</td></tr>\r\n" +
				   		"	<tr><td>Total : </td>\r\n" + 
				   	 	"	<td>"+ total +"</td></tr>\r\n" +
				    	"	<tr><td>Paid Date : </td>\r\n" + 
				   		"	<td>"+ curr_date +"</td></tr>\r\n" +
				   		"	</table>";
				   message.setContent(htmlcode,"text/html");

				   Transport.send(message);
				
			

		  } catch (MessagingException e) {
		   e.printStackTrace();
		  }
	}
}*/