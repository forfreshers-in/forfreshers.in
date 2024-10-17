package in.forFresher.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import in.forFresher.entity.Jobs;
import in.forFresher.services.JobsService;

@Controller
@RequestMapping("/jobs")
public class JobsController {
	ModelAndView mv = new ModelAndView();
	
	@Autowired
	JobsService jobsService;
	
	@GetMapping("/{jobTitle}-{jobId}")
	public ModelAndView showJobDetails(@PathVariable String jobTitle, @PathVariable String jobId) {
		Jobs job = null;
		try {
			job = jobsService.getJobByTitleAndId(jobTitle, jobId);
			if(job == null) {
				 mv.setViewName("error/404");
				 return mv;
			}
			else if( job.getPublished() != 1) {
				
			}
		}catch(Exception e) {
			
		}
		mv.addObject("jobDetails", job);
		mv.setViewName("jobs/jobPage");
		mv.addObject("authorName", job.getAuthor().getName());
		
		return mv;
	}
}
