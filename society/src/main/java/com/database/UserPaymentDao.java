package com.database;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

@Controller
public class UserPaymentDao {
	/*private String driver = "com.mysql.jdbc.Driver"; 
	private String url = "jdbc:mysql://localhost:3306/project";
	private String user_name = "root";
	private String pass = "toor"; 
	static Connection con;*/
	
	@Value("${database.driver}")
	private String driver; 
	@Value("${database.url}")
	private String url;
	@Value("${database.user_name}")
	private String user_name;
	@Value("${database.pass}")
	private String pass; 
	static Connection con;
	
	@PostConstruct 
	public void init() throws ClassNotFoundException, SQLException { 
		startconnection();
	}
	public void startconnection() throws ClassNotFoundException, SQLException {
		//System.out.println("connected to DB successfully");
				Class.forName(driver);
				con = (Connection) DriverManager.getConnection(url,user_name,pass);
	}
	
	@RequestMapping("/insert_monthly_pay")
	public @ResponseBody String pay_request(HttpServletRequest request) throws SQLException {
		int status=0;
		
		/*Statement st3 = (Statement) con.createStatement();
		st3.executeQuery("truncate table temp");*/
		
		String query = "insert into temp values (?,?,?,?,?,?)";
		
		PreparedStatement st = (PreparedStatement) con.prepareStatement(query);
		
		Statement st2 = (Statement) con.createStatement();
		ResultSet rs = st2.executeQuery("select * from user");
		while(rs.next()) {
			String d_no = rs.getString(1);
			st.setString(1,d_no);
			st.setString(2,request.getParameter("monthly"));
			st.setString(3,request.getParameter("extra"));
			st.setString(4,"0");
			st.setString(5,"requested");
			st.setString(6,"NOT PAID");
			status+=st.executeUpdate();
		}
		int[] array = new int[]{1,6,7,8,9,10,110};
		for(int i:array) {
		Statement st3 = (Statement) con.createStatement();
		st3.executeUpdate("update date set state='no' where day="+i);
		}
		if(status>0)
			return "success";
		else
			return "fail";
	}
	//*********************
	// this block is from admin inward side
	
	@RequestMapping("/create_payment")
	public String create_payment() {
		return "create_pay";
	}
	
	
	
	public static List<Payment> getAllRecords_rejected() throws SQLException{
		List<Payment> list=new ArrayList<Payment>();
		
			List<Temp> list_temp = new ArrayList<Temp>();
			PreparedStatement ps=(PreparedStatement) con.prepareStatement("select * from temp where pay_stage='rejected'");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Temp t = new Temp();
				t.setD_no(rs.getString(1));
				t.setMonthly_charge(rs.getString(2));
				t.setExtra_charge(rs.getString(3));
				t.setFine(rs.getString(4));
				list_temp.add(t);
			}
			for(Temp tl : list_temp) {
				PreparedStatement st = (PreparedStatement) con.prepareStatement("select * from user where D_no='"+tl.getD_no()+"'");
				ResultSet rs2 = st.executeQuery();
				
				while(rs2.next()){	
					Payment u=new Payment();
					u.setD_no(tl.getD_no());
					u.setFam_head(rs2.getString(2));
					u.setEmail(rs2.getString(5));
					u.setPhone(rs2.getString(6));
					u.setMonthly_charge(tl.getMonthly_charge());
					u.setExtra_charge(tl.getExtra_charge());
					u.setFine(tl.getFine());
					
					list.add(u);
				}
			}
		return list;
	}

	
	@RequestMapping("/rejected_user")
	public String rejected_user() {
		return "rejected_user";
	}
	
	
	@RequestMapping("/make_request")
	public @ResponseBody String make_request(HttpServletRequest request) throws SQLException {
		int status = 0;
		String D_no = request.getParameter("D_no");
		PreparedStatement st = (PreparedStatement) con.prepareStatement("update temp set pay_stage='requested' where D_no='"+D_no+"'");
		status = st.executeUpdate();
		if(status>0)
			return "success";
		else
			return "fail";
	}
	
	
	
	
	

	@PreDestroy
	public void destroy() throws SQLException { 
		//System.out.println("connection successfully closed");
		closeconnection();
	}
	public void closeconnection() throws SQLException {
		//System.out.println("connection successfully closed");
		con.close();
	}
}
