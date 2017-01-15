package blog.services;

public interface RegistrationService {
	
	boolean register(String username, String password, String verifyPassword, String fullName);

}
