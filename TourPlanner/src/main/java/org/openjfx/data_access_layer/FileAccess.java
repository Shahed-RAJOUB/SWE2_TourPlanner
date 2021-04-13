package org.openjfx.data_access_layer;

import org.openjfx.view_model.Log;
import org.openjfx.view_model.Tour;

import java.sql.SQLException;
import java.util.List;

public class FileAccess implements DataAccess{

    private  String filePath;

    @Override
    public List<Log> GetLogs() {
        // read the items from file system
        return null;
    }

    @Override
    public List<Tour> GetTours() {
        return null;
    }

    @Override
    public List<Log> searchLogs() {
        return null;
    }

    @Override
    public List<Tour> searchTours() {
        return null;
    }

    @Override
    public void insertNewLog(String date, Float duration, Float destination, String tourName) throws SQLException {

    }

    @Override
    public void insertNewTour(String tourName) {

    }
}
