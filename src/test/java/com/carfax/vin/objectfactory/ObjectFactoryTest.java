package com.carfax.vin.objectfactory;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import com.carfax.vin.factory.ObjectFactory;

public class ObjectFactoryTest {

	@InjectMocks
	private ObjectFactory objectFactory;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateNewRegisterResponse() {
		assertEquals(RestTemplate.class, objectFactory.createRestTemplate().getClass());
	}
}