package org.openjfx.data_access_layer;

import org.openjfx.view_model.Log;
import org.openjfx.view_model.Tour;

import java.sql.SQLException;
import java.util.List;

public interface TourDataAccess {
    List<Tour> GetTours();
    void insertNewTour(String tourName) throws SQLException;
}
