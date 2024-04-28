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
1. Open a web browser or use a tool like Postman to send requests to the API endpoint.
    Example Browser URL: http://localhost:8080/user-info/octocat 
   
2. Powershell Example:
   ```bash
   $response = Invoke-RestMethod 'localhost:8080/user-info/octocat' -Method 'GET' -Headers $headers
   $response | ConvertTo-Json
   ```
3. Curl Example:
   ```bash
   curl --location 'localhost:8080/user-info/kwiknick'
   ```


