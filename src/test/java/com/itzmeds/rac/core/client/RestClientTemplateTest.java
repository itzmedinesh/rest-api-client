package com.itzmeds.rac.core.client;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itzmeds.rac.core.ClientConfiguration;
import com.itzmeds.rac.core.ServiceUrlConfig;
import com.itzmeds.rac.utility.TestConfiguration;

public class RestClientTemplateTest {

	private static final String BASIC_AUTH_UID = "basicauth.uid";
	private static final String BASIC_AUTH_PWD = "basicauth.pwd";

	private static ObjectMapper objectMapper = new ObjectMapper();

	RestClientTemplate restClientTemplate;

	WebTarget mockTarget;

	Response mockResponse;

	ServiceUrlConfig serviceUrlConfig;

	ClientConfiguration configuration;

	@Before
	public void setUp() throws Exception {
		configuration = TestConfiguration.load();
		serviceUrlConfig = configuration.getServiceUrlConfig().get("ATG_SERVICE_URL_NO_PROXY");

		WebTarget mockTarget = Mockito.mock(WebTarget.class);
		mockResponse = Mockito.mock(Response.class);

		Invocation.Builder restTargetBuilder = Mockito.mock(Invocation.Builder.class);

		Mockito.when(mockResponse.getStatus()).thenReturn(200);

		Mockito.when(mockTarget.getUri()).thenReturn(new URI(
				"http://test.noproxy.atg.com:80/direct/rest/price/sku/102033-383?format=standard"));

		Mockito.when(mockTarget.queryParam(Matchers.anyString(), Matchers.anyObject())).thenReturn(mockTarget);

		Mockito.when(mockTarget.path(Matchers.anyString())).thenReturn(mockTarget);

		Mockito.when(mockTarget.request()).thenReturn(restTargetBuilder);
		Mockito.when(restTargetBuilder.get()).thenReturn(mockResponse);

		restClientTemplate = new RestClientTemplate(mockTarget);
	}

	@Test
	public void testCallService() {
		Map<String, String> pathParams = serviceUrlConfig.getUrlPathParams();
		pathParams.put("sku.value", "102033-383");
		Response response = restClientTemplate.retrieve(serviceUrlConfig.getUrlQueryParams(), pathParams);
		Assert.assertNotNull(response);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Test
	public void testCallServiceType2() throws URISyntaxException {

		configuration = TestConfiguration.load();
		serviceUrlConfig = configuration.getServiceUrlConfig().get("PRODUCT_SERVICE_CATID_LOOKUP");

		WebTarget mockTarget = Mockito.mock(WebTarget.class);
		mockResponse = Mockito.mock(Response.class);

		Invocation.Builder restTargetBuilder = Mockito.mock(Invocation.Builder.class);

		Mockito.when(mockResponse.getStatus()).thenReturn(200);

		Mockito.when(mockTarget.getUri())
				.thenReturn(new URI("http://test.product.com:80/v2/products"));

		Mockito.when(mockTarget.queryParam(Matchers.anyString(), Matchers.anyObject())).thenReturn(mockTarget);

		Mockito.when(mockTarget.path(Matchers.anyString())).thenReturn(mockTarget);

		Mockito.when(mockTarget.request()).thenReturn(restTargetBuilder);
		Mockito.when(restTargetBuilder.get()).thenReturn(mockResponse);

		restClientTemplate = new RestClientTemplate(mockTarget);

		Map queryParams = serviceUrlConfig.getUrlQueryParamListType();

		Iterator<String> queryParamIter = serviceUrlConfig.getUrlQueryParams().keySet().iterator();

		while (queryParamIter.hasNext()) {
			String key = queryParamIter.next();
			String value = serviceUrlConfig.getUrlQueryParams().get(key);
			queryParams.put(key, value);
		}

		Response response = restClientTemplate.retrieve(queryParams, new HashMap<String, String>());
		Assert.assertNotNull(response);

	}

	@Test
	public void testPostCall() throws URISyntaxException {

		configuration = TestConfiguration.load();
		serviceUrlConfig = configuration.getServiceUrlConfig().get("TEST_POST");

		Entity<String> postCallInput = Entity.json("{\"messaage\":\"parse this message and save\"}");

		WebTarget mockTarget = Mockito.mock(WebTarget.class);
		mockResponse = Mockito.mock(Response.class);

		Invocation.Builder restTargetBuilder = Mockito.mock(Invocation.Builder.class);

		Mockito.when(mockResponse.getStatus()).thenReturn(201);

		Mockito.when(mockTarget.getUri()).thenReturn(new URI("http://localhost:8080/rewards/json"));

		Mockito.when(mockTarget.queryParam(Matchers.anyString(), Matchers.anyObject())).thenReturn(mockTarget);

		Mockito.when(mockTarget.path(Matchers.anyString())).thenReturn(mockTarget);

		Mockito.when(mockTarget.request()).thenReturn(restTargetBuilder);

		Mockito.when(restTargetBuilder.post(postCallInput)).thenReturn(mockResponse);

		restClientTemplate = new RestClientTemplate(mockTarget);

		Response response = restClientTemplate.create(null, null, postCallInput);

		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatus(), 201);

	}

