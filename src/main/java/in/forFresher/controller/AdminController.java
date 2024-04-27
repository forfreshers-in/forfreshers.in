package in.forFresher.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	private ModelAndView mv;
	
	@GetMapping("/dashboard")
	public String showDashboard() {
		mv = new ModelAndView();
		mv.addObject("active_nav", "dashboard");
		mv.setViewName("admin/index");
		return "admin/dashboard";
	}
	
	@GetMapping("/addjob")
	public ModelAndView showJobAddForm() {
		mv = new ModelAndView();
		mv.addObject("active_nav", "addjob");
		mv.setViewName("admin/newJobForm");
		return mv;
	}
}
