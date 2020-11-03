package br.com.loja.controller.form;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.loja.model.Orders;
import br.com.loja.model.Products;
import br.com.loja.model.User;
import br.com.loja.service.interfaces.OrdersInterface;
import br.com.loja.service.interfaces.ProductsInterface;
import br.com.loja.service.interfaces.UserInterface;

public class OrderForm {

	@NotNull
	private String user;

	@NotNull
	@NotEmpty
	private List<Long> idProductsList;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public List<Long> getIdProductsList() {
		return idProductsList;
	}

	public void setIdProductsList(List<Long> idProductsList) {
		this.idProductsList = idProductsList;
	}

	public Orders convert(ProductsInterface productsServie, UserInterface userSerice,
			OrdersInterface ordersInterface) {

		Optional<User> userOpt = userSerice.findByUser(this.user.toLowerCase());
		if (userOpt.isPresent()) {
			Orders order = new Orders(); 
			order = ordersInterface.findByUser_User(userOpt.get().getUser());
			if (order != null && order.getIdOrder() != null) {
				FillOrders(productsServie, order);
				order.setUser(userOpt.get());
				return order;
			} else {
				order = new Orders();
				order.setProductsList(new HashSet<>());
				order.setUser(userOpt.get());
				FillOrders(productsServie, order);
			}
			return order;
		}

		return null;
	}

	private void FillOrders(ProductsInterface productsServie, Orders order) {
		this.idProductsList.forEach(id -> {
			Optional<Products> product = productsServie.findById(id);
			if(product.isPresent()) {
				order.getProductsList().add(product.get());
			}
		});
	}


	/*
	 * public Products update(Long id, ProductsRepository
	 * productsRepository,TypeProductRepository typeProductRepository) { Products
	 * products = productsRepository.getOne(id); TypeProduct type =
	 * typeProductRepository.findByid_NameType(this.nameType);
	 * 
	 * products.setName(this.name); products.setSize(this.size);
	 * products.setType(type); products.setWeight(this.weight); return products; }
	 */

}
