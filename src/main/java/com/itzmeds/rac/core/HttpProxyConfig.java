package com.itzmeds.rac.core;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author itzmeds Configuration class that holds the HTTP proxy configuration
 *         of a RESTful service specified in the YAML input
 */
public class HttpProxyConfig {

	@JsonProperty("proxy.url")
	private String proxyURL;

	@JsonProperty("proxy.username")
	private String username;

	@JsonProperty("proxy.password")
	private String password;

	public HttpProxyConfig() {
	}

	public String getProxyURL() {
		return proxyURL;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	@Override
	public String toString() {
		return "HttpProxyConfig [proxyURL=" + proxyURL + ", username=" + username + ", password=" + password + "]";
	}

}
