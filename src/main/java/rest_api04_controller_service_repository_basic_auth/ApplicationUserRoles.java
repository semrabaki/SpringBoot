package rest_api04_controller_service_repository_basic_auth;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import static rest_api04_controller_service_repository_basic_auth.ApplicationUserPermissions.*;
import com.google.common.collect.Sets;

public enum ApplicationUserRoles {
	

	STUDENT(Sets.newHashSet(STUDENT_READ)), ADMIN(Sets.newHashSet(STUDENT_READ, STUDENT_WRITE)); //variables it means student only cna read burt admin can read and write
	
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
