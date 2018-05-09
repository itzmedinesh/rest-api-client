package com.itzmeds.rac.core.client;

import java.util.Map;

import javax.ws.rs.client.Entity;

/**
 * @author itzmeds Contract that provides methods to perform CRUD operations on
 *         any defined web service. The implementation of the contract can be
 *         REST, SOAP etc.
 *
 * @param <T>
 *            refers to the return object type of the CRUD operations
 */
public interface ClientTemplate<T> {

	/**
	 * Performs POST call on the WEB API.
	 * 
	 * @param queryParams
	 *            - Key Value Pairs that appears as query parameters in the WEB
	 *            service access URL.
	 * 
	 * @param <M>
	 *            - M is of type 'String' for single valued query parameter and type
	 *            'List' for multi-valued query parameters.
	 * 
	 * @param pathParams
	 *            - Key value pairs for dynamic path parameters in the WEB service
	 *            access URL. Key can be arbitrary string literal. Ordered map
	 *            implementations should be used as input such as LinkedHashMap. WEB
	 *            service URL is formed of path parameters in the same order
	 *            provided in the input map.
	 * 
	 * @param requestEntity
	 *            - Web API POST body
	 * 
	 * @param <E>
	 *            - entity type of the WEB API POST body, such as 'json', 'form',
	 *            'xml'
	 * 
	 * @return returnType
	 */
	public <M, E> T create(Map<String, M> queryParams, Map<String, String> pathParams, Entity<E> requestEntity);

	/**
	 * Performs GET call on the WEB API.
	 * 
	 * @param <M>
	 *            - M is of type 'String' for single valued query parameter and type
	 *            'List' for multi-valued query parameters
	 * 
	 * @param queryParams
	 *            - Key Value Pairs that appears as query parameters in the WEB
	 *            service access URL.
	 * 
	 * @param pathParams
	 *            - Key value pairs for dynamic path parameters in the WEB service
	 *            access URL. Key can be arbitrary string literal. Ordered map
	 *            implementations should be used as input such as LinkedHashMap. WEB
	 *            service URL is formed of path parameters in the same order
	 *            provided in the input map.
	 * 
	 * @return returnType
	 */
	public <M> T retrieve(Map<String, M> queryParams, Map<String, String> pathParams);

	/**
	 * Performs PUT call on the WEB API
	 * 
	 * @param <M>
	 *            - M is of type 'String' for single valued query parameter and type
	 *            'List' for multi-valued query parameters
	 * 
	 * @param queryParams
	 *            - Key Value Pairs that appears as query parameters in the WEB
	 *            service access URL.
	 * 
	 * @param pathParams
	 *            - Key value pairs for dynamic path parameters in the WEB service
	 *            access URL. Key can be arbitrary string literal. Ordered map
	 *            implementations should be used as input such as LinkedHashMap. WEB
	 *            service URL is formed of path parameters in the same order
	 *            provided in the input map.
	 * 
	 * @param requestEntity
	 *            - Web API POST body
	 * 
	 * @param <E>
	 *            - entity type of the WEB API POST body, such as 'json', 'form',
	 *            'xml'
	 * 
	 * @return returnType
	 */
	public <M, E> T update(Map<String, M> queryParams, Map<String, String> pathParams, Entity<E> requestEntity);

	/**
	 * Performs DELETE call on the WEB API.
	 * 
	 * @param <M>
	 *            - M is of type 'String' for single valued query parameter and type
	 *            'List' for multi-valued query parameters
	 * 
	 * @param queryParams
	 *            - Key Value Pairs that appears as query parameters in the WEB
	 *            service access URL.
	 * 
	 * @param pathParams
	 *            - Key value pairs for dynamic path parameters in the WEB service
	 *            access URL. Key can be arbitrary string literal. Ordered map
	 *            implementations should be used as input such as LinkedHashMap. WEB
	 *            service URL is formed of path parameters in the same order
	 *            provided in the input map.
	 * 
	 * 
	 * @return returnType
	 */
	public <M> T delete(Map<String, M> queryParams, Map<String, String> pathParams);

