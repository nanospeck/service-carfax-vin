

# Starting the application

1. Checkout the code and go to the root folder
2. **mvn spring-boot:run**
3. Open the Swagger UI at : **http://localhost:8080/swagger-ui.html#/**
4. Try out the queries right from the browser using sample data from swagger
5. Use VSSZZZ6JZ9R056308 as sample vin id
6. Run **mvn test** to generate test coverage in target folder

# Details


Following is the endpoint required:
GET http://localhost:8080/service-vin/v1/vin/{vin}

**Function:** Analyze the data points and detect odometerRollback

* Add a boolean field **isOdometerRollback** to the record if an odometer rollback has occured
* All other data fields are passed on as recieved


**ABOUT TESTS**

* Unit tests are in place majority of the use cases.
* Integration tests are included for relevant areas *eg. external api calls and controller testing*
* I've tried to follow proper TDD in Fail, Success & Refactor cycles. 
* 100% test coverage (excluding model objects)
* *JaCoCo* is used to measure coverage

**EXTERNAL DEPENDENCIES**

* Swagger
* JaCoCo

