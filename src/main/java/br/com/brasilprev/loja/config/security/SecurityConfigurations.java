package br.com.brasilprev.loja.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.brasilprev.loja.repository.UserRepository;
import br.com.brasilprev.loja.service.TokenService;

@EnableWebSecurity
@Configuration
public class SecurityConfigurations extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManager();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(authenticationService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
		.antMatchers(HttpMethod.GET, "/products").hasRole("USER")
		.antMatchers(HttpMethod.GET, "/products/*").hasRole("USER")
		.antMatchers(HttpMethod.DELETE, "/products/*").hasRole("ADMINISTRATOR")
		.antMatchers(HttpMethod.POST, "/products").permitAll()
		.antMatchers(HttpMethod.PUT, "/products/*").hasRole("ADMINISTRATOR")
		.antMatchers(HttpMethod.POST, "/auth").permitAll()
		.antMatchers(HttpMethod.GET, "/actuator/**").hasRole("ADMINISTRATOR")
		.antMatchers(HttpMethod.GET, "/orders").hasRole("USER")
		.antMatchers(HttpMethod.GET, "/orders/*").hasRole("USER")
		.antMatchers(HttpMethod.PUT, "/orders/*").hasRole("USER")
		.antMatchers(HttpMethod.POST, "/orders").hasRole("USER")
		.antMatchers(HttpMethod.GET, "/users").hasRole("USER")
		.antMatchers(HttpMethod.GET, "/users/*").hasRole("USER")
		.antMatchers(HttpMethod.POST, "/users").hasRole("ADMINISTRATOR")
		.antMatchers(HttpMethod.PUT, "/users/*").hasRole("ADMINISTRATOR")
		.antMatchers(HttpMethod.DELETE, "/users/*").hasRole("ADMINISTRATOR")
		.antMatchers(HttpMethod.GET, "/typeProdcuts").hasRole("USER")
		.antMatchers(HttpMethod.GET, "/typeProdcuts/*").hasRole("USER")
		.antMatchers(HttpMethod.POST, "/typeProdcuts").hasRole("ADMINISTRATOR")
		.antMatchers(HttpMethod.PUT, "/typeProdcuts/*").hasRole("ADMINISTRATOR")
		.antMatchers(HttpMethod.DELETE, "/typeProdcuts/*").hasRole("ADMINISTRATOR")
		.anyRequest().authenticated()
		.and().csrf().disable()
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and().addFilterBefore(new TokenAuthenticationFilter(tokenService,userRepository), UsernamePasswordAuthenticationFilter.class);

	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
		web.ignoring().antMatchers("/**.html", "login.do", "/webjars/**", "/h2-console/**", "/swagger-resources/**");
		
	}
	


}
 