package br.com.loja.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.loja.model.ProfileUser;
import br.com.loja.repository.ProfileRepository;
import br.com.loja.service.interfaces.ProfileInterface;

@Service
public class ProfileService implements ProfileInterface{
	
	@Autowired
	private  ProfileRepository profileRepository;

	@Override
	public List<ProfileUser> findAll() {
		return profileRepository.findAll();
	}

	@Override
	public ProfileUser getOne(Long id) {
		return profileRepository.getOne(id);
	}
	
}
