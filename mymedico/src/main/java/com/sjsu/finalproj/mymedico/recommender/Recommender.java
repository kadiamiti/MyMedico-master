package com.sjsu.finalproj.mymedico.recommender;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

public class Recommender {
	 public static String jdbc_url="jdbc:mysql://meddbinstance.cozrev6gggap.us-west-1.rds.amazonaws.com/sharanyadb";
	  public static String jdbc_username="CMPE295B"; 
	  public static  String jdbc_password="rememberme";
	  public static String jdbc_driver="com.mysql.jdbc.Driver"; 
	
	public int recommender_function(String email_id) throws Exception	{
		 Class.forName(jdbc_driver);
		 Connection con=DriverManager.getConnection(jdbc_url, jdbc_username,jdbc_password);
		 Statement stmt = null;
		 stmt = con.createStatement();
		 Statement stmt1 = null;
		 stmt1 = con.createStatement();
		 Scanner scan = new Scanner(System.in);
		 int j = scan.nextInt();
		 int min =Integer.MAX_VALUE;
   	  	 int id = Integer.MAX_VALUE;
		  ResultSet rs = stmt.executeQuery("select BMI,Age,Eating_Habits,Sleeping_Habits,Activity_Level,Smoking_Habits,Drinking_Habits,Caffeine_Intake,BP_Level,Cholestrol_Level,Sugar_Level,Haemoglobin_Content,Family_Hypertension,Family_Obesity,Family_Diabetes from recommender_data where email_id = "+email_id);
    	  rs.next();
    	  rs.getInt("BMI");
    	  int mean_array[][] = new int[4][3];
    	  int mean_array_sort[][] = new int[4][3];
    	  for(int i=0;i<4;i++)	{
    		  ResultSet rs1 = stmt1.executeQuery("select BMI,Age,Eating_Habits,Sleeping_Habits,Activity_Level,Smoking_Habits,Drinking_Habits,Caffeine_Intake,BP_Level,Cholestrol_Level,Sugar_Level,Haemoglobin_Content,Family_Hypertension,Family_Obesity,Family_Diabetes from centroids where id = "+i);
        	  rs1.next();
        	  rs1.getInt("BMI");
        	  int mean = Math.abs((rs.getInt("BMI")-rs1.getInt("BMI"))+(rs.getInt("Age")-rs1.getInt("Age"))+(rs.getInt("Eating_Habits")-rs1.getInt("Eating_Habits"))+(rs.getInt("Sleeping_Habits")-rs1.getInt("Sleeping_Habits"))+(rs.getInt("Activity_Level")-rs1.getInt("Activity_Level"))+(rs.getInt("Smoking_Habits")-rs1.getInt("Smoking_Habits"))+(rs.getInt("Drinking_Habits")-rs1.getInt("Drinking_Habits"))+(rs.getInt("Caffeine_Intake")-rs1.getInt("Caffeine_Intake"))+(rs.getInt("BP_Level")-rs1.getInt("BP_Level"))+(rs.getInt("Cholestrol_Level")-rs1.getInt("Cholestrol_Level"))+(rs.getInt("Sugar_Level")-rs1.getInt("Sugar_Level"))+(rs.getInt("Haemoglobin_Content")-rs1.getInt("Haemoglobin_Content"))+(rs.getInt("Family_Hypertension")-rs1.getInt("Family_Hypertension"))+(rs.getInt("Family_Obesity")-rs1.getInt("Family_Obesity"))+(rs.getInt("Family_Diabetes")-rs1.getInt("Family_Diabetes")));
        	  mean_array[i][0] = mean;
        	  mean_array[i][1] = i+1;
        	  mean_array[i][2] = 0;
        //	  System.out.println("mean"+mean_array[i][0]+" , "+mean_array[i][1]);
        	  if(min>mean)	{
        		  min = mean;
        		  id = i;
        	  }
    	  }
    	  for(int i=0;i<4;i++)	{
    		  mean_array_sort[i][0] = mean_array[i][0];
    		  mean_array_sort[i][1] = mean_array[i][1];
    		  mean_array_sort[i][2] = mean_array[i][2];
    	  }
    	  for(int i=0;i<4;i++)	{
    		  for(int k=i+1;k<4;k++)	{
    			  if(mean_array_sort[i][0]>mean_array_sort[k][0])	{
    				  int temp = mean_array_sort[i][0];
    				  int temp1 = mean_array_sort[i][1];
    				  mean_array_sort[i][0] = mean_array_sort[k][0];
    				  mean_array_sort[i][1] = mean_array_sort[k][1];
    				  mean_array_sort[k][0] = temp;
    				  mean_array_sort[k][1] = temp1;
    			  }	  
    		  }
    	  }
    	
    	  
    	  int row_count =0,priority = 1;
    	  while(mean_array_sort[row_count][1] != 4)	{
    		  mean_array_sort[row_count][2] = priority;
    		  priority++;
    		  row_count++;
    	  }
    	 
    	  mean_array_sort[row_count][2] = priority;
    	  if(mean_array_sort[row_count][0] == min)	{
    		  mean_array_sort[row_count][2] = 1;
    	  }
    	  
    	 // for(int i=0;i<4;i++)	{
      		//  System.out.println("mean sort "+mean_array_sort[i][0]+","+mean_array_sort[i][1]+","+mean_array_sort[i][2]);
        	 // }
    	  
    	  for(int i=0;i<4;i++)	{
    		  if(mean_array_sort[i][1] == 1)
    			  mean_array[0][2] = mean_array_sort[i][2];
    		  else if(mean_array_sort[i][1] == 2)
    			  mean_array[1][2] = mean_array_sort[i][2];
    		  if(mean_array_sort[i][1] == 3)
    			  mean_array[2][2] = mean_array_sort[i][2];
    		  if(mean_array_sort[i][1] == 4)
    			  mean_array[3][2] = mean_array_sort[i][2];
    	  }
    	  
    	  for(int i=0;i<4;i++)	{
  		  System.out.println("mean1"+mean_array[i][0]+","+mean_array[i][1]+","+mean_array[i][2]);
    	  }
    	  int recommender_id=0;
    	  Random rand = new Random();
    	  recommender_id= rand.nextInt();
    	  Statement stmt2 = con.createStatement();
    	  stmt2.executeUpdate("insert into sharanyadb.recommend(id,email_id,diabetese,hypertension,obesity,healthy) values("+recommender_id+"'"+email_id+"',"+mean_array[0][2]+","+mean_array[2][2]+","+mean_array[1][2]+","+mean_array[3][2]+")");
    	//  id++;
    	  //ResultSet rs2 = stmt.executeQuery("select recommend from recommendation where id = "+id);
    	  //rs2.next();
    	  //System.out.println(rs2.getString("recommend"));
    	
    	  return recommender_id;
	}
}
