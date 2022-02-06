package rest_api_03_Mr.Suleyman_basic_authentication;

public enum ApplicationUserPermissions {
	
	STUDENT_READ("student:read"), STUDENT_WRITE("student:write");  //If there is an other code continues put semicolon
	
	private final String permission;
	
	private ApplicationUserPermissions(String permission)
	{
		this.permission=permission;
	}

	public String getPermission() {//////
		return permission;
	}
    
	
	

}
