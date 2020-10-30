package br.com.loja.controller.form;

import br.com.loja.model.Products;
import br.com.loja.model.TypeProduct;
import br.com.loja.repository.TypeProductRepository;

public class UpdateTypeProductForm {

	
	private String typeDescription;



	public String getTypeDescription() {
		return typeDescription;
	}



	public TypeProduct update(TypeProduct typeProduct, TypeProductRepository typeProductRepository) {
		typeProduct.setTypeDescription(typeDescription);
		return typeProduct;
	}




}
