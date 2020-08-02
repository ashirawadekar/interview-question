Tiny Expression Application
============================

The application can be used to shorten urls into tiny expressions.

The app can be started via spring-boot app. Run it (using `mvn spring-boot:run`) or in your favorite IDE.

If you prefer creating a jar file:

Create jar using the following command `mvn clean package` check the version number in pom.xml or look 
inside your `target` directory.

Once you have identified the jar file name, start the app using the following command.
```
java -jar target/tinyexpression-0.0.1.jar
``` 

Using the Tiny Expression Application
=====================================

The app supports two Restful Operations:

1. You can send a POST request to convert a long url into a tiny expression. If you prefer using `CURL` you 
execute the following command:

```
curl --location --request POST 'http://localhost:5000/short?url=https://stash.backbase.com/projects/PO/repos/payment-order-integration-spec/browse/src/main/resources/schemas/definitions.json#38'
``` 

The response should give you a tiny expression example : `asdsas`

2. You can send GET request to retrieve the long url using the tiny expression received previously.

```
curl --location --request GET 'http://localhost:5000/long?tiny=asdsas'
```

Accessing Test Reports
======================

The application is setup with `jacoco-maven-plugin` to create test report.

The reports can be accessed at the following location, use your IDE to generate reports.

```
./target/site/jacoco/index.html
```