	/**
	 * Performs POST call on the basic authentication enabled WEB API.
	 * 
	 * @param <M>
	 *            - M is of type 'String' for single valued query parameter and type
	 *            'List' for multi-valued query parameters
	 * 
	 * @param queryParams
	 *            - Key Value Pairs that appears as query parameters in the WEB
	 *            service access URL.
	 * 
	 * @param pathParams
	 *            - Key value pairs for dynamic path parameters in the WEB service
	 *            access URL. Key can be arbitrary string literal. Ordered map
	 *            implementations should be used as input such as LinkedHashMap. WEB
	 *            service URL is formed of path parameters in the same order
	 *            provided in the input map.
	 * 
	 * @param requestEntity
	 *            - Web API POST body
	 * 
	 * @param <E>
	 *            - entity type of the WEB API POST body, such as 'json', 'form',
	 *            'xml'
	 * 
	 * @param basicAuthUid
	 *            - User id that is supplied as part of basic authentication headers
	 *            in the WEB API call
	 * 
	 * @param basicAuthPwd
	 *            - Password that is supplied as part of basic authentication
	 *            headers in the WEB API call
	 * 
	 * @return returnType
	 */
	public <M, E> T create(Map<String, M> queryParams, Map<String, String> pathParams, Entity<E> requestEntity,
			String basicAuthUid, String basicAuthPwd);

	/**
	 * Performs GET call on the WEB API.
	 * 
	 * @param <M>
	 *            - M is of type 'String' for single valued query parameter and type
	 *            'List' for multi-valued query parameters
	 * 
	 * @param queryParams
	 *            - Key Value Pairs that appears as query parameters in the WEB
	 *            service access URL.
	 * 
	 * @param pathParams
	 *            - Key value pairs for dynamic path parameters in the WEB service
	 *            access URL. Key can be arbitrary string literal. Ordered map
	 *            implementations should be used as input such as LinkedHashMap. WEB
	 *            service URL is formed of path parameters in the same order
	 *            provided in the input map.
	 * 
	 * @param basicAuthUid
	 *            - User id that is supplied as part of basic authentication headers
	 *            in the WEB API call
	 * 
	 * @param basicAuthPwd
	 *            - Password that is supplied as part of basic authentication
	 *            headers in the WEB API call
	 * 
	 * @return returnType
	 */
	public <M> T retrieve(Map<String, M> queryParams, Map<String, String> pathParams, String basicAuthUid,
			String basicAuthPwd);

	/**
	 * Performs PUT call on the WEB API.
	 * 
	 * @param <M>
	 *            - M is of type 'String' for single valued query parameter and type
	 *            'List' for multi-valued query parameters
	 * 
	 * @param queryParams
	 *            - Key Value Pairs that appears as query parameters in the WEB
	 *            service access URL.
	 * 
	 * @param pathParams
	 *            - Key value pairs for dynamic path parameters in the WEB service
	 *            access URL. Key can be arbitrary string literal. Ordered map
	 *            implementations should be used as input such as LinkedHashMap. WEB
	 *            service URL is formed of path parameters in the same order
	 *            provided in the input map.
	 * 
	 * @param requestEntity
	 *            - Web API POST body
	 * 
	 * @param <E>
	 *            - entity type of the WEB API POST body, such as 'json', 'form',
	 *            'xml'
	 * 
	 * @param basicAuthUid
	 *            - User id that is supplied as part of basic authentication headers
	 *            in the WEB API call
	 * 
	 * @param basicAuthPwd
	 *            - Password that is supplied as part of basic authentication
	 *            headers in the WEB API call
	 * 
	 * @return returnType
	 */
	public <M, E> T update(Map<String, M> queryParams, Map<String, String> pathParams, Entity<E> requestEntity,
			String basicAuthUid, String basicAuthPwd);

	/**
	 * Performs DELETE call on the WEB API.
	 * 
	 * @param <M>
	 *            - M is of type 'String' for single valued query parameter and type
	 *            'List' for multi-valued query parameters
	 * 
	 * @param queryParams
	 *            - Key Value Pairs that appears as query parameters in the WEB
	 *            service access URL.
	 * 
	 * @param pathParams
	 *            - Key value pairs for dynamic path parameters in the WEB service
	 *            access URL. Key can be arbitrary string literal. Ordered map
	 *            implementations should be used as input such as LinkedHashMap. WEB
	 *            service URL is formed of path parameters in the same order
	 *            provided in the input map.
	 * 
	 * @param basicAuthUid
	 *            - User id that is supplied as part of basic authentication headers
	 *            in the WEB API call
	 * 
	 * @param basicAuthPwd
	 *            - Password that is supplied as part of basic authentication
	 *            headers in the WEB API call
	 * 
	 * @return returnType
	 */
	public <M> T delete(Map<String, M> queryParams, Map<String, String> pathParams, String basicAuthUid,
			String basicAuthPwd);

