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

    }

    public Student(long id, String firstName,String lastName, String gender, String userName, String password, String department, String yearofStudy ){
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.userName = userName;
        this.password = password;
        this.department = department;
        this.yearofStudy = yearofStudy;
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

    @Override
    public String toString(){
        return "ID" + id
                + " First Name: " + firstName
                + " Last Name: " + lastName
                + " Gender: " + gender
                + " Username: " + userName
                + " Password: " + password
                + " Department: " + department
                + " Year of Study: " + yearofStudy;
    }


}


