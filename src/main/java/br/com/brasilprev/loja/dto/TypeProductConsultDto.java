package br.com.brasilprev.loja.dto;

import org.springframework.data.domain.Page;

import br.com.brasilprev.loja.model.Products;
import br.com.brasilprev.loja.model.TypeProduct;

public class TypeProductConsultDto {

	private String name;

	private String typeDescription;

	public TypeProductConsultDto(TypeProduct type) {
		this.name = type.getId().getName();
		this.typeDescription = type.getTypeDescription();
	}

	public String getName() {
		return name;
	}

	public String getTypeDescription() {
		return typeDescription;
	}

	public static Page<TypeProductConsultDto> convertTypeList(Page<TypeProduct> typeProduct) {
		return typeProduct.map(TypeProductConsultDto::new);
	}
}
