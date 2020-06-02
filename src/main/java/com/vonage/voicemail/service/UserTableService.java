package com.vonage.voicemail.service;

import java.util.logging.Logger;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import com.vonage.voicemail.config.DynamoDbConfig;
import com.vonage.voicemail.model.User;

public class UserTableService {

	  /** DynamoDB read throughput */
    private final static long READ_THROUGHPUT = 4;
    /** DynamoDB write throughput */
    private final static long WRITE_THROUGHPUT = 2;
    /** The name of the DynamoDB table used to store the book information */
    private final String tableName;
    private static DynamoDbConfig dynamoDBService = null;
    final private Logger log;

    private String getTableName() {
        return tableName;
    }
	
	 public UserTableService(final String bookTableName ) {
	        tableName = bookTableName;
	        log = Logger.getLogger( this.getClass().getName() );

	        if (dynamoDBService == null) {
	            dynamoDBService = new DynamoDbConfig();
	        }
	      
	        // check to see if the book table exists. If it doesn't, create it.
	        checkUserTable();
	    }
	   protected void checkUserTable() {
		   
		   AmazonDynamoDB client = dynamoDBService.amazonDynamoDB();
           DynamoDBMapper mapper = dynamoDBService.getMapper();

		   CreateTableRequest tableRequest = mapper
		           .generateCreateTableRequest(User.class);

		   tableRequest.setProvisionedThroughput(
		           new ProvisionedThroughput(1L, 1L));

		   TableUtils.createTableIfNotExists(client, tableRequest);
		
	    }
	   
	   public void writeToUserTable(User info, String tableName ) {
	        if (info != null) {
	            DynamoDBMapper mapper = dynamoDBService.getMapper();
	            mapper.save( info, new DynamoDBMapperConfig.TableNameOverride( tableName ).config());
	        }
	    }
}
