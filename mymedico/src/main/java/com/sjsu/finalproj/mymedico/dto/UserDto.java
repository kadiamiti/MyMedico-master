/**
 * 
 */
package com.sjsu.finalproj.mymedico.dto;

/**
 * @author mitikadia
 *
 */

import java.util.ArrayList;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sjsu.finalproj.mymedico.domain.User;

@JsonPropertyOrder(alphabetic = true)
public class UserDto {
	
	
	
	
	public UserDto(User user) {
		super();
		this.user = user;
	}

	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	

}
