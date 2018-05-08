package com.itzmeds.rac.core.client;

import static com.itzmeds.rac.core.client.ServiceClientUtils.addPathParameters;
import static com.itzmeds.rac.core.client.ServiceClientUtils.addQueryParameters;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.client.oauth2.OAuth2ClientSupport;

/**
 * 
 * Client implementation to access RESTful APIs
 * 
 * @author itzmeds
 *
 */
public class RestClientTemplate implements ClientTemplate<Response> {

	// private static final Logger LOGGER = LogManager
	// .getLogger(RestClientTemplate.class);

	private WebTarget restServiceTarget = null;

	public RestClientTemplate(WebTarget restServiceTarget) {
		this.restServiceTarget = restServiceTarget;
	}

	@Override
	public <M> Response retrieve(Map<String, M> queryParams, Map<String, String> pathParams) {
		Invocation.Builder requestBuilder = getRequestBuilder(queryParams, pathParams);
		return requestBuilder.get();
	}

	@Override
	public <M> Response delete(Map<String, M> queryParams, Map<String, String> pathParams) {
		Invocation.Builder requestBuilder = getRequestBuilder(queryParams, pathParams);
		return requestBuilder.delete();
	}

	@Override
	public <M, E> Response create(Map<String, M> queryParams, Map<String, String> pathParams, Entity<E> requestEntity) {
		Invocation.Builder requestBuilder = getRequestBuilder(queryParams, pathParams);
		return requestBuilder.post(requestEntity);
	}

	@Override
	public <M, E> Response update(Map<String, M> queryParams, Map<String, String> pathParams, Entity<E> requestEntity) {
		Invocation.Builder requestBuilder = getRequestBuilder(queryParams, pathParams);
		return requestBuilder.put(requestEntity);
	}

	@Override
	public <M> Response retrieve(Map<String, M> queryParams, Map<String, String> pathParams, String basicAuthUid,
			String basicAuthPwd) {

		return getRequestBuilder(queryParams, pathParams, basicAuthUid, basicAuthPwd).get();
	}

	@Override
	public <M> Response delete(Map<String, M> queryParams, Map<String, String> pathParams, String basicAuthUid,
			String basicAuthPwd) {

		return getRequestBuilder(queryParams, pathParams, basicAuthUid, basicAuthPwd).delete();
	}

	@Override
	public <M, E> Response create(Map<String, M> queryParams, Map<String, String> pathParams, Entity<E> requestEntity,
			String basicAuthUid, String basicAuthPwd) {

		return getRequestBuilder(queryParams, pathParams, basicAuthUid, basicAuthPwd).post(requestEntity);
	}

	@Override
	public <M, E> Response update(Map<String, M> queryParams, Map<String, String> pathParams, Entity<E> requestEntity,
			String basicAuthUid, String basicAuthPwd) {

		return getRequestBuilder(queryParams, pathParams, basicAuthUid, basicAuthPwd).put(requestEntity);
	}

	@Override
	public <M, E> Response create(Map<String, M> queryParams, Map<String, String> pathParams, Entity<E> requestEntity,
			String accessToken) {
		return getRequestBuilder(queryParams, pathParams, accessToken).post(requestEntity);
	}

	@Override
	public <M> Response retrieve(Map<String, M> queryParams, Map<String, String> pathParams, String accessToken) {
		return getRequestBuilder(queryParams, pathParams, accessToken).get();
	}

	@Override
	public <M, E> Response update(Map<String, M> queryParams, Map<String, String> pathParams, Entity<E> requestEntity,
			String accessToken) {
		return getRequestBuilder(queryParams, pathParams, accessToken).put(requestEntity);
	}

	@Override
	public <M> Response delete(Map<String, M> queryParams, Map<String, String> pathParams, String accessToken) {
		return getRequestBuilder(queryParams, pathParams, accessToken).delete();
	}

	private <M> Invocation.Builder getRequestBuilder(Map<String, M> queryParams, Map<String, String> pathParams) {

		WebTarget viewTarget = null;

		pathParams = pathParams == null ? new HashMap<String, String>() : pathParams;

		queryParams = queryParams == null ? new HashMap<String, M>() : queryParams;

		viewTarget = addPathParameters(pathParams, restServiceTarget);
		viewTarget = addQueryParameters(queryParams, viewTarget);

		// LOGGER.info("Rest Call Target Url : " + viewTarget.getUri().toString());

		return viewTarget.request();
	}

	private <M> Invocation.Builder getRequestBuilder(Map<String, M> queryParams, Map<String, String> pathParams,
			String basicAuthUid, String basicAuthPwd) {
		Invocation.Builder reqBuilder = getRequestBuilder(queryParams, pathParams);
		if (basicAuthUid != null) {
			reqBuilder = reqBuilder.property(HttpAuthenticationFeature.HTTP_AUTHENTICATION_BASIC_USERNAME,
					basicAuthUid);
		}
		if (basicAuthPwd != null) {
			reqBuilder = reqBuilder.property(HttpAuthenticationFeature.HTTP_AUTHENTICATION_BASIC_PASSWORD,
					basicAuthPwd);
		}
		return reqBuilder;
	}

	private <M> Invocation.Builder getRequestBuilder(Map<String, M> queryParams, Map<String, String> pathParams,
			String accessToken) {

		Invocation.Builder reqBuilder = getRequestBuilder(queryParams, pathParams);
		return reqBuilder.property(OAuth2ClientSupport.OAUTH2_PROPERTY_ACCESS_TOKEN, accessToken);
	}
}
