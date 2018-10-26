package com.carfax.vin.controller;

import com.carfax.vin.exceptions.VinNotFoundException;
import com.carfax.vin.helper.TestDataHelper;
import com.carfax.vin.service.VinService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Paths;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(VinController.class)
public class VinControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private VinService service;
    private String path = Paths.get(".").toAbsolutePath().normalize().toString() + "\\src\\test\\resources\\test.json";

    @Test
    public void analyzeDataPointsShouldReturnJsonDataOfVin() throws Exception {
        when(service.analyzeDataPoints(anyString())).thenReturn(TestDataHelper.getVinMockData());
        this.mockMvc.perform(get("/service-vin/v1/vin/VSSZZZ6JZ9R056308")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString(TestDataHelper.getVinMockDataString())));
    }


    @Test
    public void analyzeDataPointsShouldReturnError() throws Exception {
        when(service.analyzeDataPoints(anyString())).thenThrow(VinNotFoundException.class);
        this.mockMvc.perform(get("/service-vin/v1/vin/VSSZZZ6JZ9R056308")).andDo(print()).andExpect(status().isNotFound());
    }


}