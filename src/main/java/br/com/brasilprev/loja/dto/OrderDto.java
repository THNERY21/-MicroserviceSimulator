package br.com.brasilprev.loja.dto;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;

import br.com.brasilprev.loja.model.Orders;

public class OrderDto {
	
	private Long id;
	
	private List<ProductDto> productsList;
	
	private LocalDateTime purchaseDate;

	public OrderDto(Orders order) {
		super();
		this.id = order.getIdOrder();
		this.productsList = ProductDto.convertProductList(order.getProductsList());
		this.purchaseDate = order.getPurchaseDate();
	}

	public Long getId() {
		return id;
	}

	public List<ProductDto> getProductsList() {
		return productsList;
	}


	public LocalDateTime getPurchaseDate() {
		return purchaseDate;
	}
	
	public static Page<OrderDto> convertOrdersList(Page<Orders> orders) {
		return orders.map(OrderDto::new);
	}

	
}
