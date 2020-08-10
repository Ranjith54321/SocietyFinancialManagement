package com.database;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;


@Controller
public class UserDatabaseDao { 
	private String driver = "com.mysql.jdbc.Driver"; // after completion of the project change replace this using properties file
	private String url = "jdbc:mysql://localhost:3306/project";
	private String user_name = "root";
	private String pass = "toor"; 
	static Connection con;

	@PostConstruct // it is called by the spring when you create your obj in the bean class first step after creating thr obj
	public void init() throws ClassNotFoundException, SQLException { // this declaration for good coding style then only other developer can under stand
		startconnection();
	}
	public void startconnection() throws ClassNotFoundException, SQLException {
		//System.out.println("connected to DB successfully");
			//1..) load the driver
				Class.forName(driver);
				
				//2.) get a connection
				con = (Connection) DriverManager.getConnection(url,user_name,pass);
	}
	
	@RequestMapping("/insert_user")
	public String got_form() {
		return "insert";
	}
	
	//----

	//@ResponseBody
	@RequestMapping(value="/add_user_war", method = RequestMethod.POST)
	public @ResponseBody String save_info(HttpServletRequest request) throws SQLException{
		int status=0;
		System.out.println("im coming here");
			PreparedStatement st=(PreparedStatement) con.prepareStatement("insert into user values (?,?,?,?,?,?,?,?)");
			st.setString(1,request.getParameter("D_no"));
			st.setString(2,request.getParameter("fam_head"));
			st.setString(3,request.getParameter("DOJ"));
			st.setString(4,request.getParameter("fam_no"));
			st.setString(5,request.getParameter("email"));
			st.setString(6,request.getParameter("phone"));
			st.setString(7,request.getParameter("user_name"));
			st.setString(8,request.getParameter("pass"));
			status=st.executeUpdate();
			if(status>0)
				return "success";
			else
				return "fail";
	}
	
	
	//----
	@RequestMapping("/edit_user_cont") // use the getRecord by id directly**********
	public String edit_user(HttpServletRequest request,Model model) throws IOException, SQLException {
		
		User u = getRecordById(request.getParameter("D_no"));
		/*System.out.println(u.getDoor_num());
		System.out.println(u.getFam_head());
		System.out.println(u.getDOJ());
		System.out.println(u.getFam_num());
		System.out.println(u.getEmail());
		System.out.println(u.getPhone());
		System.out.println(u.getUser_name());
		System.out.println(u.getPass());*/
		model.addAttribute("D_no",request.getParameter("D_no"));
		model.addAttribute("user_obj",u);
		return "edit_user_form";
	}
	
	@RequestMapping("/edit_user_data")
	public @ResponseBody String update(HttpServletRequest request) throws SQLException{
			int status=0;
			//System.out.println("from edit user controller");
			PreparedStatement st=(PreparedStatement) con.prepareStatement("update user set Fam_head=?,DOJ=?,noofmem=?,email=?,phone=?,username=?,pass=? where D_no=?");
			st.setString(1,request.getParameter("fam_head"));
			st.setString(2,request.getParameter("DOJ"));
			st.setString(3,request.getParameter("fam_no"));
			st.setString(4,request.getParameter("email"));
			st.setString(5,request.getParameter("phone"));
			st.setString(6,request.getParameter("user_name"));
			st.setString(7,request.getParameter("pass"));
			st.setString(8,request.getParameter("D_no"));
			status = st.executeUpdate();
			
				
			if(status>0)
				return "success";
			else
				return "fail";
	}
	@RequestMapping("/delete_user_meth")
	public @ResponseBody String  delete(HttpServletRequest request) throws SQLException{
			int status=0;
			String D_no = request.getParameter("D_no");
			String query = "delete from user where D_no='"+D_no+"'";
			PreparedStatement ps=(PreparedStatement) con.prepareStatement(query);
			status = ps.executeUpdate();
			//System.out.println("from delete meth : "+status);
			if(status>0)
				return "success";
			else
				return "fail";
	}

	public static List<User> getAllRecords() throws SQLException{
		List<User> list=new ArrayList<User>();
		
			PreparedStatement ps=(PreparedStatement) con.prepareStatement("select * from user");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				User u=new User();
				u.setDoor_num(rs.getString(1));
				u.setFam_head(rs.getString(2));
				u.setDOJ(rs.getString(3));
				u.setFam_num(rs.getString(4));
				u.setEmail(rs.getString(5));
				u.setPhone(rs.getString(6));
				u.setUser_name(rs.getString(7));
				u.setPass(rs.getString(8));
				list.add(u);
			}
		return list;
	}
	public static User getRecordById(String D_no) throws SQLException{
		User u=null;
			PreparedStatement ps=(PreparedStatement) con.prepareStatement("select * from user where D_no='"+D_no+"'");
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				u=new User();
				u.setDoor_num(rs.getString(1));
				u.setFam_head(rs.getString(2));
				u.setDOJ(rs.getString(3));
				u.setFam_num(rs.getString(4));
				u.setEmail(rs.getString(5));
				u.setPhone(rs.getString(6));
				u.setUser_name(rs.getString(7));
				u.setPass(rs.getString(8));
			}
		return u;
	}
	
	@RequestMapping("/report_contr")
	public String report() {
		return "report";
	}
	
	@RequestMapping("/retive_date_range_cont")
	public String retive_given_range() {
		return "retrive_data_range";
	}
	
	
	@RequestMapping("/show_data_range")
	public String show_data_range(HttpServletRequest request,Model model){
		model.addAttribute("start",request.getParameter("start"));
		model.addAttribute("end",request.getParameter("end"));
		return "retrive_data_range_display";
	}
	
	public static List<Payment_Range> get_payment_range(String start,String end) throws SQLException{
		List<Payment_Range> list = new ArrayList<Payment_Range>();
		PreparedStatement st=(PreparedStatement) con.prepareStatement("select * from temp where paid_date between '"+start+"' and '"+end+"' and pay_stage='accepted' order by paid_date");
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			Payment_Range pr = new Payment_Range();
			pr.setD_no(rs.getString(1));
			pr.setMonth_charge(rs.getString(2));
			pr.setExtra_charge(rs.getString(3));
			pr.setFine(rs.getString(4));
			pr.setPaid_date(rs.getString(6));
			
			list.add(pr);
		}
		return list;
	}
	
	public static List<Delay_Payment> get_delayed_pay() throws SQLException{
		List<Delay_Payment> list = new ArrayList<Delay_Payment>();
		PreparedStatement st=(PreparedStatement) con.prepareStatement("select * from temp where not fine='0'");
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			Delay_Payment dp = new Delay_Payment();
			dp.setD_no(rs.getString(1));
			dp.setMonth_charge(rs.getString(2));
			dp.setExtra_charge(rs.getString(3));
			dp.setFine(rs.getString(4));
			//dp.setPay_stage(rs.getString(5));
			dp.setPaid_date(rs.getString(6));
			PreparedStatement st2=(PreparedStatement) con.prepareStatement("select * from temp where D_no='"+rs.getString(1)+"'");
			ResultSet rs2 = st2.executeQuery();
			rs2.next();
			dp.setMail(rs2.getString(5));
			dp.setPhone(rs2.getString(6));
			
			list.add(dp);
		}
		
		return list;
	}
	
	@RequestMapping("/delay_family_cont")
	public String frequent_delay() {
		return "frequent_delay";
	}
	//***********************
	



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

