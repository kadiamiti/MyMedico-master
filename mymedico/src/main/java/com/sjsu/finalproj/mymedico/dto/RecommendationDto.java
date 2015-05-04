package com.sjsu.finalproj.mymedico.dto;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sjsu.finalproj.mymedico.domain.Recommendation;

@JsonPropertyOrder(alphabetic = true)

public class RecommendationDto {

	public RecommendationDto(Recommendation recommendation) {
		super();
		this.setRecommendation(recommendation);
	}
	
	

	public Recommendation getRecommendation() {
		return recommendation;
	}



	public void setRecommendation(Recommendation recommendation) {
		this.recommendation = recommendation;
	}



	private Recommendation recommendation;

	

}
