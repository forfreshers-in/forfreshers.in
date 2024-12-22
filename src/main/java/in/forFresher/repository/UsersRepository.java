package in.forFresher.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.forFresher.entity.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, Long>{

		Users findByUsername(String username);
		Users findByEmail(String email);
		
		Optional<Users> findByResetToken(String resetToken);
}