	@Test
	public void testPutCall() throws URISyntaxException {

		configuration = TestConfiguration.load();
		serviceUrlConfig = configuration.getServiceUrlConfig().get("TEST_PUT");

		Entity<String> postCallInput = Entity.json("{\"messaage\":\"parse this message and save\"}");

		WebTarget mockTarget = Mockito.mock(WebTarget.class);
		mockResponse = Mockito.mock(Response.class);

		Invocation.Builder restTargetBuilder = Mockito.mock(Invocation.Builder.class);

		Mockito.when(mockResponse.getStatus()).thenReturn(200);

		Mockito.when(mockTarget.getUri()).thenReturn(new URI("http://localhost:8080/rewards/json"));

		Mockito.when(mockTarget.queryParam(Matchers.anyString(), Matchers.anyObject())).thenReturn(mockTarget);

		Mockito.when(mockTarget.path(Matchers.anyString())).thenReturn(mockTarget);

		Mockito.when(mockTarget.request()).thenReturn(restTargetBuilder);

		Mockito.when(restTargetBuilder.put(postCallInput)).thenReturn(mockResponse);

		restClientTemplate = new RestClientTemplate(mockTarget);

		Response response = restClientTemplate.update(null, null, postCallInput);

		Assert.assertNotNull(response);
		Assert.assertEquals(response.getStatus(), 200);

	}

	@Test
	public void testDeleteCall() throws URISyntaxException {

		configuration = TestConfiguration.load();
		serviceUrlConfig = configuration.getServiceUrlConfig().get("TEST_POST");

		WebTarget mockTarget = Mockito.mock(WebTarget.class);
		mockResponse = Mockito.mock(Response.class);

		Invocation.Builder restTargetBuilder = Mockito.mock(Invocation.Builder.class);

		Mockito.when(mockResponse.getStatus()).thenReturn(204);

		Mockito.when(mockTarget.getUri()).thenReturn(new URI("http://localhost:8080/rewards/json"));

		Mockito.when(mockTarget.queryParam(Matchers.anyString(), Matchers.anyObject())).thenReturn(mockTarget);

		Mockito.when(mockTarget.path(Matchers.anyString())).thenReturn(mockTarget);

		Mockito.when(mockTarget.request()).thenReturn(restTargetBuilder);

		Mockito.when(restTargetBuilder.delete()).thenReturn(mockResponse);

		restClientTemplate = new RestClientTemplate(mockTarget);

		Response response = restClientTemplate.delete(null, null);

		Assert.assertNotNull(response);

		Assert.assertEquals(response.getStatus(), 204);

	}

