package com.example.brisbuds.BristolBuddies.ao;
import com.example.brisbuds.BristolBuddies.DBConnection;
import com.example.brisbuds.BristolBuddies.Student;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class StudentDbDAO implements StudentDAO {
    List<Student> sList = null;
    private final Connection conn = DBConnection.getConnection();


    public List<Student> query(String sqlQueryStr) {
        List<Student> resultList = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sqlQueryStr)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                resultList.add(
                        new Student(rs.getLong("ID"), rs.getString("FIRSTNAME"),
                                rs.getString("LASTNAME"), rs.getString("USERNAME"),
                                rs.getString("PASSWORD"), rs.getString("DEPARTMENT"),
                                rs.getString("NATIONALITY"), rs.getString("INTERESTS"),rs.getString("PERSONALITY"),
                                rs.getString("PREFERENCES"), rs.getString("BUDDY"))
                );
            }
        } catch (SQLException e) {
            System.out.println("SQL Query Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Query Error: " + e.getMessage());
        }
        return resultList;
    }

    @Override
    public List<Student> getAllStudents(){
        String queryStr = "SELECT * FROM BRISBUDS";
        List<Student> resultList = this.query(queryStr);
        return resultList;
    }


    @Override
    public Student getStudent(long id){
        String queryStr = "SELECT * FROM BRISBUDS WHERE ID=" + id;
        List<Student> resultList = this.query(queryStr);

        if (resultList.size() > 0) {
            return resultList.get(0);
        } else {
            return null;
        }
    }


    @Override
    public List<Student> getByFirstName(String name1){
        String queryStr = "SELECT * FROM BRISBUDS WHERE FIRSTNAME LIKE '" + name1 + "%'";
        List<Student> resultList = this.query(queryStr);

        return resultList;
    }
    public List<Student> getByLastName(String name2){
        String queryStr = "SELECT * FROM BRISBUDS WHERE LASTNAME LIKE '" + name2 + "%'";
        List<Student> resultList = this.query(queryStr);

        return resultList;
    }

    @Override
    public List<Student> getByUsername (String username){
        String queryStr = "SELECT * FROM BRISBUDS WHERE USERNAME LIKE'" + username + "%'";
        List<Student> resultList = this.query(queryStr);

        return resultList;
    }

    public List<Student> getByDepartment (String department){
        String queryStr = "SELECT * FROM BRISBUDS WHERE DEPARTMENT LIKE'" + department + "%'";
        List<Student> resultList = this.query(queryStr);

        return resultList;
    }

    @Override
    public boolean add(Student student){
        String insertTableSQL = "INSERT INTO BRISBUDS"
            + "(FIRSTNAME, LASTNAME, USERNAME, PASSWORD, DEPARTMENT, NATIONALITY, INTERESTS, PERSONALITY, PREFERENCES, BUDDY) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";

        try (PreparedStatement preparedStatement = this.conn
                .prepareStatement(insertTableSQL)) {

            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setString(3, student.getUserName());
            preparedStatement.setString(4, student.getPassword());
            preparedStatement.setString(5, student.getDepartment());
            preparedStatement.setString(6,student.getNationality());
            preparedStatement.setString(7,student.getInterests());
            preparedStatement.setString(8,student.getPersonality());
            preparedStatement.setString(9,student.getPreferences());
            preparedStatement.setString(10,student.getBuddy());

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("SQL Add Error: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Add Error: " + e.getMessage());
            return false;
        }

    }


    @Override
    public boolean update(long id, Student student){
        String updateTableSQL = "UPDATE BRISBUDS SET FIRSTNAME=?, LASTNAME=?, USERNAME=?, PASSWORD=?, DEPARTMENT=?, NATIONALITY=?, INTERESTS=?, PERSONALITY=?, PREFERENCES=?, BUDDY=? WHERE ID=?";
        try (PreparedStatement preparedStatement = this.conn
                .prepareStatement(updateTableSQL);) {
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setString(3, student.getUserName());
            preparedStatement.setString(4, student.getPassword());
            preparedStatement.setString(5, student.getDepartment());
            preparedStatement.setString(6,student.getNationality());
            preparedStatement.setString(7,student.getInterests());
            preparedStatement.setString(8,student.getPersonality());
            preparedStatement.setString(9,student.getPreferences());
            preparedStatement.setString(10,student.getBuddy());
            preparedStatement.setString(11, Long.toString(id));

            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("SQL Update Error: "	+ e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Update Error: "	+ e.getMessage());
            return false;
        }

    }


    @Override
    public boolean delete(long id){
        String deleteRowSQL = "DELETE FROM BRISBUDS WHERE ID=?";
        try (PreparedStatement preparedStatement = this.conn
                .prepareStatement(deleteRowSQL)) {
            preparedStatement.setString(1, Long.toString(id));
            preparedStatement.executeUpdate();
            return true;

        } catch (SQLException e) {
            System.out.println("SQL Delete Error: " + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Delete Error: " + e.getMessage());
            return false;
        }
    }
}

