package in.forFresher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;  

import in.forFresher.repository.CategoryRepository;
import in.forFresher.repository.CompanyRepository;
import in.forFresher.repository.JobsRepository;
import jakarta.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private JobsRepository jobsRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private CompanyRepository companyRepository;
	
	private ModelAndView mv;

	@GetMapping("/dashboard")
	public String showDashboard() {
//		mv = new ModelAndView();
//		mv.addObject("active_nav", "dashboard");
//		mv.setViewName("admin/index")
		return "admin/dashboard";
	}
	
	@GetMapping("/addjob")
	public ModelAndView showJobAddForm() {
		mv = new ModelAndView();
		mv.addObject("active_nav", "addjob");
		
		// types, qualifications, locations, positions, company
		mv.setViewName("admin/newJobForm");
		return mv;
	} 
	
	@GetMapping("/myjobs")
	public ModelAndView showMyJobs() {
		mv = new ModelAndView();
		mv.addObject("active_nav", "myjobs");
		mv.setViewName("admin/myJobs");
		return mv;
	}

	@GetMapping("/editJob/{job_id}")
	public ModelAndView editJob() {
		mv = new ModelAndView();
		mv.addObject("active_nav", "myjobs");
		mv.setViewName("admin/myJobs");
		return mv;
	}
	
	@GetMapping("/settings")
	public ModelAndView showAdminSetting() {
		mv = new ModelAndView();
		mv.addObject("active_nav", "setting");
		mv.setViewName("admin/settings");
		return mv;
	}
}
