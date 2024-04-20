package in.forFresher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.forFresher.entity.Jobs;

@Repository
public interface JobsRepository extends JpaRepository<Jobs, Long> {

}
