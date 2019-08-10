package vip.zicp.mitumao.controller.menu;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("menu/menuHandle.do")
public class MenuController {
	@RequestMapping(params = {"action=toList"})
	public String showMenu() {
		
		return "";
	}
}
