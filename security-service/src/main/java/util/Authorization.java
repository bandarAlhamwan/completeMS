package util;

public class Authorization {
	
	// @PreAuthorize("hasAuthority('ROLE_ADMIN') or hasAuthority('ROLE_USER')")
	// @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public static final String ADMIN_ROLE = "hasAnyRole('ROLE_ADMIN')";
	public static final String USER_ROLE = "hasAnyRole('ROLE_USER')";
	public static final String ADMIN_USER_ROLE = "hasAnyRole('ROLE_USER','ROLE_ADMIN')";


	public static final String EMPLOYEE_ADD = "hasAnyRole('ROLE_EMPLOYEE_ADD')";
	public static final String EMPLOYEE_UPDATE = "hasAnyRole('ROLE_EMPLOYEE_UPDATE')";
	public static final String EIMPLOYEE_LIST = "hasAnyRole('ROLE_EMPLOYEE_LIST')";
	public static final String EIMPLOYEE_FIND_ID = "hasAnyRole('ROLE_EMPLOYEE_FIND_ID')";
	public static final String EIMPLOYEE_DELETE = "hasAnyRole('ROLE_EMPLOYEE_DELETE')";
	public static final String EIMPLOYEE_PAGE = "hasAnyRole('ROLE_EMPLOYEE_PAGE')";
	
	public static final String WELCOME = "hasAnyRole('ROLE_WELCOME')";
	public static final String TEXT = "hasAnyRole('ROLE_TEXT')";
	public static final String create2 = "hasAnyRole('ROLE_CREATE2')";
	public static final String ID = "hasAnyRole('ROLE_ID')";
	public static final String TEST = "hasAnyRole('ROLE_TEST')";
	public static final String Authentication = "hasAnyRole('ROLE_Authentication')";
	public static final String principal = "hasAnyRole('ROLE_principal')";
	
	
	
}
