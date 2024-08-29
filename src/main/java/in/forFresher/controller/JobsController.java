package in.forFresher.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import in.forFresher.entity.Jobs;
import in.forFresher.entity.Position;
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
		}catch(Exception e) {
			
		}
		mv.addObject("jobDetails", job);
		mv.setViewName("jobs/jobPage");
		// for meta details
		List<String> positions = new ArrayList<>();
		for (Position position : job.getPositions()) {
			positions.add( position.getName() );
		}
		mv.addObject("positionsInArray", positions.toArray());
		mv.addObject("authorName", job.getAuthor().getName());
		 //debug
		
		
		return mv;
	}
}
