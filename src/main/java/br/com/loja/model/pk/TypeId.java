package br.com.loja.model.pk;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class TypeId implements Serializable{
 
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5853238849395247838L;
	
	private String nameType;

	public String getName() {
		return nameType;
	}

	public void setName(String nameType) {
		this.nameType = nameType;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nameType == null) ? 0 : nameType.hashCode());
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
		TypeId other = (TypeId) obj;
		if (nameType == null) {
			if (other.nameType != null)
				return false;
		} else if (!nameType.equals(other.nameType))
			return false;
		return true;
	}

}
