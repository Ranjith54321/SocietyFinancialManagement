package com.userlogin;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

@Controller
public class UserController {

	
	@Value("${database.driver}")
	private String driver; 
	@Value("${database.url}")
	private String url;
	@Value("${database.user_name}")
	private String user_name;
	@Value("${database.pass}")
	private String pass; 

	@RequestMapping("/checkuser")
	public String checkuser(HttpServletRequest request,Model model) throws ClassNotFoundException, SQLException {
		String name = request.getParameter("user_name");
		String passf = request.getParameter("user_pass");
		
		Class.forName(driver);
		
		//2.) get a connection
		Connection con = (Connection) DriverManager.getConnection(url,user_name,pass);
		Statement st = (Statement) con.createStatement();
		 
		if(name.equals("Admin")) {
			ResultSet rs1 = st.executeQuery("select * from Admin");
			rs1.next();
				if(name.equals(rs1.getString(1)) && passf.equals(rs1.getString(2))) {
					model.addAttribute("name",name);
					HttpSession session = request.getSession();
					session.setAttribute("username", name);
					return "admin";
				}
		}
			
		
		ResultSet rs = st.executeQuery("select * from user");
		
		while(rs.next()) {
			String named = rs.getString(7);
			String passd = rs.getString(8);
			if(named.equals(name) && passd.equals(passf)) {
				model.addAttribute("name",name);
				model.addAttribute("Dno",rs.getString(1));
				HttpSession session = request.getSession();
				session.setAttribute("username", name);
				
				
				return "user";
			}

		}
		
		return "wrong";
	}	
	
}
