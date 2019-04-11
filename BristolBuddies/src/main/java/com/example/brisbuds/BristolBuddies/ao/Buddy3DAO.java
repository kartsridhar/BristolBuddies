package com.example.brisbuds.BristolBuddies.ao;

import com.example.brisbuds.BristolBuddies.Buddy;
import com.example.brisbuds.BristolBuddies.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Buddy3DAO implements Buddy3DbDAO {
    private final Connection conn = DBConnection.getConnection();
    @Override
    public boolean add(Buddy buddy) {
        String insertTableSQL = "INSERT INTO BUDDIES"
                + "(FIRSTNAME, LASTNAME, USERNAME, COURSE, NATIONALITY, INTERESTS, PERSONALITY, PREFERENCES, PASSWORD, NUMBEROFMATCHES, STUDENT1ID, STUDENT2ID, STUDENT3ID) "
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
}
