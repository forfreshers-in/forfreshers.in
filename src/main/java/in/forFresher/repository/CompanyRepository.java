package in.forFresher.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.forFresher.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
	
	Company findByName(String name);
	
	@Query("select c.name from Company c")
	List<String> getAllNames();
	
}
