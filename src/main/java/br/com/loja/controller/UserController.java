package br.com.loja.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.loja.controller.form.UpdateUserForm;
import br.com.loja.controller.form.UserRegisterForm;
import br.com.loja.dto.UserDto;
import br.com.loja.model.Orders;
import br.com.loja.model.User;
import br.com.loja.service.interfaces.OrdersInterface;
import br.com.loja.service.interfaces.ProfileInterface;
import br.com.loja.service.interfaces.UserInterface;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserInterface userSerice;
	
	@Autowired
	private ProfileInterface profileService;
	
	@Autowired
	private OrdersInterface ordersService;
	



	@GetMapping
	@Cacheable(value = "userList")
	public Page<UserDto> searchUsers(@RequestParam(required = false) @Valid String name,
			@PageableDefault(sort = "id", direction = Direction.ASC, page =0, size=10) Pageable pagination) {
		if (name != null) {
			Page<User> users = userSerice.findByUsers(name.toLowerCase(), pagination);
			return UserDto.convertUserList(users);
		} else {
			Page<User> users = userSerice.findAll(pagination);
			return UserDto.convertUserList(users);
		}
		
	}

	@PostMapping
	@Transactional
	@CacheEvict(value = "userList", allEntries = true)
	public ResponseEntity<UserDto> registerUser(@RequestBody @Valid UserRegisterForm userRegisterForm,
			UriComponentsBuilder uriBuilder) {
		User user = userRegisterForm.convert(profileService);
		Optional<User> userOpt =  userSerice.findByUser(user.getUser());
		if(userOpt.isPresent()) {
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();
		}
		userSerice.save(user);
		URI uri = uriBuilder.path("/prodcuts/{id}").buildAndExpand(user.getId()).toUri();
		return ResponseEntity.created(uri).body(new UserDto(user));
	}

	
	@GetMapping("/{user}")
	public ResponseEntity<UserDto> userdetail(@PathVariable String user) {

		Optional<User> userOpt = userSerice.findByUser(user);
		if (userOpt.isPresent()) {
			return ResponseEntity.ok(new UserDto(userOpt.get()));
		}
		return ResponseEntity.notFound().build();

	}

	@PutMapping("/{user}")
	@Transactional
	@CacheEvict(value = "userList", allEntries = true)
	public ResponseEntity<UserDto> updateUser(@PathVariable String user, @RequestBody @Valid UpdateUserForm form) {
		Optional<User> userOpt = userSerice.findByUser(user);
		if (userOpt.isPresent()) {
			User userModel = form.update(userOpt.get(),profileService);
			return ResponseEntity.ok(new UserDto(userModel));
		}
		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{user}")
	@Transactional
	@CacheEvict(value = "userList", allEntries = true)
	public ResponseEntity<?> deleteUser(@PathVariable String user) {
		Optional<User> userOpt = userSerice.findByUser(user);
		if (userOpt.isPresent()) {
			Orders order =  ordersService.findByUser_User(user);
			if(order!=null) {
				ordersService.delete(order);
			}
			userSerice.deleteById(userOpt.get().getId());
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}
	 
}
