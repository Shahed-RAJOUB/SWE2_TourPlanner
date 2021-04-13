package org.openjfx.data_access_layer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.Getter;
import lombok.Setter;
import lombok.Data;
import org.openjfx.view_model.Log;
import org.openjfx.view_model.Tour;

import java.sql.*;
import java.util.List;

@Data
@Getter
@Setter
public class dbContent implements DataAccess{
    public static Connection c;

    public void configureConnection()  {
        try {
            c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/TourLogs" , "postgres" , "if19b166");
            c.setAutoCommit(false);
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public ObservableList<Log> GetLogs() {

        ObservableList<Log> logsList = FXCollections.observableArrayList();
        configureConnection();
        String query1 = "SELECT * FROM \"Logs\"";
        PreparedStatement statement;
        ResultSet rs;
        try{
            statement = c.prepareStatement(query1);
            rs = statement.executeQuery();
            Log logs;
            while(rs.next()){
                logs = new Log(rs.getInt("id") , rs.getString("date") , rs.getFloat("duration") , rs.getFloat("destination") , rs.getFloat("calories") , rs.getString("url"), rs.getString("tour_name"));
                logsList.add(logs);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return logsList;
    }

    @Override
    public List<Tour> GetTours() {
        ObservableList<Tour> logsList = FXCollections.observableArrayList();
        configureConnection();
        String query2 = "SELECT * FROM \"Tours\"";
        PreparedStatement state;
        ResultSet rs;
        try{
            state = c.prepareStatement(query2);
            rs = state.executeQuery();
            Tour tours;
            while(rs.next()){
                tours = new Tour(rs.getInt("id") , rs.getString("tourName"));
                logsList.add(tours);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return logsList;
    }

    @Override
    public List<Log> searchLogs() {
        return null;
    }

    @Override
    public List<Tour> searchTours() {
        return null;
    }

    /**
     *
     * @param date
     * @param duration
     * @param destination
     * @param tourName
     * @throws SQLException
     */
    public void insertNewLog(String date , Float duration , Float destination , String tourName) throws SQLException {
        float calories = 6 * (duration / 60 ) * 80;
        configureConnection();
        String query1 = "INSERT INTO \"Logs\" VALUES (?, ?, ? , ? , ? , ?)";
        PreparedStatement statement = c.prepareStatement(query1);
        statement.setString(1,date );
        statement.setFloat(2,duration);
        statement.setFloat(3, destination);
        statement.setFloat(4, calories);
        statement.setString(5, "URL");
        statement.setString(6, tourName);
        statement.executeUpdate();
        c.commit();
        statement.close();
    }

    @Override
    public void insertNewTour(String tourName) throws SQLException {
        configureConnection();
        String query1 = "INSERT INTO \"Tours\" VALUES (?)";
        PreparedStatement statement = c.prepareStatement(query1);
        statement.setString(1,tourName );
        statement.executeUpdate();
        c.commit();
        statement.close();
    }
}
