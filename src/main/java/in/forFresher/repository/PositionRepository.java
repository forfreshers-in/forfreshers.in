package in.forFresher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.forFresher.entity.Position;

@Repository
public interface PositionRepository extends JpaRepository<Position, Long> {
	
	Position findByName(String name);
}
