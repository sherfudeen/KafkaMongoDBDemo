package com.sherfu.kafka.demo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class EmailPassword {

	@Id
	private String email;
	private String password;
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	public EmailPassword() {
		
	}
	
	public EmailPassword(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	@Override
	public String toString() {
		return "EmailPassword [email=" + email + ", password=" + password + "]";
	}
}
