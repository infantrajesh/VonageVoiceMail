package com.vonage.voicemail.model;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ObjectConverter  implements DynamoDBTypeConverter<String, Date> {

	@Override
	public String convert(Date object) {
		// TODO Auto-generated method stub
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-DD'T'HH:mm:ss"); 
		String finalString = formatter.format(object);

		return finalString;
	}

	@Override
	public Date unconvert(String object) {
		// TODO Auto-generated method stub
		return null;
	}

   
}
