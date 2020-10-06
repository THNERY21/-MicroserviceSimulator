package br.com.brasilprev.loja.model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Products {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "type", referencedColumnName = "nameType")
	private TypeProduct type;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "produc_order", joinColumns = {
			@JoinColumn(name = "id_product", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "idOrder", referencedColumnName = "idOrder") })
	private List<Orders> orders;

	private BigDecimal Weight;

	private String size;

	
	public Products() {
		// TODO Auto-generated constructor stub
	}
	
	public Products(String name, TypeProduct type, BigDecimal weight, String size) {
		super();
		this.name = name;
		this.type = type;
		Weight = weight;
		this.size = size;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public TypeProduct getType() {
		return type;
	}


	public void setType(TypeProduct type) {
		this.type = type;
	}


	public BigDecimal getWeight() {
		return Weight;
	}


	public void setWeight(BigDecimal weight) {
		Weight = weight;
	}


	public String getSize() {
		return size;
	}


	public void setSize(String size) {
		this.size = size;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Weight == null) ? 0 : Weight.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((orders == null) ? 0 : orders.hashCode());
		result = prime * result + ((size == null) ? 0 : size.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Products other = (Products) obj;
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
		if (orders == null) {
			if (other.orders != null)
				return false;
		} else if (!orders.equals(other.orders))
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}


	
	
}
