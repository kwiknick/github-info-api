# Github Info API

## Description
This project is an API that when passed a valid Github User username, will return publically available information.

## Prerequisites
- Java Development Kit (JDK) installed
- Maven installed
- Git installed (optional)

## Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/kwiknick/github-info-api.git
   ```
   *If you don't have Git installed, you can download the repository as a ZIP file and extract it.*


2. Navigate to the project directory:
   ```bash
   cd <project-directory>
   ```

## Build
1. Run Maven clean install at the root of the project to build it:
   ```bash
   mvn clean install
   ```

## Running the Application
1. Execute the following command to run the application:
   ```bash
   java -jar .\target\github_info_api-0.0.1-SNAPSHOT.jar
   ```
   *Replace the version in the filename with the version of your JAR file.*

## Testing and Usage
1. Open a web browser or use a tool like **Postman** to send requests to the API endpoint. <br/>Example Browser URL: http://localhost:8080/user-info/octocat
2. You can check out the API using **Swagger Documentation** as well. <br>Go to this link in your browser and "Try it out": http://localhost:8080/swagger-ui/index.html 
   
3. Powershell Example:
   ```bash
   $response = Invoke-RestMethod 'localhost:8080/user-info/octocat' -Method 'GET' -Headers $headers
   $response | ConvertTo-Json
   ```
4. Curl Example:
   ```bash
   curl --location 'localhost:8080/user-info/kwiknick'
   ```

## Code Explanation
- I would like to provide a brief explanation on why I made some of the choices I did in this project.
In the controller, I decided to use ResponseEntity's even tho I think I could get away without doing so.  
I suppose it's just habit due to the ability to add additional information to the Response such as a Correlation Id, in order to track the life of the request through the application.
I didn't add a Correlation Id due to it seemed to be out of scope for the project.
Focusing on the input validation was one of my higher priorities for the controller, since it's important to let the
consumer of the API know as soon as possible if the param is incorrect.  Which also saves resources of unecessary API calls that will just fail.

- I try and keep my controllers precise, and put all of the business logic in the Service layer.  As you can see, the service has most of the heavy lifting.
I've implemented an Interface for the Service as well.  This Abstracts the implementation which makes the code more modular and easier to read.
The Interface helps with the maintenance of the code as well.  If the underlying Github APIs change, and I end up having to implement the calls a different way,
the contract is never broken with the Interface, so the consumers of it don't notice any change. The interface also makes it easier to test, given you can Mock the interface.

- I didn't create a log config for this, but would most likely do so for a project. Using log aggregation software is pretty standard now, and having the logs not in JSON format is definately something
that would make ingestion into the many logging tools.

- OpenAPI documentation, while it wasn't a requirement, I felt was important to include so that the API could be visualized as well as the ease of testing using the provided website.

- There is a Global Exception Handler that can be expanded on.  I use these to intercept Exceptions and modify the response using an Exception Response object to make the Response cleaner to the end user.
This helps me control the message and potentially hide any information that could create an entrypoint for a potential cyber attack.