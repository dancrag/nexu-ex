# nexu-ex
Backend exercise for nexu hiring process

## Required software
This application was created using: 
- Maven v3.8.6
- Java 17

Be sure you have the required software

## Steps to run this application in local mode

1. Ensure you are inside the right folder
~~~
cd car-manager
~~~

2. First install with maven
~~~
mvn clean install
~~~

3. Run the application with spring boot
~~~
mvn spring-boot:run
~~~

4. The application will run in localhost:8080

**Note: the DB is volatile and it'll be populated every start of application with the src/main/resources/data.sql file**

## Requests
1. List all brands
~~~
METHOD: GET
/brands
~~~

response 200 ok
~~~
[
    {"id": 1, "nombre": "Mazda", "average_price": 500000}
]
~~~

2. List all models of the brand
~~~
METHOD: GET
/brands/:id/models
~~~

response 200 ok
~~~
[
    {"id": 1, "nombre": "3", "average_price": 300000}
]
~~~

3. Add new brand
~~~
METHOD: POST
/brands
~~~

request 200 ok
~~~
{ "name": "BMW" }
~~~

response 200 ok
~~~
{ "id": 2, "name": "BMW", "models": null, "average_price": null }
~~~