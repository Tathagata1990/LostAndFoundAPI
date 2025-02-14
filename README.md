This API provides the following endpoints:

* POST /api/lost-items/upload : Upload a PDF document containing lost items
Note: The PDF file is present in the resources. The item name, quantity and place are comma separated

* GET /api/lost-items : Provides a list of all the items currently in the database

* POST /api/claims/claim : Allows users to claim one or more lost items of the same type

* GET /api/claims : Provides a list of claimed items and associated users

* GET /api/user/{userId} : Provides the details of the user for which the user id has been passed as a parameter
Note: This is a mock endpoint

The project can be compiled and tested by the following maven command

**mvn test**

To run the integration tests, use the following command

**mvn verify**

To run the application, use the following command or the application can also be run from within IntelliJ

**mvn spring-boot:run**

Once the application is running, the following endpoints become available

**Swagger documentation url**: http://localhost:8080/swagger-ui/index.html

**Embedded h2 database console**: http://localhost:8080/h2-console (login with username: sa & password: password)

The Swagger documentation can be used to view and execute the REST endpoints.
The h2 console can be used to view/add/modify data to the embedded database.