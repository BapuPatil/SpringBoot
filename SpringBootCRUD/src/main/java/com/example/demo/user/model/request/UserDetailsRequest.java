package com.example.demo.user.model.request;


//class to responsd to the HTTP POST request 
// the controller receives a json input 
//(XML input can also be provided , for which configuaration is to be done at controllers end ) 
//the input is mapped to object of this class
public class UserDetailsRequest {
private String name;
private String email;
private int age;
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}
}