	/**
	 * Performs POST call on the basic authentication enabled WEB API.
	 * 
	 * @param <M>
	 *            - M is of type 'String' for single valued query parameter and type
	 *            'List' for multi-valued query parameters
	 * 
	 * @param queryParams
	 *            - Key Value Pairs that appears as query parameters in the WEB
	 *            service access URL.
	 * 
	 * @param pathParams
	 *            - Key value pairs for dynamic path parameters in the WEB service
	 *            access URL. Key can be arbitrary string literal. Ordered map
	 *            implementations should be used as input such as LinkedHashMap. WEB
	 *            service URL is formed of path parameters in the same order
	 *            provided in the input map.
	 * 
	 * @param requestEntity
	 *            - Web API POST body
	 * 
	 * @param <E>
	 *            - entity type of the WEB API POST body, such as 'json', 'form',
	 *            'xml'
	 * 
	 * @param accessToken
	 *            - Access token value supplied as part of 'bearer' token headers in
	 *            the WEB API call
	 * 
	 * @return returnType
	 */
	public <M, E> T create(Map<String, M> queryParams, Map<String, String> pathParams, Entity<E> requestEntity,
			String accessToken);

	/**
	 * Performs GET call on the WEB API.
	 * 
	 * @param <M>
	 *            - M is of type 'String' for single valued query parameter and type
	 *            'List' for multi-valued query parameters
	 * 
	 * @param queryParams
	 *            - Key Value Pairs that appears as query parameters in the WEB
	 *            service access URL.
	 * 
	 * @param pathParams
	 *            - Key value pairs for dynamic path parameters in the WEB service
	 *            access URL. Key can be arbitrary string literal. Ordered map
	 *            implementations should be used as input such as LinkedHashMap. WEB
	 *            service URL is formed of path parameters in the same order
	 *            provided in the input map.
	 * 
	 * @param accessToken
	 *            - Access token value supplied as part of 'bearer' token headers in
	 *            the WEB API call
	 * 
	 * @return returnType
	 */
	public <M> T retrieve(Map<String, M> queryParams, Map<String, String> pathParams, String accessToken);

	/**
	 * Performs PUT call on the WEB API.
	 * 
	 * @param <M>
	 *            - M is of type 'String' for single valued query parameter and type
	 *            'List' for multi-valued query parameters
	 * 
	 * @param queryParams
	 *            - Key Value Pairs that appears as query parameters in the WEB
	 *            service access URL.
	 * 
	 * @param pathParams
	 *            - Key value pairs for dynamic path parameters in the WEB service
	 *            access URL. Key can be arbitrary string literal. Ordered map
	 *            implementations should be used as input such as LinkedHashMap. WEB
	 *            service URL is formed of path parameters in the same order
	 *            provided in the input map.
	 * 
	 * @param requestEntity
	 *            - Web API POST body
	 * 
	 * @param <E>
	 *            - entity type of the WEB API POST body, such as 'json', 'form',
	 *            'xml'
	 * 
	 * @param accessToken
	 *            - Access token value supplied as part of 'bearer' token headers in
	 *            the WEB API call
	 * 
	 * @return returnType
	 */
	public <M, E> T update(Map<String, M> queryParams, Map<String, String> pathParams, Entity<E> requestEntity,
			String accessToken);

	/**
	 * Performs DELETE call on the WEB API.
	 * 
	 * @param <M>
	 *            - M is of type 'String' for single valued query parameter and type
	 *            'List' for multi-valued query parameters
	 * 
	 * @param queryParams
	 *            - Key Value Pairs that appears as query parameters in the WEB
	 *            service access URL.
	 * 
	 * @param pathParams
	 *            - Key value pairs for dynamic path parameters in the WEB service
	 *            access URL. Key can be arbitrary string literal. Ordered map
	 *            implementations should be used as input such as LinkedHashMap. WEB
	 *            service URL is formed of path parameters in the same order
	 *            provided in the input map.
	 * 
	 * @param accessToken
	 *            - Access token value supplied as part of 'bearer' token headers in
	 *            the WEB API call
	 * 
	 * @return returnType
	 */
	public <M> T delete(Map<String, M> queryParams, Map<String, String> pathParams, String accessToken);
}
