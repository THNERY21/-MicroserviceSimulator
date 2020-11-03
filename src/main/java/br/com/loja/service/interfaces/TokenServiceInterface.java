package br.com.loja.service.interfaces;

import org.springframework.security.core.Authentication;

public interface TokenServiceInterface {

	String generateToken(Authentication authentication);
	
	boolean isTokenValid(String token);
	
	Long getIdUser(String token);
}
