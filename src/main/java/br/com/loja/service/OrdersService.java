package br.com.loja.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.loja.model.Orders;
import br.com.loja.repository.OrdersRepository;
import br.com.loja.service.interfaces.OrdersInterface;

@Service
public class OrdersService implements OrdersInterface{
	
	@Autowired
	private OrdersRepository ordersRepository;
	
	
	public Orders findByidOrder(long l) {
		return ordersRepository.findByidOrder(l);
	}

	public Orders findByUser_User(String user) {
		return ordersRepository.findByUser_User(user);
	}

	public Page<Orders> findByUser(String user, Pageable pagination){
		return ordersRepository.findByUser(user, pagination);
	}
	
	public void save(Orders orders) {
		ordersRepository.save(orders);
	}
	
	public void deleteById(Long id) {
		ordersRepository.deleteById(id);
	}

	@Override
	public void delete(Orders orders) {
		ordersRepository.delete(orders);		
	}
}
