package br.com.loja.controller.form;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.loja.model.ProfileUser;
import br.com.loja.model.User;
import br.com.loja.repository.ProfileRepository;
import br.com.loja.service.interfaces.ProfileInterface;

public class UpdateUserForm {
		
	private String name;
	
	private static final long ROLE_USER = 1L;

	@Length(min = 11, max = 11)
	private String cpf;

	@Email
	private String email;
	
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


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = new BCryptPasswordEncoder().encode(password);
	}

	public User update(User user,ProfileInterface profileService) {
		if (StringUtils.isNotEmpty(cpf)) {
			user.setCpf(cpf);
		}
		if (StringUtils.isNotEmpty(name)) {
			user.setName(name);
		}
		if (StringUtils.isNotEmpty(email)) {
			user.setEmail(email);
		}
		if (StringUtils.isNotEmpty(cpf)) {
			user.setPassword(password);
		}
		List<ProfileUser> profiles = new ArrayList<>();
		if(isAdmin) {
			profiles = profileService.findAll();
		}else {
			profiles.add(profileService.getOne(ROLE_USER));
		}
		user.setProfiles(profiles);
		return user;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
}
