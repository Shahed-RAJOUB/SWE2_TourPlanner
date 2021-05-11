package org.rajoub.data_access_layer;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import lombok.RequiredArgsConstructor;
import org.rajoub.model.Tour;
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
                tours = new Tour(rs.getInt("id"), rs.getString("tourName"),rs.getString("from"),rs.getString("to"));
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
    public void insertNewTour(String tourName , String from , String to) throws SQLException {
        String query1 = "INSERT INTO \"Tours\" VALUES (DEFAULT , ? ,? ,?)";
        PreparedStatement statement = connection.getC().prepareStatement(query1);
        statement.setString(1, tourName);
        statement.setString(2, from);
        statement.setString(3, to);
        statement.executeUpdate();
        connection.getC().commit();
        statement.close();
    }

    @Override
    public void deleteTour(String tourName) throws SQLException {
        String query1 = "DELETE FROM \"Tours\" WHERE \"tourName\" = ?";
        PreparedStatement statement = connection.getC().prepareStatement(query1);
        statement.setString(1, tourName);
        statement.executeUpdate();
        connection.getC().commit();
        statement.close();
    }

    @Override
    public void copyTour(String tourName, String from , String to) throws SQLException {
        insertNewTour("copy_" + tourName , from,to);
    }

    @Override
    public void EditTour(String tourName, String from , String to , String old) throws SQLException {
        String query1 = "UPDATE \"Tours\" SET \"tourName\"=?,\"from\"=?,\"to\"=? WHERE \"tourName\"=? ";
        PreparedStatement statement = connection.getC().prepareStatement(query1);
        statement.setString(1, tourName);
        statement.setString(2, from);
        statement.setString(3, to);
        statement.setString(4,old);
        statement.executeUpdate();
        connection.getC().commit();
        statement.close();
    }
}
