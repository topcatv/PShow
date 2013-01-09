package org.pshow.controller;

import java.util.Map;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.pshow.service.HelloAjaxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
	@Autowired
	HelloAjaxService has;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String fail(@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName, Model model) {
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM, userName);
		return "login";
	}

	@RequestMapping(value = "/jsonTest")
	@ResponseBody
	public Map<String, String> jsonTest() {
		return has.helloAjax();
	}
	
	@RequestMapping(value="/blog/{email}/rec",method=RequestMethod.GET)
	@ResponseBody
	public String restTest(@PathVariable("email") String email) {
		return email;
	}
}
