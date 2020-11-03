package br.com.loja.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.loja.model.TypeProduct;
import br.com.loja.model.pk.TypeId;
import br.com.loja.repository.TypeProductRepository;
import br.com.loja.service.interfaces.TypeProductInterface;

@Service
public class TypeProductService implements TypeProductInterface{
	
	@Autowired
	private TypeProductRepository typeProductRepository;

	@Override
	public TypeProduct findByid_NameType(String type) {
		return typeProductRepository.findByid_NameType(type);
	}

	@Override
	public Page<TypeProduct> findByid_NameType(String name, Pageable pagination) {
		return typeProductRepository.findByid_NameType(name, pagination);
	}

	@Override
	public Page<TypeProduct> findAll(Pageable pagination) {
		return typeProductRepository.findAll(pagination);
	}

	@Override
	public void save(TypeProduct typeProduct) {
		typeProductRepository.save(typeProduct);
	}
	
	@Override
	public void deleteById(TypeId id) {
		typeProductRepository.deleteById(id);
	}

	@Override
	public Optional<TypeProduct> findById(TypeId id) {
		return typeProductRepository.findById(id);
	}
}
