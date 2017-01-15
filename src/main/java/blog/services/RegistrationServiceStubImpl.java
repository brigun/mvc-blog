package blog.services;

import java.util.Objects;

import org.springframework.stereotype.Service;

@Service
public class RegistrationServiceStubImpl implements RegistrationService{

	@Override
	public boolean register(String username, String password, String verifyPassword, String fullName) {
		// TODO Auto-generated method stub
		if(!Objects.equals(password, verifyPassword))
		{
			return false;
		}
		return Objects.equals(username, password) && (fullName != "");
			
		
	}

}
