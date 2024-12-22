package in.forFresher.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;

import in.forFresher.dto.JobDto;
import in.forFresher.entity.Company;
import in.forFresher.entity.Jobs;
import in.forFresher.entity.Location;
import in.forFresher.entity.Type;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;

public class JobsRepositoryCustomImpl implements JobsRepositoryCustom{

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<JobDto> findJobs(LocalDateTime initialLoadTime, String location, String company, String type,
			Pageable pageable) {
		// Initialize CriteriaBuilder and CriteriaQuery
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<JobDto> query = cb.createQuery(JobDto.class);
		Root<Jobs> job = query.from(Jobs.class);

		// Select fields for JobDto (you can select specific fields to map to JobDto)
		query.select(cb.construct(JobDto.class, 
			    job.get("id"), 
			    job.get("title"), 
			    job.get("company").get("name"),
			    job.get("category").get("name"), 
			    job.get("minSalary"), 
			    job.get("maxSalary"), 
			    job.get("createdAt"),
			    
//			    // Handling locations - assuming job.get("locations") is a collection
//			    cb.function("group_concat", String.class, job.join("locations").get("city")),
//			    
//			    // Handling types - assuming job.get("types") is a collection
//			    cb.function("group_concat", String.class, job.join("types").get("name")),
			    
			    job.get("expiredAt"), 
			    job.get("author").get("name"), 
			    job.get("published"))
			);

		// List of conditions
		List<Predicate> predicates = new ArrayList<>();

		// Filter by created time before initialLoadTime
		if (initialLoadTime != null) {
			predicates.add(cb.lessThanOrEqualTo(job.get("createdAt"), initialLoadTime));
		}
		
		// Optional filters
		if (location != null && !location.isEmpty()) {
			Join<Jobs, Location> locationJoin = job.join("locations", JoinType.LEFT);
			predicates.add(cb.like(locationJoin.get("city"), wrapToLike(location)));
		}

		if (company != null && !company.isEmpty()) {
			Join<Jobs, Company> companyJoin = job.join("company", JoinType.LEFT);
		    predicates.add(cb.like(companyJoin.get("name"),wrapToLike( company)));
		}

		if (type != null && !type.isEmpty()) {
			Join<Jobs, Type> typeJoin = job.join("types", JoinType.LEFT);
			predicates.add(cb.like(typeJoin.get("name"),wrapToLike( type)));
		}

		// Apply the conditions
		query.where(cb.and(predicates.toArray(new Predicate[0])));
		
		// order by created at sort by descenting
		query.orderBy(cb.desc(job.get("createdAt")));

		// Apply pagination
		TypedQuery<JobDto> typedQuery = entityManager.createQuery(query);
		typedQuery.setFirstResult((int) pageable.getOffset());
		typedQuery.setMaxResults(pageable.getPageSize());
		 // Fetch results
	    List<JobDto> results = typedQuery.getResultList();

	    // Now process the locations and types for each JobDto
	    for (JobDto jobDto : results) {
	        // Fetch the Job entity to access its locations and types
	        Jobs jobEntity = entityManager.find(Jobs.class, jobDto.getId());

	        // Concatenate locations
	        String locations = jobEntity.getLocations().stream()
	                .map(Location::getCity)  // Assuming 'city' is a field in Location entity
	                .collect(Collectors.joining(","));
	        jobDto.setLocations(locations);

	        // Concatenate types
	        String types = jobEntity.getTypes().stream()
	                .map(Type::getName)  // Assuming 'name' is a field in Type entity
	                .collect(Collectors.joining(","));
	        jobDto.setTypes(types);
	    }	

		
		
		// Return results
		return results;
	}
	
	//helper function
	public static String wrapToLike(String value) {

        return value == null ? "%" : "%"+value+"%";
    }
}
