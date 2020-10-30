package br.com.loja.controller.form;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.loja.model.ProfileUser;
import br.com.loja.model.User;
import br.com.loja.repository.ProfileRepository;

public class UserRegisterForm {
		
	
	private static final long ROLE_USER = 1L;

	private String name;

	@Length(min = 11, max = 11)
	private String cpf;

	@Email
	private String email;
	
	@Length(min = 6, max=10)
	private String user;
	
	@Length(min = 8)
	private String password;
	
	private boolean isAdmin;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = new BCryptPasswordEncoder().encode(password);
	}



	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public User convert(ProfileRepository profileRepository) {
		List<ProfileUser> profiles = new ArrayList<>();
		if(isAdmin) {
			profiles = profileRepository.findAll();
		}else {
			profiles.add(profileRepository.getOne(ROLE_USER));
		}
		return new User(name.toLowerCase(), cpf, email, user,password,profiles); 
	}

	
}
