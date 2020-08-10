package com.database;

public class Payment_Range {
	private String D_no;
	private String month_charge;
	private String extra_charge;
	private String fine;
	private String paid_date;
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
	public String getPaid_date() {
		return paid_date;
	}
	public void setPaid_date(String paid_date) {
		this.paid_date = paid_date;
	}
	
}
