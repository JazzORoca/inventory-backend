package com.company.inventory.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name="product")
public class Product implements Serializable {
	 
	private static final long serialVersionUID = -7626560619459238044L;
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
	
private Long id;
private String name;
private int price;
private int account;
@ManyToOne(fetch=FetchType.LAZY)//
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
private Category category;
//propiedad foto
@Lob
@Basic(fetch=FetchType.LAZY)
@Column(name="picture",columnDefinition="longblob")
private byte [] picture;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getPrice() {
	return price;
}
public void setPrice(int price) {
	this.price = price;
}
public int getAccount() {
	return account;
}
public void setAccount(int account) {
	this.account = account;
}
public Category getCategory() {
	return category;
}
public void setCategory(Category category) {
	this.category = category;
}
public byte[] getPicture() {
	return picture;
}
public void setPicture(byte[] picture) {
	this.picture = picture;
}


}
