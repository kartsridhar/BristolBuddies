### System Architecture

* Since we are implementing a social application, we have chosen to build a Client-Server application.
* The three main components of our system is going to be the Application, the server and the database since we need to store information about the user as well as implement the functionality required by the system.
* We are integrating the main functions of the system with the Application which we are going to use to build the front-end part of the architecture. The users are only going to only interact with this part of the system directly.
* The Application then proceeds to interact with the server and provides the user details which allows the server to carry out the matching process for a student by checking for the closest possible match with all the buddies .
* The database stores the information from the application and the server so this data can be retrieved from when the user logs back in and to minimise the amount of data stored locally.
* We will use SQL within the database to handle requests from the application to some extent.However, most of the interactions our database would be through the Spring Framework.
