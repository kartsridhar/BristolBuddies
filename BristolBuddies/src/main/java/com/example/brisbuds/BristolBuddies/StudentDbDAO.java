package com.example.brisbuds.BristolBuddies;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class StudentDbDAO implements StudentDAO{
    List<Student> sList = null;
    private final Connection conn = DBConnection.getConnection();


    public List<Student> query(String sqlQueryStr) {
        List<Student> resultList = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sqlQueryStr)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                resultList.add(
                        new Student(rs.getLong("ID"), rs.getString("FIRSTNAME"),
                                rs.getString("LASTNAME"), rs.getString("GENDER"), rs.getString("USERNAME"),
                                rs.getString("PASSWORD"), rs.getString("DEPARTMENT"), rs.getString("YEAROFSTUDY"))
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
    public List<Student> getByGender (String gender){
        String queryStr = "SELECT * FROM BRISBUDS WHERE GENDER LIKE '" + gender + "%'";
        List<Student> resultList = this.query(queryStr);

        return resultList;
    }


    @Override
    public List<Student> getByUsername (String username){
        String queryStr = "SELECT * FROM BRISBUDS WHERE USERNAME LIKE'" + username + "%'";
        List<Student> resultList = this.query(queryStr);

        return resultList;
    }

    @Override
    public List<Student> getByPassword (String password){
        String queryStr = "SELECT * FROM BRISBUDS WHERE PASSWORD LIKE'" + password + "%'";
        List<Student> resultList = this.query(queryStr);

        return resultList;
    }
    public List<Student> getByDepartment (String department){
        String queryStr = "SELECT * FROM BRISBUDS WHERE DEPARTMENT LIKE'" + department + "%'";
        List<Student> resultList = this.query(queryStr);

        return resultList;
    }
    public List<Student> getByYearofStudy (String year){
        String queryStr = "SELECT * FROM BRISBUDS WHERE YEAROFSTUDY LIKE'" + year + "%'";
        List<Student> resultList = this.query(queryStr);

        return resultList;
    }





    @Override
    public boolean add(Student student){
        String insertTableSQL = "INSERT INTO BRISBUDS"
            + "(FIRSTNAME, LASTNAME, GENDER, USERNAME, PASSWORD, DEPARTMENT, YEAROFSTUDY) "
                + "VALUES(?,?,?,?,?,?,?);";

        try (PreparedStatement preparedStatement = this.conn
                .prepareStatement(insertTableSQL)) {

            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setString(3, student.getGender());
            preparedStatement.setString(4, student.getUserName());
            preparedStatement.setString(5, student.getPassword());
            preparedStatement.setString(6, student.getDepartment());
            preparedStatement.setString(7, student.getYearofStudy());
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
        String updateTableSQL = "UPDATE BRISBUDS SET FIRSTNAME=?, LASTNAME=?, GENDER=?, USERNAME=?, PASSWORD=?, DEPARTMENT=?, YEAROFSTUDY=?  WHERE ID=?";
        try (PreparedStatement preparedStatement = this.conn
                .prepareStatement(updateTableSQL);) {
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setString(3, student.getGender());
            preparedStatement.setString(4, student.getUserName());
            preparedStatement.setString(5, student.getPassword());
            preparedStatement.setString(6, student.getDepartment());
            preparedStatement.setString(7, student.getYearofStudy());
            preparedStatement.setString(8, Long.toString(id));

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

