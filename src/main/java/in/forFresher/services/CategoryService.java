package in.forFresher.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.forFresher.entity.Category;
import in.forFresher.exception.InvalidDataException;
import in.forFresher.repository.CategoryRepository;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<String> getAllTypes(){
		return categoryRepository.findAll().stream().map(Category::getName).toList();
	}
	
	public Set<Category> getCategories(List<String> categoryNames) throws InvalidDataException {
        Set<Category> categories = new HashSet<>();
        for (String name : categoryNames) {
            Category category = categoryRepository.findByName(name);
            if (category == null) {
            	throw new InvalidDataException(name +" category is not exist in Database");
            }
            categories.add(category);
        }
        return categories;
    } 
	
	public Category getCategory(String categoryName) throws InvalidDataException {
		Category category = categoryRepository.findByName(categoryName);
		if(category == null) {
			throw new InvalidDataException(categoryName + "category not exist in Database");
		}
//			category.setJobs(null);
		return category;
	}
	
}
