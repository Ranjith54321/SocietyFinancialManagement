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

public class Database_day1 implements Job{
	private String driver = "com.mysql.jdbc.Driver"; 
	private String url = "jdbc:mysql://localhost:3306/project";
	private String user_name = "root";
	private String pass = "toor"; 
	static Connection con;
	
	public void startconnection() throws ClassNotFoundException, SQLException {
		System.out.println("connected to DB successfully");
				Class.forName(driver);
				con = (Connection) DriverManager.getConnection(url,user_name,pass);
	}
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException{
		try {
			startconnection();
			Statement st2 = (Statement) con.createStatement();
			ResultSet rs = st2.executeQuery("select * from date where day=1");
			rs.next();
			String str="no";
			if((str.equals(rs.getString("state"))) == true) {
				insert();
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
	}
	public void insert() throws SQLException {
		String query = "insert into main values (?,?,?,?,?,?)";
		
		PreparedStatement ps = (PreparedStatement) con.prepareStatement(query);
		Statement st2 = (Statement) con.createStatement();
		ResultSet rs = st2.executeQuery("select * from temp");
		//int count=0;
		while(rs.next()) {
			ps.setString(1, rs.getString(1));
			ps.setString(2,rs.getString(2));
			ps.setString(3, rs.getString(3));
			ps.setString(4,rs.getString(4));
			ps.setString(5, rs.getString(5));
			ps.setString(6,rs.getString(6));
			ps.executeUpdate();
		}
		Statement st3 = (Statement) con.createStatement();
		st3.executeUpdate("truncate table temp");
		Statement st4 = (Statement) con.createStatement();
		st4.executeUpdate("update date set state='yes' where day=1");
	}
}
