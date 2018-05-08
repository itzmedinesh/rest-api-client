package com.itzmeds.rac.core.client;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.itzmeds.rac.core.ClientConfiguration;
import com.itzmeds.rac.core.ServiceUrlConfig;
import com.itzmeds.rac.core.client.RestClientTemplate;
import com.itzmeds.rac.core.client.RestServiceClient;
import com.itzmeds.rac.core.client.ServiceClient;
import com.itzmeds.rac.utility.TestConfiguration;

public class RestServiceClientTest {

	ServiceClient<RestClientTemplate> serviceClient;

	ClientConfiguration configuration;

	@Before
	public void setUp() throws Exception {
		configuration = TestConfiguration.load();
		serviceClient = new RestServiceClient();
	}

	@Test
	public void testCreateClientTemplate() {

		RestClientTemplate restClientTemplate = serviceClient
				.createClientTemplate("ATG_SERVICE_URL", configuration
						.getServiceUrlConfig().get("ATG_SERVICE_URL"));
		Assert.assertNotNull(restClientTemplate);

		serviceClient = new RestServiceClient(restClientTemplate);
		RestClientTemplate duplicateClientTemplate = serviceClient
				.createClientTemplate("ATG_SERVICE_URL", configuration
						.getServiceUrlConfig().get("ATG_SERVICE_URL"));

		Assert.assertEquals(restClientTemplate, duplicateClientTemplate);

	}

	@Test
	public void testCreateClientTemplateWithoutProxy() {
		serviceClient = new RestServiceClient();
		configuration = Mockito.mock(ClientConfiguration.class);

		Map<String, ServiceUrlConfig> mockConfigMap = new HashMap<String, ServiceUrlConfig>();
		ServiceUrlConfig mockServiceUrlConfig = Mockito
				.mock(ServiceUrlConfig.class);
		Mockito.when(mockServiceUrlConfig.getHostname()).thenReturn("abc.com");
		Mockito.when(mockServiceUrlConfig.getPort()).thenReturn(null);
		Mockito.when(mockServiceUrlConfig.getResourcePath()).thenReturn(
				"/test/");
		Mockito.when(mockServiceUrlConfig.getProxySetting()).thenReturn(null);
		mockConfigMap.put("ATG_SERVICE_URL", mockServiceUrlConfig);
		Mockito.when(configuration.getServiceUrlConfig()).thenReturn(
				mockConfigMap);

		RestClientTemplate restClientTemplate = serviceClient
				.createClientTemplate("ATG_SERVICE_URL", configuration
						.getServiceUrlConfig().get("ATG_SERVICE_URL"));
		Assert.assertNotNull(restClientTemplate);

	}

}
