package com.example.brisbuds.BristolBuddies;

public class Buddy {
    private final long id;
    private final String firstName;
    private final String lastName;
    private final String username;
    private final String course;
    private final String nationality;
    private final String interests;
    private final String personality;
    private final String preferences;
    private final String password;
    private final int numberOfMatches;
    private final String student1ID;
    private final String student2ID;
    private final String student3ID;

    public Buddy(){
        super();
        id = 0;
        firstName = "";
        lastName = "";
        username="";
        password = "";
        course = "";
        nationality = "";
        interests = "";
        personality = "";
        preferences = "";
        numberOfMatches = 0;
        student1ID = "";
        student2ID = "";
        student3ID = "";
    }


    public Buddy(long id, String firstName, String lastName, String username, String course, String nationality,
                 String interests, String personality, String preferences, String password,
                 int numberOfMatches, String student1ID, String student2ID, String student3ID){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.course = course;
        this.nationality = nationality;
        this.interests = interests;
        this.personality = personality;
        this.preferences = preferences;
        this.password = password;
        this.numberOfMatches = numberOfMatches;
        this.student1ID = student1ID;
        this.student2ID = student2ID;
        this.student3ID = student3ID;

    }

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public String getUsername() {
        return username;
    }
    public String getCourse() {
        return course;
    }
    public String getNationality() {
        return nationality;
    }
    public String getInterests() {
        return interests;
    }
    public String getPersonality() {
        return personality;
    }
    public String getPreferences() {
        return preferences;
    }
    public String getPassword() {
        return password;
    }
    public int getNumberOfMatches() {
        return numberOfMatches;
    }
    public String getStudent1ID() {
        return student1ID;
    }
    public String getStudent2ID() {
        return student2ID;
    }
    public String getStudent3ID() {
        return student3ID;
    }
    public long getId() { return id;}

    @Override
    public String toString() {
        return "Buddy{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + username + '\'' +
                ", course='" + course + '\'' +
                ", nationality='" + nationality + '\'' +
                ", interests='" + interests + '\'' +
                ", personality='" + personality + '\'' +
                ", preferences='" + preferences + '\'' +
                ", password='" + password + '\'' +
                ", number of matches='" + numberOfMatches + '\'' +
                ", first student ID='" + student1ID + '\'' +
                ", second student ID='" + student2ID + '\'' +
                ", third student ID='" + student3ID + '\'' +
                '}';
    }
}


