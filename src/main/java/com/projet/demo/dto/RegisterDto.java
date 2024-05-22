package com.projet.demo.dto;


public class RegisterDto {
	private String firstname;
	private String lastname;
	private String email;
	private String username;
	private String password;
	private long idsalle;
	
	
	
	public RegisterDto() {
		super();
		// TODO Auto-generated constructor stub
	}



	



	public RegisterDto(String firstname, String lastname, String email, String username, String password,
			long idsalle) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.username = username;
		this.password = password;
		this.idsalle = idsalle;
	}



	public String getFirstname() {
		return firstname;
	}



	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}



	public String getLastname() {
		return lastname;
	}



	public void setLastname(String lastname) {
		this.lastname = lastname;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}







	public long getIdsalle() {
		return idsalle;
	}







	public void setIdsalle(long idsalle) {
		this.idsalle = idsalle;
	}

	
	
	
	
	
	
	
	
	
	
	
	
}
