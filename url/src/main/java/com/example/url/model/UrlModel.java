package com.example.url.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class UrlModel {

	@Id
	@SequenceGenerator(name = "id", sequenceName = "id", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id")
	private int id;
	
	private String givenUrl;
	
	private String generatedUrl;
	
	private java.time.LocalDateTime currentDttm;
	
	private java.time.LocalDateTime expireDttm;
	
	private boolean deleteFl;

	public boolean isDeleteFl() {
		return deleteFl;
	}

	public void setDeleteFl(boolean deleteFl) {
		this.deleteFl = deleteFl;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGivenUrl() {
		return givenUrl;
	}

	public void setGivenUrl(String givenUrl) {
		this.givenUrl = givenUrl;
	}

	public String getGeneratedUrl() {
		return generatedUrl;
	}

	public void setGeneratedUrl(String generatedUrl) {
		this.generatedUrl = generatedUrl;
	}

	public java.time.LocalDateTime getCurrentDttm() {
		return currentDttm;
	}

	public void setCurrentDttm(java.time.LocalDateTime currentDttm) {
		this.currentDttm = currentDttm;
	}

	public java.time.LocalDateTime getExpireDttm() {
		return expireDttm;
	}

	public void setExpireDttm(java.time.LocalDateTime expireDttm) {
		this.expireDttm = expireDttm;
	}

	
}
