# README

## Overview

The `test-spring-ndtx` project builds a Spring Boot application/service that provides a REST endpoint.
This endpoint can be used to query the service and retrieve the applicable price for the date, product and brand.

### Request parameters

The application/service accepts there 3 parameters as part of the query string:

| Parameter name  | Parameter type    | Required? | Format              |
| --------------- | ----------------- | --------- | ------------------- |
| applicationDate | java.util.Date    | YES       | yyyy-MM-dd-HH.mm.ss |
| productId       | java.lang.Integer | YES       | N/A                 |
| brandId         | java.lang.Integer | YES       | N/A                 |

### Request Headers

The application/service requires these 2 headers to be correctly set:

| Header name  | Header value     | Required? |
| ------------ | ---------------- | --------- |
| Content-Type | application/json | YES       |
| Accept       | application/json | YES       |

### Responses

The application/service responses in JSON format. A typical response is like this:

```json
{
  "price": 00.00,
  "brandId": 0,
  "productId": 00000,
  "startDate": "0000-00-00-00.00.00",
  "endDate": "0000-00-00-00.00.00"
}
```

In case of an error, you will be getting a response like this;

```json
{
  "message": "Price not found"
}
```

## How to test the application/service

### Pre-requisites

In order to be able to build and run the application/service, you need to have the following dependendies already installed in your laptop:

- Java: version 15 was used during the development.
- Maven: version 3.6.3 was used during the development

### Step-by-step process

Clone the repository:

```bash
git clone https://github.com/robegf/test-spring-ndtx.git
```

Run the application/service:

```bash
mvn spring-boot:run
```

Once the startup process has completed you should be able to test the service like this;

```bash
curl --request GET --url http://localhost:8080/api/prices\?applicationDate\=2020-06-14-10.00.00\&productId\=35455\&brandId\=1 --header 'Content-Type: application/json' --header 'Accept: application/json'
```

If the application/service has been installed and is running correctly, you should see the following response:

```json
{
  "price": 35.5,
  "brandId": 1,
  "productId": 35455,
  "startDate": "2020-06-14-00.00.00",
  "endDate": "2020-12-31-23.59.59"
}
```
