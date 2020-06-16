package com.example.demo.ui.servicesImpl;

import org.springframework.stereotype.Service;

import com.example.demo.ui.services.UserService;
import com.example.demo.user.model.response.UserRest;


//when the program runs instance of this class is created 
//and autowired to the UserService reference in the UserController class
@Service
public class UserServiceImpl implements UserService {

	@Override
	public UserRest getUser() {
		// TODO Auto-generated method stub
		
		UserRest ur = new UserRest();
		
		ur.setName("Paul");
		ur.setEmail("paul@gmail.com");
		ur.setAge(23);
		
		return ur;
	}
	
	

}
