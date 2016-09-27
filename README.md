#university-test

This application provides an api to retrieve the answers to the following questions regarding the relationships between university classes, professors, and students (supplied via an input csv file [Class(String), Professor(String), Student ID(Integer)]).

* List class sections being taught (i.e., unique Class/Professor pairs)
* List classes being taken by each student
* List professors that teach at least two different classes that have two or more of the same students in each class they teach.  For  each professor that meets the criterion, list his/her classes and the common students. 
* How many students are registered for each Class?  List them.
* How many students take more than one Class?  List them.
* How many professors teach more than one Class?

## Module Summary
This is a Java based application using the [Dropwizard](http://www.dropwizard.io/1.0.2/docs/ "Dropwizard") framework.  The application is composed of 3 main modules.

### university-api: 
* bootstraps the dropwizard application (setting up google inject for DI, declaring the api, reading configuration, etc)
* has a dependency on the university-domain module to fetch domain model relationships
* uses the information returned from the domain model module to build reports
    
### university-domain
* a very simple module, for the most part a pass thru to the university-repository module
* exposes a set of services to fetch classes, professors, and student domain objects
* has a depdendency on university-repository to fetch entities from the database
* will map entities to domain objects
    
### university-repository
* does the heavy lifting of mimic-ing a data layer
* responsible for reading a provided csv file (location specified in configuration.yml file)
* will build relationships between class, professor, and students
* exposes repositories (IUniversityClassRepository, IProfessorRepository, IStudentRepository) to fetch entities

### university-dist
* has a dependency on all other modules
* run application from target of this module as explained later

## Installation
1) This is a maven project.  After pulling this repository, simply issue the following command to build the application.
> mvn clean package

2) upon successful build, update the configuration.yml file at root of repository
   - update inputFile to point to location of csv to init db
   - update log level if required

3) navigate to university-dist\target  directory and issue the following command
> java -jar university-dist-1.0-SNAPSHOT.jar server configuration.yml

4) jetty server should be up and running listening on port 8080

## Usage
1) open a browser

2) if not already installed it may be a good idea to have a json viewer extension installed to more clearly see the report output
   ie JSONView Chrome extension

3) hit this endpoint http://localhost:8080/report

4) should see the output answering the questions above

5) to update db data, simply update input.csv, and reload the page

## Testing
To run the existing unit tests issue the following from the repository root
> mvn test

## Simulation Test

The simulation test was done by using a feature of the dropwizard framework which as part of a test will spin up
the application, and run the load test before shutting down.

This test is called the RequestSimulationTest, and will kick off 100 async requests to the /report endpoint.

This is driven as part of issuing the mvn test command, in which you will see output similar to the following
showing request start/complete logging.

INFO  [2016-09-27 13:41:50,912] com.reddy.university.api.resources.ReportingResource: starting request 8af9d62a-9f6f-4f6a-8f00-3d566a95b3e7
INFO  [2016-09-27 13:41:50,913] com.reddy.university.api.resources.ReportingResource: starting request ee5ebea0-a27f-4b5e-99b7-69b3aa5bd613
INFO  [2016-09-27 13:41:50,913] com.reddy.university.api.resources.ReportingResource: starting request 54d3bcda-a947-40d2-9209-760cd10112cb
INFO  [2016-09-27 13:41:50,913] com.reddy.university.api.resources.ReportingResource: starting request 1c988630-eb35-4bc0-851d-46ea86069f02
...
INFO  [2016-09-27 13:41:51,013] com.reddy.university.api.resources.ReportingResource: starting request 853dc7cb-0221-4cbb-8929-d706bb2ddd6a
INFO  [2016-09-27 13:41:51,016] com.reddy.university.api.resources.ReportingResource: completing request 936c8431-1600-4ec2-99b1-d5eac943cb3d
INFO  [2016-09-27 13:41:51,023] com.reddy.university.api.resources.ReportingResource: completing request 24896adf-f904-496f-af36-9f61cf96ee24
INFO  [2016-09-27 13:41:51,033] com.reddy.university.api.resources.ReportingResource: starting request ed22bce3-6ddf-4722-aa87-0d873c853d7e
INFO  [2016-09-27 13:41:51,036] com.reddy.university.api.resources.ReportingResource: completing request a19bab63-d19b-4140-b352-e1732533226d
INFO  [2016-09-27 13:41:51,038] com.reddy.university.api.resources.ReportingResource: completing request 02b9afdd-122b-4726-977e-4696e8d36db0
INFO  [2016-09-27 13:41:51,044] com.reddy.university.api.resources.ReportingResource: starting request d106563a-dc21-43da-8208-25da99077c48
INFO  [2016-09-27 13:41:51,046] com.reddy.university.api.resources.ReportingResource: starting request b934edf6-a640-403b-8be9-56627950c641
INFO  [2016-09-27 13:41:51,049] com.reddy.university.api.resources.ReportingResource: completing request 35f7488f-102b-4576-ae3e-20035c90727d