	/*
	 * Has to remain ignored as it tries to query actual rest endpoint. Used for
	 * development time testing, not supposed to run in jenkins
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Ignore
	public void testCallServiceListQueryParams() {
		ServiceClient<RestClientTemplate> svcClient = new RestServiceClient();

		RestClientTemplate restClientTemplateTemp = svcClient.createClientTemplate("PRODUCT_SERVICE_CATID_LOOKUP",
				configuration.getServiceUrlConfig().get("PRODUCT_SERVICE_CATID_LOOKUP"));

		Map<String, String> pathParams = new LinkedHashMap<String, String>();
		pathParams.put("prod.key", "products");

		Map queryParams = new LinkedHashMap<String, String>();
		queryParams.put("tpnb", "052162304");
		queryParams.put("fields", Arrays.asList("TPNB", "TPNC"));

		Response response = restClientTemplateTemp.retrieve(queryParams, pathParams);

		queryParams = new HashMap<String, String>();
		queryParams.put("tpnc", "3383838");
		queryParams.put("fields", "TPNC");

		response = restClientTemplateTemp.retrieve(queryParams, pathParams);

		Assert.assertNotNull(response);

	}

	/*
	 * Has to remain ignored as it tries to query actual rest endpoint. Used for
	 * development time testing, not supposed to run in jenkins
	 */
	@Ignore
	public void testPostRealCall() {
		ServiceClient<RestClientTemplate> svcClient = new RestServiceClient();

		RestClientTemplate restClientTemplateTemp = svcClient.createClientTemplate("TEST_POST",
				configuration.getServiceUrlConfig().get("TEST_POST"));

		Entity<String> postCallInput = Entity.json("{\"messaage\":\"parse this message and save\"}");

		Response response = restClientTemplateTemp.create(null, null, postCallInput);
		Assert.assertNotNull(response);

	}

	/*
	 * Has to remain ignored as it tries to query actual rest endpoint. Used for
	 * development time testing, not supposed to run in jenkins
	 */
	@Ignore
	public void testPutRealCall() {
		ServiceClient<RestClientTemplate> svcClient = new RestServiceClient();

		RestClientTemplate restClientTemplateTemp = svcClient.createClientTemplate("TEST_POST",
				configuration.getServiceUrlConfig().get("TEST_POST"));

		Entity<String> postCallInput = Entity.json("{\"rewards\":\"my rewards\"}");

		Map<String, String> pathParams = new LinkedHashMap<String, String>();
		pathParams.put("rewards_id", "123");

		Response response = restClientTemplateTemp.update(null, pathParams, postCallInput);
		Assert.assertNotNull(response);

	}

	/*
	 * Has to remain ignored as it tries to query actual rest endpoint. Used for
	 * development time testing, not supposed to run in jenkins
	 */
	@Ignore
	public void testDeleteRealCall() {
		ServiceClient<RestClientTemplate> svcClient = new RestServiceClient();

		RestClientTemplate restClientTemplateTemp = svcClient.createClientTemplate("TEST_POST",
				configuration.getServiceUrlConfig().get("TEST_POST"));

		Map<String, String> pathParams = new LinkedHashMap<String, String>();
		pathParams.put("rewards_id", "123");

		Response response = restClientTemplateTemp.delete(null, pathParams);
		Assert.assertNotNull(response);

	}

	/*
	 * Has to remain ignored as it tries to query actual rest endpoint. Used for
	 * development time testing, not supposed to run in jenkins
	 */
	@Ignore
	public void testGetHTTSRealCall() {
		ServiceClient<RestClientTemplate> svcClient = new RestServiceClient();

		RestClientTemplate restClientTemplateTemp = svcClient.createClientTemplate("TEST_HTTPS_GET",
				configuration.getServiceUrlConfig().get("TEST_HTTPS_GET"));

		Map<String, String> pathParams = new LinkedHashMap<String, String>();
		pathParams.put("value", "dinesh");

		Response response = restClientTemplateTemp.retrieve(null, pathParams);
		Assert.assertNotNull(response);
	}

