package com.company.inventory.response;
import java.util.*;
import com.company.inventory.model.Category;

import lombok.Data;
@Data
public class CategoryResponse {
private List<Category>category;
public List<Category> getCategory() {
	return category;
}
public void setCategory(List<Category> category2) {
	this.category=category2;
	
}


}
