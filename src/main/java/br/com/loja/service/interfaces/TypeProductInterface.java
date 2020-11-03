package br.com.loja.service.interfaces;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.loja.model.TypeProduct;
import br.com.loja.model.pk.TypeId;

public interface TypeProductInterface {
	
	TypeProduct findByid_NameType(String type);

	Page<TypeProduct> findByid_NameType(String name, Pageable pagination);
	
	Page<TypeProduct> findAll(Pageable pagination);
	
	void save(TypeProduct typeProduct);
	
	void deleteById(TypeId id);
	
	Optional<TypeProduct> findById(TypeId id);
}
