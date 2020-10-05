package br.com.brasilprev.loja.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import br.com.brasilprev.loja.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByUser(String user);
	
	@Query("Select u from User u where user= :user")
	Page<User>findByUsers(@Param("user") String user,Pageable pagination); 

}
