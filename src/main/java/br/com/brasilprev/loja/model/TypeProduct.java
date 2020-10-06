package br.com.brasilprev.loja.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import br.com.brasilprev.loja.model.pk.TypeId;

@Entity
public class TypeProduct {

	@EmbeddedId
	private TypeId id;

	private String typeDescription;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "type", cascade = CascadeType.ALL)
	private List<Products> products;
	
	public TypeProduct() {
		// TODO Auto-generated constructor stub
	}

	public TypeProduct(TypeId id, String typeDescription) {
		super();
		this.id = id;
		this.typeDescription = typeDescription;
	}

	public TypeId getId() {
		return id;
	}

	public void setId(TypeId id) {
		this.id = id;
	}

	public String getTypeDescription() {
		return typeDescription;
	}

	public void setTypeDescription(String typeDescription) {
		this.typeDescription = typeDescription;
	}

	public List<Products> getProducts() {
		return products;
	}

	public void setProducts(List<Products> products) {
		this.products = products;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		TypeProduct other = (TypeProduct) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	

}
