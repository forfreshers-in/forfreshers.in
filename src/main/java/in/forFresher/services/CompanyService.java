package in.forFresher.services;

import java.util.List;

import org.springframework.stereotype.Service;

import in.forFresher.repository.CompanyRepository;

@Service
public class CompanyService {
	private CompanyRepository companyRepository;
	
	//constructor
	public CompanyService(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}
	
	// all company names as String list
	public List<String> getAllName(){
		return companyRepository.getAllNames();
	}
}