	/*
	 * Has to remain ignored as it tries to query actual rest endpoint. Used for
	 * development time testing, not supposed to run in jenkins
	 */
	@Ignore
	public void testDisplayDataAuthentication() {
		ServiceClient<RestClientTemplate> svcClient = new RestServiceClient();

		ServiceUrlConfig svcUrlConfig = configuration.getServiceUrlConfig().get("DISPLAY_DATA_AUTH_URL");

		System.out.println(svcUrlConfig.toString());

		RestClientTemplate restClientTemplateTemp = svcClient.createClientTemplate("DISPLAY_DATA_AUTH_URL",
				svcUrlConfig);

		Response response = restClientTemplateTemp.retrieve(null, null,
				svcUrlConfig.getUrlAdditionalProperties().get(BASIC_AUTH_UID),
				svcUrlConfig.getUrlAdditionalProperties().get(BASIC_AUTH_PWD));

		Assert.assertEquals(200, response.getStatus());
	}

	/*
	 * Has to remain ignored as it tries to query actual rest endpoint. Used for
	 * development time testing, not supposed to run in jenkins
	 */
	@Ignore
	public void testDisplayDataGetProductDetails() {

		ServiceUrlConfig svcUrlConfig = configuration.getServiceUrlConfig().get("DISPLAY_DATA_AUTH_URL");

		System.out.println(svcUrlConfig.toString());

		RestClientTemplate restClientTemplateTemp = new RestServiceClient()
				.createClientTemplate("DISPLAY_DATA_AUTH_URL", svcUrlConfig);

		Response response = restClientTemplateTemp.retrieve(null, null,
				svcUrlConfig.getUrlAdditionalProperties().get(BASIC_AUTH_UID),
				svcUrlConfig.getUrlAdditionalProperties().get(BASIC_AUTH_PWD));

		String authToken = response.readEntity(String.class);

		authToken = authToken.replaceAll("\"", "");

		System.out.println("Token : " + authToken);

		Assert.assertEquals(200, response.getStatus());

		ServiceUrlConfig svcUrlConfigProdDet = configuration.getServiceUrlConfig().get("DISPLAY_DATA_PRODUCT_DETAILS");

		System.out.println(svcUrlConfigProdDet.toString());

		RestClientTemplate restClientTemplateProdDet = new RestServiceClient()
				.createClientTemplate("DISPLAY_DATA_PRODUCT_DETAILS", svcUrlConfigProdDet);

		Map<String, String> pathParams = new LinkedHashMap<String, String>();
		pathParams.put("prodid", "1");
		pathParams.put("detailskey", "detail");

		Response responseProdDet = restClientTemplateProdDet.retrieve(null, pathParams, authToken, "");
		Assert.assertEquals(404, responseProdDet.getStatus());

		System.out.println("Prod Det Response : " + responseProdDet.readEntity(String.class));
	}

