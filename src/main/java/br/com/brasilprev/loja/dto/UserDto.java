package br.com.brasilprev.loja.dto;

import org.springframework.data.domain.Page;

import br.com.brasilprev.loja.model.User;

public class UserDto {

	private static final String ROLE_ADMINISTRATOR = "ROLE_ADMINISTRATOR";

	private String name;

	private String cpf;

	private String email;

	private String user;
	
	
	private boolean isAdmin;

	public UserDto(User user) {
		this.name = user.getName();
		this.cpf = user.getCpf();
		this.email = user.getEmail();
		this.user = user.getUser();
		user.getProfiles().forEach(pr -> {
			if (pr.getName().equals(ROLE_ADMINISTRATOR)) {
				setAdmin(true);
			}
		});
	}

	public String getName() {
		return name;
	}

	public String getCpf() {
		return cpf;
	}

	public String getEmail() {
		return email;
	}

	public String getUser() {
		return user;
	}
	
	
	public User convert() {
		return new User(name,cpf,email,user);
	}

	public static Page<UserDto> convertUserList(Page<User> users) {
		return users.map(UserDto::new);
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}
