package com.company.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.company.inventory.model.Category;
import com.company.inventory.response.CategoryResponseRest;
import com.company.inventory.services.ICategoryService;

@RestController
//maping general
@RequestMapping("/api/v1")
public class CategoryRestController {
@Autowired
	private ICategoryService service;
	@GetMapping("/categories")
	public ResponseEntity<CategoryResponseRest>searchCategorys(){
		
		ResponseEntity<CategoryResponseRest>response=service.search();
		return response;
		
		
		
	}
	//get categories by id
	@GetMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseRest>searchCategoriesById(@PathVariable Long id){
		ResponseEntity<CategoryResponseRest>response=service.searchById(id);
		return response;
	}
	
	@PostMapping("/categories")
	//request body recuperar desde lo que venga del cliente como objeto  json
	public ResponseEntity<CategoryResponseRest>saveCategoriesById(@RequestBody Category category){
		ResponseEntity<CategoryResponseRest>response=service.save(category);
		return response;
	}
	@PutMapping("/categories/{id}")
	//request body recuperar desde lo que venga del cliente como objeto  json
	public ResponseEntity<CategoryResponseRest>updateCategoriesById(@RequestBody Category category,@PathVariable Long id){
		ResponseEntity<CategoryResponseRest>response=service.update(category, id);
		return response;
	}
	@DeleteMapping("/categories/{id}")
	public ResponseEntity<CategoryResponseRest>deleteById(@PathVariable Long id){
		ResponseEntity<CategoryResponseRest>response=service.delete(id);
		return response;
	}

}
