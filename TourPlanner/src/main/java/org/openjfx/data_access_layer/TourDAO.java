package org.openjfx.data_access_layer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.RequiredArgsConstructor;
import org.openjfx.view_model.Tour;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TourDAO implements TourDataAccess {
    private final DbContent connection;

    @Override
    public List<Tour> GetTours() {
        ObservableList<Tour> logsList = FXCollections.observableArrayList();
        String query2 = "SELECT * FROM \"Tours\"";
        PreparedStatement state;
        ResultSet rs;
        try {
            state = connection.getC().prepareStatement(query2);
            rs = state.executeQuery();
            Tour tours;
            while (rs.next()) {
                tours = new Tour(rs.getInt("id"), rs.getString("tourName"));
                logsList.add(tours);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return logsList;
    }
    @Override
    public void insertNewTour(String tourName) throws SQLException {
        String query1 = "INSERT INTO \"Tours\" VALUES (DEFAULT , ?)";
        PreparedStatement statement = connection.getC().prepareStatement(query1);
        statement.setString(1, tourName);
        statement.executeUpdate();
        connection.getC().commit();
        statement.close();
    }
}
