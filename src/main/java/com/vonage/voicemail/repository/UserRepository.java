package com.vonage.voicemail.repository;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vonage.voicemail.model.User;


@EnableScan
public interface UserRepository extends CrudRepository<User, Long>{
 
     List<User> findAll();
     
//     public User save(User user);
     
}

