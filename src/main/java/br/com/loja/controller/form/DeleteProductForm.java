package br.com.loja.controller.form;

import javax.validation.constraints.NotNull;

import org.apache.commons.collections4.CollectionUtils;

import br.com.loja.model.Orders;
import br.com.loja.model.Products;
import br.com.loja.service.interfaces.OrdersInterface;
import br.com.loja.service.interfaces.ProductsInterface;

public class DeleteProductForm {

	
	@NotNull
	private Long idProdcut;
	

	public Long getIdProdcut() {
		return idProdcut;
	}

	public void setIdProdcut(Long idProdcut) {
		this.idProdcut = idProdcut;
	}


	public void deleteOrderProdcut(Orders orders ,OrdersInterface ordersInterface, ProductsInterface productsServie) {
		Products products = productsServie.getOne(idProdcut);
		orders.getProductsList().remove(products);
		ordersInterface.save(orders);
		if(!CollectionUtils.isNotEmpty(orders.getProductsList())) {
			ordersInterface.deleteById(orders.getIdOrder());
		}
	
	}

}
