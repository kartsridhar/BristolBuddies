package com.example.brisbuds.BristolBuddies;

public class Buddy {
    private final String firstName;
    private final String lastName;
    private final String username;
    private final String course;
    private final String nationality;

    private final String interests;
    private final String personality;
    private final String preferences;

    private final String password;


    public Buddy(String firstName, String lastName, String userName, String course, String nationality, String interests, String personality, String preferences, String password){
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = userName;
        this.course = course;
        this.nationality = nationality;
        this.interests = interests;
        this.personality = personality;
        this.preferences = preferences;
        this.password = password;
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
                '}';
    }
}


