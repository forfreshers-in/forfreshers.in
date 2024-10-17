package in.forFresher.apiController;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.forFresher.entity.Category;
import in.forFresher.repository.CategoryRepository;
import in.forFresher.services.CategoryService;

@RestController
@RequestMapping("/api")
public class CategoryApiController {
	
	private CategoryService categoryService;
	
	private CategoryRepository categoryRepository;
	
	public CategoryApiController(CategoryService categoryService, CategoryRepository categoryRepository) {
		this.categoryService = categoryService;
		this.categoryRepository = categoryRepository;
	}
	
	/*
	 * Authorize with Admin 
	 */
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(value = "/categories", name = "getAllCategories")
	public ResponseEntity<Object> getAllCategories() {
		try {
			List<String> categories = categoryService.getAllName();
			return ResponseEntity.ok(categories);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
}
