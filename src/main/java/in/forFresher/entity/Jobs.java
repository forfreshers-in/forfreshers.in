package in.forFresher.entity;

import org.hibernate.annotations.IdGeneratorType;
import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;

@Entity
public class Jobs {
	
	@Id
	int id;
	
	
}
