package in.forFresher.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import in.forFresher.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository< Category, Long > {
	
	Category findByName(String name);
	
	@Query("SELECT c.name FROM Category c")
    List<String> getAllNames();
	
}  