package in.forFresher.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;

import in.forFresher.dto.JobDto;

public interface JobsRepositoryCustom {
	
	List<JobDto> findJobs(LocalDateTime initialLoadTime, String location, String company, String type, Pageable pageable);
}
