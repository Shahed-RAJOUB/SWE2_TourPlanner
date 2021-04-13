package org.openjfx.data_access_layer;

import org.openjfx.view_model.Log;

import java.util.List;

public class TourLogDAO {
    private final DataAccess dataAccess;
     // welche daten system als target ist
    public TourLogDAO(){
        dataAccess = new FileAccess();
    }
    public List<Log> GetLogs(){
     return dataAccess.GetLogs();
    }
}
