package org.openjfx.data_access_layer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.RequiredArgsConstructor;
import org.openjfx.view_model.Log;
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
                logs = new Log(rs.getInt("id"), rs.getString("date"), rs.getFloat("duration"), rs.getFloat("destination"), rs.getFloat("calories"), rs.getString("url"), rs.getString("tour_name"));
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
    public void insertNewLog(String date, Float duration, Float destination, String tourName) throws SQLException {
        float calories = 6 * (duration / 60) * 80;
        String query1 = "INSERT INTO \"Logs\" VALUES (?, ?, ? , ? , ? , ?)";
        PreparedStatement statement = connection.getC().prepareStatement(query1);
        statement.setString(1, date);
        statement.setFloat(2, duration);
        statement.setFloat(3, destination);
        statement.setFloat(4, calories);
        statement.setString(5, "URL");
        statement.setString(6, tourName);
        statement.executeUpdate();
        connection.getC().commit();
        statement.close();
    }
}
