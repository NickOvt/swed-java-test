## swed-java-test
### This is a Swedbank test done in Java.

### The test is about an API for a Parking House with N floors each of which has some ceiling height and some total weight capacity.

###### Local devserver should start on localhost:8080

#### Available routes:
*localhost:8080~*  
- **/api/v1/parking** - `GET` | Root route for the API that displays a welcome message
- **/cars** - `GET` | Get all cars that are currently parked in the parking house. Returns an array of Car objects.
- **/cars/{id}** - `GET` | Get single car    
{id} - (String) plate number of a single car to get.  
Returns a single Car object or an error message if car with specified plate number was not found.
- **/cars/park** - `POST` | Park a single car.  
Pass in car as a JSON object with `weight` (int), `platenumber` (string), `height` (float).  
Returns a response with the parked car info and what floor it was parked to or an error message if there is any.
- **/cars/unpark/{id}** - `POST` | Unpark a single car.  
{id} - (String) plate number of a car to unpark.  
Returns a response: 
```  
{  
  carPlateNumber: "<Plate number of the deleted car>",  
  parkingPrice: (float) price of the parking  
}  
``` 
or an error message if there is any.
- **/floors**- `GET` | Get all floors
- **/floors/{id}** - `GET` | Get single floor
- **/floors/{id}/platenumbers** - `GET` | Get plate numbers of all parked cars on that floor
- **/initialize** - `POST` | Initialize a parking house with random floors.  
**Must run this before actually testing the API as there are no floors when the server is first started.**

#### How to run:
You can either run it directly in IntelliJ IDEA, or first build the project using gradle. For that enter the root of the project and run `./gradlew build` command and then run the created `.jar` file with `java -jar <name of the jar>.jar`.
Alternatively you can directly run the project by issuing a command `./gradlew bootRun` in the root of the App.
