package com.carfax.vin.helper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import org.springframework.stereotype.Component;

import com.carfax.vin.model.VinData;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestDataHelper {

    public static final String TEST_RESOURCES__JSON = "\\src\\test\\resources\\test.json";

    public static VinData getVinMockData() {
        VinData vinData = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            vinData = mapper.readValue(getTestFileData(),
                    VinData.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vinData;
    }

    public static String getVinMockDataString() {
        String jsonString = null;
        try {
            ObjectMapper mapper = new ObjectMapper();
            jsonString = mapper.writeValueAsString(getVinMockData());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonString;
    }

    private static File getTestFileData() {
        return new File(
                Paths.get(".").toAbsolutePath().normalize().toString() + TEST_RESOURCES__JSON);
    }
}
