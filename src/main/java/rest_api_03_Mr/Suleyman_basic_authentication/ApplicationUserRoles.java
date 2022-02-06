package rest_api_03_Mr.Suleyman_basic_authentication;


import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.google.common.collect.Sets;

import rest_api04_controller_service_repository_basic_auth.ApplicationUserPermissions;

import static rest_api04_controller_service_repository_basic_auth.ApplicationUserPermissions.*;


public enum ApplicationUserRoles {
	
	
	 //These are role variables. public static final
	//Dark blue ones are role, the capital ones are permissions

     STUDENT(Sets.newHashSet(STUDENT_READ)), ADMIN(Sets.newHashSet(STUDENT_READ, STUDENT_WRITE));
	
	private final Set<ApplicationUserPermissions> permission;

	private ApplicationUserRoles(Set<ApplicationUserPermissions> permission) {
		this.permission = permission;
	}

	public Set<ApplicationUserPermissions> getPermission() {
		return permission;
	}
	
	
      public Set<SimpleGrantedAuthority> getGrantedAuthorities(){
		
		Set<SimpleGrantedAuthority> permissions = getPermission().
															stream().
															map(permission -> new SimpleGrantedAuthority(permission.getPermission())).
															collect(Collectors.toSet());
		
		permissions.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
		
		return permissions;
	}
	
	
	
	
	
	
	
	}


