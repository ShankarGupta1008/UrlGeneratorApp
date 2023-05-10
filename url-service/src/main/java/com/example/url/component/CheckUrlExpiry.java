package com.example.url.component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.url.model.UrlModel;
import com.example.url.repository.UrlRepository;

@Component
public class CheckUrlExpiry {
	
	@Autowired
	private UrlRepository urlRepository;

	@Scheduled(fixedRate = 60000)
	public void updateFLag() {
		ZoneId istZoneId = ZoneId.of("Asia/Kolkata");

		List<UrlModel> urlEntity = urlRepository.findAll();
		
		for (UrlModel entity : urlEntity) {
			LocalDateTime currentDateTimeFormatted = getCurrentDateTime(LocalDateTime.now(), istZoneId);
            if (currentDateTimeFormatted.isAfter(entity.getExpireDttm())) {
                entity.setDeleteFl(true);
                urlRepository.save(entity);
            }
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
}
