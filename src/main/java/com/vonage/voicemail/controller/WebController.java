package com.vonage.voicemail.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public String saveBook(@RequestBody User userForm) {
            if (userForm != null) {
            	getUserTableService().writeToUserTable(userForm,"User");
            } 
       
        return "redirect:addperson";
    }
    
    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
    public String testById(@PathVariable String name) {

    	 List<User> user = repository.findAll();
    	
        return "Hello Controller - Test By Id";
    }
    
}
