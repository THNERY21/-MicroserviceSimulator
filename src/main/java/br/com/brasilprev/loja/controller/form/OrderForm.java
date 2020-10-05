package br.com.brasilprev.loja.controller.form;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.brasilprev.loja.model.Orders;
import br.com.brasilprev.loja.model.Products;
import br.com.brasilprev.loja.model.User;
import br.com.brasilprev.loja.repository.OrdersRepository;
import br.com.brasilprev.loja.repository.ProductsRepository;
import br.com.brasilprev.loja.repository.UserRepository;

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

	public Orders convert(ProductsRepository productsRepository, UserRepository userRepository,
			OrdersRepository ordersRepository) {

		Optional<User> userOpt = userRepository.findByUser(this.user);
		if (userOpt.isPresent()) {
			Orders order = new Orders(); 
			order = ordersRepository.findByidOrder(userOpt.get().getId());
			if (order != null && order.getIdOrder() != null) {
				FillOrders(productsRepository, order);
				return order;
			} else {
				order = new Orders();
				order.setProductsList(new HashSet<>());
				order.setIdOrder(userOpt.get().getId());
				FillOrders(productsRepository, order);
			}
			return order;
		}

		return null;
	}

	private void FillOrders(ProductsRepository productsRepository, Orders order) {
		this.idProductsList.forEach(id -> {
			Optional<Products> product = productsRepository.findById(id);
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
