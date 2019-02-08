package com.example.brisbuds.BristolBuddies.ao;

import com.example.brisbuds.BristolBuddies.Buddy;
import com.example.brisbuds.BristolBuddies.DBConnection;
import com.example.brisbuds.BristolBuddies.Match;

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
                        new Buddy(rs.getString("FIRSTNAME"),
                                rs.getString("LASTNAME"), rs.getString("USERNAME"), rs.getString("COURSE"),
                                rs.getString("NATIONALITY"), rs.getString("INTERESTS"), rs.getString("PERSONALITY"),
                                rs.getString("PREFERENCES"), rs.getString("PASSWORD"))
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
        return false;
        

    }
}

