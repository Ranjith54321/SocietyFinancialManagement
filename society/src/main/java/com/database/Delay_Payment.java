package com.database;

public class Delay_Payment {
	private String D_no;
	private String month_charge;
	private String extra_charge;
	private String fine;
	//private String pay_stage;
	private String paid_date;
	private String mail;
	private String phone;
	public String getD_no() {
		return D_no;
	}
	public void setD_no(String d_no) {
		D_no = d_no;
	}
	public String getMonth_charge() {
		return month_charge;
	}
	public void setMonth_charge(String month_charge) {
		this.month_charge = month_charge;
	}
	public String getExtra_charge() {
		return extra_charge;
	}
	public void setExtra_charge(String extra_charge) {
		this.extra_charge = extra_charge;
	}
	public String getFine() {
		return fine;
	}
	public void setFine(String fine) {
		this.fine = fine;
	}
	/*public String getPay_stage() {
		return pay_stage;
	}
	public void setPay_stage(String pay_stage) {
		this.pay_stage = pay_stage;
	}*/
	public String getPaid_date() {
		return paid_date;
	}
	public void setPaid_date(String paid_date) {
		this.paid_date = paid_date;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
}
