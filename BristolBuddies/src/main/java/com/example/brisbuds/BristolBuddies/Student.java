package com.example.brisbuds.BristolBuddies;

public class Student {
    private final long id;
    private final String firstName;
    private final String lastName;
    private final String userName;
    private final String password;
    private final String department;
    private final String nationality;
    private final String interests;
    private final String personality;
    private final String preferences;
    private final String buddy;



    public Student(){
        super();
        id = 0;
        firstName = "";
        lastName = "";
        userName="";
        password = "";
        department = "";
        nationality = "";
        interests = "";
        personality = "";
        preferences = "";
        buddy = "";
    }

    public Student(long id, String firstName,String lastName,
                   String userName, String password, String department,
                   String nationality,String interests,String personality,String preferences, String buddy){

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.department = department;
        this.nationality = nationality;
        this.interests = interests;
        this.personality = personality;
        this.preferences = preferences;
        this.buddy = buddy;
    }



    public long getId() {return id;}

    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String getUserName(){ return userName; }
    public String getPassword(){
        return password;
    }
    public String getDepartment(){
        return department;
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
    public String getBuddy() { return buddy;}


    @Override
    public String toString(){
        return "ID" + id
                + " First Name: " + firstName
                + " Last Name: " + lastName
                + " Username: " + userName
                + " Password: " + password
                + " Department: " + department
                + " Nationality: " + nationality
                + " Interests: " + interests
                + " personality: "+ personality
                + " preferences: " + preferences
                + " buddy: " + buddy ;
    }


}


