package br.com.brasilprev.loja.controller.form;

import br.com.brasilprev.loja.model.TypeProduct;
import br.com.brasilprev.loja.model.pk.TypeId;
import br.com.brasilprev.loja.repository.TypeProductRepository;

public class TypeProductForm {

	private String name;

	private String typeDescription;

	public TypeProductForm(TypeProduct type) {
		this.name = type.getId().getName();
		this.typeDescription = type.getTypeDescription();
	}

	public String getName() {
		return name;
	}

	public String getTypeDescription() {
		return typeDescription;
	}

	public TypeProduct convert(TypeProductRepository typeProductRepository) {
		TypeId id = new TypeId();
		id.setName(name);
		TypeProduct type = typeProductRepository.findByid_NameType(name);
		if(type==null) {
			return new TypeProduct(id,typeDescription);
		}
		return null;
	}


}
