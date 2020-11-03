package br.com.loja.controller.form;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

import br.com.loja.model.Products;
import br.com.loja.model.TypeProduct;
import br.com.loja.service.interfaces.ProductsInterface;
import br.com.loja.service.interfaces.TypeProductInterface;

public class UpdateProductForm {

	private String name;

	private String nameType;

	private BigDecimal weight;

	private String size;

	public String getName() {
		return name;
	}

	public String getNameType() {
		return nameType;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Products convert(TypeProductInterface typeProductSerice) {
		TypeProduct type = typeProductSerice.findByid_NameType(this.nameType);
		return new Products(name, type, weight, size);
	}

	public Products update(Long id, ProductsInterface productsServie, TypeProductInterface typeProductSerice) {
		Products products = productsServie.getOne(id);
		TypeProduct type = typeProductSerice.findByid_NameType(this.nameType);

		if (StringUtils.isNotEmpty(this.name)) {
			products.setName(this.name);
		}

		if (StringUtils.isNotEmpty(this.size)) {
			products.setSize(this.size);
		}

		if (type != null) {
			products.setType(type);
		}

		if (this.weight != null) {
			products.setWeight(this.weight);
		}

		return products;
	}

}
