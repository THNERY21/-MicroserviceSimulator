package br.com.brasilprev.loja.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brasilprev.loja.model.TypeProduct;
import br.com.brasilprev.loja.model.pk.TypeId;

public interface TypeProductRepository extends JpaRepository<TypeProduct, TypeId> {

	TypeProduct findByid_NameType(String type);

	Page<TypeProduct> findByid_NameType(String name, Pageable pagination);


}
