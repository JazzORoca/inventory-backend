package com.company.inventory.response;
import java.util.List;
import com.company.inventory.model.*;

import lombok.Data;

@Data
public class ProductResponse {
List<Product>products;

public List<Product> getProducts() {
	return products;
}

public void setProducts(List<Product> products) {
	this.products = products;
}


}
