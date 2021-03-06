package org.rajoub.data_access_layer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.RequiredArgsConstructor;
import org.rajoub.model.Log;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Component
@RequiredArgsConstructor
public class LogDAO implements LogDataAccess {

    private final DbContent connection;

    @Override
    public List<Log> GetLogs() {
        ObservableList<Log> logsList = FXCollections.observableArrayList();
        String query1 = "SELECT * FROM \"Logs\"";
        PreparedStatement statement;
        ResultSet rs;
        try {
            statement = connection.getC().prepareStatement(query1);
            rs = statement.executeQuery();
            Log logs;
            while (rs.next()) {
                logs = new Log(rs.getInt("id"), rs.getString("date"), rs.getFloat("duration"), rs.getFloat("destination"), rs.getFloat("calories"), rs.getString("url"), rs.getString("tour_name"), rs.getString("ratings"));
                logsList.add(logs);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return logsList;
    }

    @Override
    public void insertNewLog(String date, Float duration, Float destination, String tourName , String ratings) throws SQLException {
        float calories = 6 * (duration / 60) * 80;
        String query1 = "INSERT INTO \"Logs\" VALUES (DEFAULT , ?, ?, ? , ? , ? , ? , ?)";
        PreparedStatement statement = connection.getC().prepareStatement(query1);
        statement.setString(1, date);
        statement.setFloat(2, duration);
        statement.setFloat(3, destination);
        statement.setFloat(4, calories);
        statement.setString(5, "URL");
        statement.setString(6, tourName);
        statement.setString(7, ratings);
        statement.executeUpdate();
        connection.getC().commit();
        statement.close();
    }

    @Override
    public void deleteLog(int id) throws SQLException {
        String query1 = "DELETE FROM \"Logs\" WHERE \"id\" = ?";
        PreparedStatement statement = connection.getC().prepareStatement(query1);
        statement.setInt(1, id);
        statement.executeUpdate();
        connection.getC().commit();
        statement.close();
    }

    @Override
    public void copyLog(String date, Float duration, Float destination, String tourName , String ratings) throws SQLException {
        insertNewLog(date,duration ,destination ,tourName , ratings);
    }

    @Override
    public void EditTLog(String date, Float duration, Float destination, String tourName, int id) throws SQLException {
        String query1 = "UPDATE \"Logs\" SET \"date\"=?,\"duration\"=?,\"destination\"=? ,\"tour_name\"=? WHERE \"id\"=? ";
        PreparedStatement statement = connection.getC().prepareStatement(query1);
        statement.setString(1, date);
        statement.setFloat(2, duration);
        statement.setFloat(3, destination);
        statement.setString(4, tourName);
        statement.setInt(5, id);
        statement.executeUpdate();
        connection.getC().commit();
        statement.close();
    }
}
