
package com.carfax.vin.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({ "vin", "date", "data_provider_id", "odometer_reading", "service_details" })
public class Record {

	/**
	 * Vehicle identification number. (Required)
	 * 
	 */
	@JsonProperty("vin")
	@JsonPropertyDescription("Vehicle identification number.")
	@NotNull
	private String vin;
	/**
	 * Date following format 'YYYY-MM-DD'. (Required)
	 * 
	 */
	@JsonProperty("date")
	@JsonPropertyDescription("Date following format 'YYYY-MM-DD'.")
	@NotNull
	private String date;
	/**
	 * Data provider id (Required)
	 * 
	 */
	@JsonProperty("data_provider_id")
	@JsonPropertyDescription("Data provider id")
	@NotNull
	private Integer dataProviderId;
	/**
	 * Odometer reading in KM (Required)
	 * 
	 */
	@JsonProperty("odometer_reading")
	@JsonPropertyDescription("Odometer reading in KM")
	@NotNull
	private Integer odometerReading;
	/**
	 * List of service details e.g Oil changed, Tires rotated, etc (Required)
	 * 
	 */
	@JsonProperty("service_details")
	@JsonPropertyDescription("List of service details e.g Oil changed, Tires rotated, etc")
	@Valid
	@NotNull
	private List<String> serviceDetails = null;
	@JsonIgnore
	@Valid
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();


	/**
	 * Vehicle identification number. (Required)
	 *
	 */
	@JsonProperty("isOdometerRollback")
	@JsonPropertyDescription("Odometer rollback.")
	private Boolean isOdometerRollback;

	/**
	 * Vehicle identification number. (Required)
	 * 
	 */
	@JsonProperty("vin")
	public Object getVin() {
		return vin;
	}

	/**
	 * Vehicle identification number. (Required)
	 * 
	 */
	@JsonProperty("vin")
	public void setVin(String vin) {
		this.vin = vin;
	}

	/**
	 * Date following format 'YYYY-MM-DD'. (Required)
	 * 
	 */
	@JsonProperty("date")
	public Object getDate() {
		return date;
	}

	/**
	 * Date following format 'YYYY-MM-DD'. (Required)
	 * 
	 */
	@JsonProperty("date")
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Data provider id (Required)
	 * 
	 */
	@JsonProperty("data_provider_id")
	public Integer getDataProviderId() {
		return dataProviderId;
	}

	/**
	 * Data provider id (Required)
	 * 
	 */
	@JsonProperty("data_provider_id")
	public void setDataProviderId(Integer dataProviderId) {
		this.dataProviderId = dataProviderId;
	}

	/**
	 * Odometer reading in KM (Required)
	 * 
	 */
	@JsonProperty("odometer_reading")
	public Integer getOdometerReading() {
		return odometerReading;
	}

	/**
	 * Odometer reading in KM (Required)
	 * 
	 */
	@JsonProperty("odometer_reading")
	public void setOdometerReading(Integer odometerReading) {
		this.odometerReading = odometerReading;
	}

	/**
	 * List of service details e.g Oil changed, Tires rotated, etc (Required)
	 * 
	 */
	@JsonProperty("service_details")
	public List<String> getServiceDetails() {
		return serviceDetails;
	}

	/**
	 * List of service details e.g Oil changed, Tires rotated, etc (Required)
	 * 
	 */
	@JsonProperty("service_details")
	public void setServiceDetails(List<String> serviceDetails) {
		this.serviceDetails = serviceDetails;
	}

	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}

	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}

	/**
	 * Odometer rollback property
	 *
	 */
	@JsonProperty("isOdometerRollback")
	public Object getIsOdometerRollback() {
		return isOdometerRollback;
	}

	/**
	 * Odometer rollback property
	 *
	 */
	@JsonProperty("isOdometerRollback")
	public void setIsOdometerRollback(Boolean isOdometerRollback) {
		this.isOdometerRollback = isOdometerRollback;
	}
}
