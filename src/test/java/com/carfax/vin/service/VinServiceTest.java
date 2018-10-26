package com.carfax.vin.service;

import com.carfax.vin.factory.ObjectFactory;
import com.carfax.vin.helper.TestDataHelper;
import com.carfax.vin.util.DataReaderUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.when;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.any;

import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.Assert;

import static org.junit.Assert.*;

import com.carfax.vin.exceptions.VinNotFoundException;
import com.carfax.vin.model.VinData;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Paths;

@RunWith(SpringJUnit4ClassRunner.class)
public class VinServiceTest {

    public static final String CARFAX_URL_VIN = "https://s3-eu-west-1.amazonaws.com/coding-challenge.carfax.eu/";

    @InjectMocks
    private VinService vinService;

    @Mock
    private DataReaderUtil dataReaderUtil;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

    }

    @Test(expected = VinNotFoundException.class)
    public void testVinNotFoundExceptionEmptyInput() {
        when(dataReaderUtil.getVinData("")).thenThrow(new VinNotFoundException(""));
        VinData result = vinService.analyzeDataPoints("");
    }


    @Test(expected = VinNotFoundException.class)
    public void testVinNotFoundExceptionInvalidVin() {
        String invalidVin = "-1";
        when(dataReaderUtil.getVinData(invalidVin)).thenThrow(new VinNotFoundException(invalidVin));
        VinData result = vinService.analyzeDataPoints(invalidVin);
    }

    @Test
    public void testNotNullResult() {
        String validVin = "VSSZZZ6JZ9R056308";
        when(dataReaderUtil.getVinData(validVin)).thenReturn(TestDataHelper.getVinMockData());
        VinData result = vinService.analyzeDataPoints(validVin);
        Assert.notNull(result);
    }

    @Test
    public void testGetOdometerValue() {
        String validVin = "VSSZZZ6JZ9R056308";
        when(dataReaderUtil.getVinData(validVin)).thenReturn(TestDataHelper.getVinMockData());
        VinData result = vinService.analyzeDataPoints(validVin);
        assertEquals(Integer.valueOf(10010),Integer.valueOf(result.getRecords().get(0).getOdometerReading()) );
    }

    @Test
    public void testGetOdometerRollbackTrue() {
        String validVin = "VSSZZZ6JZ9R056308";
        when(dataReaderUtil.getVinData(validVin)).thenReturn(TestDataHelper.getVinMockData());
        VinData result = vinService.analyzeDataPoints(validVin);

        assertEquals(null, result.getRecords().get(0).getIsOdometerRollback());
        assertEquals(null, result.getRecords().get(1).getIsOdometerRollback());
        assertEquals(null, result.getRecords().get(2).getIsOdometerRollback());
        assertEquals(null, result.getRecords().get(4).getIsOdometerRollback());
        assertEquals(true, result.getRecords().get(3).getIsOdometerRollback());
    }


}
