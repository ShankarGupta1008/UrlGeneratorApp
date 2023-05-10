package com.example.url.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.url.model.UrlModel;
import com.example.url.service.UrlService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api")
public class urlController {
	
	@Autowired
	private UrlService urlService;
	
	@PostMapping("/postCreate")
	public ResponseEntity<UrlModel> generateRandomUrl(@RequestBody String givenUrl) throws Exception{
		UrlModel urlType = urlService.generateUrl(givenUrl);
		if(null == urlType) {
			throw new Exception("Can not crate or update the given Url.");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(urlType);
	}
	
	@GetMapping("/get")
	public ResponseEntity<UrlModel> getOriginalUrlFromGeneratedUrl(@RequestParam String generatedUrl) throws Exception{
		System.out.println(generatedUrl);
		UrlModel urlType = urlService.getMappedOriginalUrl(generatedUrl);
		if(null == urlType) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(urlType);
	}

}
