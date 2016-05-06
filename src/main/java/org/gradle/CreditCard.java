/**
 * 
 */
package org.gradle;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class CreditCard {

	@JsonProperty("first_name")
	private String firstName;
	@JsonProperty("last_name")
	private String lastName;
	
}
