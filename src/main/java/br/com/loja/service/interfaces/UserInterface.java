package br.com.loja.service.interfaces;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import br.com.loja.model.User;

public interface UserInterface {

	Optional<User> findByUser(String user);

	Page<User> findByUsers(@Param("user") String user, Pageable pagination);
	
	Page<User> findAll(Pageable pagination);
	
	void save(User user);
	
	void deleteById(Long id);
}
