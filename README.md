To make it work locally:

Create a postgres database with name "idu_0080" or change the appropriate values in both:
app/src/main/resources/application.properties
app/src/main/resources/application.properties

Run the service first to make it create the database tables.
Then run the app.

Navigate to the following urls:
APP:
http://localhost:8090/ - index
http://localhost:8090/rest/external/computers - external service
http://localhost:8090/rest/computers - all computers
http://localhost:8090/rest/computers/search?make=xxx - search by make like ...
http://localhost:8090/rest/computers/search?model=xxx - search by model like ...
http://localhost:8090/rest/computers/search?processor=xxx - search by processor like ...