package br.com.brasilprev.loja.config.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.brasilprev.loja.model.User;
import br.com.brasilprev.loja.repository.UserRepository;
import br.com.brasilprev.loja.service.TokenService;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

	private TokenService tokenService;

	private UserRepository userRepository;

	public TokenAuthenticationFilter(TokenService tokenService, UserRepository userRepository) {
		super();
		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = fetchToken(request);
		boolean valid = tokenService.isTokenValid(token);
	

		if (valid) {
			authenticateClient(token);
		}
		 
		filterChain.doFilter(request, response);
	}

	private void authenticateClient(String token) {
		Long idUser = tokenService.getIdUser(token);
		Optional<User> optional = userRepository.findById(idUser);
		User user = optional.get();
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, null,
				user.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(authentication);

	}

	private String fetchToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (!StringUtils.isNotEmpty(token) || !token.startsWith("Bearer")) {
			return null;
		}
		return token.replace("Bearer", "").trim();
	}

}
