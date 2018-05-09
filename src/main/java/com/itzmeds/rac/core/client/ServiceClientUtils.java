package com.itzmeds.rac.core.client;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.WebTarget;

/**
 * Utility class to construct query parameters and path parameters for the REST
 * API target URL
 * 
 * @author itzmeds
 *
 */
public class ServiceClientUtils {

	/**
	 * Adds query parameters to the REST API web target
	 * 
	 * @param <M>
	 *            - M is of type 'String' for single valued query parameter and type
	 *            'List' for multi-valued query parameters
	 * 
	 * @param queryParams
	 *            - map of query parameters
	 * @param restResource
	 *            - jersey web target
	 * @return WebTarget - web target with query parameters appended
	 */
	@SuppressWarnings("rawtypes")
	public static <M> WebTarget addQueryParameters(Map<String, M> queryParams, WebTarget restResource) {
		Iterator<String> queryParamIter = queryParams.keySet().iterator();
		while (queryParamIter.hasNext()) {
			String queryParamKey = queryParamIter.next();
			Object queryParamValue = queryParams.get(queryParamKey);

			if (queryParamValue instanceof List) {
				for (int i = 0; i < ((List) queryParamValue).size(); i++) {
					restResource = restResource.queryParam(queryParamKey, ((List) queryParamValue).get(i));
				}
			} else {
				restResource = restResource.queryParam(queryParamKey, queryParams.get(queryParamKey));
			}
		}
		return restResource;
	}

	/**
	 * Adds path parameters to the REST API web target
	 * 
	 * @param pathParams
	 *            - map of query parameters
	 * @param restResource
	 *            - jersey web target
	 * @return WebTarget - web target with path parameters appended
	 */
	public static WebTarget addPathParameters(Map<String, String> pathParams, WebTarget restResource) {
		Iterator<String> pathParamIter = pathParams.keySet().iterator();
		while (pathParamIter.hasNext()) {
			String pathParamKey = pathParamIter.next();
			restResource = restResource.path(pathParams.get(pathParamKey));
		}
		return restResource;
	}

}
