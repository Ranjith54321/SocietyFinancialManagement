package com.database;

public class Payment {
	private String D_no;
	private String Fam_head;
	private String Email;
	private String phone;
	private String monthly_charge;
	private String extra_charge;
	private String fine;
	public String getD_no() {
		return D_no;
	}
	public void setD_no(String d_no) {
		D_no = d_no;
	}
	public String getFam_head() {
		return Fam_head;
	}
	public void setFam_head(String fam_head) {
		Fam_head = fam_head;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getMonthly_charge() {
		return monthly_charge;
	}
	public void setMonthly_charge(String monthly_charge) {
		this.monthly_charge = monthly_charge;
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
}
