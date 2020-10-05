package br.com.brasilprev.loja.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.brasilprev.loja.controller.form.DeleteProductForm;
import br.com.brasilprev.loja.controller.form.OrderForm;
import br.com.brasilprev.loja.controller.form.UpdateProductForm;
import br.com.brasilprev.loja.dto.OrderDto;
import br.com.brasilprev.loja.dto.ProductDto;
import br.com.brasilprev.loja.model.Orders;
import br.com.brasilprev.loja.model.Products;
import br.com.brasilprev.loja.repository.OrdersRepository;
import br.com.brasilprev.loja.repository.ProductsRepository;
import br.com.brasilprev.loja.repository.UserRepository;

@RestController
@RequestMapping("/orders")
public class OrdersController {

	@Autowired
	private OrdersRepository ordersRepository;

	@Autowired
	private ProductsRepository productsRepository;
	
	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public Page<OrderDto> listOrders(@RequestParam(required = false) @Valid String name,
			@PageableDefault(sort = "id", direction = Direction.ASC, page =0, size=10) Pageable pagination) {
			Page<Orders> orders = ordersRepository.findAll(pagination);
			return OrderDto.convertOrdersList(orders);
	}
 
	@PostMapping
	@Transactional
	public ResponseEntity<OrderDto> registerOrder(@RequestBody @Valid OrderForm orderForm,
			UriComponentsBuilder uriBuilder) {
		Orders orders = orderForm.convert(productsRepository, userRepository, ordersRepository);
		if (orders != null) {
			ordersRepository.save(orders);
			URI uri = uriBuilder.path("/orders/{id}").buildAndExpand(orders.getIdOrder()).toUri();
			return ResponseEntity.created(uri).body(new OrderDto(orders));

		}
		return ResponseEntity.notFound().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrderDto> orderDetail(@PathVariable Long id) {

		Optional<Orders> order = ordersRepository.findById(id);
		if (order.isPresent()) {
			return ResponseEntity.ok(new OrderDto(order.get()));
		}
		return ResponseEntity.notFound().build();

	}

	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deleteOrderProduct(@PathVariable Long id, @Valid DeleteProductForm form) {
		Optional<Orders> optional = ordersRepository.findById(id);
		if (optional.isPresent()) {
			form.deleteOrderProdcut(optional.get(), ordersRepository, productsRepository);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
}
