package com.autoupdate;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class Database_Upadate_Day11 implements Job{
	private String driver = "com.mysql.jdbc.Driver"; 
	private String url = "jdbc:mysql://localhost:3306/project";
	private String user_name = "root";
	private String pass = "toor"; 
	static Connection con;
	
	public void startconnection() throws ClassNotFoundException, SQLException {
		//System.out.println("connected to DB successfully");
				Class.forName(driver);
				con = (Connection) DriverManager.getConnection(url,user_name,pass);
	}
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException{

		try {
			startconnection();
			Statement st2 = (Statement) con.createStatement();
			ResultSet rs = st2.executeQuery("select * from date where day=11");
			rs.next();
			String str="no";
			if((str.equals(rs.getString("state"))) == true) {
				insert();
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void insert() throws SQLException {
		String query = "update temp set fine='100' where D_no= '?'";
		
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
		Statement st2 = (Statement) con.createStatement();
		ResultSet rs = st2.executeQuery("select * from temp where pay_stage='requested' or pay_stage='rejected'");

		while(rs.next()) {
			ps.setString(1, rs.getString(1));
			ps.executeUpdate();
		}
		Statement st3 = (Statement) con.createStatement();
		st3.executeUpdate("update date set state='yes' where day=11");
	}
}
