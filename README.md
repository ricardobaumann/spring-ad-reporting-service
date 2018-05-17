# Spring ad reporting service

A simple spring boot service to load and summarize ad metric information.

## Requirements
- Java 8+
- Gradle

## Running
- On the root folder run `./gradlew bootRun -Dinput.data.path=<path_to_data_files>`
- You can use the csv files on the root folder to give it a try!

## API Usage
This project uses Swagger for API documentation and testing. So, if you're running it locally, you can access `http://localhost:8080/swagger-ui.html#/` on your broser and try it the API. 

## Developer notes
I took the decision to not include some of the requirements, like:
- The `month` param is an integer, and does not allow alpha numeric input. I think a REST API should be simple and straight forward, without those parameter mappings.
- I did not enforce the rounding of decimal numbers. I think this should be done by the UI, because its more related to data presentation.
- I used a simple embedded relational database to store the data. I guess Elastic Search could be also a good option for this, but my time was a bit short. 
- There is a lot of things that could be upgraded in this application. I just tried to provide a simple view of my coding style. There is a lot of skills and techniques I use on my daily tasks that are not shown here.