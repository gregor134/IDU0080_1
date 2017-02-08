# IDU0080_01

## Running/installation:

Create a postgres database with name "idu_0080" or change the appropriate values in both:
* app/src/main/resources/application.properties
* service/src/main/resources/application.properties

Run the service first to make it create the database tables.
Then run the app.

## Navigation:
### APP:
1. http://localhost:8090/ - index
2. http://localhost:8090/rest/external/computers - external service
3. http://localhost:8090/rest/computers - all computers
4. http://localhost:8090/rest/computers/search?make=xxx - search by make like ...
5. http://localhost:8090/rest/computers/search?model=xxx - search by model like ...
6. http://localhost:8090/rest/computers/search?processor=xxx - search by processor like ...

### SERVICE:
1. http://localhost:8080/service/computers

---
Gregor Johannson