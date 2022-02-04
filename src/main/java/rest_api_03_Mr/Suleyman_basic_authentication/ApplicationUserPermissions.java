package rest_api_03_Mr.Suleyman_basic_authentication;

public enum ApplicationUserPermissions {
	
	STUDENT_READ("student:read"), STUDENT_WRITE("student:write");
	
	private final String permission;
	
	private ApplicationUserPermissions(String permission)
	{
		this.permission=permission;
	}

	

}
