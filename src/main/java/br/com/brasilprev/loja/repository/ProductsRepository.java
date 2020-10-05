package br.com.brasilprev.loja.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brasilprev.loja.model.Products;

public interface ProductsRepository extends JpaRepository<Products, Long> {

	Page<Products> findByName(String name,Pageable pagination);

	List<Products> findById(Products id);

}
