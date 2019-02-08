package com.example.brisbuds.BristolBuddies.ao;

import com.example.brisbuds.BristolBuddies.DBConnection;
import com.example.brisbuds.BristolBuddies.Match;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MatchDbDAO implements MatchDAO {
    private final Connection conn = DBConnection.getConnection();


    public List<Match> query(String sqlQueryStr) {
        List<Match> resultList = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(sqlQueryStr)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                resultList.add(
                        new Match(rs.getLong("ID"), rs.getInt("NUMBEROFMATCHES"),
                                rs.getString("BUDDYID"), rs.getString("STUDENT1ID"), rs.getString("STUDENT2ID"),
                                rs.getString("STUDENT3ID"))
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
    public List<Match> getAllMatches(){
        String queryStr = "SELECT * FROM MATCHES";
        List<Match> resultList = this.query(queryStr);
        return resultList;
    }


    @Override
    public Match getMatch(long id){
        String queryStr = "SELECT * FROM MATCHES WHERE ID=" + id;
        List<Match> resultList = this.query(queryStr);

        if (resultList.size() > 0) {
            return resultList.get(0);
        } else {
            return null;
        }
    }


    @Override
    public Match getByBuddyID(String id){
        String queryStr = "SELECT * FROM MATCHES WHERE BUDDYID LIKE '" + id + "%'";
        List<Match> resultList = this.query(queryStr);

        if (resultList.size() > 0) {
            return resultList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public Match getByStudentID (String id){
        String queryStr = "SELECT * FROM MATCHES WHERE STUDENT1ID LIKE '" + id + "%' OR STUDENT2ID LIKE '" + id + "%' OR STUDENT3ID LIKE '" + id + "%' ";
        List<Match> resultList = this.query(queryStr);

        if (resultList.size() > 0) {
            return resultList.get(0);
        } else {
            return null;
        }
    }

    @Override
    public boolean add(Match match){
        String insertTableSQL = "INSERT INTO MATCHES"
                + "(NUMBEROFMATCHES, BUDDYID, STUDENT1ID, STUDENT2ID, STUDENT3ID) "
                + "VALUES(?,?,?,?,?);";

        try (PreparedStatement preparedStatement = this.conn
                .prepareStatement(insertTableSQL)) {

            preparedStatement.setString(1, Integer.toString(match.getNumberOfMatches()));
            preparedStatement.setString(2, match.getBuddyId());
            preparedStatement.setString(3, match.getStudent1ID());
            preparedStatement.setString(4, match.getStudent2ID());
            preparedStatement.setString(5, match.getStudent3ID());
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
    public boolean update(long id, Match match){
        String updateTableSQL = "UPDATE MATCHES SET NUMBEROFMATCHES=?, BUDDYID=?, STUDENT1ID=?, STUDENT2ID=?, STUDENT3ID=? WHERE ID=?";
        try (PreparedStatement preparedStatement = this.conn
                .prepareStatement(updateTableSQL);) {
            preparedStatement.setString(1, Integer.toString(match.getNumberOfMatches()));
            preparedStatement.setString(2, match.getBuddyId());
            preparedStatement.setString(3, match.getStudent1ID());
            preparedStatement.setString(4, match.getStudent2ID());
            preparedStatement.setString(5, match.getStudent3ID());
            preparedStatement.setString(6, Long.toString(id));

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

}

