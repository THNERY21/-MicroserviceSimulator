package br.com.loja.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.loja.model.User;
import br.com.loja.repository.UserRepository;
import br.com.loja.service.interfaces.UserInterface;

@Service
public class UserService implements UserInterface{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public Optional<User> findByUser(String user) {
		return userRepository.findByUser(user);
	}

	@Override
	public Page<User> findByUsers(String user, Pageable pagination) {
		return userRepository.findByUsers(user, pagination);
	}

	@Override
	public Page<User> findAll(Pageable pagination) {
		return userRepository.findAll( pagination);
	}

	@Override
	public void save(User user) {
		userRepository.save(user);		
	}

	@Override
	public void deleteById(Long id) {
		userRepository.deleteById(id);
	}
	
}
