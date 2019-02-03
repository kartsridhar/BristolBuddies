package com.example.brisbuds.BristolBuddies;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@CrossOrigin
@RestController
@RequestMapping("/students1")
public class StudentController {

    StudentDAO edao = new StudentDbDAO();

    // Get all employees
    @RequestMapping(method = RequestMethod.GET)
    public Student[] getAll() {
        return edao.getAllStudents().toArray(new Student[0]);
    }

    // Get an employee
    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public ResponseEntity get(@PathVariable long id) {

        Student match = null;
        match = edao.getStudent(id);

        if (match != null) {
            return new ResponseEntity<>(match, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Get employees by lastName
    @RequestMapping(method = RequestMethod.GET, value = "/firstname/{name}")
    public ResponseEntity getByFirstName(@PathVariable String name) {

        List<Student> matchList = edao.getByFirstName(name);

        if (matchList.size() > 0) {
            return new ResponseEntity<>(matchList.toArray(new Student[0]), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    @RequestMapping(method = RequestMethod.GET, value = "/lastname/{name}")
    public ResponseEntity getByLastName(@PathVariable String name) {

        List<Student> matchList = edao.getByLastName(name);

        if (matchList.size() > 0) {
            return new ResponseEntity<>(matchList.toArray(new Student[0]), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

    }

    // Get employee by title
    @RequestMapping(method = RequestMethod.GET, value = "/department/{name}")
    public ResponseEntity getByGender(@PathVariable String name) {

        List<Student> matchList = edao.getByDepartment(name);

        if (matchList.size() > 0) {
            return new ResponseEntity<>(matchList.toArray(new Student[0]), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Get employee by dept
    @RequestMapping(method = RequestMethod.GET, value = "/username/{name}")
    public ResponseEntity getByDept(@PathVariable String name) {

        List<Student> matchList = edao.getByUsername(name);

        if (matchList.size() > 0) {
            return new ResponseEntity<>(matchList.toArray(new Student[0]), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Add an employee
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity add(@RequestBody Student student) {
        if (edao.add(student)) {
            return new ResponseEntity<>(null, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Update an employee
    @RequestMapping(method = RequestMethod.PUT, value = "{id}")
    public ResponseEntity update(@PathVariable long id, @RequestBody Student student) {

        if (edao.update(id, student)) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    // Delete a employee
    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public ResponseEntity delete(@PathVariable long id) {

        boolean result = edao.delete(id);

        if (result) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

}