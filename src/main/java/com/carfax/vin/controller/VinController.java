package com.carfax.vin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.carfax.vin.exceptions.VinNotFoundException;
import com.carfax.vin.model.VinData;
import com.carfax.vin.service.VinService;

/**
 * Vin Controller class
 * 
 * @author Akhil Anil
 *
 */
@RestController
@RequestMapping("/service-vin/v1/*")
public class VinController {

	@Autowired
	VinService vinService;

	@GetMapping("/vin/{vin}")
	public ResponseEntity<VinData> analyzeDataPoints(@PathVariable String vin) {
		VinData data;
		try {
			data = vinService.analyzeDataPoints(vin);
			return new ResponseEntity<VinData>(data, HttpStatus.OK);

		} catch (VinNotFoundException e) {
			return new ResponseEntity<VinData>(HttpStatus.NOT_FOUND);
		}
	}
}
