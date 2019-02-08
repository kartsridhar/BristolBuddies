package com.example.brisbuds.BristolBuddies.ao;

import com.example.brisbuds.BristolBuddies.DBConnection;
import com.example.brisbuds.BristolBuddies.Event;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventDbDAO implements EventDAO {
    List<Event> eventList = null;
    private final Connection connection = DBConnection.getConnection();

    public List<Event> query(String sql_query) {
        List<Event> resultList = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql_query)) {
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                resultList.add(
                        new Event(rs.getLong("ID"), rs.getString("DATE"), rs.getString("TITLE"),
                                rs.getString("DESCRIPTION"), rs.getString("VENUE"), rs.getString("TIME"))
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
    public List<Event> getAllEvents() {
        String queryStr = "SELECT * FROM EVENTS";
        List<Event> resultList = this.query(queryStr);
        return resultList;
    }

    @Override
    public Event getEventById(long id) {
        String queryStr = "SELECT * FROM EVENTS WHERE ID = " + id;
        List<Event> resultList = this.query(queryStr);

        if(resultList.size() > 0) {
            return resultList.get(0);
        }
        else {
            return null;
        }
    }

    @Override
    public boolean add(Event event) {
        String insertTableSQL = "INSERT INTO EVENTS VALUES " + "(DATE, TITLE, DESCRIPTION, VENUE, TIME) " + "VALUES(?, ?, ?, ?, ?);";

        try (PreparedStatement preparedStatement = this.connection.prepareStatement(insertTableSQL)) {

            preparedStatement.setString(1, event.getDate());
            preparedStatement.setString(2, event.getTitle());
            preparedStatement.setString(3, event.getDescription());
            preparedStatement.setString(4, event.getVenue());
            preparedStatement.setString(5, event.getTime());
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
    public boolean update(long id, Event event) {
        String updateTableSQL = "UPDATE EVENTS SET DATE = ?, TITLE = ?, DESCRIPTION = ?, VENUE = ?, TIME = ? WHERE ID = ?";

        try (PreparedStatement preparedStatement = this.connection.prepareStatement(updateTableSQL)) {
            preparedStatement.setString(1, event.getDate());
            preparedStatement.setString(2, event.getTitle());
            preparedStatement.setString(3, event.getDescription());
            preparedStatement.setString(4, event.getVenue());
            preparedStatement.setString(5, event.getTime());
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

    @Override
    public boolean delete(long id) {
        String deleteRowSQL = "DELETE FROM EVENTS WHERE ID = ?";

        try(PreparedStatement preparedStatement = this.connection.prepareStatement(deleteRowSQL)) {
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
