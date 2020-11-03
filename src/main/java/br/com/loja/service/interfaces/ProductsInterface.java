package br.com.loja.service.interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.loja.model.Products;

public interface ProductsInterface {
	
	Page<Products> findByName(String name,Pageable pagination);

	List<Products> findById(Products id);

	void delete(Long id);
	
	Optional<Products> findById(Long id);
	
	Products getOne(Long id);

	Page<Products> findAll(Pageable pagination);
	
	void save(Products products);
	
	

}
