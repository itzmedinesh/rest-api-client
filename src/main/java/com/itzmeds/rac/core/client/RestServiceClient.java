package com.itzmeds.rac.core.client;

import static com.itzmeds.rac.core.client.ServiceClientUtils.addPathParameters;
import static com.itzmeds.rac.core.client.ServiceClientUtils.addQueryParameters;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.glassfish.jersey.apache.connector.ApacheConnectorProvider;
import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.client.authentication.HttpAuthenticationFeature;
import org.glassfish.jersey.client.oauth2.OAuth2ClientSupport;
import org.glassfish.jersey.client.spi.ConnectorProvider;

import com.itzmeds.rac.core.HttpProxyConfig;
import com.itzmeds.rac.core.ServiceUrlConfig;

/**
 * REST service client contract implementation
 * 
 * @author itzmeds
 *
 */
@SuppressWarnings("unused")
public class RestServiceClient implements ServiceClient<RestClientTemplate> {

	private static final Logger LOGGER = LogManager.getLogger(RestServiceClient.class);

	private static final String HTTP_URL_PROTOCOL_PREFIX = "http://";

	private static final String HTTPS_URL_PROTOCOL_PREFIX = "https://";

	private static final String BASIC_AUTH_UID = "basicauth.uid";

	private static final String BASIC_AUTH_PWD = "basicauth.pwd";

	private static final String REQUEST_ENTITY_PROCESSING = "BUFFERED";

	private static final String OAUTH_USERNAME = "url.oauth.enabled";

	private RestClientTemplate restClientTemplate;

	private Integer connectionTimeout;

	private Integer readTimeout;

	public RestServiceClient() {
	}

	public RestServiceClient(int connTimeout, int readTimeout) {
		this.connectionTimeout = connTimeout;
		this.readTimeout = readTimeout;
	}

	public RestServiceClient(RestClientTemplate mockRestClientTemplate) {
		this.restClientTemplate = mockRestClientTemplate;
	}

	@Override
	public RestClientTemplate createClientTemplate(String urlConfigKey, ServiceUrlConfig serviceUrlConfiguration) {
		if (restClientTemplate == null) {
			restClientTemplate = constructRestClientTemplate(urlConfigKey, serviceUrlConfiguration);
		}
		return restClientTemplate;
	}

	/**
	 * Creates a template object that encapsulates jersey web target which is used
	 * to perform CRUD operation on RESTful API
	 * 
	 * @param urlConfigKey
	 * @param serviceUrlConfiguration
	 * @return
	 */
	private RestClientTemplate constructRestClientTemplate(String urlConfigKey,
			ServiceUrlConfig serviceUrlConfiguration) {

		ClientConfig clientConfig = new ClientConfig();

		HttpProxyConfig proxyConfig = serviceUrlConfiguration.getProxySetting();

		StringBuilder restCallUrl = new StringBuilder();

		if (proxyConfig != null) {
			clientConfig.property(ClientProperties.PROXY_URI, proxyConfig.getProxyURL());
			clientConfig.property(ClientProperties.PROXY_USERNAME, proxyConfig.getUsername());
			clientConfig.property(ClientProperties.PROXY_PASSWORD, proxyConfig.getPassword());
			clientConfig.property(ClientProperties.REQUEST_ENTITY_PROCESSING, REQUEST_ENTITY_PROCESSING);
			ConnectorProvider httpConnector = new ApacheConnectorProvider();
			clientConfig.connectorProvider(httpConnector);
		}

		Client client = ClientBuilder.newClient(clientConfig);

		if (serviceUrlConfiguration.isSSLEnabled()) {
			SSLContext sc = null;
			try {
				sc = SSLContext.getInstance("TLSv1");
				System.setProperty("https.protocols", "TLSv1");
				TrustManager[] trustAllCerts = { new InsecureTrustManager() };
				sc.init(null, trustAllCerts, new java.security.SecureRandom());
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (KeyManagementException e) {
				e.printStackTrace();
			}

			HostnameVerifier allHostsValid = new InsecureHostnameVerifier();

			client = ClientBuilder.newBuilder().withConfig(clientConfig).sslContext(sc).hostnameVerifier(allHostsValid)
					.build();

			restCallUrl.append(HTTPS_URL_PROTOCOL_PREFIX).append(serviceUrlConfiguration.getHostname());
		} else {
			restCallUrl.append(HTTP_URL_PROTOCOL_PREFIX).append(serviceUrlConfiguration.getHostname());
		}

		if (serviceUrlConfiguration.getUrlAdditionalProperties() != null
				&& serviceUrlConfiguration.getUrlAdditionalProperties().get(BASIC_AUTH_UID) != null) {
			HttpAuthenticationFeature feature = HttpAuthenticationFeature.basicBuilder().build();
			client.register(feature);
		}

		if (connectionTimeout != null)
			client.property(ClientProperties.CONNECT_TIMEOUT, connectionTimeout.intValue());

		if (readTimeout != null)
			client.property(ClientProperties.READ_TIMEOUT, readTimeout.intValue());

		if (serviceUrlConfiguration.getPort() != null) {
			restCallUrl.append(":").append(serviceUrlConfiguration.getPort());
		}

		restCallUrl.append(serviceUrlConfiguration.getResourcePath());

		WebTarget restServiceTarget = null;

		restServiceTarget = client.target(restCallUrl.toString());

		restServiceTarget = serviceUrlConfiguration.getUrlPathParams() != null
				? addPathParameters(serviceUrlConfiguration.getUrlPathParams(), restServiceTarget)
				: restServiceTarget;

		restServiceTarget = serviceUrlConfiguration.getUrlQueryParamListType() != null
				? addQueryParameters(serviceUrlConfiguration.getUrlQueryParamListType(), restServiceTarget)
				: restServiceTarget;

		restServiceTarget = serviceUrlConfiguration.getUrlQueryParams() != null
				? addQueryParameters(serviceUrlConfiguration.getUrlQueryParams(), restServiceTarget)
				: restServiceTarget;

		if (serviceUrlConfiguration.isOauthEnabled()) {
			restServiceTarget.register(OAuth2ClientSupport.feature(null));
		}

		LOGGER.info("REST URL for config id : " + urlConfigKey + " with static query & path params : "
				+ restServiceTarget.getUri().toString());

		return new RestClientTemplate(restServiceTarget);
	}

}
