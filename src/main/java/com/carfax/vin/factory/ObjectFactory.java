package com.carfax.vin.factory;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class ObjectFactory {

	/**
	 * Create {@link RestTemplate} object
	 * 
	 * @return {@link RestTemplate}
	 */
	public RestTemplate createRestTemplate() {
		return new RestTemplate();
	}

}
