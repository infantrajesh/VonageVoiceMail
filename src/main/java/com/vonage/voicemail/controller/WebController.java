package com.vonage.voicemail.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vonage.voicemail.common.HttpResponse;
import com.vonage.voicemail.model.User;
import com.vonage.voicemail.repository.UserRepository;


@RestController
@RequestMapping("/vonage")

public class WebController extends UserControllerBase {

	@Autowired(required=true)
    private final UserRepository repository;
    
	String tablename;
	
    public WebController(UserRepository repository,String tablename) {
        this.repository = repository;
        this.tablename=tablename;
    }
    
    
    @RequestMapping( value="/save-user", method = RequestMethod.POST,headers="Accept=application/json")
    public ResponseEntity<HttpResponse> saveBook(@RequestBody User userForm) {
            if (userForm != null) {
            	getUserTableService().writeToUserTable(userForm,"User");
            } 
       
            return new ResponseEntity<HttpResponse>(
    				new HttpResponse("200", "SUCCESS", "Date updated successfully", userForm), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public ResponseEntity<HttpResponse> testById(@PathVariable String name) {

    	 List<User> user = repository.findAll();
    	
    	  return new ResponseEntity<HttpResponse>(
  				new HttpResponse("200", "SUCCESS", "Date updated successfully", user), HttpStatus.OK);
    	  }
    
}
