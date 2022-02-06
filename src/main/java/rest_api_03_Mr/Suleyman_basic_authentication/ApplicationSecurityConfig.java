package rest_api_03_Mr.Suleyman_basic_authentication;

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


/*
      Admin=> GET(Read), PUT+POST+PATCH+DELETE(Write)
      Student=>Get(Read)
 */




@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true) //If I want to enable @preauthorize annatotion  in any classs(it is  in colntroller class)I must put ths annotation
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(). //csrf().disable(). lets you to use delete, put,post patch methods
		authorizeRequests().
		antMatchers("/","index","/css/*","js/*").permitAll().//if you see  home page or any css or js codes make tehm public
		//antMatchers("/**").hasRole(ApplicationUserRoles.ADMIN.name()). //antMatchers("/**") it means after homes every page 
																	    // If you type that justADMIN willl be able to use all methods
		                                                               //If you do not type that all roles will be able to use all methos
		anyRequest().
		authenticated().
		and().
		httpBasic(); //Basic-Authentication:Uses username and password for every request
	}
	
	private final PasswordEncoder passwordEncoder; //This is the ojact to make connection between passwordconfig and Application sEcirty config
	//since is says final I needto initialize it. that is why I am creating contructor. 
	
	@Autowired //with this autowired I am able get the object which is created in Passwordconfig class named as passwordencoder
	ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
	
		this.passwordEncoder = passwordEncoder;
	}

	@Override //to create a user in your application we neeed to override UserDetailsSErvice methos. 
	@Bean  //since it is returning object I should put Qbean
	protected UserDetailsService userDetailsService() {
		
		UserDetails student = User. 
								builder().
								username("techproed").
								password(passwordEncoder.encode("password123")). // in here I am giving my password to spring boot to encode and use
								//roles(ApplicationUserRoles.STUDENT.name()).
								authorities(ApplicationUserRoles.STUDENT.getGrantedAuthorities()).
								build();
		
		UserDetails admin= User. 
				builder().
				username("admin").
				password(passwordEncoder.encode("nimda")). // in here I am giving my password to spring boot to encode and use
				//roles(ApplicationUserRoles.ADMIN.name()).
				authorities(ApplicationUserRoles.ADMIN.getGrantedAuthorities()).
				build();
		
		return new InMemoryUserDetailsManager(student,admin);
		
	}

	
	

}
