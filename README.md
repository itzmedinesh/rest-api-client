# RestApiClient
Java client(Android friendly) for CRUD operations on REST API.

# Versions on maven
http://central.maven.org/maven2/com/github/itzmedinesh/rest-api-client/

# Sample YAML configuration
***********************************
```html
service.url:
  TEST_URL_CONFIG_1:
    url.hostname: xxxxxxxxx.test.com
    url.ssl.enabled: false
    url.port: 80
    url.resource.path: /direct/rest/price/
    url.proxy:
      proxy.url: http://proxy.com
      proxy.username: test
      proxy.password: test
    url.query.params:
      format: standard
    url.path.params:
      sku.key: sku
    url.properties:
      url.query.limit: 5
  TEST_URL_CONFIG_2:
    url.hostname: xxxxx.com
    url.ssl.enabled: true
    url.port: 443
    url.resource.path: /apis/v1.0/
    url.path.params:
      apiId: 45c9bbb23584
      key: Shortname 
```

# Initialization of rest clients and accessing REST API
*********************************************************************
```html

private static final int CONNECT_TIMEOUT = 3000;
private static final int READ_TIMEOUT = 3000;

ClientTemplate<Response> restRetrieveTemplate = new RestServiceClient(CONNECT_TIMEOUT,READ_TIMEOUT).createClientTemplate(
								"TEST_URL_CONFIG_2",
								configuration.getServiceUrlConfig().get(
										"TEST_URL_CONFIG_2"));
										
Map<String, String> queryParams = new LinkedHashMap<String, String>();
pathParams.put("clientId", "test");

Map<String, String> pathParams = new LinkedHashMap<String, String>();
pathParams.put("value", "dinesh");										
										
Response response = restRetrieveTemplate.retrieve(queryParams, pathParams);	


For Android Apps

ClientTemplate<Response> restRetrieveTemplate = new AndroidRestServiceClient(CONNECT_TIMEOUT,READ_TIMEOUT).createClientTemplate(
								"TEST_URL_CONFIG_2",
								configuration.getServiceUrlConfig().get(
										"TEST_URL_CONFIG_2"));									
```
