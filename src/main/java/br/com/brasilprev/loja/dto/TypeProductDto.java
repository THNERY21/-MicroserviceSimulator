package br.com.brasilprev.loja.dto;

import java.math.BigDecimal;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.com.brasilprev.loja.model.Products;
import br.com.brasilprev.loja.model.TypeProduct;

public class TypeProductDto {

	private Long id;

	private String name;

	private String nameType;

	private String typeDescription;

	private BigDecimal Weight;

	private String size;

	public TypeProductDto(Products products) {
		super();
		this.id = products.getId();
		this.name = products.getName();
		this.nameType = products.getType().getId().getName();
		this.typeDescription = products.getType().getTypeDescription();
		Weight = products.getWeight();
		this.size = products.getSize();
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getNameType() {
		return nameType;
	}

	public String getTypeDescription() {
		return typeDescription;
	}

	public BigDecimal getWeight() {
		return Weight;
	}

	public String getSize() {
		return size;
	}
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Weight == null) ? 0 : Weight.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nameType == null) ? 0 : nameType.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + ((typeDescription == null) ? 0 : typeDescription.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TypeProductDto other = (TypeProductDto) obj;
		if (Weight == null) {
			if (other.Weight != null)
				return false;
		} else if (!Weight.equals(other.Weight))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nameType == null) {
			if (other.nameType != null)
				return false;
		} else if (!nameType.equals(other.nameType))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		if (typeDescription == null) {
			if (other.typeDescription != null)
				return false;
		} else if (!typeDescription.equals(other.typeDescription))
			return false;
		return true;
	}
	

	public static Page<TypeProductDto> convertProductList(Page<Products> products) {
		return products.map(TypeProductDto::new);
	}
}
