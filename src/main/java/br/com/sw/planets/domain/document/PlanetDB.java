package br.com.sw.planets.domain.document;

import java.io.Serializable;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@DynamoDBTable(tableName = "planets")
public class PlanetDB extends AbstractDocument implements Serializable {
	
	private static final long serialVersionUID = -5754728667661603822L;
	
	@DynamoDBAttribute
	private String 	name;
	
	@DynamoDBAttribute
	private String 	climate;
	
	@DynamoDBAttribute
	private String 	terrain;
	
	@JsonIgnore
	@DynamoDBAttribute
	private Integer appearances;
	
	@JsonIgnore
	@DynamoDBAttribute
	public String criationDate;
}
