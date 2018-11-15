### System Architecture

* Since we are implementing a social application, we have chosen to build a Client-Server application.
* The three main components of our system is going to be the Application, the Server and the Database since we need to store information about the user as well as implement the functionality required by the system.
* We are integrating the main functions of the system with the Application which we are going to use to build the front-end of the architecture. Only the users will interact with this part of the system directly.
* The Application proceeds to interact with the server and provides the user details which allows the server to carry out the matching process for a student by checking for the closest possible match with all the buddies.
* The database stores the information from the application and the server so this data can be retrieved from when the user logs back in. This also minimises the amount of data stored locally. This data includes the history of the messages sent, match information, passwords and information about the upcoming events.
* We are using MySQL within the database to handle requests from the application to an extent. However, most of the interactions our database would be through the Spring Framework.
* We are using APIs to handle communication between all three components in our system.

___

## High Level Diagram

![](HighLevel.jpg)

### Application
This forms the front-end for the User so it contains most of the functionality which includes the User-Interface, Registration features as well as the Community and Messaging features.

### Server Side
The application interacts with the server which carries out the student-buddy matching while supporting a database through the Spring framework. It communicates with the database to store information about the users and storing the messages which is output every time the application requests from the server.

### Database
Stores information about the Users. We will use the database to make selects for certain subsets of information about the users so that the user will not need to keep a local copy of their data.

___

### UML Class Diagrams

#### Static UML Example:
![](StaticUML.jpg)

#### Dynamic UML Example:
![](DynamicUML.jpg)

### Student User
In this class, we are taking in the unique ID of the student, their name, age, email, gender , course and password. The first function we implement in our application is:
* *getDetails()* - gets all the data entered by the user in the front end.
* *register()* - stores the data in the database.
* *retrieveDetails(id, Password)* - returns this data from the database to check if login credentials are the same.

### Questionnaire
In the Questionnaire class, we take in BitSet which stores the options the users picked for their questionnaire in the form of bits.
* *getSelectedOptions()* - does precisely this by getting the options that the user selected in the questionnaire (part of the registration process).
* *convertToList()* - converts these options into a BitSet.
* *storeOptions()* - stores this BitSet in the database.

### Matching
In the Matching class, we take in the student BitSet as well as the list of buddy BitSets containing the questionnaire answers for the buddies. *betterMatch* is the index of the buddyLists which contains the BitSet of the buddy with the highest matching score. *betterScore* is the variable that gets updated each time a higher score for the match is found.
* *retrieveStudentList(id)* - retrieves the BitSet of the student's questionnaire answers from the database.
* *retrieveBuddyList(id)* - Applies the same functionality as above but for the Buddy.
* *calculateDistance(BitSet, List<BitSet>)* - calculates the overall score of the match by comparing the student BitSet with each of the buddy BitSets to come up with the match.
* *getBuddyName*(int) - returns the name of the Buddy from their score.
* *storeMatch*(StudentID, BuddyID)- stores information about the match in the database.
* *sendMatch()* - updates the front-end for the matching that has been made.

### Events
In the Events class, we are taking in the date as a field as well as the event name and description.
* *checkEvents(dateField)*- updates the events in the front-end. This is performed every time the user opens the application.
