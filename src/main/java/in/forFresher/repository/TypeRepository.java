package in.forFresher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.forFresher.entity.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long>{
	
	Type findByName(String name);

}
