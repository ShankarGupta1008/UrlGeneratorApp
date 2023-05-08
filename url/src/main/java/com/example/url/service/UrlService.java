package com.example.url.service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.url.model.UrlModel;
import com.example.url.repository.UrlRepository;

@Service
public class UrlService {
	
	private static Log log = LogFactory.getLog(UrlService.class);
	
	@Autowired
	private UrlRepository urlRepository;
	
	private final String BASE_URL = "https://localhost:7070/";

	public UrlModel generateUrl(String givenUrl) {
		UrlModel urlModel = urlRepository.findByGivenUrlAndDeleteFl(givenUrl, Boolean.FALSE);
		
		ZoneId istZoneId = ZoneId.of("Asia/Kolkata");
		LocalDateTime currentDateTimeFormatted = getCurrentDateTime(LocalDateTime.now(), istZoneId);
		LocalDateTime newDateTimeFormatted = getExpireDateTime(LocalDateTime.now(), istZoneId);
		
        
		if(null == urlModel) {	//i.e either the given url had already been generated and expired or it has not yet created in db
			UrlModel entity = new UrlModel();
			String randomGeneratedUrl = shortenUrl(givenUrl);
			
			entity.setCurrentDttm(currentDateTimeFormatted);
			entity.setDeleteFl(false);
			entity.setGeneratedUrl(randomGeneratedUrl);
			entity.setGivenUrl(givenUrl);
			entity.setExpireDttm(newDateTimeFormatted);
			urlRepository.save(entity);
			
			return entity;
		}else {	//i.e, givenUrl has already have a generated random url and is not expired.
			urlModel.setCurrentDttm(currentDateTimeFormatted);
			urlModel.setExpireDttm(newDateTimeFormatted);
			urlRepository.save(urlModel);
			return urlModel;
		}
	}
	
	public UrlModel getMappedOriginalUrl(String generatedUrl) {
		log.info(generatedUrl);
		UrlModel urlModel = urlRepository.findByGeneratedUrlAndDeleteFl(generatedUrl, Boolean.FALSE);
		return urlModel;
	}
	
	public String shortenUrl(String url) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(url.getBytes());
            String shortHash = Base64.getUrlEncoder().encodeToString(hash).substring(0, 8);
            return BASE_URL + shortHash;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to compute hash", e);
        }
    }
	
	public java.time.LocalDateTime getCurrentDateTime(LocalDateTime currentDateTime, ZoneId istZoneId) {
		ZonedDateTime istCurrDateTime = currentDateTime.atZone(istZoneId);
		// Create a DateTimeFormatter object for the desired format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		// Format the current date time and new date time using the formatter
		String formattedCurrentDateTime = istCurrDateTime.format(formatter);
		LocalDateTime currentDateTimeFormatted = LocalDateTime.parse(formattedCurrentDateTime, formatter);
		
		return currentDateTimeFormatted;
	}
	
	public java.time.LocalDateTime getExpireDateTime(LocalDateTime currentDateTime, ZoneId istZoneId){
		LocalDateTime newDateTime = currentDateTime.plusMinutes(5);
	    ZonedDateTime istNewDateTime = newDateTime.atZone(istZoneId);
	    
	    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    
	    String formattedNewDateTime = istNewDateTime.format(formatter);
	    LocalDateTime newDateTimeFormatted = LocalDateTime.parse(formattedNewDateTime, formatter);
	    return newDateTimeFormatted;
	}
}
