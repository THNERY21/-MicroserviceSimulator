package br.com.loja.controller.form;

import br.com.loja.model.TypeProduct;
import br.com.loja.model.pk.TypeId;
import br.com.loja.repository.TypeProductRepository;
import br.com.loja.service.interfaces.TypeProductInterface;

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

	public TypeProduct convert(TypeProductInterface typeProductSerice) {
		TypeId id = new TypeId();
		id.setName(name.toLowerCase());
		TypeProduct type = typeProductSerice.findByid_NameType(id.getName());
		if(type==null) {
			return new TypeProduct(id,typeDescription);
		} 
		return null;
	}


}
