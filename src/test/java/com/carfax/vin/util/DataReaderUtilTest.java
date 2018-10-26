package com.carfax.vin.util;

import com.carfax.vin.exceptions.VinNotFoundException;
import com.carfax.vin.factory.ObjectFactory;
import com.carfax.vin.helper.TestDataHelper;
import com.carfax.vin.model.VinData;
import com.carfax.vin.service.VinService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
public class DataReaderUtilTest {

    public static final String CARFAX_URL_VIN = "https://s3-eu-west-1.amazonaws.com/coding-challenge.carfax.eu/";
    public static final String CARFAX_URL_VIN_FULL = "https://s3-eu-west-1.amazonaws.com/coding-challenge.carfax.eu/VSSZZZ6JZ9R056308";

    @InjectMocks
    private DataReaderUtil dataReaderUtil;

    @Mock
    private ObjectFactory objectFactory;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

    }

//    @Test(expected = VinNotFoundException.class)
//    public void testVinNotFoundExceptionEmptyInput() {
//        when(dataReaderUtil.getVinData("")).thenThrow(new VinNotFoundException(""));
//        VinData result = dataReaderUtil.getVinData("");
//    }
//
//
//    @Test(expected = VinNotFoundException.class)
//    public void testVinNotFoundExceptionInvalidVin() {
//        String invalidVin = "-1";
//        VinData result = dataReaderUtil.getVinData(invalidVin);
//    }

    @Test(expected = VinNotFoundException.class)
    public void testVinNotFoundExceptionForEmptyNull() {
        when(objectFactory.createRestTemplate()).thenReturn(Mockito.mock(RestTemplate.class));
        when(objectFactory.createRestTemplate().getForObject(CARFAX_URL_VIN_FULL, VinData.class)).thenReturn(null);
        VinData d = dataReaderUtil.getVinData("VSSZZZ6JZ9R056308");
    }

    @Test(expected = VinNotFoundException.class)
    public void testVinNotFoundExceptionForEmptyData() {
        when(objectFactory.createRestTemplate()).thenReturn(Mockito.mock(RestTemplate.class));
        when(objectFactory.createRestTemplate().getForObject(CARFAX_URL_VIN_FULL, VinData.class)).thenReturn(new VinData());
        VinData d = dataReaderUtil.getVinData("VSSZZZ6JZ9R056308");
    }

}
