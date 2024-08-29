package in.forFresher.repository;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import in.forFresher.entity.Jobs;

@Repository
public interface JobsRepository extends JpaRepository<Jobs, Long> {
	
	boolean existsByTitle(String title);
	
	@Query("SELECT j FROM Jobs j JOIN FETCH j.locations WHERE j.author.id = :authorId")
	List<Jobs> findAllJobsByAuthor(@Param("authorId") Long authorId);
	
    Jobs findByTitleAndId(String jobTitle, Long jobId);
    
    /*
     * using for load more button load more jobs
     */
    @Query("SELECT DISTINCT j FROM Jobs j " )
     Page<Jobs> findJobs(@Param("initialLoadTime") LocalDateTime initialLoadTime,
                        @Param("location") String location,
                        @Param("company") String company,
                        @Param("type") String type,
                        Pageable pageable);


}
