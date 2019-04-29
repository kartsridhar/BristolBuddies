package com.example.brisbuds.BristolBuddies.ao;

import com.example.brisbuds.BristolBuddies.Buddy;
import com.example.brisbuds.BristolBuddies.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class BuddyDbDAO implements BuddyDAO {
    private final Connection conn = DBConnection.getConnection();


    public List<Buddy> query(String sqlQueryStr) {
        List<Buddy> resultList = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sqlQueryStr)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                resultList.add(
                        new Buddy(rs.getLong("ID"), rs.getString("FIRSTNAME"),
                                rs.getString("LASTNAME"), rs.getString("USERNAME"), rs.getString("COURSE"),
                                rs.getString("NATIONALITY"), rs.getString("INTERESTS"), rs.getString("PERSONALITY"),
                                rs.getString("PREFERENCES"), rs.getString("PASSWORD"), rs.getInt("NUMBEROFMATCHES"),
                                rs.getString("STUDENT1ID"), rs.getString("STUDENT2ID"), rs.getString("STUDENT3ID"))
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
    public List<Buddy> getAllBuddies(){
        String queryStr = "SELECT * FROM BUDDIES";
        List<Buddy> resultList = this.query(queryStr);
        return resultList;
    }



    @Override
    public boolean add(Buddy buddy) {
        String insertTableSQL = "INSERT INTO BUDDIES"
                + "(FIRSTNAME, LASTNAME, USERNAME, COURSE, NATIONALITY," +
                " INTERESTS, PERSONALITY, PREFERENCES, PASSWORD, NUMBEROFMATCHES, STUDENT1ID, STUDENT2ID, STUDENT3ID) "
                + "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";

        try (PreparedStatement preparedStatement = this.conn
                .prepareStatement(insertTableSQL)) {

            preparedStatement.setString(1, buddy.getFirstName());
            preparedStatement.setString(2, buddy.getLastName());
            preparedStatement.setString(3, buddy.getUsername());
            preparedStatement.setString(4, buddy.getCourse());
            preparedStatement.setString(5, buddy.getNationality());
            preparedStatement.setString(6, buddy.getInterests());
            preparedStatement.setString(7, buddy.getPersonality());
            preparedStatement.setString(8, buddy.getPreferences());
            preparedStatement.setString(9, buddy.getPassword());
            preparedStatement.setString(10, Integer.toString(buddy.getNumberOfMatches()));
            preparedStatement.setString(11, buddy.getStudent1ID());
            preparedStatement.setString(12, buddy.getStudent2ID());
            preparedStatement.setString(13, buddy.getStudent3ID());


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
    public boolean update(long id, Buddy student){
        String updateTableSQL = "UPDATE BUDDIES SET FIRSTNAME=?, LASTNAME=?, USERNAME=?, DEPARTMENT=?, YEAROFSTUDY=?, NATIONALITY=?, BUDDY=? WHERE ID=?";
        try (PreparedStatement preparedStatement = this.conn
                .prepareStatement(updateTableSQL);) {
            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getLastName());
            preparedStatement.setString(3, student.getUsername());
            preparedStatement.setString(4, student.getCourse());
            preparedStatement.setString(5, student.getYearofStudy());
            preparedStatement.setString(6,student.getNationality());
            preparedStatement.setString(7,student.getBuddy());
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
    public List<Buddy> getByUsername(String name){
        String queryStr = "SELECT * FROM BUDDIES WHERE USERNAME LIKE '" + name + "%'";
        List<Buddy> resultList = this.query(queryStr);

        return resultList;
    }

    @Override
    public boolean delete(long id){
        String deleteRowSQL = "DELETE FROM BUDDIES WHERE ID=?";
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

