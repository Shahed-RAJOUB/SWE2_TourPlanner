package org.openjfx.data_access_layer;

import org.openjfx.view_model.Tour;

import java.sql.SQLException;
import java.util.List;

public interface TourDataAccess {
    List<Tour> GetTours();
    void insertNewTour(String tourName , String from , String to) throws SQLException;
    void deleteTour(String tourName) throws SQLException;
    void copyTour(String tourName, String from , String to) throws SQLException;
    void EditTour(String tourName, String from , String to , String old) throws SQLException;
}
