package br.com.brasilprev.loja.model;

import java.time.LocalDateTime;
import java.util.Set;

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
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idOrder;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "product_order", joinColumns = {
			@JoinColumn(name = "idOrder", referencedColumnName = "idOrder") }, inverseJoinColumns = {
					@JoinColumn(name = "id_protected", referencedColumnName = "id") })
	private Set<Products> productsList;  

	
	@ManyToOne(fetch = FetchType.EAGER)
	private User user;
 
	private LocalDateTime purchaseDate = LocalDateTime.now();

	public Orders() {
		// TODO Auto-generated constructor stub
	}
	
	
	public Orders(Set<Products> productsList, Long id) {
		this.productsList = productsList;
		this.idOrder = id;
	}

	

	public Long getIdOrder() {
		return idOrder;
	}


	public void setIdOrder(Long idOrder) {
		this.idOrder = idOrder;
	}


	public LocalDateTime getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDateTime purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	public Set<Products> getProductsList() {
		return productsList;
	}

	public void setProductsList(Set<Products> productsList) {
		this.productsList = productsList;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idOrder == null) ? 0 : idOrder.hashCode());
		result = prime * result + ((productsList == null) ? 0 : productsList.hashCode());
		result = prime * result + ((purchaseDate == null) ? 0 : purchaseDate.hashCode());
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
		Orders other = (Orders) obj;
		if (idOrder == null) {
			if (other.idOrder != null)
				return false;
		} else if (!idOrder.equals(other.idOrder))
			return false;
		if (productsList == null) {
			if (other.productsList != null)
				return false;
		} else if (!productsList.equals(other.productsList))
			return false;
		if (purchaseDate == null) {
			if (other.purchaseDate != null)
				return false;
		} else if (!purchaseDate.equals(other.purchaseDate))
			return false;
		return true;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	

}
