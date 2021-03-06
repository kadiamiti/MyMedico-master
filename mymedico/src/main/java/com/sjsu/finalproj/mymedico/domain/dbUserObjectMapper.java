/**
 * 
 */
package com.sjsu.finalproj.mymedico.domain;
import com.sjsu.finalproj.mymedico.domain.dbUser;

/**
 * @author mitikadia
 *
 *Map the user object with the db user object and vice versa
 *0-2 infant
 *3-12 junior
 *13-19 young adult
 *20-50 adult
 *51-75 senior
 *75+ Elderly
 */
public class dbUserObjectMapper {
	
	
	public dbUser mapFromUserToDbUser(User userObj){
		
		dbUser dbuserObj = new dbUser();
		
		dbuserObj.setEmailId(userObj.getEmailId());
		dbuserObj.setId(userObj.getId());
		dbuserObj.setFirstName(userObj.getFirstName());
		dbuserObj.setLastName(userObj.getLastName());
		dbuserObj.setPwd(userObj.getPwd());
		
		if(userObj.getGender().equalsIgnoreCase("Male"))
			dbuserObj.setGender(1);
		if(userObj.getGender().equalsIgnoreCase("Female") )
			dbuserObj.setGender(2);
		System.out.println("check1");
		if(userObj.getRace().equalsIgnoreCase("African Americans") )
			dbuserObj.setRace(1);
		if(userObj.getRace().equalsIgnoreCase("Hispanics" ))
			dbuserObj.setRace(2);
		if(userObj.getRace().equalsIgnoreCase("Asians") )
			dbuserObj.setRace(3);
		if(userObj.getRace().equalsIgnoreCase("Caucasians") )
			dbuserObj.setRace(4);
		if(userObj.getRace().equalsIgnoreCase("Latin Americans") )
			dbuserObj.setRace(5);
		if(userObj.getRace().equalsIgnoreCase("Native Americans") )
			dbuserObj.setRace(6);
		if(userObj.getRace().equalsIgnoreCase("Others") )
			dbuserObj.setRace(7);
		System.out.println("check2");
/*		
		float height = Float.parseFloat(userObj.getHeight());
		float decimalHeight = height - (int)height;
		int intHeight = (int)height;
		decimalHeight = (float) (decimalHeight * 2.54);
		decimalHeight = (float) (decimalHeight + ((float)intHeight *30.48));
		dbuserObj.setHeight(decimalHeight);*/
		
		
		
		System.out.println("check3");
		dbuserObj.setHeight(Float.parseFloat(userObj.getHeight()));
		dbuserObj.setWeight(Float.parseFloat(userObj.getWeight()));
		
		System.out.println("check4");
		float tempBMI =0;
		int tempdbBMI=0;
		tempBMI = Float.parseFloat(userObj.getBmi());
		if (tempBMI < 18.5){
			tempdbBMI = 1;	
		}
		else if (tempBMI >= 18.5 && tempBMI <= 24.9) {
			tempdbBMI = 2;
		}
		else if (tempBMI >= 25 && tempBMI <=29.9) {
			tempdbBMI = 3;
		}
		else if (tempBMI >= 30 ) {
			tempdbBMI = 4;
		}
		
		dbuserObj.setBmi(tempdbBMI);
		
		dbuserObj.setAge(Integer.parseInt(userObj.getAge()));
		
		// set eating habits after ranges from sharanya		
		dbuserObj.setEatingHabits(Integer.parseInt(userObj.getEatingHabits()));
		
		
		System.out.println("check5");
		
		String stringSleeping = userObj.getSleepingHabits();		
		dbuserObj.setSleepingHabits(Integer.parseInt(stringSleeping));
		
		String activityLevels = userObj.getActivityLevel();		
		dbuserObj.setActivityLevel(Integer.parseInt(activityLevels));
		
		String smokingHabit = userObj.getSmokingHabits();		
		dbuserObj.setSmokingHabits(Integer.parseInt(smokingHabit));
		
		String drinkingHabits = userObj.getDrinkingHabits();		
		dbuserObj.setDrinkingHabits(Integer.parseInt(drinkingHabits));

		System.out.println("check6");
		String caffeineHabits = userObj.getCaffeineHabits();		
		dbuserObj.setCaffeineHabits(Integer.parseInt(caffeineHabits));
		
		String BPLevel = userObj.getBloodpressureLevel();	
		dbuserObj.setBloodpressureLevel(Integer.parseInt(BPLevel));
	//	String BPLevel = "1";
	//	dbuserObj.setBPLevel(Integer.parseInt(BPLevel));
		
		
		String cholestrolLevel = userObj.getCholestrolLevel();		
		dbuserObj.setCholestrolLevel(Integer.parseInt(cholestrolLevel));
		System.out.println("check7");
		String sugarLevel = userObj.getSugarLevel();		
		dbuserObj.setSugarLevel(Integer.parseInt(sugarLevel));
		
		String haemoglobinContent = userObj.getHaemoglobinContent();		
		dbuserObj.setHaemoglobinContent(Integer.parseInt(haemoglobinContent));
		
		String familyHypertension = userObj.getFamilyHypertension();		
		dbuserObj.setFamilyHypertension(Integer.parseInt(familyHypertension));
		System.out.println("check8");
		String familyObesity = userObj.getFamilyObesity();		
		dbuserObj.setFamilyObesity(Integer.parseInt(familyObesity));
		
		String familyDiabetes = userObj.getFamilyDiabetes();		
		dbuserObj.setFamilyDiabetes(Integer.parseInt(familyDiabetes));
		
		dbuserObj.setStates(userObj.getStates());
			
		System.out.println("check9");
		return dbuserObj;
	}

