/**
 * 
 */
package com.sjsu.finalproj.mymedico.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sjsu.finalproj.mymedico.domain.Recommendation;
import com.sjsu.finalproj.mymedico.domain.User;
import com.sjsu.finalproj.mymedico.domain.dbUser;
import com.sjsu.finalproj.mymedico.domain.dbUserObjectMapper;
import com.sjsu.finalproj.mymedico.recommender.Recommender;
import java.util.Random;

/**
 * @author mitikadia
 *
 */
public class UserRepository implements UserRepositoryInterface{
	
	
	@Override
	public int registerUser(User request){
	
		String url = "jdbc:mysql://meddbinstance.cozrev6gggap.us-west-1.rds.amazonaws.com:3306/";
		String userName ="CMPE295B";
		String password = "rememberme";
		String dbName = "mitidb";
		String driver = "com.mysql.jdbc.Driver";
		int gender = 0;
		int id=0;
		Random rand = new Random();
		 id= rand.nextInt();
		if(request.getGender() == "Male" )
			gender=1;
		if(request.getGender() == "Female" )
			gender=2;
			
		 try {
			    System.out.println("Loading driver...");
			    Class.forName("com.mysql.jdbc.Driver");
			    System.out.println("Driver loaded!");
			  } catch (ClassNotFoundException e) {
			    throw new RuntimeException("Cannot find the driver in the classpath!", e);
			  }
		try {
			Connection connection = DriverManager.getConnection(url + dbName, userName, password);
			
			Statement statement = connection.createStatement();
			statement.executeUpdate("INSERT INTO recommender_data (`id`,`first_name`,`last_name`,`email_id`,"+
						"`pwd`,`Gender`) " + "VALUES ("+id+", '"+request.getFirstName()+"', '"+request.getLastName()+"', '"+request.getEmailId()+"', '"+request.getPwd()+"','"+gender+"')");
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
	
		
		return 200;
	}
	
	@Override
	public int loginUser(User request) throws SQLException{
		String url = "jdbc:mysql://meddbinstance.cozrev6gggap.us-west-1.rds.amazonaws.com:3306/";
		String userName ="CMPE295B";
		String password = "rememberme";
		String dbName = "mitidb";
		String driver = "com.mysql.jdbc.Driver";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int rowcount=0;
		try {
			Connection connection = DriverManager.getConnection(url + dbName, userName, password);	
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery("Select * from recommender_data where email_id='"+request.getEmailId()+"'and pwd='"+request.getPwd()+"'");
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(resultSet.next() == true)
		{
			return 200;
		}
		else {
			return 500;
		}
		
	}

	@Override
	public int addUserInfo(User request) {
		
		dbUser dbuser = new dbUser();
		dbUserObjectMapper dbuserobjectmapper = new dbUserObjectMapper();
		
		User temp=new User();
		temp= getUserBmi(request);	
		request.setBmi(temp.getBmi());
		
		dbuser = dbuserobjectmapper.mapFromUserToDbUser(request);
		
	/*	String insertQueryString = "INSERT INTO `recommender_data` (`id`,`first_name`,`last_name`,`email_id`,"+
						"`pwd`,`Gender`,`Race`,`BMI`,`Age`,`Eating_Habits`,`Sleeping_Habits`,`Activity_Level`,"+
						"`Smoking_Habits`,`Drinking_Habits`,`Caffeine_Intake`,`BP_Level`,`Cholestrol_Level`,"+
						"`Sugar_Level`,`Haemoglobin_Content`,`Family_Hypertension`,`Family_Obesity`,`Family_Diabetes`,"+
						"`States`) VALUES ("+ dbuser.getId()+",\""+dbuser.getFirstName()+"\",\""+
						dbuser.getLastName()+"\",\""+dbuser.getEmailId()+"\",\""+dbuser.getPwd()+"\","+
						dbuser.getGender()+","+dbuser.getRace()+","+dbuser.getBmi()+","+ dbuser.getAge()+
						","+ dbuser.getEatingHabits()+","+dbuser.getSleepingHabits()+","+ dbuser.getActivityLevel()+
						","+dbuser.getSmokingHabits()+","+dbuser.getDrinkingHabits()+","+dbuser.getCaffeineHabits()+","+
						dbuser.getBPLevel()+","+dbuser.getCholestrolLevel()+","+dbuser.getSugarLevel()+","+dbuser.getHaemoglobinContent()+
						","+dbuser.getFamilyHypertension()+","+dbuser.getFamilyObesity()+","+dbuser.getFamilyDiabetes()+",\""+dbuser.getStates()+"\")";*/
		
		
		
		String insertQueryString = "UPDATE `recommender_data` SET"+
				"`first_name` = '"+dbuser.getFirstName()+"',"+
				"`last_name` = '"+ dbuser.getLastName()+"',"+
				"`email_id` = '"+ dbuser.getEmailId()+"',"+
				"`pwd` = '"+ dbuser.getPwd()+"',"+
				"`Gender` = "+dbuser.getGender()+","+
				"`Race` = "+ dbuser.getRace()+","+
				"`BMI` = "+ dbuser.getBmi()+","+
				"`Age` = "+dbuser.getAge()+","+
				"`Eating_Habits` = "+dbuser.getEatingHabits()+","+
				"`Sleeping_Habits` = "+dbuser.getSleepingHabits()+","+
				"`Activity_Level` = "+dbuser.getActivityLevel()+","+
				"`Smoking_Habits` = "+ dbuser.getSmokingHabits()+","+
				"`Drinking_Habits` ="+dbuser.getDrinkingHabits()+","+
				"`Caffeine_Intake` ="+dbuser.getCaffeineHabits()+","+
				"`BP_Level` = "+dbuser.getBPLevel()+","+
				"`Cholestrol_Level` = "+dbuser.getCholestrolLevel()+","+
				"`Sugar_Level` = "+dbuser.getSugarLevel()+","+
				"`Haemoglobin_Content` = "+dbuser.getHaemoglobinContent()+","+
				"`Family_Hypertension` = "+ dbuser.getFamilyHypertension()+","+
				"`Family_Obesity` = "+ dbuser.getFamilyObesity()+","+
				"`Family_Diabetes` ="+dbuser.getFamilyDiabetes()+","+ 
				"`States` ='"+ dbuser.getStates()+"'"+
				" WHERE "+ "`id` = "+ dbuser.getId();
		
		String url = "jdbc:mysql://meddbinstance.cozrev6gggap.us-west-1.rds.amazonaws.com:3306/";
		String userName ="CMPE295B";
		String password = "rememberme";
		String dbName = "mitidb";
		String driver = "com.mysql.jdbc.Driver";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int rowcount=0;
		try {
			Connection connection = DriverManager.getConnection(url + dbName, userName, password);	
			Statement statement = connection.createStatement();
			rowcount = statement.executeUpdate(insertQueryString);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(rowcount > 0)
		{
			return 200;
		}
		else {
			return 500;
		}
	}

	@Override
	public int updateUserInfo(User request) {
		
		dbUser dbuser = new dbUser();
		dbUserObjectMapper dbuserobjectmapper = new dbUserObjectMapper();
		
		User temp=new User();
		temp= getUserBmi(request);	
		request.setBmi(temp.getBmi());
		
		dbuser = dbuserobjectmapper.mapFromUserToDbUser(request);

		String updateQueryString = "UPDATE `recommender_data` SET"+
				"`first_name` = '"+dbuser.getFirstName()+"',"+
				"`last_name` = '"+ dbuser.getLastName()+"',"+
				"`email_id` = '"+ dbuser.getEmailId()+"',"+
				"`pwd` = '"+ dbuser.getPwd()+"',"+
				"`Gender` = "+dbuser.getGender()+","+
				"`Race` = "+ dbuser.getRace()+","+
				"`BMI` = "+ dbuser.getBmi()+","+
				"`Age` = "+dbuser.getAge()+","+
				"`Eating_Habits` = "+dbuser.getEatingHabits()+","+
				"`Sleeping_Habits` = "+dbuser.getSleepingHabits()+","+
				"`Activity_Level` = "+dbuser.getActivityLevel()+","+
				"`Smoking_Habits` = "+ dbuser.getSmokingHabits()+","+
				"`Drinking_Habits` ="+dbuser.getDrinkingHabits()+","+
				"`Caffeine_Intake` ="+dbuser.getCaffeineHabits()+","+
				"`BP_Level` = "+dbuser.getBPLevel()+","+
				"`Cholestrol_Level` = "+dbuser.getCholestrolLevel()+","+
				"`Sugar_Level` = "+dbuser.getSugarLevel()+","+
				"`Haemoglobin_Content` = "+dbuser.getHaemoglobinContent()+","+
				"`Family_Hypertension` = "+ dbuser.getFamilyHypertension()+","+
				"`Family_Obesity` = "+ dbuser.getFamilyObesity()+","+
				"`Family_Diabetes` ="+dbuser.getFamilyDiabetes()+","+ 
				"`States` ='"+ dbuser.getStates()+"'"+
				" WHERE "+ "`id` = "+ dbuser.getId();
		
		String url = "jdbc:mysql://meddbinstance.cozrev6gggap.us-west-1.rds.amazonaws.com:3306/";
		String userName ="CMPE295B";
		String password = "rememberme";
		String dbName = "mitidb";
		String driver = "com.mysql.jdbc.Driver";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		int rowcount=0;
		try {
			Connection connection = DriverManager.getConnection(url + dbName, userName, password);	
			Statement statement = connection.createStatement();
			rowcount = statement.executeUpdate(updateQueryString);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(rowcount > 0)
		{
			return 200;
		}
		else {
			return 500;
		}

	}

	@Override
	public User getUserInfo(User request) {
		
		dbUser dbuser = new dbUser();
		dbUserObjectMapper dbuserobjectmapper = new dbUserObjectMapper();
		
		String url = "jdbc:mysql://meddbinstance.cozrev6gggap.us-west-1.rds.amazonaws.com:3306/";
		String userName ="CMPE295B";
		String password = "rememberme";
		String dbName = "mitidb";
		String driver = "com.mysql.jdbc.Driver";
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		User response = new User();
		try {
			Connection connection = DriverManager.getConnection(url + dbName, userName, password);	
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery("Select * from users where emailId='"+request.getEmailId()+"'");
			 while (resultSet.next()) {
				 
				 // Create the user object. 

				 dbuser.setId(resultSet.getInt("id"));
				 dbuser.setFirstName(resultSet.getString("firstName"));
				 dbuser.setLastName(resultSet.getString("lastName"));
				 dbuser.setEmailId(request.getEmailId());
				 dbuser.setGender(resultSet.getInt("gender"));
				 dbuser.setRace(resultSet.getInt("race"));
				 dbuser.setHeight(resultSet.getFloat("height"));
				 dbuser.setWeight(resultSet.getFloat("weight"));
				 dbuser.setBmi(resultSet.getInt("bmi"));
				 dbuser.setAge(resultSet.getInt("age"));
				 dbuser.setEatingHabits(resultSet.getInt("eatingHabits"));
				 dbuser.setSleepingHabits(resultSet.getInt("sleepingHabits"));
				 dbuser.setActivityLevel(resultSet.getInt("activityLevel"));
				 dbuser.setSmokingHabits(resultSet.getInt("smokingHabits"));
				 dbuser.setDrinkingHabits(resultSet.getInt("drinkingHabits"));
				 dbuser.setCaffeineHabits(resultSet.getInt("caffeineHabits"));
				 dbuser.setBPLevel(resultSet.getInt("bpLevel"));
				 dbuser.setCholestrolLevel(resultSet.getInt("cholestrolLevel"));
				 dbuser.setSugarLevel(resultSet.getInt("sugarLevel"));
				 dbuser.setHaemoglobinContent(resultSet.getInt("haemoglobinContent"));
				 dbuser.setFamilyHypertension(resultSet.getInt("familyHypertension"));
				 dbuser.setFamilyObesity(resultSet.getInt("familyObesity"));
				 dbuser.setFamilyDiabetes(resultSet.getInt("familyDiabetes"));
				 dbuser.setStates(resultSet.getString("states"));
				 
				 
				 
							 
			 }
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		if(dbuser.getEmailId() != null)
		{
			
			response = dbuserobjectmapper.mapFromDbUserToUser(dbuser);
			return response;
		}
		else {
			return null;
		}
	}

	@Override
	public Recommendation getUserRecommendation(User request) {
		// TODO Auto-generated method stub
		Recommender recommenderObject = new Recommender();
		Recommendation recommendationObject = new Recommendation();
		int recommendationId=0;
		
		try {
			recommendationId = recommenderObject.recommender_function(request.getEmailId());
					
		} catch (Exception e) {

			e.printStackTrace();
		}
		
		recommendationObject.setRecommendation_id(recommendationId);
		
		dbUser dbuser = new dbUser();
		
		String url = "jdbc:mysql://meddbinstance.cozrev6gggap.us-west-1.rds.amazonaws.com:3306/";
		String userName ="CMPE295B";
		String password = "rememberme";
		String dbName = "mitidb";
		String driver = "com.mysql.jdbc.Driver";
		ResultSet resultSet = null;

		try {
			Connection connection = DriverManager.getConnection(url + dbName, userName, password);	
			Statement statement = connection.createStatement();
			resultSet = statement.executeQuery("Select * from users where emailId='"+request.getEmailId()+"'");
			 while (resultSet.next()) {
				 
				 dbuser.setId(resultSet.getInt("id"));
				 dbuser.setFirstName(resultSet.getString("firstName"));
				 dbuser.setLastName(resultSet.getString("lastName"));
				 dbuser.setEmailId(request.getEmailId());
				 dbuser.setGender(resultSet.getInt("gender"));
				 dbuser.setRace(resultSet.getInt("race"));
				 dbuser.setHeight(resultSet.getFloat("height"));
				 dbuser.setWeight(resultSet.getFloat("weight"));
				 dbuser.setBmi(resultSet.getInt("bmi"));
				 dbuser.setAge(resultSet.getInt("age"));
				 dbuser.setEatingHabits(resultSet.getInt("eatingHabits"));
				 dbuser.setSleepingHabits(resultSet.getInt("sleepingHabits"));
				 dbuser.setActivityLevel(resultSet.getInt("activityLevel"));
				 dbuser.setSmokingHabits(resultSet.getInt("smokingHabits"));
				 dbuser.setDrinkingHabits(resultSet.getInt("drinkingHabits"));
				 dbuser.setCaffeineHabits(resultSet.getInt("caffeineHabits"));
				 dbuser.setBPLevel(resultSet.getInt("bpLevel"));
				 dbuser.setCholestrolLevel(resultSet.getInt("cholestrolLevel"));
				 dbuser.setSugarLevel(resultSet.getInt("sugarLevel"));
				 dbuser.setHaemoglobinContent(resultSet.getInt("haemoglobinContent"));
				 dbuser.setFamilyHypertension(resultSet.getInt("familyHypertension"));
				 dbuser.setFamilyObesity(resultSet.getInt("familyObesity"));
				 dbuser.setFamilyDiabetes(resultSet.getInt("familyDiabetes"));
				 dbuser.setStates(resultSet.getString("states"));
				 						 
			 }
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		recommendationObject.setDiabetesBand(dbuser.getSugarLevel());
	 	recommendationObject.setObesityBand(dbuser.getBmi());
		recommendationObject.setHyperTensionBand(dbuser.getBPLevel());
		
		// get from recommender db
		
		  String jdbc_url="jdbc:mysql://meddbinstance.cozrev6gggap.us-west-1.rds.amazonaws.com/sharanyadb";
		  String jdbc_username="CMPE295B"; 
		  String jdbc_password="rememberme";
		  String jdbc_driver="com.mysql.jdbc.Driver"; 
		  Connection connection;
		try {
			connection = DriverManager.getConnection(url , userName, password);
			  Statement statement1 = connection.createStatement();
				resultSet = statement1.executeQuery("Select * from recommender where id='"+recommendationId+"'");
				 while (resultSet.next()) {
					 
					 dbuser.setId(resultSet.getInt("id"));
					 recommendationObject.setDiabetes(resultSet.getInt("daibetese"));
					 recommendationObject.setObesity(resultSet.getInt("obesity"));
					 recommendationObject.setHyperTension(resultSet.getInt("hypertension"));
					 recommendationObject.setHealthyProfile(resultSet.getInt("healthy"));
					
					 						 
				 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
		
		
		return recommendationObject;
	}

	@Override
	public User getUserBmi(User request) {
		// Formula: weight (lb) / [height (in)]2 x 703
		//Underweight = <18.5
		//Normal weight = 18.5–24.9 
		//Overweight = 25–29.9 
		//Obesity = BMI of 30 or greater
		// TODO Auto-generated method stub
		
		float weight = Float.parseFloat(request.getWeight());
		float height = Float.parseFloat(request.getHeight());
		
		float bmi;
		bmi = (weight)/(height*height) * 703 ;
		request.setBmi(""+ bmi);
		return request;
	}

	@Override
	public User getUserIdealWeight(User request) {
		// TODO Auto-generated method stub
		//52 kg + 1.9 kg per inch over 5 feet       (man)
		//49 kg + 1.7 kg per inch over 5 feet       (woman)
		String gender = request.getGender();
		float height = Float.parseFloat(request.getHeight());
		float decimalHeight = height - (int)height;
		float weight = 0;
		
		if(gender=="Female") {
			weight = (float) (49 + (1.7 *decimalHeight));		
		}
		else if(gender == "Male") {
			weight = (float) (52 + (1.9 *decimalHeight));
		}
		weight = (float) (weight * 2.204);
		request.setWeight(""+weight);
		
		return request;
	}
	
	public long generateIds() {
		
		
		
		return 0;
	}

}
