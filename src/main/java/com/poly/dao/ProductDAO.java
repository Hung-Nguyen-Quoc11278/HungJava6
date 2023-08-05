package com.poly.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.poly.entity.Product;

public interface ProductDAO extends JpaRepository<Product, Integer> {
	
	Page<Product> findAllByNameLike(String keywords, Pageable pageable);

	@Query("select p from Product p where p.category.id=?1")
	List<Product> findByCategoryId(String cid);
	
	@Query(value = "select top 8 * from Products order by Price ASC;", nativeQuery = true)
	List<Product> findByPrice();
	
	@Query(value = "select top 8 * from Products order by CreateDate Desc;", nativeQuery = true)
	List<Product> findBySell();
	
	@Query(value = "select top 8 * from Products where Available='true';", nativeQuery = true)
	List<Product> findByAVB();
	
	@Query(value = "select top 5 * from Products order by CreateDate Desc;", nativeQuery = true)
	List<Product> findBySellPro();
	
	@Query(value = "select * from Products order by ?1 ASC;", nativeQuery = true)
	Page<Product> findByField(String field , Pageable pageable);
	

}
