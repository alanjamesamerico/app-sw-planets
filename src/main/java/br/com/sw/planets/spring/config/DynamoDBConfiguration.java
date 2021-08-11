package br.com.sw.planets.spring.config;

import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Configuration
@PropertySource("classpath:dynamondb-credentials.properties")
@EnableDynamoDBRepositories(basePackages = "br.com.sw.planets.repository")
public class DynamoDBConfiguration {
	
	@Autowired
	private Environment env;
	
	@Bean
	public AmazonDynamoDB amazonDynamoDB() {
		
		log.info("Access configuration for AWS started !");
		
		var service = this.env.getProperty("dynamon.serivce-endpoint");
		var region  = this.env.getProperty("dynamon.signin-region");
		var accessKey = this.env.getProperty("dynamon.access-key");
		var secretKey = this.env.getProperty("dynamon.secret-key");
		
		return AmazonDynamoDBClientBuilder.standard()
					.withEndpointConfiguration(
							new AwsClientBuilder.EndpointConfiguration(service, region)
						)
					.withCredentials(new AWSStaticCredentialsProvider(
							new BasicAWSCredentials(accessKey, secretKey)
						)
					).build();
	}
}
