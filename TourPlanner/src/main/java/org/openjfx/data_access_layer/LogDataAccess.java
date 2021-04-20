package org.openjfx.data_access_layer;

import org.openjfx.view_model.Log;
import java.sql.SQLException;
import java.util.List;

public interface LogDataAccess {
    List<Log> GetLogs();
    void insertNewLog(String date, Float duration, Float destination, String tourName) throws SQLException;
}
