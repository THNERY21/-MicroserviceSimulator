package br.com.loja.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.loja.model.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

	Orders findByidOrder(long l);

	Orders findByUser_User(String user);

	@Query("Select o from Orders o where o.user.user= :user")
	Page<Orders> findByUser(String user, Pageable pagination);
   

}
