package com.vonage.voicemail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Repository;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.vonage.voicemail.repository.UserRepository;

@SpringBootApplication
public class VonageVoiceMailApplication implements CommandLineRunner{

	    private DynamoDBMapper dynamoDBMapper;

	    @Autowired
	    private AmazonDynamoDB amazonDynamoDB;

	  
		public static void main(String[] args) {
			SpringApplication.run(VonageVoiceMailApplication.class, args);
		}

		@Override
		public void run(String... args) throws Exception {
			// TODO Auto-generated method stub
			dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

	    }
	
}
