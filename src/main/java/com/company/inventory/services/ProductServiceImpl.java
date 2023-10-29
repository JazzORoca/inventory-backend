package com.company.inventory.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.company.inventory.dao.ICategoryDao;
import com.company.inventory.dao.IProductDao;
import com.company.inventory.model.*;
import com.company.inventory.model.Product;
import com.company.inventory.response.ProductResponseRest;
import com.company.inventory.util.Util;

@Service
public class ProductServiceImpl implements IProductService {
private ICategoryDao categoryDao;
private IProductDao productDao;
	public ProductServiceImpl(ICategoryDao categoryDao,IProductDao productDao){
	this.categoryDao=categoryDao;
	this.productDao=productDao;
}
	@Override
	@Transactional
	public ResponseEntity<ProductResponseRest> save(Product product, Long categoryId) {
		
		ProductResponseRest response=new ProductResponseRest();
		List<Product>list=new ArrayList<>();
		try{
			//search category to set in the product object
			Optional<Category>category=categoryDao.findById(categoryId);
			if(category.isPresent()){
				product.setCategory(category.get());
			}else {
				response.setMetadata("rta nok", "-1", "categoria no encontrada asociada al producto");
			return new ResponseEntity<ProductResponseRest>(response,HttpStatus.NOT_FOUND);
			
			}
			//save the product
			Product productSaved=productDao.save(product);
			if(productSaved!=null) {
				list.add(productSaved);
				response.getProductResponse().setProducts(list);

				response.setMetadata("rta ok", "00", " producto guardado");
			}else {
				response.setMetadata("rta nok", "-1", " producto no guardado");
				return new ResponseEntity<ProductResponseRest>(response,HttpStatus.BAD_REQUEST);
			}
		}catch(Exception e) {
			e.getStackTrace();
			response.setMetadata("rta nok", "-1", "error al guardar producto");
			return new ResponseEntity<ProductResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 return new ResponseEntity<ProductResponseRest>(response,HttpStatus.OK);
	}
	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<ProductResponseRest> searchById(Long id) {
		ProductResponseRest response=new ProductResponseRest();
		List<Product>list=new ArrayList<>();
		try{
			//search product by id
			Optional<Product>product=productDao.findById(id);
			if(product.isPresent()){
				byte [] imagenDescompressed=Util.compressZLib(product.get().getPicture());
			product.get().setPicture(imagenDescompressed);
			list.add(product.get());
			response.getProductResponse().setProducts(list);
			response.setMetadata("rta ok","00","Porducto encontrado");
			}else {
				response.setMetadata("rta nok", "-1", "producto no encontrado");
			return new ResponseEntity<ProductResponseRest>(response,HttpStatus.NOT_FOUND);
			
			}
	
		}catch(Exception e) {
			e.getStackTrace();
			response.setMetadata("rta nok", "-1", "error al guardar producto");
			return new ResponseEntity<ProductResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 return new ResponseEntity<ProductResponseRest>(response,HttpStatus.OK);
	
	}
	@Override
	@Transactional(readOnly=true)
	public ResponseEntity<ProductResponseRest> searchByName(String name) {
		ProductResponseRest response=new ProductResponseRest();
		List<Product>list=new ArrayList<>();
		List<Product>listAux=new ArrayList<>();
		try{
			//search product by name
listAux=productDao.findByNameContainingIgnoreCase(name);
			if(listAux.size()>0){
				listAux.stream().forEach((p)->{
					byte [] imagenDescompressed=Util.compressZLib(p.getPicture());
					p.setPicture(imagenDescompressed);
					list.add(p);
				});
				
			response.getProductResponse().setProducts(list);
			response.setMetadata("rta ok","00","Porductos encontrados");
			}else {
				response.setMetadata("rta nok", "-1", "productos no encontrados");
			return new ResponseEntity<ProductResponseRest>(response,HttpStatus.NOT_FOUND);
			
			}
	
		}catch(Exception e) {
			e.getStackTrace();
			response.setMetadata("rta nok", "-1", "error al buscar producto por nombre");
			return new ResponseEntity<ProductResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 return new ResponseEntity<ProductResponseRest>(response,HttpStatus.OK);
	
	}
	@Override
	@Transactional
	public ResponseEntity<ProductResponseRest> deleteById(Long id) {
		ProductResponseRest response=new ProductResponseRest();
		
		try{
			//delete product  by id
			productDao.deleteById(id);
			response.setMetadata("rta ok","00","Porducto eliminado");
		
	
		}catch(Exception e) {
			e.getStackTrace();
			response.setMetadata("rta nok", "-1", "error al eliminar  producto");
			return new ResponseEntity<ProductResponseRest>(response,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		 return new ResponseEntity<ProductResponseRest>(response,HttpStatus.OK);
	
	}

}
