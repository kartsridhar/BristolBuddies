package com.example.brisbuds.BristolBuddies.ao;

import com.example.brisbuds.BristolBuddies.MockStudentList;
import com.example.brisbuds.BristolBuddies.Student;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class StudentListDAO implements StudentDAO {
    private final CopyOnWriteArrayList<Student> eList = MockStudentList.getInstance();

    @Override
    public List<Student> getAllStudents(){
        return eList;
    }


    @Override
    public Student getStudent(long id){
        Student match = null;

        match = eList.stream()
                .filter(e -> e.getId() == id)
                .findFirst().orElse(match);

        return match;
    }

    @Override
    public List<Student> getByFirstName(String name1){

        List<Student> matchList =
                eList.stream()
                        .filter((e) -> (e.getFirstName().contains(name1)))
                        .collect(Collectors.toList());

        return matchList;
    }
    @Override
    public List<Student> getByLastName(String name2){

        List<Student> matchList =
                eList.stream()
                        .filter((e) -> (e.getLastName().contains(name2)))
                        .collect(Collectors.toList());

        return matchList;
    }


    @Override
    public List<Student> getByGender (String gender){
        List<Student> matchList =
                eList.stream()
                        .filter((e) -> (e.getGender().contains(gender)))
                        .collect(Collectors.toList());

        return matchList;
    }


    @Override
    public List<Student> getByUsername(String username){
        List<Student> matchList =
                eList.stream()
                        .filter((e) -> (e.getUserName().contains(username)))
                        .collect(Collectors.toList());

        return matchList;
    }


    @Override
    public List<Student> getByDepartment(String department){
        List<Student> matchList =
                eList.stream()
                        .filter((e) -> (e.getDepartment().contains(department)))
                        .collect(Collectors.toList());

        return matchList;
    }


    @Override
    public List<Student> getByPassword (String password){
        List<Student> matchList =
                eList.stream()
                        .filter((e) -> (e.getPassword().contains(password)))
                        .collect(Collectors.toList());

        return matchList;
    }
    @Override
    public List<Student> getByYearofStudy (String year){
        List<Student> matchList =
                eList.stream()
                        .filter((e) -> (e.getYearofStudy().contains(year)))
                        .collect(Collectors.toList());

        return matchList;
    }



    @Override
    public boolean add(Student student){
        long next = eList.size() + 100;

        Student nextStudent =
                new Student( next, student.getFirstName(), student.getLastName(),
                        student.getGender(), student.getUserName(),
                        student.getPassword(), student.getDepartment(), student.getYearofStudy(),null,null,null,null);

        eList.add(nextStudent);
        return true;
    }


    @Override
    public boolean update(long id, Student student){
        int matchIndex = -1;

        matchIndex = eList.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .map(e -> eList.indexOf(e))
                .orElse(matchIndex);

        if (matchIndex > -1){
            eList.set(matchIndex, student);
            return true;
        } else {
            return false;
        }

    }


    @Override
    public boolean delete(long id){
        int matchIndex = -1;

        matchIndex = eList.stream()
                .filter(e -> e.getId() == id)
                .findFirst()
                .map(e -> eList.indexOf(e))
                .orElse(matchIndex);

        if (matchIndex > -1){
            eList.remove(matchIndex);
            return true;
        } else {
            return false;
        }
    }

}

