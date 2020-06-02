package com.vonage.voicemail.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGenerateStrategy;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAutoGeneratedKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBGeneratedUuid;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBIndexHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverted;

import lombok.Data;

@Data
@DynamoDBTable(tableName = "User")
public class User {

   @DynamoDBHashKey
   @DynamoDBGeneratedUuid(DynamoDBAutoGenerateStrategy.CREATE) // Requires a mutable object
   private String id;

   @DynamoDBAttribute(attributeName="firstname")
   private String firstname;
  
   @DynamoDBAttribute(attributeName="lastname")
   private String lastname;
   
   @DynamoDBAttribute
   private String email;
	   
   @DynamoDBAttribute
   private int age;
   
   @DynamoDBAttribute
   private String username;
   
   @DynamoDBAttribute
   private String password;
   
   @DynamoDBTypeConverted(converter = ObjectConverter.class)
   @DynamoDBAttribute
   private Date createdtime;
   
   public void setId(String id) {
		this.id=id;
   }

	public String getId() {
		return id;
	}

	
	public void setFirstname(String firstname) {
		this.firstname=firstname;
	}
	
	public String getFirstname() {
		return firstname;
	}


	public void setLastname(String lastname) {
		this.lastname=lastname;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	
	public void setUsername(String username) {
		this.username=username;
	}
	
	public String getUsername() {
		return username;
	}
	
	
	
	public void setPassword(String password) {
		this.password=password;
	}
	
	public String getPassword() {
		return password;
	}
	
	
	public void setEmail(String email) {
		this.email=email;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setAge(int age) {
		this.age=age;
	}
	
	public int getAge() {
		return age;
	}
	

	public void setCreatedtime(Date createdtime) {
		this.createdtime=createdtime;
	}
	
	public Date getCreatedtime() {
		return createdtime;
	}
	
	
}

