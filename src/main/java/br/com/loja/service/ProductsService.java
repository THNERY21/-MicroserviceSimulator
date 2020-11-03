package br.com.loja.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.loja.model.Products;
import br.com.loja.repository.ProductsRepository;
import br.com.loja.service.interfaces.ProductsInterface;

@Service
public class ProductsService implements ProductsInterface{
	
	@Autowired
	private ProductsRepository productsRepository;

	@Override
	public Page<Products> findByName(String name, Pageable pagination) {
		return productsRepository.findByName(name, pagination);
	}

	@Override
	public List<Products> findById(Products id) {
		return productsRepository.findById(id);
	}

	@Override
	public void delete(Long id) {
		productsRepository.delete(id);
	}

	@Override
	public Optional<Products> findById(Long id) {
		return productsRepository.findById(id);
	}

	@Override
	public Products getOne(Long id) {
		return productsRepository.getOne(id);
	}

	@Override
	public Page<Products> findAll(Pageable pagination) {
		return productsRepository.findAll(pagination);
	}

	@Override
	public void save(Products products) {
		productsRepository.save(products);
	}
	
}
