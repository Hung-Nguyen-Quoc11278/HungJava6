package com.poly.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.poly.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Integer> {
	
	Page<Product> findAllByNameLike(String keywords, Pageable pageable);

}
