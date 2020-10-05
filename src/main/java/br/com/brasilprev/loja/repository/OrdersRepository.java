package br.com.brasilprev.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brasilprev.loja.model.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

	Orders findByidOrder(long l);

   

}
