package br.com.brasilprev.loja.repository;

import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.brasilprev.loja.model.Products;

public interface ProductsRepository extends JpaRepository<Products, Long> {

	Page<Products> findByName(String name,Pageable pagination);

	List<Products> findById(Products id);

	
	@Modifying
	@Query("DELETE FROM Products pro WHERE pro.id = :id")
	@Transactional
	void delete(Long id);
}
