package br.com.loja.controller.form;

import br.com.loja.model.TypeProduct;
import br.com.loja.repository.TypeProductRepository;
import br.com.loja.service.interfaces.TypeProductInterface;

public class UpdateTypeProductForm {

	
	private String typeDescription;



	public String getTypeDescription() {
		return typeDescription;
	}



	public TypeProduct update(TypeProduct typeProduct) {
		typeProduct.setTypeDescription(typeDescription);
		return typeProduct;
	}




}
