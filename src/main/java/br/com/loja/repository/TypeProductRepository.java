package br.com.loja.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.loja.model.TypeProduct;
import br.com.loja.model.pk.TypeId;

public interface TypeProductRepository extends JpaRepository<TypeProduct, TypeId> {

	TypeProduct findByid_NameType(String type);

	Page<TypeProduct> findByid_NameType(String name, Pageable pagination);


}
