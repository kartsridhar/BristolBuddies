### System Architecture

* Since we are implementing a social application, we have chosen to build a Client-Server application.
* The three main components of our system is going to be the Application, the Server and the Database since we need to store information about the user as well as implement the functionality required by the system.
* We are integrating the main functions of the system with the Application which we are going to use to build the front-end part of the architecture. The users are only going to only interact with this part of the system directly.
* The Application then proceeds to interact with the server and provides the user details which allows the server to carry out the matching process for a student by checking for the closest possible match with all the buddies .
* The database stores the information from the application and the server so this data can be retrieved from when the user logs back in and to minimise the amount of data stored locally.This data includes the history of the messages sent, match information, passwords and information about the upcoming events.
* We will use SQL within the database to handle requests from the application to some extent.However, most of the interactions our database would be through the Spring Framework.
* We will use APIs to handle communication between all 3 major components in our system.

### UML Class Diagrams

#### Static UML Example:
![](https://i.imgur.com/cp8Sn7m.jpg)

#### Dynamic UML Example:
![](https://i.imgur.com/OUrxFQB.jpg)

### Student User
In this class, we are taking in the unique ID of the student, their name, age, email, gender , course and password. The first function we will implement in our application is *getDetails*() which gets all the data inputted by the user in the front end. *register* () stores this data in the database and *retrieveDetails*(id,Password) returns this data from the database to check if login credentials are the same.

### Questionnaire
In the questionnaire class, we are taking in a bitset which stores which options the users picked for their questionaire. *getselectedOptions()* does precisely this by getting the options that the user selected in the questionaire part of the registration process. *converttoList()* - converts these options into a bitset so it is easier to work with.
*storeOptions*() - stores this bitset in the database.

### Matching
In the Matching class, we are taking in the student bitset as well as the list of buddy bitsets containing the questionaire answers for the buddies. Better match is the index of the buddyLists which contains the bitset of the buddy with the highest matching score. betterScore is the variable that gets updated each time a higher score for the match is found.
*retrieveStudentList(id)* - retrieves the bitset of the student's questionaire answers from the database.
*retrieveBuddyList(id)*- Applies the same functionality as the above but for the Buddy.
*CalculateDistance(Bitset, List <Bitset)* - Calculates the overall score of the match by comparing the student bitset with each of the buddy bitsets to come up with the match.
*getBuddyName*(int) - returns the name of the Buddy from their score.
*storeMatch*(StudentID, BuddyID)- stores information about the matching in the database.
*SendMatch()* - updates the front-end for the matching that has been made.

### Events
In the Events class, we are taking in the date as a field as well as the event name and description.
*checkEvents(dateField)*- updates the events in the front-end.This is performed every time the user opens the application.

## High Level Diagram

![](https://i.imgur.com/LpRdoJb.jpg)

### Application
This forms the front-end for the User so it contains most of the functionality which includes the User-Interface, Registration features as well as the Community and messaging features.

### Server Side
The Applications interacts with the server which carries out the student-buddy matching while supporting a database through the Spring framework. It communicates with the database to store information about the users and storing the messages which is ouputted everytime the application requests from the server.

### Database
This stores information about the Users and we will use the database to make selects for certain subsets of information about the users so we do not require the user to keep their own local copy of data.
