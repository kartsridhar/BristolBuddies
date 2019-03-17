package com.example.brisbuds.BristolBuddies;

import org.junit.Test;

import static org.junit.Assert.*;

public class StudentTest {

    private Student s = new Student(100, "Frank", "Castle", "Male", "fc17231", "quardive", "PE", "3", "American", "1001", "1100", "0001");

    @Test
    public void getId() {
        assertEquals(100, s.getId());
    }

    @Test
    public void getFirstName() {
        assertEquals("Frank", s.getFirstName());
    }

    @Test
    public void getLastName() {
        assertEquals("Castle", s.getLastName());
    }

    @Test
    public void getGender() {
        assertEquals("Male", s.getGender());
    }

    @Test
    public void getUserName() {
        assertEquals("fc17231", s.getUserName());
    }

    @Test
    public void getPassword() {
        assertEquals("quardive", s.getPassword());
    }

    @Test
    public void getDepartment() {
        assertEquals("PE", s.getDepartment());
    }

    @Test
    public void getYearofStudy() {
        assertEquals("3", s.getYearofStudy());
    }

    @Test
    public void getNationality() {
        assertEquals("American", s.getNationality());
    }

    @Test
    public void getInterests() {
        assertEquals("1001", s.getInterests());
    }

    @Test
    public void getPersonality() {
        assertEquals("1100", s.getPersonality());
    }

    @Test
    public void getPreferences() {
        assertEquals("0001", s.getPreferences());
    }
}