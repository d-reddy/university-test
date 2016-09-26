#university-test

This application provides an api to retrive the answer to the following questions regarding the relationships between university classes, professors, and students.

* List class sections being taught (I.e., unique Class/Professor pairs)
* List classes being taken by each student
* List professors that teach at least two different classes that have two or more of the same students in each class they teach.  For  each professor that meets the criterion, list his/her classes and the common students. 
* How many students are registered for each Class?  List them.
* How many students take more than one Class?  List them.
* How many professors teach more than one Class?

## Technical Design Summary
This is Java based application using the Dropwizard framework.  The application is composed of 3 main modules.

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
* does the heavy lifting of mimicing a data layer
* responsible for reading a provided csv file (location specified in configuration.yml file)
* will build relationships between class, professor, and students
* exposes repositories (IUniversityClassRepository, IProfessorRepository, IStudentRepository) to fetch entities

## Installation
1) This is a maven project.  After pulling this repository, simply issue the following command to build the application.
> mvn clean package

2) upon successful build, update configuration.yml file at root of repository
   - update inputFile to point to location of csv to init db
   - update log level if required

3) navigate to university-dist\target  directory and issue the following command
> java -jar .\university-dist-1.0-SNAPSHOT.jar server ..\\..\\configuration.yml

4) jetty server should be up and running listening on port 8080

## Usage
1) open a browser

2) if not already installed it may be a good idea to have a json viewer extension installed
   ie JSONView Chrome extension

3) hit this endpoint http://localhost:8080/report

4) should see the output answering the questions above

## Testing
To run the existing unit tests issue the following from the repository root
> mvn test
