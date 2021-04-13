package org.openjfx.data_access_layer;

import org.openjfx.view_model.Log;
import org.openjfx.view_model.Tour;

import java.sql.SQLException;
import java.util.List;

public interface DataAccess {
    List<Log> GetLogs();
    List<Tour> GetTours();
    List<Log> searchLogs();
    List<Tour> searchTours();
    void insertNewLog(String date, Float duration, Float destination, String tourName) throws SQLException;
    void insertNewTour(String tourName) throws SQLException;

}
