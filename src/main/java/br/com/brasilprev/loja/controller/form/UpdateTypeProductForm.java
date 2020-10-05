package br.com.brasilprev.loja.controller.form;

import br.com.brasilprev.loja.model.Products;
import br.com.brasilprev.loja.model.TypeProduct;
import br.com.brasilprev.loja.repository.TypeProductRepository;

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
