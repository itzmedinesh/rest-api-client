package com.itzmeds.rac.core.client;

import com.itzmeds.rac.core.ServiceUrlConfig;

/**
 * @author itzmeds Contract that provides operations to create REST API web
 *         target template. The template object is used to perform CRUD
 *         operations on the RESTful resource.
 *
 * @param <T>
 *            REST API web target template
 */
public interface ServiceClient<T> {

	/**
	 * @param Key
	 *            provided for a REST API configuration in the YAML file. It can be
	 *            any arbitrary string literal to identify URL configuration of a
	 *            RESTful service
	 * @param serviceUrlConfiguration
	 *            REST API URL configuration object
	 * @return REST API web target template
	 */
	public T createClientTemplate(String urlConfigKey, ServiceUrlConfig serviceUrlConfiguration);

}
