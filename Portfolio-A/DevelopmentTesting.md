# Development Testing

As we can see from our architecture diagram in the previous section, our system involves a Phone Interface, Web Service and a database. It is important that the system performs per required on the designated platforms, giving the user the best experience. We ran our system on multiple mobile devices with different **android versions** to make sure the front-end is well compatible and consistent. At the same time, we had to ensure the foundation of the system was well tested. When a system is developed with multiple function calls, it is natural to overlook some exceptional cases which can cause the system to crash. To avoid scenarios like this, we decided to test each component of our system using different testing frameworks and implementations. 


## Challenges Faced

Choosing the ideal testing framework was the biggest challenge we faced as a developer team. Since our system involved many interconnected components, our first goal was to identify the possible edge cases for each of our functions and work around tests from there. Initially, our functions were naïve, but with systematic testing, we were able to improve the performance of our functions as well as increase the scope.

## Testing Frameworks 

Since the heart of our application was user data, we decided to follow a **Data Driven Testing Framework**. At the beginning of our testing, we created five test values in our table, each covering a different case for our functions. This allowed us to reuse the values for multiple tests and also standardize our testing process. 

### Back-end and Web Service

The middle man of our system is the Web Service, powered by SpringBoot. The Web Service establishes connection to the MySQL database, the back-end of the system. Since the database simply stores the information, we did not feel the need to ensure that it stores the information properly. However, it was extremely important to test whether our Web-Service _functions_ correctly by establishes the right connection. We made use of Spring's **MockMvc** framework to perform integration tests on our WebMvc Controllers in order to make sure our service receives the right values from the database. This also helped us test our service's CRUD operations by every single component with a mocked platform.

### Android App - API calls and Matching

Testing the API calls made on our android application was crucial as all the activities were linked and each of them relied on the information received from the Web Service. We used the **Mockito** Java testing framework to make sure we are *POSTING* and *GETTING* the data per the requirements. On the other hand, we performed unit tests for our Matching algorithm using **JUnit**. We tested each of our test values against different questionnaire scores, covering all the edge cases, thereby confirming our algorithm.

### Continuous Integration

Since there are multiple components in the system interacting with each other, we wanted to run our integration tests sequentially. We used continuous integration through **CircleCI** to do the same, followed by continuous deployment on the *Oracle VM*. Not only did this help us identify our build failures, but also automated our deployment on the cloud.

