package vip.zicp.mitumao.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("login.do")
public class LoginController {
	@RequestMapping(params= {"action=welcome"})
	public String login() {
		
		return "login/login";
	}
}
