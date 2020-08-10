package com.user.process;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.database.Payment_history;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

@Controller
public class UserProcess {
	private String driver = "com.mysql.jdbc.Driver"; 
	private String url = "jdbc:mysql://localhost:3306/project";
	private String user_name = "root";
	private String pass = "toor"; 
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
	
	//@ResponseBody
	@RequestMapping("/pay")
	public String user_pay(HttpServletRequest request,Model model) throws SQLException, ClassNotFoundException {
		int status=0;
		int mon_charge=0;
		int extra_charge=0;
		int fine=0;
		String D_no = request.getParameter("D_no");
		
		Statement st = (Statement) con.createStatement();
		Statement st2 = (Statement) con.createStatement();
		
		ResultSet rs = st.executeQuery("select * from temp where D_no = '"+D_no+"'");
		ResultSet rs2 = st2.executeQuery("select * from main where D_no = '"+D_no+"'");
		
		while(rs.next()) {
			if(rs.getString(5).equals("requested")) {
				mon_charge += Integer.parseInt(rs.getString(2));
				extra_charge += Integer.parseInt(rs.getString(3));
				String f = rs.getString(4);
				if(f!=null)
					fine += Integer.parseInt(f);

				status++;
			}
		}
		while(rs2.next()) {
			if(rs2.getString(5).equals("requested")) {
				mon_charge += Integer.parseInt(rs2.getString(2));
				extra_charge += Integer.parseInt(rs2.getString(3));
				fine += Integer.parseInt(rs2.getString(4));

				status++;
			}
		}
		if(status>0) {
			model.addAttribute("D_no",D_no);
			model.addAttribute("mon_charge",Integer.toString(mon_charge));
			model.addAttribute("extra_charge",Integer.toString(extra_charge));
			model.addAttribute("fine",Integer.toString(fine));
			return "checker";
		}
		else
			return "nodue";
	}
	

	@RequestMapping("/user_info")
	public String user_info(HttpServletRequest request,Model model) throws ClassNotFoundException, SQLException {
		String D_no = request.getParameter("D_no");
		
		Statement st = (Statement) con.createStatement();
		
		ResultSet rs = st.executeQuery("select * from user where D_no='"+D_no+"'");
		while(rs.next()) {
			model.addAttribute("D_no",rs.getString(1));
			model.addAttribute("Fam_head",rs.getString(2));
			model.addAttribute("DOJ",rs.getString(3));
			model.addAttribute("noofmem",rs.getString(4));
			model.addAttribute("email",rs.getString(5));
			model.addAttribute("phone",rs.getString(6));
			model.addAttribute("username",rs.getString(7));
			//model.addAttribute("pass",rs.getString(8));
		}
		return "display_user_info";
	}
	
	
	@RequestMapping("/accept_pay")
	public @ResponseBody String pay_charge(HttpServletRequest request) throws SQLException {
		int status=0;
		String D_no = request.getParameter("D_no");
	    
		long millis=System.currentTimeMillis();  
	    java.sql.Date date=new java.sql.Date(millis);
	    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	    
	    String curr_date = df.format(date);  

	    //System.out.println("curr_date : "+curr_date);
	    
		String query = "update temp set pay_stage='accepted' , paid_date='"+curr_date+"' where D_no='"+D_no+"'"; //accepted
		PreparedStatement st = (PreparedStatement) con.prepareStatement(query);
		status=st.executeUpdate();
		if(status>0)
			return "success";
		else
			return "fail";
	}
	
	
	@RequestMapping("/reject_pay")
	public @ResponseBody String reject_pay(HttpServletRequest request) throws SQLException {
		int status=0;
		String D_no = request.getParameter("D_no");
		String query = "update temp set pay_stage='rejected' where D_no='"+D_no+"'";
		PreparedStatement st = (PreparedStatement) con.prepareStatement(query);
		status = st.executeUpdate();
		if(status>0)
			return "success";
		else
			return "fail";
	}
	
	@RequestMapping("/pay_history")
	public String user_pay_history(HttpServletRequest request,Model model) {
		model.addAttribute("D_no",request.getParameter("D_no"));
		return "user_pay_history";
	}
	
	///---
	public static List<Payment_history> getAllRecords_accepted_byid(String D_no) throws SQLException{
		List<Payment_history> list=new ArrayList<Payment_history>();

			// from temp table											  select * from temp where pay_stage='accepted' and D_no='A001';
			PreparedStatement ps=(PreparedStatement) con.prepareStatement("select * from temp where pay_stage='accepted' and D_no='"+D_no+"'");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				Payment_history ph = new Payment_history();
				ph.setMonth_charge(rs.getString(1));
				ph.setExtra_charge(rs.getString(2));
				ph.setFine(rs.getString(3));
				ph.setPaid_date(rs.getString(4));
				list.add(ph);
			}
			// from main table
			PreparedStatement ps2=(PreparedStatement) con.prepareStatement("select * from main where pay_stage='accepted' and D_no='D_no'");
			ResultSet rs2=ps2.executeQuery();
			while(rs2.next()) {
				Payment_history ph = new Payment_history();
				ph.setMonth_charge(rs2.getString(1));
				ph.setExtra_charge(rs2.getString(2));
				ph.setFine(rs2.getString(3));
				ph.setPaid_date(rs2.getString(4));
				list.add(ph);
			}

		return list;
	}
	
	
	///---
	
	@PreDestroy
	public void destroy() throws SQLException { // this is also for good coding style
		//System.out.println("connection successfully closed");
		closeconnection();
	}
	public void closeconnection() throws SQLException {
		//System.out.println("connection successfully closed");
		con.close();
	}
}
