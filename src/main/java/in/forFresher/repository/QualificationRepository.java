package in.forFresher.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.forFresher.entity.Qualification;

@Repository
public interface QualificationRepository extends JpaRepository<Qualification, Long> {
    Qualification findByName(String name);
}
