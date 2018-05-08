package com.itzmeds.rac.core;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author itzmeds Configuration class that holds the configurations to access a
 *         RESTful service
 */
public class ServiceUrlConfig {

	@JsonProperty("url.hostname")
	private String hostname;

	/**
	 * Set it to 'TRUE' if the REST API URL is SSL enabled
	 */
	@JsonProperty("url.ssl.enabled")
	private String sslEnabled;

	@JsonProperty("url.port")
	private String port;

	/**
	 * URL path params that are static. Provide it in the relative URL format.
	 */
	@JsonProperty("url.resource.path")
	private String resourcePath;

	@JsonProperty("url.proxy")
	private HttpProxyConfig proxySetting;

	/**
	 * Key Value Pairs that appears as query parameters in the RESTFUL service
	 * access URL
	 */
	@JsonProperty("url.query.params")
	private Map<String, String> urlQueryParams;

	/**
	 * Multi-valued query parameters in the RESTFUL service access URL
	 */
	@JsonProperty("url.query.params.list")
	private Map<String, List<String>> urlQueryParamListType;

	/**
	 * Key value pairs for dynamic path parameters in the RESTFUL service access
	 * URL. Key can be arbitrary string literal
	 */
	@JsonProperty("url.path.params")
	private Map<String, String> urlPathParams;

	/**
	 * Additional input required to configure the client to access the REST API.
	 * Refer sample test.yml
	 */
	@JsonProperty("url.properties")
	private Map<String, String> urlAdditionalProperties;

	/**
	 * Set it to value 'TRUE' if OAUTH is enabled on the REST API
	 */
	@JsonProperty("url.oauth.enabled")
	private String oauthEnabled;

	public ServiceUrlConfig() {
	}

	public String getHostname() {
		return hostname;
	}

	public boolean isSSLEnabled() {
		return Boolean.parseBoolean(sslEnabled);
	}

	public void setSSLEnabled(boolean isEnabled) {
		this.sslEnabled = Boolean.valueOf(isEnabled).toString();
	}

	public String getPort() {
		return port;
	}

	public String getResourcePath() {
		return resourcePath;
	}

	public HttpProxyConfig getProxySetting() {
		return proxySetting;
	}

	public Map<String, String> getUrlQueryParams() {
		return urlQueryParams;
	}

	public Map<String, List<String>> getUrlQueryParamListType() {
		return urlQueryParamListType;
	}

	public Map<String, String> getUrlPathParams() {
		return urlPathParams;
	}

	public Map<String, String> getUrlAdditionalProperties() {
		return urlAdditionalProperties;
	}

	public void setHostname(String hostname) {
		this.hostname = hostname;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public void setResourcePath(String resourcePath) {
		this.resourcePath = resourcePath;
	}

	public void setProxySetting(HttpProxyConfig proxySetting) {
		this.proxySetting = proxySetting;
	}

	public void setUrlQueryParams(Map<String, String> urlQueryParams) {
		this.urlQueryParams = urlQueryParams;
	}

	public void setUrlQueryParamListType(Map<String, List<String>> urlQueryParamListType) {
		this.urlQueryParamListType = urlQueryParamListType;
	}

	public void setUrlPathParams(Map<String, String> urlPathParams) {
		this.urlPathParams = urlPathParams;
	}

	public void setUrlAdditionalProperties(Map<String, String> urlAdditionalProperties) {
		this.urlAdditionalProperties = urlAdditionalProperties;
	}

	public boolean isOauthEnabled() {
		if ("TRUE".equalsIgnoreCase(oauthEnabled)) {
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "ServiceUrlConfig [hostname=" + hostname + ", port=" + port + ", resourcePath=" + resourcePath
				+ ", proxySetting=" + proxySetting + ", urlQueryParams=" + urlQueryParams + ", urlQueryParamListType="
				+ urlQueryParamListType + ", urlPathParams=" + urlPathParams + ", urlAdditionalProperties="
				+ urlAdditionalProperties + "]";
	}

}
