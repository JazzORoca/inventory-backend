package com.company.inventory.model;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Data//coloca los metodos getter,setter,toString,equals,hashCode automaticamente
@Entity
@Table(name="category")
public class Category implements Serializable {

	
	private static final long serialVersionUID = -4310027227752446841L;
//campos
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String description;
	
	public Long getId() {
		return this.id;
	}
	
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public String getNombre() {
		return this.nombre;
	}
	public void  SetNombre(String nombre) {
		this.nombre=nombre;
	}
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description=description;
	}
	
	
	
	
	
	

	
}
