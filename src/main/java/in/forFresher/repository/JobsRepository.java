package in.forFresher.repository;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.forFresher.entity.Jobs;
import in.forFresher.repository.JobsRepositoryCustom;

@Repository
public interface JobsRepository extends JpaRepository<Jobs, Long>, JobsRepositoryCustom  {
	
	Optional<Jobs> findById(Long id);
	
	boolean existsByTitle(String title);
	
	@Query("SELECT j FROM Jobs j JOIN FETCH j.locations WHERE j.author.id = :authorId")
	List<Jobs> findAllJobsByAuthor(@Param("authorId") Long authorId);
	
    Jobs findByTitleAndId(String jobTitle, Long jobId);
    
    /*
     * using for load more button load more jobs
     */


}
