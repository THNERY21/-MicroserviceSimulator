package br.com.brasilprev.loja.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.brasilprev.loja.model.ProfileUser;

public interface ProfileRepository extends JpaRepository<ProfileUser, Long> {

	

}
