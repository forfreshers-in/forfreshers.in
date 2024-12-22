package in.forFresher.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.forFresher.entity.Location;
import in.forFresher.entity.Qualification;
import in.forFresher.repository.QualificationRepository;

@Service
public class QualificationService {
	
	private final QualificationRepository qualificationRepo;

	 @Autowired
	public QualificationService(QualificationRepository qualificationRepo) {
		this.qualificationRepo = qualificationRepo;
	}
	
	public List<String> getAllQualifications(){
		return qualificationRepo.findAll().stream().map(Qualification::getName).toList();
	}

	public Set<Qualification> getOrCreateQualification(List<String> name) {
        Set<Qualification> qualifications= new HashSet<>();
        for (String QName : name) {
            Qualification qualification = qualificationRepo.findByName(QName);
            if (qualification == null) {
            	qualification = new Qualification();
            	qualification.setName(QName);
            	qualification = qualificationRepo.save(qualification);
            }
//            location.setJobs(null);
            qualifications.add(qualification);
        }
        return qualifications;
	}
}
