package com.vonage.voicemail.config;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.Protocol;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapperConfig;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

@Configuration
@EnableDynamoDBRepositories(basePackages = "com.vonage.voicemail.repository")
public class DynamoDbConfig {
	
	@Value("${amazon.dynamodb.endpoint}")
	private String dynamoDBEndPoint;
	
	@Value("${amazon.aws.accesskey}")
	private String accessKey;
	
	@Value("${amazon.aws.secretkey}")
	private String secretKey;
	
    @Value("${amazon.aws.region}")
    private String awsRegion;

    @Value("${amazon.aws.s3.bucket}")
    private String awsS3AudioBucket;


    private static AmazonDynamoDB mClient = null;
    private static DynamoDBMapper mMapper = null;

    public DynamoDbConfig(){
    	
    	this.accessKey=accessKey;
    	this.secretKey=secretKey;
    	
    }
  
   public AWSCredentials amazonAWSCredentialsList() {

	    System.out.println("--accessKey---"+accessKey);
	    System.out.println("--secretKey---"+secretKey);

        return new BasicAWSCredentials(accessKey,secretKey);
    }
   
  
   @Bean
    public AmazonDynamoDB amazonDynamoDB() {
        if (mClient == null) {

            AWSCredentials credentials = amazonAWSCredentialsList();
           
            ClientConfiguration config = new ClientConfiguration();
            config.setProtocol(Protocol.HTTP);
            mClient = AmazonDynamoDBClientBuilder.standard()
            	      .withCredentials(new AWSStaticCredentialsProvider(credentials))
                      .withClientConfiguration(config)
                      .withRegion(Regions.US_EAST_1)
                      .build();
        }
    	
        return mClient;
    }
   
    @Bean
    public DynamoDB dynamoDB() {
        return new DynamoDB(amazonDynamoDB());
    }
    
    /**
     * Build a mapper for a specific table.  Note that this method does not set the mMapper class variable.
     * 
     * @param tableName
     * @return
     */
    public DynamoDBMapper getMapper( String tableName ) {
        AmazonDynamoDB client = amazonDynamoDB();
        DynamoDBMapper mapper = new DynamoDBMapper( client,  new DynamoDBMapperConfig.TableNameOverride( tableName ).config() );
        return mapper;
    }
    
    /**
     * 
     * @return a DynamoDBMapper object, initialized with a static instance AmazonDynamoDB
     */
    public DynamoDBMapper getMapper() {
        if (mMapper == null) {
            AmazonDynamoDB client = amazonDynamoDB();
            mMapper = new DynamoDBMapper( client );
        }
        return mMapper;
    }
	    
   @Bean(name = "awsS3AudioBucket")
    public String getAWSS3AudioBucket() {
        return awsS3AudioBucket;
    }
  
   
}
