package br.com.loja.service.interfaces;

import java.util.List;

import br.com.loja.model.ProfileUser;

public interface ProfileInterface {
	
	List<ProfileUser> findAll();

	ProfileUser getOne(Long id);

}
