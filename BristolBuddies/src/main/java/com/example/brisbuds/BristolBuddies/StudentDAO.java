package com.example.brisbuds.BristolBuddies;

import java.util.List;

public interface StudentDAO {
    public List<Student> getAllStudents();
    public Student getStudent(long id);
    public List<Student> getByFirstName(String name1);
    public List <Student> getByLastName(String name2);
    public List <Student> getByGender (String gender);
    public List <Student> getByUsername (String username);
    public List <Student> getByPassword (String password);
    public List <Student> getByDepartment (String department);
    public List <Student> getByYearofStudy (String yearOfStudy);
    public boolean add(Student student);
    public boolean update(long id, Student student);
    public boolean delete(long id);
}