	public User mapFromDbUserToUser(dbUser dbuserObj){
		

		User userObj = new User();
		
		userObj.setEmailId(dbuserObj.getEmailId());
		userObj.setId(dbuserObj.getId());
		userObj.setFirstName(dbuserObj.getFirstName());
		userObj.setLastName(dbuserObj.getLastName());
		userObj.setPwd(dbuserObj.getPwd());
		
		if(dbuserObj.getGender() == 1 )
			userObj.setGender("Male");
		if(dbuserObj.getGender() == 2 )
			userObj.setGender("Female");
		
		if(dbuserObj.getRace() == 1 )
			userObj.setRace("African Americans");
		if(dbuserObj.getRace() ==  2)
			userObj.setRace("Hispanics");
		if(dbuserObj.getRace() == 3 )
			userObj.setRace("Asians");
		if(dbuserObj.getRace() == 4)
			userObj.setRace("Caucasians" );
		if(dbuserObj.getRace() == 5 )
			userObj.setRace("Latin Americans");
		if(dbuserObj.getRace() == 6 )
			userObj.setRace("Native Americans");
		if(dbuserObj.getRace() == 7)
			userObj.setRace("Others" );
		
		
		float height = dbuserObj.getHeight();
/*		float decimalHeight = height - (int)height;
		int intHeight = (int)height;
		decimalHeight = (float) (decimalHeight * 2.54);
		decimalHeight = (float) (decimalHeight + ((float)intHeight *30.48));*/
		userObj.setHeight(""+height);
		
		userObj.setWeight(""+dbuserObj.getWeight());
		
		userObj.setBmi(""+dbuserObj.getBmi());
		
		userObj.setAge(""+dbuserObj.getAge());
		
		
		userObj.setEatingHabits(""+userObj.getEatingHabits());
	
		
		int stringSleeping = dbuserObj.getSleepingHabits();		
		userObj.setSleepingHabits(""+stringSleeping);
		
		int activityLevels = dbuserObj.getActivityLevel();		
		userObj.setActivityLevel(""+activityLevels);
		
		int smokingHabit = dbuserObj.getSmokingHabits();		
		userObj.setSmokingHabits(""+smokingHabit);
		
		int drinkingHabits = dbuserObj.getDrinkingHabits();		
		userObj.setDrinkingHabits(""+drinkingHabits);
	
		
		int caffeineHabits = dbuserObj.getCaffeineHabits();		
		userObj.setCaffeineHabits(""+caffeineHabits);
		
		int BPLevel = dbuserObj.getBloodpressureLevel();		
		userObj.setBloodpressureLevel(""+BPLevel);
		
		
		int cholestrolLevel = dbuserObj.getCholestrolLevel();		
		userObj.setCholestrolLevel(""+cholestrolLevel);
		
		int sugarLevel = dbuserObj.getSugarLevel();		
		userObj.setSugarLevel(""+sugarLevel);
		
		int haemoglobinContent = dbuserObj.getHaemoglobinContent();		
		userObj.setHaemoglobinContent(""+haemoglobinContent);
		
		int familyHypertension = dbuserObj.getFamilyHypertension();		
		userObj.setFamilyHypertension(""+familyHypertension);
		
		int familyObesity = dbuserObj.getFamilyObesity();		
		userObj.setFamilyObesity(""+familyObesity);
		
		int familyDiabetes = dbuserObj.getFamilyDiabetes();		
		userObj.setFamilyDiabetes(""+familyDiabetes);
		
		userObj.setStates(dbuserObj.getStates());
			
		
		return userObj;

	}

}
