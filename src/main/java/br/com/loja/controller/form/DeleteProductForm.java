package br.com.loja.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.commons.collections4.CollectionUtils;

import br.com.loja.model.Orders;
import br.com.loja.model.Products;
import br.com.loja.repository.OrdersRepository;
import br.com.loja.repository.ProductsRepository;

public class DeleteProductForm {

	
	@NotNull
	
	private Long idProdcut;

	

	public Long getIdProdcut() {
		return idProdcut;
	}

	public void setIdProdcut(Long idProdcut) {
		this.idProdcut = idProdcut;
	}

	/*
	 * public Products convert(TypeProductRepository typeProductRepository) {
	 * TypeProduct type = typeProductRepository.findByid_NameType(this.nameType);
	 * return new Products(name, type, weight, size); }
	 */
	
	
	public void deleteOrderProdcut(Orders orders ,OrdersRepository ordersRepository , ProductsRepository productsRepository) {
		Products products = productsRepository.getOne(idProdcut);
		orders.getProductsList().remove(products);
		ordersRepository.save(orders);
		if(!CollectionUtils.isNotEmpty(orders.getProductsList())) {
			ordersRepository.deleteById(orders.getIdOrder());
		}
	
	}

}
