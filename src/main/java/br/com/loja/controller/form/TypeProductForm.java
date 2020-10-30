package br.com.loja.controller.form;

import br.com.loja.model.TypeProduct;
import br.com.loja.model.pk.TypeId;
import br.com.loja.repository.TypeProductRepository;

public class TypeProductForm {

	private String name;

	private String typeDescription;

	public TypeProductForm(TypeProduct type) {
		this.name = type.getId().getName();
		this.typeDescription = type.getTypeDescription();
	}
	
	public TypeProductForm() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public String getTypeDescription() {
		return typeDescription;
	}

	public TypeProduct convert(TypeProductRepository typeProductRepository) {
		TypeId id = new TypeId();
		id.setName(name.toLowerCase());
		TypeProduct type = typeProductRepository.findByid_NameType(id.getName());
		if(type==null) {
			return new TypeProduct(id,typeDescription);
		} 
		return null;
	}


}
