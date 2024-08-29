package in.forFresher.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.forFresher.entity.Type;
import in.forFresher.exception.InvalidDataException;
import in.forFresher.repository.TypeRepository;

@Service
public class TypeService {
	@Autowired
	private TypeRepository typeRepository;
	
	public List<String> getAllTypes(){
		return typeRepository.findAll().stream().map(Type::getName).toList();
	}
	
	public Set<Type> getTypes(List<String> typeNames) throws InvalidDataException {
        Set<Type> types = new HashSet<>();
        for (String name : typeNames) {
            Type type = typeRepository.findByName(name);
            if (type == null) {
            	throw new InvalidDataException(name+" type is not exist in Database");
            }
            type.setJobs(null);
            types.add(type);
        }
        return types;
    } 
	
}
