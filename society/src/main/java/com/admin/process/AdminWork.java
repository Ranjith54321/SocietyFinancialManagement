package com.admin.process;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminWork {
	@RequestMapping("/manage_user")
	public String manage_user_data() {
		return "manage";
	}
	
	@RequestMapping("/inward_payment")
	public String inward() {
		return "inward";
	}
	
	@RequestMapping("/insert")
	public String inert_user_data(HttpServletRequest request) {
		return "insert"; // call DatabaseDao using httpsession and set the value for that then change the return type to void
	}
	@RequestMapping("/delete")
	public String delete_user_data() {
		return "delete";
	}
}
