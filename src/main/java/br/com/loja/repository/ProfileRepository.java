package br.com.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.loja.model.ProfileUser;

public interface ProfileRepository extends JpaRepository<ProfileUser, Long> {

	

}
