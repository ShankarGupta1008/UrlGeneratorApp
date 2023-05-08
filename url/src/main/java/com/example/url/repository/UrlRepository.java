package com.example.url.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.url.model.UrlModel;

@Repository
public interface UrlRepository extends JpaRepository<UrlModel, Long>, JpaSpecificationExecutor<UrlModel>{
	
	UrlModel findByGivenUrlAndDeleteFl(String givenUrl, Boolean deleteFl);
	
	UrlModel findByGeneratedUrlAndDeleteFl(String generatedUrl, Boolean deleteFl);

}
