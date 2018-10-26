package com.carfax.vin.util;

import com.carfax.vin.factory.ObjectFactory;
import com.carfax.vin.model.VinData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class DataReaderUtilIntegrationTest {
    public static final String CARFAX_URL_VIN = "https://s3-eu-west-1.amazonaws.com/coding-challenge.carfax.eu/";
    public static final String CARFAX_URL_VIN_FULL = "https://s3-eu-west-1.amazonaws.com/coding-challenge.carfax.eu/VSSZZZ6JZ9R056308";

    @InjectMocks
    private DataReaderUtil dataReaderUtil;

    @Mock
    private ObjectFactory objectFactory;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(dataReaderUtil, "carfaxUrl", CARFAX_URL_VIN);
    }

    @Test
    public void testExternalUrlCallWorking() {
        when(objectFactory.createRestTemplate()).thenReturn(Mockito.mock(RestTemplate.class));
        when(objectFactory.createRestTemplate().getForObject(CARFAX_URL_VIN_FULL, VinData.class)).thenReturn(getVinTestData());
        VinData d = dataReaderUtil.getVinData("VSSZZZ6JZ9R056308");
        Assert.notNull(d);
        assertEquals(5, d.getRecords().size());
    }

    private VinData getVinTestData() {
        VinData data = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            data = mapper.readValue(new File(Paths.get(".").toAbsolutePath().normalize().toString() + "\\src\\test\\resources\\test.json"), VinData.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }
}
