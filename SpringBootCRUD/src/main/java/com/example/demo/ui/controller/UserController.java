package com.example.demo.ui.controller;

import java.util.HashMap;
import java.util.Map;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.ui.services.UserService;
import com.example.demo.user.model.request.UserDetailsRequest;
import com.example.demo.user.model.response.UserRest;
import com.example.demo.user.model.update.UpdateUserDetails;

import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("users") //http:localhost:8080/users
public class UserController {
	
	@Autowired
	UserService us;


	Map<String,UserRest> map ;
	//map for in memory database
	


	@GetMapping(
				produces= {
//						MediaType.APPLICATION_XML_VALUE,  //respond in XML
//						MediaType.APPLICATION_JSON_VALUE
						}
			)
	public UserRest getUsers() {
		
		return us.getUser();
		
	}

//	@GetMapping()
//	public String getUsers(

			//Request Param is the query parameters passed in the URL

//			@RequestParam(value="page" , defaultValue="1") int page,
//			@RequestParam(value="limit" , defaultValue="23") int limit ,
//			@RequestParam(value="sort" ,required = false,defaultValue="desc") String sort
//			) {
//		return " page = "+page+" limit = "+limit+" sort = "+sort+"\n";
//	}
	
	
	// responds to HTTP GET request when the email ID is passed in the URL
	@GetMapping(path = "/{emailId}") 
	public ResponseEntity<UserRest> getUser(@PathVariable String emailId) {
		if(map.containsKey(emailId)) {
		return new ResponseEntity<UserRest> (map.get(emailId),HttpStatus.OK); 
	}else {
		return new ResponseEntity<UserRest> (HttpStatus.NO_CONTENT);
	}
		}
	

	//Responds to post request 
	//produces , consumes data in both XML and JSON format 
	@PostMapping(
			produces= {
					MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE 
					}
		,
		
		consumes= {
				MediaType.APPLICATION_JSON_VALUE,
				MediaType.APPLICATION_XML_VALUE 
				}
			
			
			)  //response HTTP to POST request
	public ResponseEntity<UserRest> createUser(@RequestBody UserDetailsRequest udr) {
		
		UserRest ur2 = new UserRest();
		
		ur2.setName(udr.getName());
		ur2.setEmail(udr.getEmail());
		ur2.setAge(udr.getAge());
		
	if(map==null) {
		map= new HashMap();
		}
		map.put(udr.getEmail(),ur2);
		
		return new ResponseEntity<UserRest>(ur2,HttpStatus.OK);
//		return  "createUser was called";
		
		
	}

	//Responds to HTTP Put request 
	//update a record containing the email passed in the URL

	@PutMapping( path="/{email}",
			produces= {MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE  //respond in XML
					
					}
		,
		
		consumes= {
				MediaType.APPLICATION_JSON_VALUE,
				MediaType.APPLICATION_XML_VALUE 
				}
			)
public UserRest putUser(@PathVariable String email,@RequestBody UpdateUserDetails update){
			UserRest ur2=map.get(email);
			ur2.setAge(update.getAge());
			ur2.setName(update.getName());
			map.put(email, ur2);
		
		
return ur2;
}
	
	@DeleteMapping (path= "{mail}") //response to HTTP DELETE request
	//Deletes record with mail id passed in the URL 

	public ResponseEntity<Void> deleteUser(@PathVariable String mail ) {
		map.remove(mail);
		return  ResponseEntity.noContent().build();
	}
	
	
	
}
