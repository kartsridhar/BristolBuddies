package com.example.brisbuds.BristolBuddies.ao;

import com.example.brisbuds.BristolBuddies.Student;

import java.util.List;

public interface StudentDAO {
     List<Student> getAllStudents();
     Student getStudent(long id);
     List<Student> getByFirstName(String name1);
     List <Student> getByLastName(String name2);
     List <Student> getByUsername (String username);
     List <Student> getByDepartment (String department);


     boolean add(Student student);
     boolean update(long id, Student student);
     boolean delete(long id);
}
