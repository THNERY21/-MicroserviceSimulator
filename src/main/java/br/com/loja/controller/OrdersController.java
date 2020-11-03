package br.com.loja.controller;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.loja.controller.form.DeleteProductForm;
import br.com.loja.controller.form.OrderForm;
import br.com.loja.dto.OrderDto;
import br.com.loja.model.Orders;
import br.com.loja.service.interfaces.OrdersInterface;
import br.com.loja.service.interfaces.ProductsInterface;
import br.com.loja.service.interfaces.UserInterface;

@RestController
@RequestMapping("/orders")
public class OrdersController {

	
	@Autowired
	private OrdersInterface ordersService;
	
	@Autowired
	private ProductsInterface productsServie;
	
	@Autowired
	private UserInterface userSerice;

 
	@PostMapping
	@Transactional
	public ResponseEntity<OrderDto> registerOrder(@RequestBody @Valid OrderForm orderForm,
			UriComponentsBuilder uriBuilder) {
		Orders orders = orderForm.convert(productsServie, userSerice, ordersService);
		if (orders != null) {
			ordersService.save(orders);
			URI uri = uriBuilder.path("/orders/{id}").buildAndExpand(orders.getIdOrder()).toUri();
			return ResponseEntity.created(uri).body(new OrderDto(orders));

		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/{username}")
	public ResponseEntity<OrderDto> orderDetail(@PathVariable String username) {

		Orders order = ordersService.findByUser_User(username);
		if (order!=null) {
			return ResponseEntity.ok(new OrderDto(order));
		}
		return ResponseEntity.notFound().build();

	}

	
	@DeleteMapping("/{username}")
	@Transactional
	public ResponseEntity<?> deleteOrder(@PathVariable String username, @RequestBody @Valid DeleteProductForm form) {
		Orders order = ordersService.findByUser_User(username);
		if (order!=null) {
			form.deleteOrderProdcut(order, ordersService, productsServie);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
