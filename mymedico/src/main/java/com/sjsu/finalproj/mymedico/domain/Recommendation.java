/**
 * 
 */
package com.sjsu.finalproj.mymedico.domain;

/**
 * @author mitikadia
 *
 */
public class Recommendation {

	/**
	 * 
	 */
	public Recommendation() {
		// TODO Auto-generated constructor stub
	}

	private long recommendation_id;

	private int diabetes;
	private int obesity;
	private int hyperTension;
	private int healthyProfile;
	private int unhealthyProfile;
	
	private int diabetesBand;
	private int obesityBand;
	private int hyperTensionBand;
	private int healthyProfileBand;
	private int unhealthyProfileBand;
	
	public long getRecommendation_id() {
		return recommendation_id;
	}
	
	
	public void setRecommendation_id(long recommendation_id) {
		this.recommendation_id = recommendation_id;
	}
	public int getDiabetes() {
		return diabetes;
	}
	public void setDiabetes(int diabetes) {
		this.diabetes = diabetes;
	}

	public int getHyperTension() {
		return hyperTension;
	}
	public void setHyperTension(int hyperTension) {
		this.hyperTension = hyperTension;
	}
	public int getHealthyProfile() {
		return healthyProfile;
	}
	public void setHealthyProfile(int healthyProfile) {
		this.healthyProfile = healthyProfile;
	}
	public int getDiabetesBand() {
		return diabetesBand;
	}
	public void setDiabetesBand(int diabetesBand) {
		this.diabetesBand = diabetesBand;
	}

	public int getHyperTensionBand() {
		return hyperTensionBand;
	}
	public void setHyperTensionBand(int hyperTensionBand) {
		this.hyperTensionBand = hyperTensionBand;
	}
	public int getHealthyProfileBand() {
		return healthyProfileBand;
	}
	public void setHealthyProfileBand(int healthyProfileBand) {
		this.healthyProfileBand = healthyProfileBand;
	}


	public int getObesity() {
		return obesity;
	}


	public void setObesity(int obesity) {
		this.obesity = obesity;
	}


	public float getObesityBand() {
		return obesityBand;
	}


	public void setObesityBand(int f) {
		this.obesityBand = f;
	}


	public int getUnhealthyProfile() {
		return unhealthyProfile;
	}


	public void setUnhealthyProfile(int unhealthyProfile) {
		this.unhealthyProfile = unhealthyProfile;
	}


	public int getUnhealthyProfileBand() {
		return unhealthyProfileBand;
	}


	public void setUnhealthyProfileBand(int unhealthyProfileBand) {
		this.unhealthyProfileBand = unhealthyProfileBand;
	}

	
}
