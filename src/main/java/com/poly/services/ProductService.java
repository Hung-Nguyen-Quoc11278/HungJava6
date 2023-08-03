package com.poly.services;

import java.util.List;
import com.poly.entity.Product;

public interface ProductService {
	
	List<Product> findAll();

	Product findById(Integer id);

	List<Product> findByCategoryId(String cid);

}
