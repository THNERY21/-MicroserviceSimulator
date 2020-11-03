package br.com.loja.service.interfaces;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.loja.model.Orders;

public interface OrdersInterface {
	
	Orders findByidOrder(long l);

	Orders findByUser_User(String user);

	Page<Orders> findByUser(String user, Pageable pagination);
	
	void save(Orders orders);
	
	void deleteById(Long id);
	
	void delete(Orders orders);
}
