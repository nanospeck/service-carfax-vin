package com.carfax.vin.util;

import com.carfax.vin.exceptions.VinNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.carfax.vin.factory.ObjectFactory;
import com.carfax.vin.model.VinData;
import org.springframework.util.StringUtils;

@Component
public class DataReaderUtil {
    @Autowired
    ObjectFactory objectFactory;

    @Value("${carfax.url.vin}")
    private String carfaxUrl;

    public VinData getVinData(String vin) {
        VinData data = null;
        try {
            String url = carfaxUrl + vin;
            data = objectFactory.createRestTemplate().getForObject(url, VinData.class);
            if (StringUtils.isEmpty(data)||StringUtils.isEmpty(data.getRecords())) {
                throw new VinNotFoundException(vin);
            }
        } catch (Exception e) {
            throw new VinNotFoundException(vin);
        }
        return data;
    }

}
