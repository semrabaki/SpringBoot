package rest_api04_controller_service_repository_basic_auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity //if you do not put this annotation it will be like based annotation
@EnableGlobalMethodSecurity(prePostEnabled=true) //If you want to enable @PreAuthorize in any class use this.
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().//When you put those, it means you unblocked PUT,POST,PATCH,DELETE methods.
			authorizeRequests().
			antMatchers("/","index","/css/*","/js/*").permitAll(). //In here we unblock the home page- it is public
			//antMatchers("/**").hasRole(ApplicationUserRoles.ADMIN.name()).//If you type that just ADMIN will be able to use all methods =-Role based autentication
             //"/**  " it means after home page all path - //If you do not type that all roles will be able to use all methods 
			anyRequest().
			authenticated().
			and().
			httpBasic(); //Basic authentication, uses username and password for every request.
	}


	@Override
	@Bean
	protected UserDetailsService userDetailsService() { // this is how we set the password in code 
		
		UserDetails student = User.
				builder().
				username("techproed").
				password(passwordEncoder.encode("password123")).
				//roles(ApplicationUserRoles.STUDENT.name()).
				authorities(ApplicationUserRoles.STUDENT.getGrantedAuthorities()).
				build(); 

		UserDetails admin = User.
						builder().
						username("admin").
						password(passwordEncoder.encode("nimda")).
						//roles(ApplicationUserRoles.ADMIN.name()).
						authorities(ApplicationUserRoles.ADMIN.getGrantedAuthorities()).
						build();
		
		return new InMemoryUserDetailsManager(student, admin);
		
		
	}
	
	private final PasswordEncoder passwordEncoder;
	@Autowired
	public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
		
		this.passwordEncoder=passwordEncoder;
	}
	
	
	

}
