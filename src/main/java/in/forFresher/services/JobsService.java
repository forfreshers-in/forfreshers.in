package in.forFresher.services;

import java.sql.Timestamp;
import java.util.Map;

import org.springframework.stereotype.Service;

import in.forFresher.entity.Jobs;

@Service
public class JobsService {
	
	public Jobs saveDraftByMap(Map<String, Object> jobData) {
		return null;
	}
	
	public Jobs saveAndPublish(Map<String, Object> jobData) {
		
		String title = jobData.get("title").toString().trim();
		String company = jobData.get("company").toString().trim();
		String positions[] = (String[]) jobData.get("positions");
		String locations[] = (String[]) jobData.get("positions");
		String types[] = (String[]) jobData.get("types");
		String category[] = (String[]) jobData.get("category");
		String description = jobData.get("description").toString().trim();
		Integer batchFrom = (Integer) jobData.get("batchFrom");
		Integer batchTo = (Integer) jobData.get("batchTo");
		Float salaryFrom = (Float) jobData.get("salaryFrom");
		Float salaryTo = (Float) jobData.get("salaryTo");
		Timestamp dateAndTime = (Timestamp) jobData.get("startDate");
		Timestamp lastDate = (Timestamp) jobData.get("endDate");
		String applyLink = jobData.get("applyLink").toString();
		String hrMail = jobData.get("hrMail").toString();
		Timestamp expiry = (Timestamp)jobData.get("expiry");

		System.out.println(jobData);
		
		return null;
	}

}
