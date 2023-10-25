package com.company.inventory.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;

import com.company.inventory.dao.ICategoryDao;
import com.company.inventory.model.Category;
import com.company.inventory.response.CategoryResponseRest;
@Service
public class CategoryServicesImpl implements ICategoryService{
//inyectar dependencia
	@Autowired
	private ICategoryDao categoryDao;
	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<CategoryResponseRest > search() {
		
		CategoryResponseRest response=new CategoryResponseRest();
		try{
			List<Category>category=(List<Category>) categoryDao.findAll();
			response.getCategoryResponse().setCategory(category);
			response.setMetadata("Respuesta ok", "00", "rta exitosa");
		}catch(Exception e) {

			response.setMetadata("Respuesta nok", "-1", "Error al consultar");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.OK);
		
	}
	@Transactional(readOnly=true)
	public ResponseEntity<CategoryResponseRest> searchById(Long id) {
		CategoryResponseRest response=new CategoryResponseRest();
		List<Category>list=new ArrayList<>();
		try{
			//devuelve un objeto optional
			Optional<Category>category=categoryDao.findById(id);
			if(category.isPresent()) {
				list.add(category.get());
				response.getCategoryResponse().setCategory(list);

				response.setMetadata("Respuesta ok", "00", "categoria encontrada");
			}else {
				response.setMetadata("Respuesta nok", "-1", "Error categoria no encotrada");
				
				return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.NOT_FOUND);
			}
		}catch(Exception e) {

			response.setMetadata("Respuesta nok", "-1", "Error al consultar");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.OK);
	}
	@Override
	@Transactional
	public ResponseEntity<CategoryResponseRest> save(Category category) {
		CategoryResponseRest response=new CategoryResponseRest();
		List<Category>list=new ArrayList<>();
		try{
			Category categorySaved=categoryDao.save(category);
			if(categorySaved!=null) {
				list.add(categorySaved);
				response.getCategoryResponse().setCategory(list);
				response.setMetadata("Respuesta ok", "00", "Categoria guardada");
			}else {
response.setMetadata("Respuesta nok", "-1", "Error categoria no guardada");
				
				return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {

			response.setMetadata("Respuesta nok", "-1", "Error al grabar categoria");
			e.getStackTrace();
			return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<CategoryResponseRest>(response,HttpStatus.OK);
	
	}

}
