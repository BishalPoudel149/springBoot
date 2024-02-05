# Initial Setup for Application
Application contais the General Dependencies for having the RESTful Web APIs .
- Spring Web
- H2 Database for In Memory
- DebBoot for AUto Booting the Application while compiling
- Test Dependency for Testing the Application.

# Application Moto and Folder Structure
The Web app manages the data related to the Department , for the same we are using the various layer to have scalability and Readbility
- **Entity** : Which contains the entities for the Department
- **Controller**: Controller of the APIs , getting and sending back the response
- **Services**: Service Folder Structure contains the service interface and the class implementing that interface
- **Repository**: Repository is another interface which extends the JPARepository so that we can reuse the Already implemented and important methods.
