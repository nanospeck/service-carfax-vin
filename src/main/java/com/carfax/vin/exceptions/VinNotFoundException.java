package com.carfax.vin.exceptions;

public class VinNotFoundException extends RuntimeException {

    public static final String NO_DATA_FOR_VIN_ID = "No data for VIN id:";

    public VinNotFoundException(String vin) {
        super(NO_DATA_FOR_VIN_ID + vin);
    }

}