	/*
	 * Has to remain ignored as it tries to query actual rest endpoint. Used for
	 * development time testing, not supposed to run in jenkins
	 */
	// @Test
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Ignore
	public void testCallServiceListQueryParamsV1() {

		String accessToken = null;
		ServiceClient<RestClientTemplate> svcIdentityClient = new RestServiceClient();

		RestClientTemplate restClientIdtyTemplate = svcIdentityClient.createClientTemplate(
				"IDENTITY_SERVICE_TOKEN_EXTRA",
				configuration.getServiceUrlConfig().get("IDENTITY_SERVICE_TOKEN_EXTRA"));

		try {
			Response idtyResponse = restClientIdtyTemplate.create(null, null, Entity.json(configuration
					.getServiceUrlConfig().get("IDENTITY_SERVICE_TOKEN_EXTRA").getUrlAdditionalProperties()));
			Assert.assertNotNull(idtyResponse);
			accessToken = ((Map<String, String>) idtyResponse.readEntity(Map.class)).get("access_token");
		} catch (Exception e) {
			System.out.println("Exception accessing Identity service" + e.getMessage());
		}

		/**
		 * accessToken will be given by Identity Service
		 */
		ServiceClient<RestClientTemplate> svcClient = new RestServiceClient();

		RestClientTemplate restClientTemplateTemp = svcClient.createClientTemplate(
				"PRODUCT_SERVICE_CATID_LOOKUP_FOR_V3",
				configuration.getServiceUrlConfig().get("PRODUCT_SERVICE_CATID_LOOKUP_FOR_V3"));

		Map<String, String> pathParams = new LinkedHashMap<String, String>();
		pathParams.put("prod.key", "products");

		Map queryParams = new LinkedHashMap<String, String>();
		queryParams.put("tpnb", "051592804");

		if (accessToken == null) {
			Assert.assertFalse(false);
		}

		Response response = restClientTemplateTemp.retrieve(queryParams, pathParams, accessToken);
		Assert.assertNotNull(response);
	}

	/*
	 * Has to remain ignored as it tries to query actual rest endpoint. Used for
	 * development time testing, not supposed to run in jenkins
	 */
	@Ignore
	public void testAndroidRestClientFeature() {
		ServiceClient<RestClientTemplate> svcClient = new AndroidRestServiceClient();

		ObjectMapper objectMapper = new ObjectMapper();

		ServiceUrlConfig authSvcUrlConfig = null;

		String props = "{\"url.hostname\":\"zdtkmzxsrf.localtunnel.me\",\"url.ssl.enabled\":\"true\",\"url.resource.path\":\"/SignOn_V3/token\"}";

		try {
			authSvcUrlConfig = objectMapper.readValue(props, ServiceUrlConfig.class);
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println(authSvcUrlConfig.toString());

		RestClientTemplate restClientTemplateTemp = svcClient.createClientTemplate("OPEN_AUTH_URL", authSvcUrlConfig);

		Response response = restClientTemplateTemp.retrieve(null, null);

		Assert.assertEquals(200, response.getStatus());
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	@Test
	public void testAndroidRestClientHeaderCheck() {
		{

			ServiceUrlConfig taskServiceConfig = null;
			String myTasksResponseStr = null;

			String props = "{\"url.hostname\":\"localhost\",\"url.port\":\"8000\",\"url.resource.path\":\"/api/task/list\",\"url.oauth.enabled\":\"TRUE\"}";

			try {
				taskServiceConfig = objectMapper.readValue(props, ServiceUrlConfig.class);
				ClientTemplate<Response> taskClient = new AndroidRestServiceClient()
						.createClientTemplate("OAUTH_TOKEN_VALIDATION_SERVICE", taskServiceConfig);

				Map<String, String> pathParams = new LinkedHashMap<String, String>();
				pathParams.put("LocationType", "STORE");
				pathParams.put("LocationId", "3000");
				pathParams.put("User", "gtz7");
				pathParams.put("Role", "TWR");

				Map queryParams = new LinkedHashMap();
				queryParams.put("taskName", "ConciergeTask");
				queryParams.put("status", Arrays.asList("ASSIGNED", "CLAIMED"));

				Response myTasksResponse = taskClient.retrieve(queryParams, pathParams, "2016001212");

				if (myTasksResponse.getStatus() == 200) {
					myTasksResponseStr = objectMapper.writeValueAsString(myTasksResponse.readEntity(String.class));
				}

				System.out.println("MyTask Response Status" + myTasksResponse.getStatus());

				Assert.assertEquals(200, myTasksResponse.getStatus());

			} catch (Throwable e) {
				e.printStackTrace();
			}

		}
	}

}
