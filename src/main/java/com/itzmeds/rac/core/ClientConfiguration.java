package com.itzmeds.rac.core;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author itzmeds Configuration class that holds the configurations of all
 *         RESTful services defined in the input YAML
 */
public class ClientConfiguration {

	@JsonProperty("service.url")
	private Map<String, ServiceUrlConfig> serviceUrlConfig;

	public Map<String, ServiceUrlConfig> getServiceUrlConfig() {
		return serviceUrlConfig;
	}

}
