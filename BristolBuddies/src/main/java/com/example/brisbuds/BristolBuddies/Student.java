package com.example.brisbuds.BristolBuddies;

public class Student {
    private final long id;
    private final String firstName;
    private final String lastName;
    private final String gender;
    private final String userName;
    private final String password;
    private final String department;
    private final String yearofStudy;
    private final String nationality;
    private final String interests;
    private final String personality;
    private final String preferences;



    public Student(){
        super();
        id = 0;
        firstName = "";
        lastName = "";
        gender = "";
        userName="";
        password = "";
        department = "";
        yearofStudy = "";
        nationality = "";
        interests = "";
        personality = "";
        preferences = "";

    }

    public Student(long id, String firstName,String lastName, String gender, String userName, String password, String department, String yearofStudy,String nationality,String interests,String personality,String preferences ){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.userName = userName;
        this.password = password;
        this.department = department;
        this.yearofStudy = yearofStudy;
        this.nationality = nationality;
        this.interests = interests;
        this.personality = personality;
        this.preferences = preferences;
    }



    public long getId() {return id;}

    public String getFirstName(){
        return firstName;
    }

    public String getLastName(){
        return lastName;
    }
    public String getGender(){ return gender; }
    public String getUserName(){ return userName; }
    public String getPassword(){
        return password;
    }
    public String getDepartment(){
        return department;
    }
    public String getYearofStudy(){
        return yearofStudy;
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


    @Override
    public String toString(){
        return "ID" + id
                + " First Name: " + firstName
                + " Last Name: " + lastName
                + " Gender: " + gender
                + " Username: " + userName
                + " Password: " + password
                + " Department: " + department
                + " Year of Study: " + yearofStudy
                + " Nationality: " + nationality
                + " Interests: " + interests
                + " personality: "+ personality
                + " preferences: " + preferences;
    }


}


