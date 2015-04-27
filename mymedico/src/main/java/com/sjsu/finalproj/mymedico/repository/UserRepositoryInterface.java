package com.sjsu.finalproj.mymedico.repository;

import java.sql.SQLException;

import com.sjsu.finalproj.mymedico.domain.Recommendation;
import com.sjsu.finalproj.mymedico.domain.User;

public interface UserRepositoryInterface {

	int registerUser(User request);

	int loginUser(User request) throws SQLException;

	int addUserInfo(User request);

	int updateUserInfo(User request);

	User getUserInfo(User request);

	Recommendation getUserRecommendation(User request);

	User getUserBmi(User request);

	User getUserIdealWeight(User request);
	
	

}
