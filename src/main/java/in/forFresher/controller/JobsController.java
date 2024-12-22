package in.forFresher.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import in.forFresher.entity.Jobs;
import in.forFresher.entity.Position;
import in.forFresher.entity.Qualification;
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
				 	mv.setViewName("error/error-404");
				 return mv;
			}
			else if( job.getPublished() != 1) {
				 	mv.setViewName("error/jobUnpublished");
				 	return mv;
				 }
		}catch(Exception e) {
			mv.setViewName("error/error-500");
		}
		mv.addObject("jobDetails", job);
		// add Positions
		List<String> positions = new ArrayList<>();
		for (Position position : job.getPositions()) {
			positions.add( position.getName() );
	 		positions.add( position.getName() );
		}
		// add Qualification
		List<String> qualifications = new ArrayList<>();
		for (Qualification qualification : job.getQualification()) {
			qualifications.add( qualification.getName() );
		}
		mv.addObject("positionsInArray", positions.toArray());
		mv.addObject("qualificationsInArray", qualifications.toArray());
		mv.setViewName("jobs/jobPage");
		mv.addObject("authorName", job.getAuthor().getName());
			
		return mv;
	}
}
