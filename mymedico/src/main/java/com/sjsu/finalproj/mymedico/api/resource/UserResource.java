/**
 * 
 */
package com.sjsu.finalproj.mymedico.api.resource;

import java.sql.SQLException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sjsu.finalproj.mymedico.domain.Recommendation;
import com.sjsu.finalproj.mymedico.domain.User;
import com.sjsu.finalproj.mymedico.dto.*;
import com.sjsu.finalproj.mymedico.repository.UserRepositoryInterface;
import com.yammer.metrics.annotation.Timed;

/**
 * @author mitikadia
 * 
 *  
 */

@Path("/v1/account")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {
	
	private final UserRepositoryInterface userRepository;
	
	public UserResource(UserRepositoryInterface userRepository) {
		this.userRepository = userRepository;
	}
	
	@POST
	@Path("/register")
	@Timed(name = "register-user")
	public Response registerUser(User request) {
		
		int responseCode = userRepository.registerUser(request);

		if(responseCode == 200) {
			return Response.status(200).build();
		}
		else if(responseCode == 501) {
			return Response.status(501).build();
		}
		else {
		return Response.status(500).build();
		}
	}
	
	@POST
	@Path("/login")
	@Timed(name = "login-user")
	public Response loginUser(User request) {
		
		int responseCode=500;
		try {
			responseCode = userRepository.loginUser(request);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(responseCode == 200) {
			return Response.status(200).build();
		}
		else {
		return Response.status(500).build();
		}
	}
	
	
	@POST
	@Path("/user/{emailId}")
	@Timed(name = "add-user-info")
	public Response addUserInfo(User request) {

		int responseCode = userRepository.addUserInfo(request);

		if(responseCode == 200) {
			return Response.status(200).build();
		}
		else {
		return Response.status(501).build();
		}
	}
	
	

	@PUT
	@Path("/user/{emailId}")
	@Timed(name = "update-user-info")
	public Response updateUserInfo(User request) {

		int responseCode = userRepository.updateUserInfo(request);

		if(responseCode == 200) {
			return Response.status(200).build();
		}
		else {
		return Response.status(501).build();
		}
	}
	@POST
	@Path("users")
	@Timed(name = "get-user-info")
	public Response getUserInfo(User request) {
	
		
		User response = userRepository.getUserInfo(request);
		UserDto userResponse = new UserDto(response);
		
		
		return Response.status(200).entity(userResponse).build();
		
		
	}
	
	@POST
	@Path("user/recommendation/{emailId}")
	@Timed(name = "get-user-recommendation")
	public Response getUserRecommendation(User request) {
		// Store the new book in the BookRepository so that we can retrieve it.
		Recommendation response = userRepository.getUserRecommendation(request);
		RecommendationDto recommendationResponse = new RecommendationDto(response);
			
		return Response.status(200).entity(recommendationResponse).build();
		
	}
	

	@POST
	@Path("user/bmicalculator")
	@Timed(name = "get-user-bmi")
	public Response getUserBmi(User request) {
		
		
		User response = userRepository.getUserBmi(request);
		UserDto userResponse = new UserDto(response);
		
		
		return Response.status(200).entity(userResponse).build();
		
	}
	
	@POST
	@Path("user/idealweightcalculator")
	@Timed(name = "get-user-bmi")
	public Response getUserIdealWeight(User request) {
		
		
		User response = userRepository.getUserIdealWeight(request);
		UserDto userResponse = new UserDto(response);
		
		
		return Response.status(200).entity(userResponse).build();
		
	}
	
	

}
