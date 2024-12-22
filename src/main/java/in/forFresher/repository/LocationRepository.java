package in.forFresher.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.forFresher.entity.Location;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long>{
	
	Location findByCity(String city);
	
	@Query("select l.city from Location l")
	List<String> getAllCities();

}